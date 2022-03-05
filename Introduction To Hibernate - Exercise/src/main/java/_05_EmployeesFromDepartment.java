import Tools.ConnectionCreator;
import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class _05_EmployeesFromDepartment {
    static EntityManager entityManager;

    public static void main(String[] args) {
        entityManager = ConnectionCreator.getEntityManager();

        entityManager.getTransaction().begin();

        solution();
        entityManager.getTransaction().commit();
    }

    private static void solution() {
        String department = "Research and Development";

        entityManager.createQuery("SELECT e FROM Employee e" +
                        " WHERE e.department.name = :department" +
                        " ORDER BY salary, id", Employee.class)
                .setParameter("department", department)
                .getResultStream()
                .forEach(e -> System.out.printf("%s %s from Research and Development - $%.2f%n",
                        e.getFirstName(), e.getLastName(), e.getSalary()));
    }
}
