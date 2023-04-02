package hibernate;

import hibernate.model.Address;
import hibernate.model.Employee;
import hibernate.queries.Queries;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.List;
import java.util.Random;


class Manager {

    public static void main(String[] args) {
        System.out.println("Start");
        EntityManager entityManager;
        EntityManagerFactory entityManagerFactory = null;

        try {
            // FACTORY NAME HAS TO MATCH THE ONE FROM PERSISTED.XML !!!
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");

            entityManager = entityManagerFactory.createEntityManager();
            Session session = entityManager.unwrap(Session.class);

            //New transaction
            session.beginTransaction();

            // Create Employee
            Employee emp = createEmployee();
            Address address = new Address();
            address.setCity("Cali");
            address.setStreet("xdddd");
            address.setNr("12345");
            address.setPostcode("12345");
            emp.setAddress(address);

            // Save in First order Cache (not database yet)
            session.save(emp);

            Employee employee = session.get(Employee.class, emp.getId());
            if (employee == null) {
                System.out.println(emp.getId() + " not found!");
            } else {
                System.out.println("Found " + employee);
            }

            System.out.println("Employee " + employee.getId() + " " + employee.getFirstName() + employee.getLastName());

            //changeFirstGuyToNowak(session);
            employee.setLastName("NowakPRE" + new Random().nextInt()); // No SQL needed

            // Commit transaction to database
            session.getTransaction().commit();

            System.out.println("Done");

            for (int i = 1; i < 10; i++) {
                session.save(Employee.copyEmployee(emp));
            }

            session.getTransaction().begin();
            Employee employee1 = session.get(Employee.class, 1);

            session.getTransaction().commit();

            session.clear();

            session.getTransaction().begin();

            employee1 = session.get(Employee.class, 1);
            Address add = employee1.getAddress();
            System.out.println(add.getCity());

            session.getTransaction().commit();

            session.close();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
        } finally {
            entityManagerFactory.close();
        }
    }

    private static Employee createEmployee() {
        Employee emp = new Employee();
        emp.setFirstName("Jan");
        emp.setLastName("Polak" + new Random().nextInt());
        emp.setSalary(100);
        emp.setPesel(new Random().nextInt());
        return emp;
    }

    static void changeFirstGuyToNowak(Session session) {
        List<Employee> employees = new Queries(session).getEmployeeByName("Polak");
        employees.get(0).setLastName("NowakPRE" + new Random().nextInt());
    }
}
