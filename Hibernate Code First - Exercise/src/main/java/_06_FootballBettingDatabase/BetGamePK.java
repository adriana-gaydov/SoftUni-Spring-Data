package _06_FootballBettingDatabase;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BetGamePK implements Serializable {

    private static final long serialVersionUID = 3523304367057305897L;
    @Column(name = "bet_id")
    private int betId;

    @Column(name = "game_id")
    private int gameId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetGamePK betGamePK = (BetGamePK) o;
        return betId == betGamePK.betId && gameId == betGamePK.gameId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(betId, gameId);
    }
}
