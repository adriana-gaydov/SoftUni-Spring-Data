package com.example.game_store.repositories;

import com.example.game_store.entities.games.Game;
import com.example.game_store.entities.games.DetailsGamesDTO;
import com.example.game_store.entities.games.TitlePriceGames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    Optional<Game> findByTitle(String title);

    List<TitlePriceGames> findAllBy();

    List<DetailsGamesDTO> findAllByTitle(String title);
}
