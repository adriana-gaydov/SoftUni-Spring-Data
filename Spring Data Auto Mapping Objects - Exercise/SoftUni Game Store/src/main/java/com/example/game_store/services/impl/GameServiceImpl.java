package com.example.game_store.services.impl;

import com.example.game_store.entities.games.Game;
import com.example.game_store.entities.games.DetailsGamesDTO;
import com.example.game_store.exceptions.GameAlreadyExistsException;
import com.example.game_store.exceptions.GameNotExistsException;
import com.example.game_store.exceptions.InvalidGameException;
import com.example.game_store.exceptions.NoSuchOperationExists;
import com.example.game_store.repositories.GameRepository;
import com.example.game_store.services.GameService;
import com.example.game_store.utils.CredentialValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public void addGame(Game game) {
        Optional<Game> byTitle = gameRepository.findByTitle(game.getTitle());

        if(byTitle.isPresent()) {
            throw new GameAlreadyExistsException();
        }

        gameRepository.save(game);
    }

    @Override
    public Optional<Game> findById(int id) {
        return this.gameRepository.findById(id);
    }

    @Override
    public Game updateGame(int id, String[] values) {
        Optional<Game> gameById = this.gameRepository.findById(id);

        if (gameById.isEmpty()) {
            throw new GameNotExistsException();
        }

        Game game = gameById.get();

        values = Arrays.stream(values).skip(2).toArray(String[]::new);


        for (String value : values) {
            String[] updateStr = value.split("=");
            String toUpdateVal = updateStr[1];

            switch (updateStr[0]) {
                case "title" -> game.setTitle(toUpdateVal);
                case "price" -> game.setPrice(new BigDecimal(toUpdateVal));
                case "size" -> game.setSize(Float.parseFloat(toUpdateVal));
                case "trailer" -> {
                    if (!CredentialValidation.isValidTrailer(toUpdateVal)) {
                        throw new InvalidGameException();
                    }
                    toUpdateVal = toUpdateVal.substring(toUpdateVal.length() - 11);
                    game.setTrailer(toUpdateVal);
                }
                case "thumbnailURL" -> game.setThumbnailURL(toUpdateVal);
                case "description" -> game.setDescription(toUpdateVal);
                default -> throw new NoSuchOperationExists();
            }

            if (!CredentialValidation.isValidGame2(game)) {
                throw new InvalidGameException();
            }

        }

        gameRepository.save(game);
        return game;
    }

    @Override
    public Game deleteGame(int idToDelete) {
        Optional<Game> deleteGame = this.gameRepository.findById(idToDelete);

        if(deleteGame.isEmpty()) {
            throw new GameNotExistsException();
        }

        Game game = deleteGame.get();

        this.gameRepository.deleteById(idToDelete);

        return game;
    }

    @Override
    public String showAllGames() {
        return this.gameRepository.findAllBy().stream().map(e -> e.getTitle() + " " + e.getPrice())
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public String showDetailGame(String title) {
        return this.gameRepository.findAllByTitle(title)
                .stream().map(DetailsGamesDTO::toString).collect(Collectors.joining());
    }

    @Override
    public Game findByTitle(String title) {
        Optional<Game> byTitle = this.gameRepository.findByTitle(title);

        if (byTitle.isEmpty()) {
            throw new GameNotExistsException();
        }

        return byTitle.get();
    }

}
