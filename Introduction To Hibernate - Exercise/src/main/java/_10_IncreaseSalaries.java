import Tools.ConnectionCreator;
import entities.Employee;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

public class _10_IncreaseSalaries {
    static EntityManager entityManager;
    final static String[] departments = new String[]{"Engineering", "Tool Design", "Marketing", "Information Services"};

    public static void main(String[] args) {
        entityManager = ConnectionCreator.getEntityManager();

        entityManager.getTransaction().begin();
        solution();
        entityManager.getTransaction().commit();
    }

    private static void solution() {
        updateEmployees();
        printUpdatedEmployees();
    }

    private static void printUpdatedEmployees() {
        entityManager.createQuery("SELECT e FROM Employee e" +
                        " WHERE e.department.name IN :departments", Employee.class)
                .setParameter("departments", Arrays.stream(departments).toList())
                .getResultStream()
                .forEach(e -> System.out.printf("%s %s ($%.2f)%n",
                        e.getFirstName(), e.getLastName(), e.getSalary()));
    }

    private static void updateEmployees() {
        entityManager.createQuery("UPDATE Employee e" +
                        " SET e.salary = e.salary * 1.12" +
                        " WHERE e.id IN :ids")
                .setParameter("ids", getEmployeeToUpdateIds())
                .executeUpdate();
    }

    public static List<Integer> getEmployeeToUpdateIds() {
        return entityManager.createQuery("SELECT id FROM Employee e" +
                        " WHERE e.department.name IN :departments", Integer.class)
                .setParameter("departments", Arrays.stream(departments).toList())
                .getResultList();
    }
}