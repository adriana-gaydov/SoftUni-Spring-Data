import Tools.ConnectionCreator;

import javax.persistence.EntityManager;

public class Main {
    static EntityManager entityManager;
    public static void main(String[] args) {
        entityManager = ConnectionCreator.getEntityManager();

        entityManager.getTransaction().begin();

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
