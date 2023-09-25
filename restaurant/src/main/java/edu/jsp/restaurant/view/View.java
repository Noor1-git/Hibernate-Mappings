package edu.jsp.restaurant.view;

import java.util.Scanner;

import edu.jsp.restaurant.controller.CustomerController;
import edu.jsp.restaurant.controller.ManagerController;
import edu.jsp.restaurant.controller.StaffController;
import edu.jsp.restaurant.controller.UserController;
import edu.jsp.restaurant.model.Branch;
import edu.jsp.restaurant.model.FoodProduct;
import edu.jsp.restaurant.model.Menu;
import edu.jsp.restaurant.model.User;

public class View {
	static Scanner scanner = new Scanner(System.in);
	static UserController controller = new UserController();
	static StaffController staffController = new StaffController();
	static ManagerController managerController = new ManagerController();
	static CustomerController customerController = new CustomerController();
	static View test = new View();
	static boolean loop=true;
	static boolean customer=true;
	static boolean manager=true;
	static boolean staff=true;

	public static void main(String[] args) {

		test.login();
	}

	public void login() {
		System.out.println("WELCOME\n Select from below\n" + "1.Register\n" + "2.Login");
		int choice = scanner.nextInt();
		scanner.nextLine();
		switch (choice) {
		case 1: {
			test.register();
			break;
		}
		case 2: {
			test.logIn1();
			break;
		}
		default: {
			System.out.println("Invalid choice");
		}
			break;
		}
	}

	public void staff() {
		staffController.createMenu();
		while(staff) {
			System.out.println("Select Operation to Perform\n" + "1.Add FoodProduct\n" + "2.Remove FoodProduct\n"
					+ "3.Update FoodProduct\n" + "4.display FoodProducts\n"+"5.Exit");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1: {
				test.addProduct();
				break;
			}
			case 2: {
				test.removeProduct();
				break;
			}
			case 3: {
				staffController.updateFoodProduct();
				break;
			}

			case 4: {
				test.displayProduct();
				break;
			}case 5:{
				staff=false;
				System.out.println("Thank You...");
				break;
			}
			default:
				System.out.println("Invalid choice");
				break;
			}
		}
		
	}

	public void manager() {
		while(manager) {
			System.out.println("Select operation to perform\n" + "1.Add branch\n" + "2.Remove branch\n"
					+ "3.Display branch\n" + "4.Update branch\n"+"5.Exit");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1: {
				test.addBranch();
				break;
			}
			case 2: {
				test.removeBranch();
				break;
			}
			case 3: {
				test.displayBranch();
				break;
			}
			case 4: {
				test.updateBranch();
				break;
			}
			case 5:{
				staff=false;
				System.out.println("Thank You...");
				break;
			}
			default:
				System.out.println("Invalid choice");
				break;
			}
		}
		
	}

	public void customer() {
		while(customer) {
			System.out.println("Select Operation to perform\n" + "1.Display Menu\n" + "2.Order Food\n"
					+ "3.Add Item to Order\n" + "4.Remove Item from Order\n" + "5.Cancel Order\n"+"6.Show Orders\n"+"7.Exit\n");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1: {
				customerController.getMenu();
				break;
			}
			case 2: {
				customerController.createOrder();
				break;
			}
			case 3: {
				customerController.addItem();
				break;
			}
			case 4: {
				customerController.removeItem();
				break;
			}
			case 5: {
				customerController.cancelOrder();
				System.out.println("Order Cancelled");
				break;
			}
			case 6: {
				customerController.showItems();
				break;
			}
			case 7:{
				customer=false;
				System.out.println("Thank you...");
				break;
			}
			default:
				System.out.println("Invalid choice");
				break;
			}
		}
	}

	public void logIn1() {
		while(loop) {
		System.out.println("Enter User id");
		int id = scanner.nextInt();
		scanner.nextLine();
		controller.logIn(id);
		
			if (User.getRole().equalsIgnoreCase("manager")) {
				loop=false;
				test.manager();
			} else if (User.getRole().equalsIgnoreCase("staff")) {
				loop=false;
				test.staff();
			} else if(User.getRole().equalsIgnoreCase("customer")){
				loop=false;
				test.customer();
			}else {
				System.out.println("User Not found");
			}
		}
	}

	public void register() {
		System.out.println("Enter User Name");
		String username = scanner.nextLine();
		System.out.println("Enter User Email");
		String Email = scanner.nextLine();
		System.out.println("Enter Password");
		String password = scanner.nextLine();

		User user = new User(username, Email, password);

		controller.register(user);

		System.out.println("Registered");
	}

	public void addProduct() {
		FoodProduct foodProduct = new FoodProduct();

		System.out.println("Enter product name");
		foodProduct.setName(scanner.nextLine());

		foodProduct.setAvailability("Available");

		foodProduct.setAbout("Description of product");

		System.out.println("Enter Product Type");
		foodProduct.setType(scanner.nextLine());

		System.out.println("Enter product price");
		foodProduct.setPrice(scanner.nextDouble());
		scanner.nextLine();

		staffController.addFoodProduct(foodProduct);
		System.out.println("Added...");
	}

	public void removeProduct() {
		System.out.println("Enter product id to remove");
		int id = scanner.nextInt();
		scanner.nextLine();
		staffController.removeFoodProduct(id);
		System.out.println("removed...");
	}

	public void displayProduct() {
		for (FoodProduct foodProduct : staffController.displayFoodProduct()) {
			System.out.println(foodProduct.getId());
			System.out.println(foodProduct.getName());
			System.out.println(foodProduct.getAbout());
			System.out.println(foodProduct.getType());
			System.out.println(foodProduct.getAvailability());
			System.out.println(foodProduct.getPrice());
		}
	}

	public void addBranch() {
		Branch branch = new Branch();
		System.out.println("Enter Branch Name");
		branch.setName(scanner.nextLine());
		System.out.println("Enter Branch Address");
		branch.setAddress(scanner.nextLine());
		System.out.println("Enter Branch Email");
		branch.setEmail(scanner.nextLine());
		System.out.println("Enter Branch Phone Number");
		branch.setPhoneNumber(scanner.nextLong());
		scanner.nextLine();

		managerController.addBranch(branch);

		System.out.println("Added...");
	}

	public void removeBranch() {
		System.out.println("Enter branch key");
		int key = scanner.nextInt();

		managerController.removeBranch(key);
		System.out.println("removed");
	}

	public void displayBranch() {
		managerController.displayBranch();
	}

	public void updateBranch() {
		System.out.println("Enter branch Id");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println(
				"Select what to Update\n" + "1.Branch Name\n" + "2.Branch Phone Number\n" + "3.Branch Email\n");
		int choice1 = scanner.nextInt();
		scanner.nextLine();
		managerController.updateBranch(id, choice1);
		System.out.println("updated...");
	}
}