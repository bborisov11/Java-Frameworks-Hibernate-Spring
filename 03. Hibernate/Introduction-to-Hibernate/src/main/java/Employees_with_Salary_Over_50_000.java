import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class Employees_with_Salary_Over_50_000 {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();


        List<Employee> employees = em.createQuery("SELECT e FROM Employee AS e WHERE e.salary > 50000",Employee.class)
                .getResultList();

        for (Employee emp : employees) {
            System.out.println(emp.getFirstName());
        }

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
