package Tools;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("softuni_pu");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
