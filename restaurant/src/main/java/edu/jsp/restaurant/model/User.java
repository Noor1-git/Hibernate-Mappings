package edu.jsp.restaurant.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	private String email;
	private String password;
	static private String role;
	
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	private Menu menu;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Branch> branch=new ArrayList<Branch>();
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	List<FoodOrder> foodOrders=new ArrayList<FoodOrder>();
	
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public List<Branch> getBranch() {
		return branch;
	}
	public void setBranch(List<Branch> branch) {
		this.branch = branch;
	}
	public List<FoodOrder> getFoodOrders() {
		return foodOrders;
	}
	public void setFoodOrders(List<FoodOrder> foodOrders) {
		this.foodOrders = foodOrders;
	}
	public User() {
		super();
	}
	public User(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	static public String getRole() {
		return role;
	}
	static public void setRole(String role) {
		User.role = role;
	}
	
}
