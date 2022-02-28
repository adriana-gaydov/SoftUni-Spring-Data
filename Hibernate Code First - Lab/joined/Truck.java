package joined;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "trucks")
public class Truck extends TransportationVehicle {
    private final static String type = "TRUCK";
    private int numOfContainers;

    public Truck() {
    }

    public Truck(int numOfContainers, int loadCapacity) {
        super(type, loadCapacity);
        this.numOfContainers = numOfContainers;
    }
}
