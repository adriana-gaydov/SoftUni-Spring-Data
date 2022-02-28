package joined;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "vehicles")
public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    @Basic
    private String type;

    protected Vehicle() {
    }

    public Vehicle(String type) {
        this.type = type;
    }

}
