package edu.jsp.restaurant.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Cacheable
public class Menu {

	@Id
	
	private int id=1;
	
	@OneToMany(mappedBy = "menu")
	private List<FoodProduct> foodProducts=new ArrayList<FoodProduct>();
	
	@OneToOne
	private User user;

	public List<FoodProduct> getFoodProducts() {
		return foodProducts;
	}

	public void setFoodProducts(List<FoodProduct> foodProducts) {
		this.foodProducts = foodProducts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Menu(List<FoodProduct> foodProducts, User user) {
		super();
		this.foodProducts = foodProducts;
		this.user = user;
	}

	public Menu() {
		super();
	}
	
}