import Tools.ConnectionCreator;
import entities.Town;

import javax.persistence.EntityManager;
import java.util.List;

public class _02_ChangeCasing {
    static EntityManager entityManager;

    public static void main(String[] args) {

        entityManager = ConnectionCreator.getEntityManager();

        entityManager.getTransaction().begin();

        solution1();
        //solution2();

        entityManager.getTransaction().commit();
    }

    private static void solution1() {
        entityManager.createQuery("UPDATE Town t" +
                        " SET name = UPPER(name)" +
                        " WHERE CHAR_LENGTH(name) <= 5")
                .executeUpdate();
    }

    private static void solution2() {

        List<Town> resultList = getTowns();

        resultList.stream()
                .filter(t -> t.getName().length() <= 5)
                .forEach(t -> {
                    t.setName(t.getName().toUpperCase());
                    entityManager.persist(t);
                });
    }

    private static List<Town> getTowns() {
        return entityManager.createQuery("SELECT t FROM Town t", Town.class)
                .getResultList();
    }
}
