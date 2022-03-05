import Tools.ConnectionCreator;
import Tools.Reader;
import entities.Address;

import javax.persistence.EntityManager;
import java.io.IOException;

public class _06_AddingANewAddressAndUpdatingEmployee {
    static EntityManager entityManager;
    static Address address;

    public static void main(String[] args) throws IOException {
        entityManager = ConnectionCreator.getEntityManager();

        entityManager.getTransaction().begin();
        solution();
        entityManager.getTransaction().commit();
    }

    private static void solution() throws IOException {
        insertNewAddress();
        updateEmployee();
    }

    private static void updateEmployee() throws IOException {
        String lastName = Reader.readString();

        entityManager.createQuery("UPDATE Employee e" +
                        " SET e.address = :newAddress" +
                        " WHERE e.lastName = :lastName")
                .setParameter("newAddress", address)
                .setParameter("lastName", lastName)
                .executeUpdate();
    }

    private static void insertNewAddress() {
        address = createAddress();
        entityManager.persist(address);
    }

    private static Address createAddress() {
        Address address = new Address();
        address.setText("Vitoshka 15");

        return address;
    }
}
