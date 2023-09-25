package edu.jsp.restaurant.controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.jsp.restaurant.model.FoodProduct;
import edu.jsp.restaurant.model.Menu;
import edu.jsp.restaurant.model.User;

public class StaffController {

	static Menu menu = new Menu();		
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("company");
	static EntityManager manager = factory.createEntityManager();
	static EntityTransaction transaction = manager.getTransaction();
	static Scanner scanner=new Scanner(System.in);

	public void createMenu() {
		User user=manager.find(User.class, 2);
		menu.setUser(user);
		if(user.getMenu()==null) {
			transaction.begin();
			manager.merge(menu);
			transaction.commit();
		}
	}
	
	public List<FoodProduct> addFoodProduct(FoodProduct foodProduct) {
		Menu menu=manager.find(Menu.class, 1);
		menu.getFoodProducts().add(foodProduct);
		foodProduct.setMenu(menu);
		transaction.begin();
		manager.merge(foodProduct);
		transaction.commit();
		return menu.getFoodProducts();
	}

	public FoodProduct removeFoodProduct(int key) {

		FoodProduct foodProduct = manager.find(FoodProduct.class, key);
		if (foodProduct != null) {
			transaction.begin();
			manager.remove(foodProduct);
			transaction.commit();
		}
		return foodProduct;
	}

	public void updateFoodProduct() {
		User user=manager.find(User.class, 2);
		List<FoodProduct> foodProduct=user.getMenu().getFoodProducts();
		for (FoodProduct foodProduct2 : foodProduct) {
			System.out.println(foodProduct2.getId());
			System.out.println(foodProduct2.getName());
			System.out.println(foodProduct2.getPrice());
			System.out.println(foodProduct2.getType());
			System.out.println(foodProduct2.getAbout());
			System.out.println(foodProduct2.getAvailability());
		}
		System.out.println("Enter product id");
		int key = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Enter new price");
		double price = scanner.nextDouble();
		scanner.nextLine();
		
		FoodProduct product=null;
		for (FoodProduct foodProduct2 : foodProduct) {
			if (foodProduct2.getId()==key) {
				product=foodProduct2;
				foodProduct2.setPrice(price);
			}
		}
		transaction.begin();
		manager.merge(product);
		transaction.commit();
		System.out.println("Product Updated...\n");
	}

	public List<FoodProduct> displayFoodProduct() {
		User user=manager.find(User.class, 2);
		return user.getMenu().getFoodProducts();
		
	}
}