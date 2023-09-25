package edu.jsp.product_mto_u;

import javax.persistence.*;

@Entity
@Cacheable
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private double price;

	@ManyToOne(cascade = CascadeType.ALL)
	private Category category;

	public Product() {
		super();
	}

	public Product(String name, double price, Category category) {
		super();
		this.name = name;
		this.price = price;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
