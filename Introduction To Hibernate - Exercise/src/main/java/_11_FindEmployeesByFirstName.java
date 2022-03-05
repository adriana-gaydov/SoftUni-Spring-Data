import Tools.ConnectionCreator;
import Tools.Reader;
import entities.Employee;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;

public class _11_FindEmployeesByFirstName {
    static EntityManager entityManager;

    public static void main(String[] args) throws IOException {
        entityManager = ConnectionCreator.getEntityManager();

        entityManager.getTransaction().begin();
        solution();
        entityManager.getTransaction().commit();
    }

    private static void solution() throws IOException {
        String pattern = Reader.readString();
        List<Employee> employees = getEmployees(pattern);

        printEmployees(employees);
    }

    private static void printEmployees(List<Employee> employees) {
        employees.forEach(e -> System.out.printf("%s %s - %s - ($%.2f)%n",
                e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary()));
    }

    private static List<Employee> getEmployees(String pattern) {
        return entityManager.createQuery("SELECT e FROM Employee e" +
                        " WHERE firstName LIKE :pattern", Employee.class)
                .setParameter("pattern", pattern + "%")
                .getResultList();
    }
}
