import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Comparator;
import java.util.List;

public class Employees_from_Department {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        //Query query = em.createQuery("SELECT e FROM Employee e JOIN e.department AS d WHERE d.name = 'Research and Development' ORDER BY e.salary,e.id");

        List<Employee> employees = em.createQuery("SELECT e FROM Employee AS e WHERE e.department.id = 6", Employee.class)
                .getResultList();

        employees.sort(Comparator.comparing(Employee::getSalary)
                .thenComparing(Employee::getId));
        for (Employee employee : employees) {
            System.out.printf("%s %s from %s - $%.2f%n", employee.getFirstName(),
                    employee.getLastName(), employee.getDepartment().getName(), employee.getSalary());
        }

        em.close();
        factory.close();
    }
}
