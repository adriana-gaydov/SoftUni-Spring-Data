package _06_FootballBettingDatabase;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "bet_games")
public class BetGame {

    @EmbeddedId
    private BetGamePK id;

    @ManyToOne()
    @MapsId("game_id")
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;

    @ManyToOne(optional = false)
    @MapsId("bet_id")
    @JoinColumn(name = "bet_id", referencedColumnName = "id")
    private Bet bet;

    @OneToOne(optional = false)
    @JoinColumn(name = "result_prediction", referencedColumnName = "id")
    private ResultPrediction resultPrediction;

    public BetGame() {
    }

    public BetGame(Game game, Bet bet, ResultPrediction resultPrediction) {
        this.game = game;
        this.bet = bet;
        this.resultPrediction = resultPrediction;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public ResultPrediction getResultPrediction() {
        return resultPrediction;
    }

    public void setResultPrediction(ResultPrediction resultPrediction) {
        this.resultPrediction = resultPrediction;
    }
}
