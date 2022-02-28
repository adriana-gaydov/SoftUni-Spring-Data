package joined;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PassengerVehicle extends Vehicle {
    private int numOfPassengers;

    public PassengerVehicle() {
    }

    public PassengerVehicle(String type, int numOfPassengers) {
        super(type);
        this.numOfPassengers = numOfPassengers;
    }

    // Getters and setters
}
