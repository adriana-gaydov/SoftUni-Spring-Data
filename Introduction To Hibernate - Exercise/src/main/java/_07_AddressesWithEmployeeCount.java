import Tools.ConnectionCreator;
import entities.Address;

import javax.persistence.EntityManager;

public class _07_AddressesWithEmployeeCount {
    static EntityManager entityManager;

    public static void main(String[] args) {
        entityManager = ConnectionCreator.getEntityManager();

        entityManager.getTransaction().begin();
        solution();
        entityManager.getTransaction().commit();
    }

    private static void solution() {
        entityManager.createQuery("SELECT a" +
                " FROM Address a" +
                " ORDER BY a.employees.size DESC", Address.class)
                .setMaxResults(10)
                .getResultStream()
                .forEach(r -> System.out.printf("%s, %s - %d employees%n",
                        r.getText(), r.getTown().getName(), r.getEmployees().size()));
    }
}
