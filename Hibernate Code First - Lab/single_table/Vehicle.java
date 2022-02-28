package single_table;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "vehicles")
@DiscriminatorColumn(name = "type")
public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    @Basic
    @Column(insertable = false, updatable = false)
    private String type;

    protected Vehicle() {}

    protected Vehicle(String type) {
        this.type = type;
    }
}
