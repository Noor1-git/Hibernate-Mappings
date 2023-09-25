package edu.jsp.suppliers_mtm_u;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class SupplierProductTest {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("company");
	EntityManager em = emf.createEntityManager();
	EntityTransaction transaction = em.getTransaction();

	public static void main(String[] args) {
		SupplierProductTest test = new SupplierProductTest();

		Supplier supplier1 = new Supplier("Supplier 1", "supplier1@example.com", "123-456-7890");
		Supplier supplier2 = new Supplier("Supplier 2", "supplier2@example.com", "987-654-3210");

		Product product1 = new Product("Product 1", 100.0, "Description for Product 1");
		Product product2 = new Product("Product 2", 200.0, "Description for Product 2");

		test.insertSupplierProduct(supplier2, product1, product2);

//        test.displaySupplierProducts(supplier1.getId());
//        test.updateProductPrice(product1.getId(), 120.0);
//        test.removeSupplierWithProducts(supplier2.getId());

		test.em.close();
		test.emf.close();
	}

	public void insertSupplierProduct(Supplier supplier, Product... products) {

		for (Product product : products) {
			product.getSuppliers().add(supplier);
			supplier.getProducts().add(product);
		}
		transaction.begin();
		em.persist(supplier);
		em.persist(products[0]);
		em.persist(products[1]);
		transaction.commit();
		System.out.println("Supplier and Products inserted");
	}

	public void displaySupplierProducts(int supplierId) {
		Supplier supplier = em.find(Supplier.class, supplierId);

		if (supplier != null) {
			List<Product> products = supplier.getProducts();
			System.out.println("Products supplied by " + supplier.getName() + ":");
			for (Product product : products) {
				System.out.println("Product: " + product.getName() + ", Price: " + product.getPrice());
			}
		} else {
			System.out.println("Supplier not found");
		}
	}

	public void updateProductPrice(int productId, double newPrice) {
		Product product = em.find(Product.class, productId);

		if (product != null) {
			transaction.begin();
			product.setPrice(newPrice);
			transaction.commit();
			System.out.println("Product price updated");
		} else {
			System.out.println("Product not found");
		}
	}

	public void removeSupplierWithProducts(int supplierId) {
		Supplier supplier = em.find(Supplier.class, supplierId);

		if (supplier != null) {
			transaction.begin();
			em.remove(supplier);
			transaction.commit();
			System.out.println("Supplier and its products removed");
		} else {
			System.out.println("Supplier not found");
		}
	}
}
