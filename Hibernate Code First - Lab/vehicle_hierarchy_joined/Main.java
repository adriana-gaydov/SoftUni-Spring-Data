package lab_exercise.vehicle_hierarchy;

import Tools.ConnectionCreator;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        String model = "testModel";
        BigDecimal price = new BigDecimal("1000");
        String fuelType = "someFuel";

        Car car = new Car(model, price, fuelType, 4);
        Bike bike = new Bike(model, price, fuelType);
        Plane plane = new Plane(model, price, fuelType, 344);
        Truck truck = new Truck(model,price, fuelType, 23.34);

        EntityManager entityManager = ConnectionCreator.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(car);
        entityManager.persist(bike);
        entityManager.persist(plane);
        entityManager.persist(truck);

        entityManager.getTransaction().commit();
    }
}
