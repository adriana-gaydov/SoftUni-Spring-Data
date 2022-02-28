package single_table;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class TransportationVehicle extends Vehicle {
    @Column(name = "load_capacity")
    private int loadCapacity;

    public TransportationVehicle() {
    }

    public TransportationVehicle(String type, int loadCapacity) {
        super(type);
        this.loadCapacity = loadCapacity;
    }
    // Getters and setters
}
