package joined;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car extends PassengerVehicle {
    private final static String type = "CAR";

    public Car() {
    }

    public Car(int numOfPassengers) {
        super(type, numOfPassengers);
    }
}

