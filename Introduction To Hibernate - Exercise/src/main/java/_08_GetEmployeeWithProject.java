import Tools.ConnectionCreator;
import Tools.Reader;
import entities.Employee;

import javax.persistence.EntityManager;
import java.io.IOException;

public class _08_GetEmployeeWithProject {
    static EntityManager entityManager;

    public static void main(String[] args) throws IOException {
        entityManager = ConnectionCreator.getEntityManager();

        entityManager.getTransaction().begin();
        solution();
        entityManager.getTransaction().commit();
    }

    private static void solution() throws IOException {
        int employeeId = Reader.readInt();

        Employee employee = getEmployee(employeeId);
        printResult(employee);
    }

    private static void printResult(Employee employee) {
        System.out.printf("%s %s - %s%n",
                employee.getFirstName(), employee.getLastName(), employee.getJobTitle());

        printProjects(employee);
    }

    private static void printProjects(Employee employee) {
        employee.getProjects()
                .forEach(p -> System.out.printf("\t%s%n", p.getName()));
    }

    private static Employee getEmployee(int employeeId) {
        return entityManager.createQuery("SELECT e FROM Employee e" +
                        " WHERE e.id = :employeeId", Employee.class)
                .setParameter("employeeId", employeeId)
                .getSingleResult();
    }
}
