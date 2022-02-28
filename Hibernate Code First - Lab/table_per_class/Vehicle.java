package table_per_class;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)   //необходимо за този начин на наследяване
    private int id;
    @Basic
    private String type;


    protected Vehicle() {
    }

    protected Vehicle(String type) {
        this.type = type;
    }
}
