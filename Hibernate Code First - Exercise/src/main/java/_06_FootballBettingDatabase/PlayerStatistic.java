package _06_FootballBettingDatabase;

import javax.persistence.*;

@Entity
@Table(name = "player_statistics")
public class PlayerStatistic {

    @EmbeddedId
    private PlayerStatisticPK id;

    @MapsId("game_id")
    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;

    @MapsId("player_id")
    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    @Column(name = "scored_goals", nullable = false)
    private int scoredGoals;

    @Column(name = "player_assists", nullable = false)
    private int playerAssists;

    @Column(name = "played_minutes", nullable = false)
    private int playedMinutes;

    public PlayerStatistic() {
    }

    public PlayerStatistic(Game game, Player player, int scoredGoals, int playerAssists, int playedMinutes) {
        this.game = game;
        this.player = player;
        this.scoredGoals = scoredGoals;
        this.playerAssists = playerAssists;
        this.playedMinutes = playedMinutes;
    }

    public int getScoredGoals() {
        return scoredGoals;
    }

    public int getPlayerAssists() {
        return playerAssists;
    }

    public int getPlayedMinutes() {
        return playedMinutes;
    }

    public Player getPlayer() {
        return player;
    }


    public Game getGame() {
        return game;
    }
}
