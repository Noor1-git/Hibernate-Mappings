package edu.jsp.cities_mto_u;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CityCountryTest {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("company");
	EntityManager em = emf.createEntityManager();
	EntityTransaction transaction = em.getTransaction();

	public static void main(String[] args) {
		CityCountryTest test = new CityCountryTest();

//		Country country = new Country("United States", "North America");
//		City city1 = new City("New York", 8500000, country);
//		City city2 = new City("Los Angeles", 3900000, country);
//
//		test.insertCityCountry(country, city1, city2);
		 test.displayCityCountry(1);
		// test.updateCityPopulation(1);
		// test.displayCityCountry(1);
		// test.removeCityCountry(1);
		// test.displayCityCountry(1);

		test.em.close();
		test.emf.close();
	}

	public void insertCityCountry(Country country, City... cities) {
		for (City city : cities) {
			city.setCountry(country);
			transaction.begin();
			em.persist(city);
			transaction.commit();
		}
		System.out.println("Cities Data inserted");
	}

	public void displayCityCountry(int cityId) {
		City city = em.find(City.class, cityId);

		if (city != null) {
			System.out.println("City Name: " + city.getName());
			System.out.println("City Population: " + city.getPopulation());
			if (city.getCountry() != null) {
				System.out.println("Country Name: " + city.getCountry().getName());
				System.out.println("Country Continent: " + city.getCountry().getContinent());
			} else {
				System.out.println("Country: Not assigned");
			}
		} else {
			System.out.println("City not found");
		}
	}

	public void updateCityAttributes(int cityId) {
		City city = em.find(City.class, cityId);

		if (city != null) {
			Scanner scanner = new Scanner(System.in);
			int choice;

			System.out.println("Select attribute to update:");
			System.out.println("1. City Name\n");
			System.out.println("2. City Population\n");
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1: {
				System.out.println("Enter new City Name:");
				String newName = scanner.nextLine();
				city.setName(newName);
				break;
			}
			case 2: {
				System.out.println("Enter new City Population:");
				int newPopulation = scanner.nextInt();
				city.setPopulation(newPopulation);
				break;
			}
			default:
				System.out.println("Invalid choice");
				break;
			}

			transaction.begin();
			em.merge(city);
			transaction.commit();
			System.out.println("City Attributes Updated");
			scanner.close();
		} else {
			System.out.println("City not found");
		}
	}

	public void removeCityCountry(int cityId) {
		City city = em.find(City.class, cityId);

		if (city != null) {
			transaction.begin();
			em.remove(city);
			transaction.commit();
			System.out.println("City removed");
		} else {
			System.out.println("City not found");
		}
	}
}