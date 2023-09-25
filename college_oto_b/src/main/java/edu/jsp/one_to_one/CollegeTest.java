package edu.jsp.one_to_one;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CollegeTest {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("company");
    EntityManager manager = factory.createEntityManager();
    EntityTransaction transaction = manager.getTransaction();

    public static void main(String[] args) {
        CollegeTest test = new CollegeTest();

        College college=new College("NRI", "BHOPAL", 2001);
        Principal principal=new Principal("Harshwardhan", 70);
        
        test.insert(college, principal);
        
        test.display(1);
    }

    public void insert(College college, Principal principal) {
        college.setPrincipal(principal);
        principal.setCollege(college);
        transaction.begin();
        manager.merge(college);
        transaction.commit();
        System.out.println("Data inserted");
    }

    public void remove(int key) {

        College college = manager.find(College.class, key);

        transaction.begin();
        manager.remove(college);
        transaction.commit();

        System.out.println("Record removed");
    }

    public void update(int key) {

        Scanner scanner = new Scanner(System.in);

        College college = manager.find(College.class, key);

        System.out.println("Enter what to update\n"
                + "1. College name\n"
                + "2. College location\n"
                + "3. College established year\n"
                + "4. Principal details\n");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: {
                if (college != null) {
                    System.out.println("Enter new Name\n");
                    String name = scanner.nextLine();
                    college.setName(name);
                    transaction.begin();
                    manager.merge(college);
                    transaction.commit();
                    System.out.println("Updated");
                } else {
                    System.out.println("Record not found");
                }
                break;
            }
            case 2: {
                if (college != null) {
                    System.out.println("Enter new Location\n");
                    String location = scanner.nextLine();
                    college.setLocation(location);
                    transaction.begin();
                    manager.merge(college);
                    transaction.commit();
                    System.out.println("Updated");
                } else {
                    System.out.println("Record not found");
                }
                break;
            }
            case 3: {
                if (college != null) {
                    System.out.println("Enter new Established Year\n");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    college.setEstablishedYear(year);
                    transaction.begin();
                    manager.merge(college);
                    transaction.commit();
                    System.out.println("Updated");
                } else {
                    System.out.println("Record not found");
                }
                break;
            }
            case 4: {
                Principal principal = college.getPrincipal();
                if (principal != null) {
                    System.out.println("Enter new Principal Name\n");
                    String name = scanner.nextLine();
                    principal.setName(name);
                    System.out.println("Enter new Principal Age\n");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    principal.setAge(age);
                    transaction.begin();
                    manager.merge(principal);
                    transaction.commit();
                    System.out.println("Updated");
                } else {
                    System.out.println("Record not found");
                }
                break;
            }
            default: {
                System.out.println("Invalid choice");
            }
        }
        scanner.close();
    }

    public void display(int key) {
        College college = manager.find(College.class, key);

        if (college != null) {
            System.out.println("College id: " + college.getId());
            System.out.println("College name: " + college.getName());
            System.out.println("College location: " + college.getLocation());
            System.out.println("College established year: " + college.getEstablishedYear());
            System.out.println("==============================");
            Principal principal = college.getPrincipal();
            System.out.println("Principal id: " + principal.getId());
            System.out.println("Principal name: " + principal.getName());
            System.out.println("Principal Age: " + principal.getAge());
        } else {
            System.out.println("Record not found");
        }
    }
}