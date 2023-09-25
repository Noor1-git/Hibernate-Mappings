package edu.jsp.person_oto_u;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Scanner;

public class PersonPassportTest {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("company");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();

	public static void main(String[] args) {
		PersonPassportTest test = new PersonPassportTest();
		Person person=new Person("Nooruddin", "Shaikh", 25);
		Passport passport=new Passport("abc123", "India", "30/12/50");
		test.insert(person, passport);
		
//		test.display(1);
		
//		test.update(1);
//		test.remove(1);
	}

	public void insert(Person person, Passport passport) {
		person.setPassport(passport);
		passport.setPerson(person);
		transaction.begin();
		manager.merge(person);
		transaction.commit();
		System.out.println("Data inserted");
	}

	public void remove(int key) {
		Person person = manager.find(Person.class, key);
		if (person != null) {
			transaction.begin();
			manager.remove(person);
			transaction.commit();
			System.out.println("Record removed");
		} else {
			System.out.println("Record not found");
		}
	}

	public void update(int key) {
		Scanner scanner = new Scanner(System.in);

		Person person = manager.find(Person.class, key);

		if (person != null) {
			System.out.println("Enter what to update:\n1. First Name\n2. Last Name\n3. Age");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1: {
				System.out.println("Enter new First Name:");
				String firstName = scanner.nextLine();
				person.setFirstName(firstName);
				break;
			}
			case 2: {
				System.out.println("Enter new Last Name:");
				String lastName = scanner.nextLine();
				person.setLastName(lastName);
				break;
			}
			case 3: {
				System.out.println("Enter new Age:");
				int age = scanner.nextInt();
				person.setAge(age);
				break;
			}
			default:
				System.out.println("Invalid choice");
			}

			transaction.begin();
			manager.merge(person);
			transaction.commit();
			System.out.println("Updated");
		} else {
			System.out.println("Record not found");
		}

		scanner.close();
	}

	public void display(int key) {
		Person person = manager.find(Person.class, key);

		if (person != null) {
			System.out.println("Person ID: " + person.getId());
			System.out.println("First Name: " + person.getFirstName());
			System.out.println("Last Name: " + person.getLastName());
			System.out.println("Age: " + person.getAge());

			Passport passport = person.getPassport();
			if (passport != null) {
				System.out.println("Passport ID: " + passport.getId());
				System.out.println("Passport Number: " + passport.getPassportNumber());
				System.out.println("Issuing Country: " + passport.getIssuingCountry());
				System.out.println("Expiration Date: " + passport.getExpirationDate());
			} else {
				System.out.println("Passport not found");
			}
		} else {
			System.out.println("Record not found");
		}
	}
}