package edu.jsp.artist_mtm_u;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "artists")
public class Artist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private String nationality;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "artist_exhibition", joinColumns = @JoinColumn(name = "artist_id"), inverseJoinColumns = @JoinColumn(name = "exhibition_id"))
	private List<Exhibition> exhibitions = new ArrayList<Exhibition>();

	public Artist() {
	}

	public Artist(String name, String nationality) {
		this.name = name;
		this.nationality = nationality;
	}

	// Getters and Setters

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

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public List<Exhibition> getExhibitions() {
		return exhibitions;
	}

	public void setExhibitions(List<Exhibition> exhibitions) {
		this.exhibitions = exhibitions;
	}
}