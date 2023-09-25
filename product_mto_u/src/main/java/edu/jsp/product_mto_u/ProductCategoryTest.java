package edu.jsp.product_mto_u;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ProductCategoryTest {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("company");
	EntityManager em = emf.createEntityManager();
	EntityTransaction transaction = em.getTransaction();

	public static void main(String[] args) {
		ProductCategoryTest test = new ProductCategoryTest();

//        Category electronics = new Category("Electronics");
//        Category clothing = new Category("Clothing");
//        Product laptopProduct = new Product("Laptop", 1200.0,electronics);
//        Product tShirtProduct = new Product("T-Shirt", 25.0,clothing);
//
//        test.insertProductCategories(laptopProduct, tShirtProduct, electronics, clothing);

//        test.displayProductsInCategory(1);
//        test.updateProductPrice(laptopProduct.getId(), 1300.0);
//        test.removeProduct(tShirtProduct.getId());

		test.em.close();
		test.emf.close();
	}

	public void insertProductCategories(Product product1, Product product2, Category category1, Category category2) {
		transaction.begin();
		product1.setCategory(category1);
		product2.setCategory(category2);
		em.persist(category1);
		em.persist(category2);
		em.persist(product1);
		em.persist(product2);
		transaction.commit();
		System.out.println("Product and Categories inserted");
	}

	public void displayProductsInCategory(int key) {
		Product product = em.find(Product.class, 1);

		if (product != null) {

			System.out.println(product.getId());
			System.out.println(product.getName());
			System.out.println(product.getPrice());
			System.out.println(product.getCategory().getId());
			System.out.println(product.getCategory().getName());
			System.out.println();
		} else {
			System.out.println("product not found");
		}
	}

	public void updateProduct(int productId) {
		Product product = em.find(Product.class, productId);

		if (product != null) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Select attribute to update:");
			System.out.println("1. Product Name");
			System.out.println("2. Product Price");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1: {
				System.out.println("Enter new Product Name:");
				String newName = scanner.nextLine();
				product.setName(newName);
				break;
			}
			case 2: {
				System.out.println("Enter new Product Price:");
				double newPrice = scanner.nextDouble();
				product.setPrice(newPrice);
				break;
			}
			default: {
				System.out.println("Invalid choice");
				scanner.close();
				return;
			}
			}

			transaction.begin();
			em.merge(product);
			transaction.commit();
			System.out.println("Product updated");
			scanner.close();
		} else {
			System.out.println("Product not found");
		}
	}

	public void removeProduct(int productId) {
		Product product = em.find(Product.class, productId);

		if (product != null) {
			transaction.begin();
			em.remove(product);
			transaction.commit();
			System.out.println("Product removed");
		} else {
			System.out.println("Product not found");
		}
	}
}
