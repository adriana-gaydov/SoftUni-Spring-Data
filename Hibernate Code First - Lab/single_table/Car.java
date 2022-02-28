package single_table;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "car")
public class Car extends PassengerVehicle {
    private final static String type = "CAR";

    public Car() {
    }

    public Car(int numberOfPassengers) {
        super(type, numberOfPassengers);
    }

}
