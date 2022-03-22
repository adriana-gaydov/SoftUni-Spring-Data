package com.example.game_store.services;

import com.example.game_store.entities.games.AddGameDTO;
import com.example.game_store.entities.games.Game;
import com.example.game_store.entities.users.LoginDTO;
import com.example.game_store.entities.users.RegisterDTO;
import com.example.game_store.entities.users.User;
import com.example.game_store.exceptions.GameAlreadyExistsException;

import java.util.List;
import java.util.Set;

public interface UserService {
    User register(RegisterDTO registerDTO);

    User login(LoginDTO loginDTO);

    User logout();

    User getCurrentUser();

    Game addGame(AddGameDTO addGameDTO) throws GameAlreadyExistsException;

    Game editGame(int id, String[] commandParts);

    Game deleteGame(int idToDelete);

    String showOwnedGames();

    Game addItem(String title);

    Game removeItem(String title);

    Set<Game> buyItems();
}
