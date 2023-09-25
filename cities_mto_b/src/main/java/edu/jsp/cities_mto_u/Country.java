package edu.jsp.cities_mto_u;

import java.util.List;

import javax.persistence.*;

@Entity
@Cacheable
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private String continent;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "country")
	private List<City> cities;

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public Country() {
		super();
	}

	public Country(String name, String continent) {
		super();
		this.name = name;
		this.continent = continent;
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

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}
}
