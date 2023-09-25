package edu.jsp.artist_mtm_u;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class ArtistExhibitionTest {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("company");
	EntityManager em = emf.createEntityManager();
	EntityTransaction transaction = em.getTransaction();

	public static void main(String[] args) {
		ArtistExhibitionTest test = new ArtistExhibitionTest();

		Artist artist1 = new Artist("Leonardo da Vinci", "Italian");
		Artist artist2 = new Artist("Vincent van Gogh", "Dutch");

		Exhibition exhibition1 = new Exhibition("Mona Lisa Exhibition", "Louvre Museum", "2023-08-20", "2023-09-20");
		Exhibition exhibition2 = new Exhibition("Starry Night Exhibition", "Museum of Modern Art", "2023-09-01",
				"2023-10-01");

		test.insert(artist2, exhibition2);

//		test.displayArtistExhibitions(artist1.getId());
//
//		test.updateArtistNationality(artist2.getId());
//
//		test.removeArtistWithExhibitions(artist1.getId());

		test.em.close();
		test.emf.close();
	}

	public void insert(Artist artist1, Exhibition exhibition1) {
		artist1.getExhibitions().add(exhibition1);
		exhibition1.getArtists().add(artist1);

		transaction.begin();
		em.persist(artist1);
		transaction.commit();
		System.out.println("Artists and Exhibitions inserted");
	}

	public void displayArtistExhibitions(int artistId) {
		Artist artist = em.find(Artist.class, artistId);

		if (artist != null) {
			List<Exhibition> exhibitions = artist.getExhibitions();
			System.out.println("Exhibitions participated by " + artist.getName() + ":");
			for (Exhibition exhibition : exhibitions) {
				System.out.println("Exhibition: " + exhibition.getName() + ", Location: " + exhibition.getLocation());
			}
		} else {
			System.out.println("Artist not found");
		}
	}

	public void updateArtistNationality(int artistId) {
		Artist artist = em.find(Artist.class, artistId);

		if (artist != null) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter new nationality for " + artist.getName() + ":");
			String newNationality = scanner.nextLine();

			transaction.begin();
			artist.setNationality(newNationality);
			transaction.commit();
			System.out.println("Artist nationality updated");
			scanner.close();
		} else {
			System.out.println("Artist not found");
		}
	}

	public void removeArtistWithExhibitions(int artistId) {
		Artist artist = em.find(Artist.class, artistId);

		if (artist != null) {
			transaction.begin();
			em.remove(artist);
			transaction.commit();
			System.out.println("Artist and their exhibitions removed");
		} else {
			System.out.println("Artist not found");
		}
	}
}