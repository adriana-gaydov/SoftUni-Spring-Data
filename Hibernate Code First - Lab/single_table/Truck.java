package single_table;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "truck")
public class Truck extends TransportationVehicle {
    private final static String type = "TRUCK";

    @Column(name = "no_of_containers")
    private int noOfContainers;

    public Truck() {
    }

    public Truck(int loadCapacity, int noOfContainers) {
        super(type, loadCapacity);
        this.noOfContainers = noOfContainers;
    }

    // Constructors
    // Getters and setters
}
