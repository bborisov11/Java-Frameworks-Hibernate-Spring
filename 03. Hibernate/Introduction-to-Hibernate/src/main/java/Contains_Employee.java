import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Contains_Employee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        List<Town> towns = em.
                createQuery("SELECT e FROM Employee AS e WHERE concat(e.firstName,' ',e.lastName) =:input",Town.class)
                .setParameter("input",name)
                .getResultList();


        if(towns.size()==0) {
            System.out.println("No");
        }
        else {
            System.out.println("Yes");
        }

        em.getTransaction().commit();
        em.close();
        factory.close();

    }
}
