package single_table;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PassengerVehicle extends Vehicle {
    @Column(name = "no_of_passengers")
    private int noOfPassengers;

    public PassengerVehicle() {
    }

    public PassengerVehicle(String type, int noOfpassengers) {
        super(type);
        this.noOfPassengers = noOfpassengers;
    }

    // Getters and setters
}
