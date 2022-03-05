import Tools.ConnectionCreator;
import Tools.Reader;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.IOException;
import java.util.List;

public class _13_RemoveTowns {
    static EntityManager entityManager;

    public static void main(String[] args) throws IOException {
        entityManager = ConnectionCreator.getEntityManager();

        entityManager.getTransaction().begin();
        solution();
        entityManager.getTransaction().commit();
    }

    private static void solution() throws IOException {
        String townToDeleteStr = Reader.readString();

        Town townToDelete;
        try {
            townToDelete = getTownToDelete(townToDeleteStr);
        } catch (NoResultException e) {
            System.out.println("Town doesn't exist!");
            return;
        }

        Integer townIdToDelete = townToDelete.getId();

        setEmployeeDeletedAddressesNull(townIdToDelete);


        int deletedAddresses = deleteAddresses(townToDelete);
        entityManager.remove(townToDelete);

        System.out.printf("%s %s in %s deleted", deletedAddresses, deletedAddresses == 1 ? "address" : "addresses", townToDeleteStr);
    }

    private static int deleteAddresses(Town townToDelete) {
        return entityManager.createQuery("DELETE FROM Address WHERE town = :townToDelete")
                .setParameter("townToDelete", townToDelete)
                .executeUpdate();
    }

    private static Town getTownToDelete(String townToDeleteStr) {
        return entityManager.createQuery("SELECT t FROM Town t WHERE name = :townToDelete", Town.class)
                .setParameter("townToDelete", townToDeleteStr)
                .getSingleResult();
    }

    private static void setEmployeeDeletedAddressesNull(Integer townIdToDelete) {
        List<Integer> employeeIds = getEmployeeIds(townIdToDelete);

        entityManager.createQuery("UPDATE Employee SET address=null WHERE id IN :var")
                .setParameter("var", employeeIds)
                .executeUpdate();
    }

    private static List<Integer> getEmployeeIds(Integer townIdToDelete) {
        return entityManager.createQuery("SELECT id FROM Employee e WHERE address.town.id = :townIdToDelete", Integer.class)
                .setParameter("townIdToDelete", townIdToDelete)
                .getResultList();
    }
}
