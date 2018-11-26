package Problem10;

import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class RemoveTowns {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String townName = reader.readLine();

        List<Address> addresses = em.createQuery
                ("SELECT a FROM Address a WHERE a.town.name = :townName", Address.class)
                .setParameter("townName", townName)
                .getResultList();

        em.getTransaction().begin();

        for (Address address : addresses) {
            for (Employee employee : address.getEmployees()) {
                employee.setAddress(null);
            }
            em.remove(address);
        }

        Town town = em.createQuery(
                "SELECT t FROM Town t WHERE t.name = :townName", Town.class)
                .setParameter("townName", townName)
                .getSingleResult();

        em.remove(town);

        if (addresses.size() == 1) {
            System.out.printf("1 address in %s deleted%n", townName);
        } else {
            System.out.printf("%d addresses in %s deleted%n", addresses.size(), townName);
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
