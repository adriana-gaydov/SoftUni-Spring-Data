package Tools;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionCreator {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CodeFirstPU");

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
