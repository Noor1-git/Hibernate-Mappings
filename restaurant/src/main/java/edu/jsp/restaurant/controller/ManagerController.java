package edu.jsp.restaurant.controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.jsp.restaurant.model.Branch;
import edu.jsp.restaurant.model.User;

public class ManagerController {

	static User user = new User();
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("company");
	static EntityManager manager = factory.createEntityManager();
	static EntityTransaction transaction = manager.getTransaction();

	public void addBranch(Branch branch) {
		User user=manager.find(User.class, 1);
		branch.setUser(user);
		List<Branch> branchs=user.getBranch();
		branchs.add(branch);
		transaction.begin();
		manager.persist(branch);
		manager.merge(user);
		transaction.commit();
	}

	public void removeBranch(int key) {
		Branch branch = manager.find(Branch.class, key);
		if (branch != null) {
			transaction.begin();
			manager.remove(branch);
			transaction.commit();
		}else {
			System.out.println("branch not found");
		}
	}

	public void displayBranch() {
		User user=manager.find(User.class, 1);
		for (Branch branch : user.getBranch()) {
			System.out.println("Branch name : "+branch.getName());
			System.out.println("Branch Email : "+branch.getEmail());
			System.out.println("Branch Address : "+branch.getAddress());
			System.out.println("Branch Contact : "+branch.getPhoneNumber());
			System.out.println("Branch Manager Name : "+branch.getUser().getName());
		}
	}

	public void updateBranch(int key, int choice) {
		Branch branch = manager.find(Branch.class, key);
		Scanner scanner = new Scanner(System.in);
		if (branch != null) {
			switch (choice) {
			case 1: {
				System.out.println("Enter new name");
				branch.setName(scanner.nextLine());
				break;
			}
			case 2: {
				System.out.println("Enter new Phone Number");
				branch.setPhoneNumber(scanner.nextLong());
				scanner.nextLine();
				break;
			}
			case 3: {
				System.out.println("Enter new Email");
				branch.setEmail(scanner.nextLine());
				break;
			}
			default: {
				System.out.println("Invalid Choice");
			}
				break;
			}
		} else {
			System.out.println("record not found");
		}
		transaction.begin();
		manager.merge(branch);
		transaction.commit();
		scanner.close();
	}
}