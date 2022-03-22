package com.example.game_store.services;

import com.example.game_store.entities.games.Game;

import java.util.Optional;

public interface GameService {
    void addGame(Game game);
    Optional<Game> findById(int id);

    Game updateGame(int id, String[] commandParts);

    Game deleteGame(int idToDelete);

    String showAllGames();

    String showDetailGame(String title);

    Game findByTitle(String title);
}
