import entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("school");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Student student = new Student("Teo");
        em.persist(student);

        Student student1 = em.find(Student.class, 1L);
        System.out.println(student1);
        em.getTransaction().commit();
    }
}
