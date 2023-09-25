package edu.jsp.one_to_one;

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
		CompanyTest test=new CompanyTest();
		
		test.update(2);
	}

	public void insert(Company company, Ceo ceo) {
		company.setCeo(ceo);
		ceo.setCompany(company);
		transaction.begin();
		manager.merge(company);
		transaction.commit();
		System.out.println("Data inserted");
	}

	public void remove(int key) {

		Company company = manager.find(Company.class, key);

		transaction.begin();
		manager.remove(company);
		transaction.commit();

		System.out.println("record removed");
	}

	public void update(int key) {

		Scanner scanner = new Scanner(System.in);

		Company company = manager.find(Company.class, key);

		System.out.println("Enter what to update" + "1.Company name\n" + "2.Company Industry\n" + "3.Ceo details\n");
		int choice = scanner.nextInt();
		scanner.nextLine();

		switch (choice) {
		case 1: {
			if (company != null) {
				System.out.println("Enter new Name\n");
				String name = scanner.nextLine();
				company.setName(name);
				transaction.begin();
				manager.merge(company);
				transaction.commit();
				System.out.println("Updated");
			} else {
				System.out.println("record not found");
			}
			break;
		}
		case 2: {
			if (company != null) {
				System.out.println("Enter new Industry");
				String name = scanner.nextLine();
				company.setIndustry(name);
				transaction.begin();
				manager.merge(company);
				transaction.commit();
				System.out.println("Updated");
			} else {
				System.out.println("record not found");
			}
			break;
		}
		case 3: {

			Ceo ceo = company.getCeo();
			System.out.println("Enter what to Update\n" + "1.Ceo name\n" + "2.Ceo age\n");
			int choice1 = scanner.nextInt();
			scanner.nextLine();
			switch (choice1) {
			case 1: {
				if (ceo != null) {
					System.out.println("Enter new Name\n");
					String name = scanner.nextLine();
					ceo.setName(name);
					transaction.begin();
					manager.merge(ceo);
					transaction.commit();
					System.out.println("Updated");
				} else {
					System.out.println("record not found");
				}
				break;
			}
			case 2: {
				if (ceo != null) {
					System.out.println("Enter new age\n");
					int age = scanner.nextInt();
					scanner.nextLine();
					ceo.setAge(age);
					transaction.begin();
					manager.merge(ceo);
					transaction.commit();
					System.out.println("Updated");
				} else {
					System.out.println("record not found");
				}
				break;
			}

			default: {
				System.out.println("Invalid choice");
			}
				break;
			}
			break;
		}
		default: {
			System.out.println("Invalid choice");
		}
		}
		scanner.close();
	}
	
	public void display(int key) {
		Company company=manager.find(Company.class, key);
		
		if (company!=null) {
			System.out.println("Company id : "+company.getId());
			System.out.println("company name : "+company.getName());
			System.out.println("Company industry : "+company.getIndustry());
			System.out.println("==============================================");
			Ceo ceo=company.getCeo();
			System.out.println("Ceo id : "+ceo.getId());
			System.out.println("Ceo name : "+ceo.getName());
			System.out.println("Ceo Age : "+ceo.getAge());
		} else {
			System.out.println("record not found");
		}
	}
}