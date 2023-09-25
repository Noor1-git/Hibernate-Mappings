package edu.jsp.restaurant.controller;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.jsp.restaurant.model.Menu;
import edu.jsp.restaurant.model.User;

public class UserController {

	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("company");
	static EntityManager manager = factory.createEntityManager();
	static EntityTransaction transaction = manager.getTransaction();
	static Scanner scanner = new Scanner(System.in);

	public void register(User user) {
		transaction.begin();
		manager.persist(user);
		transaction.commit();
	}

	public String logIn(int key) {
		User user = manager.find(User.class, key);

		System.out.println("Enter User Name\n");
		String username = scanner.nextLine();
		if (user != null) {
			if (username.equalsIgnoreCase(user.getName())) {

				System.out.println("Enter Password\n");
				String password = scanner.nextLine();

				if (password.equalsIgnoreCase(user.getPassword())) {

					System.out.println("Welcome " + username);
					User.setRole(username);
					Menu menu = manager.find(Menu.class, 1);
					user.setMenu(menu);
				} else {

					System.out.println("Incorrect password...Please try again...\n");
					System.out.println("Enter User id");
					int id = scanner.nextInt();
					scanner.nextLine();
					logIn(id);
				}
			} else {
				System.out.println("User Not found...Please try again...\n");
				System.out.println("Enter User id");
				int id = scanner.nextInt();
				scanner.nextLine();
				logIn(id);
			}
		} else {
			System.out.println("User Not found...Please try again...\n");
			System.out.println("Enter User id");
			int id = scanner.nextInt();
			scanner.nextLine();
			logIn(id);
		}
		return null;
	}
}