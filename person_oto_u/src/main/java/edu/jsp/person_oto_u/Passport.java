package edu.jsp.person_oto_u;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Cacheable
public class Passport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String passportNumber;
	private String issuingCountry;
	private String expirationDate;

	// Constructors
	public Passport() {
	}

	public Passport(String passportNumber, String issuingCountry, String expirationDate) {
		this.passportNumber = passportNumber;
		this.issuingCountry = issuingCountry;
		this.expirationDate = expirationDate;
	}

	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getIssuingCountry() {
		return issuingCountry;
	}

	public void setIssuingCountry(String issuingCountry) {
		this.issuingCountry = issuingCountry;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
}