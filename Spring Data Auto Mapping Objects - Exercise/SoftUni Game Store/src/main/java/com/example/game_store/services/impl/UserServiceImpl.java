package com.example.game_store.services.impl;

import com.example.game_store.entities.Order;
import com.example.game_store.entities.games.AddGameDTO;
import com.example.game_store.entities.games.Game;
import com.example.game_store.entities.users.LoginDTO;
import com.example.game_store.entities.users.RegisterDTO;
import com.example.game_store.entities.users.User;
import com.example.game_store.exceptions.*;
import com.example.game_store.repositories.OrderRepository;
import com.example.game_store.repositories.UserRepository;
import com.example.game_store.services.GameService;
import com.example.game_store.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GameService gameService;
    private final OrderRepository orderRepository;
    private User currentUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, GameService gameService, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.gameService = gameService;
        this.orderRepository = orderRepository;
        this.currentUser = null;
    }

    @Override
    public User register(RegisterDTO registerDTO) {
        checkIfAnotherUserIsLogged();

        Optional<User> isExistingUser = this.userRepository.findByEmail(registerDTO.getEmail());

        if (isExistingUser.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(registerDTO, User.class);

        if (this.userRepository.count() == 0) {
            user.setAdmin(true);
        }

        this.userRepository.save(user);

        return user;
    }

    @Override
    public User login(LoginDTO loginDTO) {
        checkIfAnotherUserIsLogged();

        Optional<User> byEmailAndPassword = this.userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());

        if (byEmailAndPassword.isEmpty()) {
            throw new WrongCredentialsException("Invalid email / password");
        }
        User user = byEmailAndPassword.get();

        this.currentUser = user;
        return user;
    }

    @Override
    public User logout() {
        if (currentUser == null) {
            throw new AccountNotLoggedInException();
        }

        User user = this.currentUser;
        this.currentUser = null;

        return user;
    }

    private void checkIfAnotherUserIsLogged() {
        if (currentUser != null) {
            throw new AnotherAccountInUseException();
        }
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public Game addGame(AddGameDTO addGameDTO) {
        checkIfLoggedAndAdmin();

        ModelMapper mapper = new ModelMapper();
        Game game = mapper.map(addGameDTO, Game.class);

        this.gameService.addGame(game);

        return game;
    }

    private void checkIfLoggedAndAdmin() {
        if (currentUser == null) {
            throw new AccountNotLoggedInException("Cannot operate while logged out!");
        }

        if (!currentUser.isAdmin()) {
            throw new InsufficientPermissionException();
        }
    }

    @Override
    public Game editGame(int id, String[] commandParts) {
        checkIfLoggedAndAdmin();

        return this.gameService.updateGame(id, commandParts);
    }

    @Override
    public Game deleteGame(int idToDelete) {
        checkIfLoggedAndAdmin();

        return this.gameService.deleteGame(idToDelete);
    }

    @Override
    public String showOwnedGames() {
        isLogged();

        return this.currentUser.getGames().stream().map(Game::getTitle).collect(Collectors.joining(System.lineSeparator()));
    }

    private void isLogged() {
        if (currentUser == null) {
            throw new AccountNotLoggedInException("Cannot operate while logged out!");
        }
    }

    @Override
    public Game addItem(String title) {
        isLogged();

        Game toAdd = this.gameService.findByTitle(title);

        if (currentUser.getGames().contains(toAdd)) {
            throw new GameAlreadyExistsException("Game already owned!");
        }

        if (currentUser.getShoppingCart().contains(toAdd)) {
            throw new GameAlreadyExistsException("Game is already added in shopping cart!");
        }

        currentUser.addToShoppingCart(toAdd);
        return toAdd;
    }

    @Override
    public Game removeItem(String title) {
        isLogged();

        Game toRemove = this.gameService.findByTitle(title);

        if (!currentUser.getShoppingCart().contains(toRemove)) {
            throw new GameNotExistsException("Game isn't in shopping cart!");
        }

        currentUser.removeFromShoppingCart(toRemove);

        return toRemove;
    }

    @Override
    public Set<Game> buyItems() {
        isLogged();

        Set<Game> shoppingCart = new HashSet<>(currentUser.getShoppingCart());

        if (shoppingCart.isEmpty()) {
            throw new GameNotExistsException("Empty cart!");
        }

        Order order = new Order(currentUser, currentUser.getShoppingCart());
        orderRepository.save(order);

        currentUser.clearShoppingCart();
        return shoppingCart;
    }
}
