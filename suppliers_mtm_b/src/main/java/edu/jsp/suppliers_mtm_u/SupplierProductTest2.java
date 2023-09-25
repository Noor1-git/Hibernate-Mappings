package edu.jsp.suppliers_mtm_u;

public class SupplierProductTest2 {

	public static void main(String[] args) {
		SupplierProductTest dao = new SupplierProductTest();
		Supplier supplier1 = new Supplier("Supplier_Test1", "supplier_test1@example.com", "789-408-7890");
		Supplier supplier2 = new Supplier("Supplier_Test 2", "supplie_test2@example.com", "980-723-2683");

		Product product1 = new Product("Product_Test1", 476.0, "Description for Product_Test 1");
		Product product2 = new Product("Product_Test2 2", 763.0, "Description for Product_Test 2");

		dao.insertSupplierProduct(supplier1, product1, product2);
		System.out.println(supplier1.getId());
		System.out.println(product1.getId());
		System.out.println(product2.getId());
		dao.insertSupplierProduct(supplier2, product1, product2);
		System.out.println(supplier2.getId());
		System.out.println(product1.getId());
		System.out.println(product2.getId());
	}
}