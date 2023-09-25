package edu.jsp.restaurant.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Cacheable
public class FoodOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public int getId() {
		return id;
	}

	private String status;
	private double totalPrice;
	@CreationTimestamp
	private LocalDateTime orderCreatedTime;
	@UpdateTimestamp
	private LocalDateTime orderDeliveryTime;
	private String customerName;
	private long contactnumber;

	@ManyToOne
	private User user;
	
	@OneToMany(cascade = CascadeType.REMOVE,mappedBy = "foodOrder", orphanRemoval = true)
	private List<Item> items=new ArrayList<Item>();

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDateTime getOrderDeliveryTime() {
		return orderDeliveryTime;
	}

	public void setOrderDeliveryTime(LocalDateTime orderDeliveryTime) {
		this.orderDeliveryTime = orderDeliveryTime;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public long getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(long contactnumber) {
		this.contactnumber = contactnumber;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public FoodOrder() {
		super();
	}
	
	public FoodOrder(String status, double totalPrice,  String customerName,
			long contactnumber, User user) {
		super();
		this.status = status;
		this.totalPrice = totalPrice;
		this.customerName = customerName;
		this.contactnumber = contactnumber;
		this.user = user;
	}
}