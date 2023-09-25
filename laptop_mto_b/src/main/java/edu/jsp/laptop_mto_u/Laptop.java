package edu.jsp.laptop_mto_u;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Cacheable
public class Laptop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String brand;
	private String model;
	private double price;

	@ManyToOne
	private CPU cpu;

	public Laptop() {
		super();
	}

	public Laptop(String brand, String model, double price, CPU cpu) {
		super();
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.cpu = cpu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public CPU getCpu() {
		return cpu;
	}

	public void setCpu(CPU cpu) {
		this.cpu = cpu;
	}
}
