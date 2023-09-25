package edu.jsp.university_otm_u;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class UniversityTest {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("company");
    EntityManager manager = factory.createEntityManager();
    EntityTransaction transaction = manager.getTransaction();

    public static void main(String[] args) {
        UniversityTest test = new UniversityTest();

//       University university=new University("DBATU", "LONERE");
//       
//       List<College> colleges=new ArrayList<College>();
//       College college=new College("AGPIT", "SOLAPUR");
//       College college1=new College("NK ORCHID", "BALE");
//       colleges.add(college);
//       colleges.add(college1);
//       
//       test.insertUniversity(university, colleges);
        
        test.displayUniversity(1);
        test.displayUniversity(1);
    }

    public void insertUniversity(University university, List<College> colleges) {
        university.setColleges(colleges);

        transaction.begin();
        manager.persist(university);
        transaction.commit();
        System.out.println("University Data inserted");
    }

    public void displayUniversity(int universityId) {
        University university = manager.find(University.class, universityId);

        if (university != null) {
            System.out.println("University Name: " + university.getName());
            System.out.println("University Location: " + university.getLocation());
            System.out.println("Colleges:");

            for (College college : university.getColleges()) {
                System.out.println("College ID: " + college.getId());
                System.out.println("College Name: " + college.getName());
                System.out.println("College Location: " + college.getLocation());
                System.out.println("----------------------");
            }
        } else {
            System.out.println("University not found");
        }
    }

    public void removeUniversity(int universityId) {
        University university = manager.find(University.class, universityId);

        if (university != null) {
            transaction.begin();
            manager.remove(university);
            transaction.commit();
            System.out.println("University removed");
        } else {
            System.out.println("University not found");
        }
    }

    public void updateUniversity(int universityId) {
        University university = manager.find(University.class, universityId);

        if (university != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select attribute to update:");
            System.out.println("1. University Name\n");
            System.out.println("2. University Location\n");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: {
                    System.out.println("Enter new University Name:");
                    String newName = scanner.nextLine();
                    university.setName(newName);
                    break;
                }
                case 2: {
                    System.out.println("Enter new University Location:");
                    String newLocation = scanner.nextLine();
                    university.setLocation(newLocation);
                    break;
                }
                default: {
                    System.out.println("Invalid choice");
                    scanner.close();
                    return;
                }
            }

            transaction.begin();
            manager.merge(university);
            transaction.commit();
            System.out.println("University Updated");
            scanner.close();
        } else {
            System.out.println("University not found");
        }
    }
}
