package edu.jsp.one_to_one;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Cacheable
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private String industry;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Ceo ceo;

	public Company() {
		super();
	}

	public Ceo getCeo() {
		return ceo;
	}

	public void setCeo(Ceo ceo) {
		this.ceo = ceo;
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

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Company(String name, String industry) {
		super();
		this.name = name;
		this.industry = industry;
	}
}