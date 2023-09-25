package edu.jsp.laptop_mto_u;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CPU {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String brand;
	private String model;
	private int cores;
	private double clockSpeed;

	public CPU() {
		super();
	}

	public CPU(String brand, String model, int cores, double clockSpeed) {
		super();
		this.brand = brand;
		this.model = model;
		this.cores = cores;
		this.clockSpeed = clockSpeed;
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

	public int getCores() {
		return cores;
	}

	public void setCores(int cores) {
		this.cores = cores;
	}

	public double getClockSpeed() {
		return clockSpeed;
	}

	public void setClockSpeed(double clockSpeed) {
		this.clockSpeed = clockSpeed;
	}
}
