import Tools.ConnectionCreator;

import javax.persistence.EntityManager;
import java.util.List;

public class _04_EmployeesWithSalaryOver50000 {
    static EntityManager entityManager = ConnectionCreator.getEntityManager();

    public static void main(String[] args) {
        
        entityManager.getTransaction().begin();
        
        solution();
        entityManager.getTransaction().commit();
    }

    private static void solution() {
        List<String> resultList = entityManager.createQuery("SELECT firstName FROM Employee e" +
                        " WHERE salary > 50000", String.class)
                .getResultList();

        printList(resultList);
    }

    private static void printList(List<String> resultList) {
        resultList.forEach(System.out::println);
    }
}
