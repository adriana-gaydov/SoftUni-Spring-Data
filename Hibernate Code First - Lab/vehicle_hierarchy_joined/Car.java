package lab_exercise.vehicle_hierarchy;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
public class Car extends Vehicle {
    private static final String TYPE = "car";

    @Basic
    private Integer seats;

    public Car() {
    }

    public Car(String model, BigDecimal price, String fuelType, Integer seats) {
        super(TYPE, model, price, fuelType);
        this.seats = seats;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
}
