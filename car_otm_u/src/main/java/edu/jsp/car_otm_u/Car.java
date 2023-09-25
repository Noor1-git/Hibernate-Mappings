package edu.jsp.car_otm_u;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Cacheable
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private String manufacturer;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Variant> variants;

	public Car() {
		super();
	}

	public Car(String name, String manufacturer) {
		super();
		this.name = name;
		this.manufacturer = manufacturer;
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

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public List<Variant> getVariants() {
		return variants;
	}

	public void setVariants(List<Variant> variants) {
		this.variants = variants;
	}
}
