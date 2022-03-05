package _06_FootballBettingDatabase;

import javax.persistence.*;

@Entity
@Table(name = "positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "position_description", nullable = false, columnDefinition = "TEXT")
    private String positionDescription;

    public Position() {
    }

    public Position(String positionDescription) {
        this.positionDescription = positionDescription;
    }

    public String getPositionDescription() {
        return positionDescription;
    }

    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
