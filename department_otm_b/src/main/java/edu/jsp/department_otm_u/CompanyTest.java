package edu.jsp.department_otm_u;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CompanyTest {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("company");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();

	public static void main(String[] args) {
		CompanyTest test = new CompanyTest();
		Department department=new Department("IT", "BNGLR", "DIVYA");
		List<Employee> employees=new ArrayList<Employee>();
		Employee employee=new Employee("NIKHIL", "CHANDELWAR", 28);
		Employee employee1=new Employee("NOORUDDIN", "SHAIKH", 25);

		employees.add(employee);
		employees.add(employee1);
		
		test.insert(department, employees,employee,employee1);
		
//		test.display(1);
		
//		test.removeDepartment(1);
	}

	public void insert(Department department,List<Employee> employees,Employee...employees2) {
		

		department.setEmployees(employees);
		
		for (Employee employee : employees2) {
			employee.setDepartment(department);
		}
		
		transaction.begin();
		manager.persist(department);
		transaction.commit();
		System.out.println("Data inserted");
	}

	public void display(int departmentId) {
		Department department = manager.find(Department.class, departmentId);

		if (department != null) {
			System.out.println("Department Name: " + department.getName());
			System.out.println("Department Location: " + department.getLocation());
			System.out.println("Department Manager: " + department.getManagerName());
			System.out.println("Employees:");

			for (Employee employee : department.getEmployees()) {
				System.out.println("Employee ID: " + employee.getId());
				System.out.println("Employee Name: " + employee.getFirstName() + " " + employee.getLastName());
				System.out.println("Employee Age: " + employee.getAge());
				System.out.println("----------------------");
			}
		} else {
			System.out.println("Department not found");
		}
	}

	public void removeDepartment(int departmentId) {
		Department department = manager.find(Department.class, departmentId);

		if (department != null) {
			transaction.begin();
			manager.remove(department);
			transaction.commit();
			System.out.println("Department removed");
		} else {
			System.out.println("Department not found");
		}
	}

	public void updateDepartment(int departmentId) {
		Department department = manager.find(Department.class, departmentId);

		if (department != null) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Select attribute to update:");
			System.out.println("1. Department Name\n");
			System.out.println("2. Department Location\n");
			System.out.println("3. Department Manager Name\n");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1: {
				System.out.println("Enter new Department Name:");
				String newName = scanner.nextLine();
				department.setName(newName);
				break;
			}
			case 2: {
				System.out.println("Enter new Department Location:");
				String newLocation = scanner.nextLine();
				department.setLocation(newLocation);
				break;
			}
			case 3: {
				System.out.println("Enter new Department Manager Name:");
				String newManagerName = scanner.nextLine();
				department.setManagerName(newManagerName);
				break;
			}
			default: {
				System.out.println("Invalid choice");
				scanner.close();
				return;
			}
			}

			transaction.begin();
			manager.merge(department);
			transaction.commit();
			System.out.println("Department Updated");
			scanner.close();
		} else {
			System.out.println("Department not found");
		}
	}
}