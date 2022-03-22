package com.example.game_store.services.impl;

import com.example.game_store.entities.games.AddGameDTO;
import com.example.game_store.entities.games.Game;
import com.example.game_store.entities.users.LoginDTO;
import com.example.game_store.entities.users.RegisterDTO;
import com.example.game_store.entities.users.User;
import com.example.game_store.services.ExecutorService;
import com.example.game_store.services.GameService;
import com.example.game_store.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExecutorServiceImpl implements ExecutorService {

    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public ExecutorServiceImpl(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public String execute(String commandLine) {
        String[] commandParts = commandLine.split("\\|");

        String command = commandParts[0];

        return switch (command) {
            case REGISTER_USER_COMMAND -> registerUser(commandParts);

            case LOGIN_USER_COMMAND -> loginUser(commandParts);

            case LOGOUT_USER_COMMAND -> logoutUser(commandParts);

            case ADD_GAME_COMMAND -> addGame(commandParts);

            case EDIT_GAME_COMMAND -> editGame(commandParts);

            case DELETE_GAME_COMMAND -> deleteGame(commandParts);

            case ALL_GAMES_COMMAND -> showAllGames();

            case DETAIL_GAME_COMMAND -> showDetailGame(commandParts);

            case OWNED_GAMES_COMMAND -> showOwnedGames();

            case ADD_ITEM_COMMAND -> addItem(commandParts);

            case REMOVE_ITEM_COMMAND -> removeItem(commandParts);

            case BUY_ITEM_COMMAND -> buyItem();

            default -> "invalid command";
        };
    }

    private String buyItem() {
        Set<Game> games = this.userService.buyItems();

        return String.format("Successfully bought games:%n" +
                "%s", games.stream().map(e -> " -" + e.getTitle())
                .collect(Collectors.joining(System.lineSeparator())));
    }

    private String removeItem(String[] commandParts) {
        String title = commandParts[1];

        Game game = this.userService.removeItem(title);

        return String.format("%s removed from cart.", game.getTitle());
    }

    private String addItem(String[] commandParts) {
        String title = commandParts[1];

        Game game = this.userService.addItem(title);

        return String.format("%s added to cart.", game.getTitle());
    }

    private String showOwnedGames() {
        return this.userService.showOwnedGames().isBlank() ? "No games" : userService.showOwnedGames();
    }

    private String showDetailGame(String[] commandParts) {
        String title = commandParts[1];

        return this.gameService.showDetailGame(title);
    }

    private String showAllGames() {
        return this.gameService.showAllGames();
    }

    private String deleteGame(String[] commandParts) {
        int idToDelete = Integer.parseInt(commandParts[1]);
        Game game = this.userService.deleteGame(idToDelete);

        return String.format("Deleted %s", game.getTitle());
    }

    private String editGame(String[] commandParts) {
        int idToEdit = Integer.parseInt(commandParts[1]);
        Game game = this.userService.editGame(idToEdit, commandParts);

        return String.format("Edited %s", game.getTitle());
    }

    private String addGame(String[] commandParts) {
        AddGameDTO gameDTO = new AddGameDTO(commandParts);

        Game game = userService.addGame(gameDTO);

        return String.format("Added %s", game.getTitle());
    }

    private String logoutUser(String[] commandParts) {
        User logout = this.userService.logout();

        return String.format("User %s successfully logged out", logout.getFullName());
    }

    private String loginUser(String[] commandParts) {
        LoginDTO loginDTO = new LoginDTO(commandParts);

        User login = this.userService.login(loginDTO);

        return String.format("Successfully logged in %s", login.getFullName());
    }

    private String registerUser(String[] commandParts) {
        RegisterDTO registerDTO = new RegisterDTO(commandParts);

        this.userService.register(registerDTO);

        return String.format("%s was registered", registerDTO.getFullName());
    }
}
