import Tools.ConnectionCreator;
import relations.BasicLabel;
import relations.BasicShampoo;

import javax.persistence.EntityManager;

public class Main {
    static EntityManager entityManager;

    public static void main(String[] args) {
       /*

        entityManager.getTransaction().begin();


        Truck truck = new Truck(100, 1000);
        Car car = new Car(4);

        entityManager.persist(truck);
        entityManager.persist(car);

        entityManager.getTransaction().commit();/

        */

        entityManager = ConnectionCreator.getEntityManager();
        entityManager.getTransaction().begin();
        BasicLabel basicLabel = new BasicLabel("someLabelName");
        BasicShampoo shampoo = new BasicShampoo();
        shampoo.setLabel(basicLabel);
        basicLabel.setBasicShampoo(shampoo);

        entityManager.persist(basicLabel);
        entityManager.persist(shampoo);

        entityManager.getTransaction().commit();
    }
}
