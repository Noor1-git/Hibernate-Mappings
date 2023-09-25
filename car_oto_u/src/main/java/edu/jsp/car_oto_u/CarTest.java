package edu.jsp.car_oto_u;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CarTest {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("company");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();

	public static void main(String[] args) {
		CarTest test = new CarTest();
		
		Engine engine=new Engine("T10");
		Car car=new Car("TATA", engine);
		test.insert(car);
	}

	public void insert(Car car) {
		transaction.begin();
		manager.merge(car);
		transaction.commit();
		System.out.println("Data inserted");
	}

	public void remove(int key) {

		Car car = manager.find(Car.class, key);

		transaction.begin();
		manager.remove(car);
		transaction.commit();

		System.out.println("Record removed");
	}

	public void update(int key) {

		Scanner scanner = new Scanner(System.in);

		Car car = manager.find(Car.class, key);

		System.out.println("Enter what to update\n" + "1. Car brand\n" + "2. Car Engine\n");
		int choice = scanner.nextInt();
		scanner.nextLine();

		switch (choice) {
		case 1: {
			if (car != null) {
				System.out.println("Enter new Brand\n");
				String brand = scanner.nextLine();
				car.setBrand(brand);
				transaction.begin();
				manager.merge(car);
				transaction.commit();
				System.out.println("Updated");
			} else {
				System.out.println("Record not found");
			}
			break;
		}
		case 2: {
			Engine engine = car.getEngine();
			if (engine != null) {
				System.out.println("Enter new Engine Type\n");
				String type = scanner.nextLine();
				engine.setType(type);
				transaction.begin();
				manager.merge(engine);
				transaction.commit();
				System.out.println("Updated");
			} else {
				System.out.println("Record not found");
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
		Car car = manager.find(Car.class, key);

		if (car != null) {
			System.out.println("Car id: " + car.getId());
			System.out.println("Car brand: " + car.getBrand());
			System.out.println("==============================");
			Engine engine = car.getEngine();
			System.out.println("Engine id: " + engine.getId());
			System.out.println("Engine type: " + engine.getType());
		} else {
			System.out.println("Record not found");
		}
	}
}