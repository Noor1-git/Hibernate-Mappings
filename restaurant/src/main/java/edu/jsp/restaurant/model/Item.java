package edu.jsp.restaurant.model;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Cacheable
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int productId;
	private String name;
	private String type;

	private int quantity;
	private double price;

	@ManyToOne(cascade = CascadeType.ALL)
	private FoodOrder foodOrder;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public FoodOrder getFoodOrder() {
		return foodOrder;
	}

	public void setFoodOrder(FoodOrder foodOrder) {
		this.foodOrder = foodOrder;
	}

	public Item(int productId, String name, String type, int quantity, double price, FoodOrder foodOrder) {
		super();
		this.productId = productId;
		this.name = name;
		this.type = type;
		this.quantity = quantity;
		this.price = price;
		this.foodOrder = foodOrder;
	}

	public Item() {
		super();
	}
}