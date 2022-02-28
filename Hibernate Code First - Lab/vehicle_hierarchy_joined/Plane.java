package lab_exercise.vehicle_hierarchy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
public class Plane extends Vehicle {
    private static final String TYPE = "plane";

    @Column(name = "passenger_capacity")
    private Integer passengerCapacity;

    public Plane() {
    }

    public Plane(String model, BigDecimal price, String fuelType, Integer passengerCapacity) {
        super(TYPE, model, price, fuelType);
        this.passengerCapacity = passengerCapacity;
    }

    public Integer getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(Integer passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }
}
