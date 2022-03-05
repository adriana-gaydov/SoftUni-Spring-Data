import Tools.ConnectionCreator;
import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class _12_EmployeesMaximumSalaries {
    static EntityManager entityManager;

    public static void main(String[] args) {
        entityManager = ConnectionCreator.getEntityManager();

        entityManager.getTransaction().begin();
        solution2();
        entityManager.getTransaction().commit();
    }

    private static void solution2() {
        List<Object[]> rows = entityManager.createNativeQuery("SELECT d.name,MAX(e.salary) as `m_salary` FROM departments d\n" +
                "join employees e on d.department_id = e.department_id\n" +
                "Group by d.name\n" +
                "Having `m_salary` not between 30000 and 70000;").getResultList();

        for (Object[] row : rows) {
            System.out.printf("%s %.2f%n", row[0], row[1]);
        }
    }

    private static void solution() {
        List<Department> departments = entityManager.createQuery("SELECT d FROM Department d ", Department.class)
                .getResultList();


        departments.forEach(d -> {
            BigDecimal maxSalary = findMaxSalary(d);
            printFilteredResult(d, maxSalary);
        });
    }

    private static BigDecimal findMaxSalary(Department d) {
        BigDecimal maxSalary = new BigDecimal("0");
        for (Employee employee : d.getEmployees()) {
            if (maxSalary.compareTo(employee.getSalary()) < 0) {
                maxSalary = employee.getSalary();
            }
        }
        return maxSalary;
    }

    private static void printFilteredResult(Department d, BigDecimal maxSalary) {
        if (!(maxSalary.compareTo(new BigDecimal(30000)) >= 0 && maxSalary.compareTo(new BigDecimal(70000)) <= 0 )) {
            System.out.printf("%s %s%n", d.getName(), maxSalary.toString());
        }
    }
}
