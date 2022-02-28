package lab_exercise.vehicle_hierarchy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
public class Truck extends Vehicle {
    private static final String TYPE = "truck";

    @Column(name = "load_capacity")
    private Double loadCapacity;

    public Truck() {
    }

    public Truck(String model, BigDecimal price, String fuelType, Double loadCapacity) {
        super(TYPE, model, price, fuelType);
        this.loadCapacity = loadCapacity;
    }

    public Double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(Double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }
}
