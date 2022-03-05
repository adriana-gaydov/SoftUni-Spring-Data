package _06_FootballBettingDatabase;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PlayerStatisticPK implements Serializable {

    @Column(name = "game_id")
    private int gameId;

    @Column(name = "player_id")
    private int playerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerStatisticPK that = (PlayerStatisticPK) o;
        return gameId == that.gameId && playerId == that.playerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, playerId);
    }
}
