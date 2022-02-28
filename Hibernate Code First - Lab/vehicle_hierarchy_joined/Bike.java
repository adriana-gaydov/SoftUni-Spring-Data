package lab_exercise.vehicle_hierarchy;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
public class Bike extends Vehicle{
    private static final String TYPE = "bike";

    public Bike() {
    }

    public Bike(String model, BigDecimal price, String fuelType) {
        super(TYPE, model, price, fuelType);
    }
}
