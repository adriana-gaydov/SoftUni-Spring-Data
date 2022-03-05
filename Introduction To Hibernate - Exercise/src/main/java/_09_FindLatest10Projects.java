import Tools.ConnectionCreator;
import entities.Project;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class _09_FindLatest10Projects {
    static EntityManager entityManager;

    public static void main(String[] args) {
        entityManager = ConnectionCreator.getEntityManager();

        entityManager.getTransaction().begin();
        solution();
        entityManager.getTransaction().commit();
    }

    private static void solution() {
        List<Project> resultList = getProjects();

        resultList
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> printProjectInfo(p)
                );
    }

    private static void printProjectInfo(Project p) {
        System.out.printf("Project name: %s%n", p.getName());
        System.out.printf("\tProject Description:%s%n", p.getDescription());
        System.out.printf("\tProject Start Date:%s%n", formatDate(p.getStartDate()));
        System.out.printf("\tProject End Date:%s%n", formatDate(p.getEndDate()));
    }

    private static String formatDate(LocalDateTime date) {
        if (date == null) return null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.ms");
        return date.format(formatter);
    }

    private static List<Project> getProjects() {
        return entityManager.createQuery("SELECT p FROM Project p" +
                        " ORDER BY p.startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultList();
    }
}
