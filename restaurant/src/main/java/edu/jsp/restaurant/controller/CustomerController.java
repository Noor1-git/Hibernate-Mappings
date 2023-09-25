package edu.jsp.restaurant.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.jsp.restaurant.model.FoodOrder;
import edu.jsp.restaurant.model.FoodProduct;
import edu.jsp.restaurant.model.Item;
import edu.jsp.restaurant.model.Menu;
import edu.jsp.restaurant.model.User;

public class CustomerController {

	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("company");
	static EntityManager manager = factory.createEntityManager();
	static EntityTransaction transaction = manager.getTransaction();
	static User user = new User();
	static Scanner scanner = new Scanner(System.in);

	public void getMenu() {
		Query query = manager.createQuery("SELECT m FROM Menu m");

		List<Menu> menu = query.getResultList();

		List<FoodProduct> foodProducts = null;

		for (Menu menu1 : menu) {
			foodProducts = menu1.getFoodProducts();
			break;
		}
		if (foodProducts != null) {
			for (FoodProduct foodProduct : foodProducts) {
				System.out.println(foodProduct.getId());
				System.out.println(foodProduct.getName());
				System.out.println(foodProduct.getAbout());
				System.out.println(foodProduct.getType());
				System.out.println(foodProduct.getPrice());
				System.out.println(foodProduct.getAvailability());
			}
		}
	}

	public void createOrder() {
		FoodOrder foodOrder = new FoodOrder();
		User user = manager.find(User.class, 3);
		System.out.println("Enter Contact number\n");
		long number = scanner.nextLong();
		scanner.nextLine();
		foodOrder.setContactnumber(number);
		System.out.println("Enter Name\n");
		String name = scanner.nextLine();
		foodOrder.setCustomerName(name);
		foodOrder.setStatus("Preparing");
		foodOrder.setUser(user);
		user.getFoodOrders().add(foodOrder);
		transaction.begin();
		manager.merge(user);
		transaction.commit();
	}

	public void addItem() {
		User user1 = manager.find(User.class, 3);
		List<FoodOrder> foodOrders = user1.getFoodOrders();
		for (FoodOrder foodOrder : foodOrders) {
			System.out.println(foodOrder.getId());
			System.out.println(foodOrder.getCustomerName());
			System.out.println(foodOrder.getContactnumber());
			System.out.println(foodOrder.getStatus());
			System.out.println(foodOrder.getTotalPrice());
		}
		System.out.println("Enter Order Id to add item\n");
		int id = scanner.nextInt();
		scanner.nextLine();
		FoodOrder target = null;
		List<FoodOrder> foodOrder = user1.getFoodOrders();
		for (FoodOrder foodOrder2 : foodOrder) {
			if (foodOrder2.getId() == id) {
				target = foodOrder2;
				break;
			}
		}
		if (target == null) {
			System.out.println("Order Not found");
			return;
		}
		User user = manager.find(User.class, 2);
		Menu menu = user.getMenu();
		List<FoodProduct> foodProducts = menu.getFoodProducts();
		for (FoodProduct foodProduct : foodProducts) {
			System.out.println(foodProduct.getId());
			System.out.println(foodProduct.getName());
			System.out.println(foodProduct.getAbout());
			System.out.println(foodProduct.getType());
			System.out.println(foodProduct.getPrice());
			System.out.println(foodProduct.getAvailability());
		}
		System.out.println("Enter item id to add to order\n");
		int id1 = scanner.nextInt();
		scanner.nextLine();
		Item item = null;
		List<FoodProduct> foodProduct = user.getMenu().getFoodProducts();
		for (FoodProduct foodProduct2 : foodProduct) {
			if (foodProduct2.getId() == id1) {
				item = new Item(foodProduct2.getId(), foodProduct2.getName(), foodProduct2.getType(), 1,
						foodProduct2.getPrice(), target);
			}
		}
		transaction.begin();
		manager.persist(item);
		
		if (target.getItems() != null) {
			target.getItems().add(item);
		} else {
			target.setItems(Arrays.asList(item));
		}
		manager.merge(user1);
		transaction.commit();
	}

	public void showItems() {
		User user = manager.find(User.class, 3);
		List<FoodOrder> foodOrder = user.getFoodOrders();
		for (FoodOrder foodOrder1 : foodOrder) {
			System.out.println(foodOrder1.getId());
			System.out.println(foodOrder1.getCustomerName());
			System.out.println(foodOrder1.getStatus());
			System.out.println(foodOrder1.getOrderDeliveryTime());
			System.out.println(foodOrder1.getContactnumber());

			for (Item item : foodOrder1.getItems()) {
				System.out.println(item.getName());
				System.out.println(item.getPrice());
				System.out.println(item.getQuantity());
				System.out.println(item.getType());
			}
		}
	}

	public void removeItem() {
		User user1 = manager.find(User.class, 3);
		List<FoodOrder> foodOrders = user1.getFoodOrders();
		for (FoodOrder foodOrder : foodOrders) {
			System.out.println(foodOrder.getId());
			System.out.println(foodOrder.getCustomerName());
			System.out.println(foodOrder.getContactnumber());
			System.out.println(foodOrder.getStatus());
			System.out.println(foodOrder.getTotalPrice());
		}
		System.out.println("Enter Order Id to remove item\n");
		int id = scanner.nextInt();
		scanner.nextLine();
		FoodOrder target = null;
		List<FoodOrder> foodOrder = user1.getFoodOrders();
		for (FoodOrder foodOrder2 : foodOrder) {
			if (foodOrder2.getId() == id) {
				target = foodOrder2;
				break;
			}
		}
		if (target == null) {
			System.out.println("Order Not found");
			return;
		}
		List<Item> items = target.getItems();
		for (Item item : items) {
			System.out.println(item.getId());
			System.out.println(item.getName());
			System.out.println(item.getProductId());
			System.out.println(item.getType());
			System.out.println(item.getPrice());
			System.out.println(item.getFoodOrder().getId());
		}
		System.out.println("Enter item id to remove from order\n");
		int id1 = scanner.nextInt();
		scanner.nextLine();
		Item targetItem = null;
		for (Item item : items) {
			if (item.getId() == id1) {
				targetItem = item;
				break;
			}
		}
		transaction.begin();
		manager.remove(targetItem);

		if (target.getItems() != null) {
			target.getItems().remove(targetItem);
		}
		manager.merge(user1);
		transaction.commit();
		System.out.println("Item removed");
	}

	public void cancelOrder() {
		User user = manager.find(User.class, 3);
		List<FoodOrder> foodOrders = user.getFoodOrders();
		for (FoodOrder foodOrder : foodOrders) {
			System.out.println(foodOrder.getId());
			System.out.println(foodOrder.getCustomerName());
			System.out.println(foodOrder.getContactnumber());
			System.out.println(foodOrder.getStatus());
			System.out.println(foodOrder.getTotalPrice());
		}
		System.out.println("Enter Order Id to cancel order\n");
		int id = scanner.nextInt();
		scanner.nextLine();
		FoodOrder target = null;
		for (FoodOrder foodOrder : foodOrders) {
			if (foodOrder.getId() == id) {
				
				target = foodOrder;

			}
		}
		
		if (target != null) {
			transaction.begin();
			foodOrders.remove(target);
			manager.remove(target);
			manager.merge(user);
			transaction.commit();
		} else {
			System.out.println("Order Not found");
		}

	}
}