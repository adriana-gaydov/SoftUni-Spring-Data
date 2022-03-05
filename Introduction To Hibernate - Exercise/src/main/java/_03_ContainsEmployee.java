import Tools.ConnectionCreator;
import Tools.Reader;

import javax.persistence.EntityManager;
import java.io.IOException;

public class _03_ContainsEmployee {
    static EntityManager entityManager;

    public static void main(String[] args) throws IOException {
        entityManager = ConnectionCreator.getEntityManager();

        entityManager.getTransaction().begin();

        solution();
        entityManager.getTransaction().commit();
    }

    private static void solution() throws IOException {
        String[] wantedName = Reader.readStringArray("\\s+");

        Long firstResult = getEmployeeCount(wantedName);

        printResult(firstResult);
    }

    private static Long getEmployeeCount(String[] wantedName) {
        return entityManager.createQuery("SELECT COUNT(e) FROM Employee e" +
                        " WHERE firstName = :wantedFirstName" +
                        " AND lastName = :wantedLastName", Long.class)
                .setParameter("wantedFirstName", wantedName[0])
                .setParameter("wantedLastName", wantedName[1])
                .getSingleResult();
    }

    private static void printResult(Long firstResult) {
        if (firstResult == 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }
}
