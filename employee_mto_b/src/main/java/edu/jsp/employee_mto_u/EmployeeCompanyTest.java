package edu.jsp.employee_mto_u;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmployeeCompanyTest {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("company");
	EntityManager em = emf.createEntityManager();
	EntityTransaction transaction = em.getTransaction();

	public static void main(String[] args) {
		EmployeeCompanyTest test = new EmployeeCompanyTest();

		Company company = new Company("TechCorp", "Silicon Valley");
		Employee employee1 = new Employee("John", "Doe", 75000.0,company);
		Employee employee2 = new Employee("Jane", "Smith", 80000.0,company);

		List<Employee> employees1=new ArrayList<Employee>();
		employees1.add(employee1);
		employees1.add(employee2);
		
        test.insertEmployeeCompany(company,employees1,employee1, employee2);
//        test.displayEmployeeCompany(2);
//        test.updateEmployeeSalary(1, 80000.0);
//        test.displayEmployeeCompany(1);
//        test.removeEmployeeCompany(1);
//        test.displayEmployeeCompany(1);

//		test.em.close();
//		test.emf.close();
	}

	public void insertEmployeeCompany(Company company,List<Employee> employees1, Employee... employees) {
		company.setEmployees(employees1);
		for (Employee employee : employees) {
			transaction.begin();
			em.persist(employee);
			transaction.commit();
		}
		System.out.println("Employees Data inserted");
	}

	public void displayEmployeeCompany(int employeeId) {
		Employee employee = em.find(Employee.class, employeeId);

		if (employee != null) {
			System.out.println("Employee Name: " + employee.getFirstName() + " " + employee.getLastName());
			System.out.println("Employee Salary: " + employee.getSalary());
			System.out.println("Company Name: " + employee.getCompany().getName());
			System.out.println("Company Location: " + employee.getCompany().getLocation());
		} else {
			System.out.println("Employee not found");
		}
	}

	public void updateEmployeeAttributes(int employeeId) {
		Employee employee = em.find(Employee.class, employeeId);

		if (employee != null) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Select attribute to update:");
			System.out.println("1. First Name");
			System.out.println("2. Last Name");
			System.out.println("3. Salary");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1: {
				System.out.println("Enter new First Name:");
				String newFirstName = scanner.nextLine();
				employee.setFirstName(newFirstName);
				break;
			}
			case 2: {
				System.out.println("Enter new Last Name:");
				String newLastName = scanner.nextLine();
				employee.setLastName(newLastName);
				break;
			}
			case 3: {
				System.out.println("Enter new Salary:");
				double newSalary = scanner.nextDouble();
				employee.setSalary(newSalary);
				break;
			}
			default: {
				System.out.println("Invalid choice");
				scanner.close();
				return;
			}
			}

			transaction.begin();
			em.merge(employee);
			transaction.commit();
			System.out.println("Employee Updated");
			scanner.close();
		} else {
			System.out.println("Employee not found");
		}
	}

	public void removeEmployeeCompany(int employeeId) {
		Employee employee = em.find(Employee.class, employeeId);

		if (employee != null) {
			transaction.begin();
			em.remove(employee);
			transaction.commit();
			System.out.println("Employee removed");
		} else {
			System.out.println("Employee not found");
		}
	}
}
