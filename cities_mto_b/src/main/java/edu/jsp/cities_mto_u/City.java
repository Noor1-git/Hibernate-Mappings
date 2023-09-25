package edu.jsp.cities_mto_u;

import javax.persistence.*;

@Entity
@Cacheable
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private int population;

	@ManyToOne(cascade = CascadeType.ALL)
	private Country country;

	public City() {
		super();
	}

	public City(String name, int population, Country country) {
		super();
		this.name = name;
		this.population = population;
		this.country = country;
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

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
