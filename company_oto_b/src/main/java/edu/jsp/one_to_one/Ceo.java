package edu.jsp.one_to_one;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@Cacheable
public class Ceo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "ceo_id_gen")
	@SequenceGenerator(name="ceo_id_gen",initialValue = 100,allocationSize = 10,sequenceName = "ceo_id")
	private int id;
	
	private String name;
	
	private int age;

	@OneToOne(mappedBy = "ceo",cascade = CascadeType.ALL)
	private Company company;
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Ceo(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Ceo() {
		super();
	}
}