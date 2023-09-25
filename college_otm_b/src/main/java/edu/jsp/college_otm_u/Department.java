package edu.jsp.college_otm_u;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Cacheable
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String headOfDepartment;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private College college;
    

    public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public Department() {
        super();
    }

    public Department(String name, String headOfDepartment) {
        this.name = name;
        this.headOfDepartment = headOfDepartment;
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

    public String getHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(String headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }
}
