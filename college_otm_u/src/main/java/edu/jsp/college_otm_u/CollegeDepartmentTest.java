package edu.jsp.college_otm_u;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CollegeDepartmentTest {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("company");
    EntityManager manager = factory.createEntityManager();
    EntityTransaction transaction = manager.getTransaction();

    public static void main(String[] args) {
        CollegeDepartmentTest test = new CollegeDepartmentTest();
                
//        College college = new College("ABC College", "123 University Avenue");
//    	List<Department> departments= new ArrayList<Department>();
//
//        Department department1 = new Department("Computer Science", "CS Department");
//        Department department2 = new Department("Electrical Engineering", "EE Department");
//
//        departments.add(department1);
//        departments.add(department2);
//        
//        test.insert(college, departments);
        
//        test.display(1);
        
    }

    public void insert(College college,List<Department> departments ) {

        college.setDepartments(departments);

        transaction.begin();
        manager.persist(college);
        transaction.commit();
        System.out.println("Data inserted");
    }

    public void display(int collegeId) {
        College college = manager.find(College.class, collegeId);

        if (college != null) {
            System.out.println("College Name: " + college.getName());
            System.out.println("College Location: " + college.getLocation());
            System.out.println("Departments:");

            for (Department department : college.getDepartments()) {
                System.out.println("Department ID: " + department.getId());
                System.out.println("Department Name: " + department.getName());
                System.out.println("Department HeadOfDepartment: " + department.getHeadOfDepartment());
                System.out.println("----------------------");
            }
        } else {
            System.out.println("College not found");
        }
    }

    public void removeCollege(int collegeId) {
        College college = manager.find(College.class, collegeId);

        if (college != null) {
            transaction.begin();
            manager.remove(college);
            transaction.commit();
            System.out.println("College removed");
        } else {
            System.out.println("College not found");
        }
    }

    public void updateCollege(int collegeId) {
        College college = manager.find(College.class, collegeId);

        if (college != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select attribute to update:");
            System.out.println("1. College Name\n");
            System.out.println("2. College Location\n");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: {
                    System.out.println("Enter new College Name:");
                    String newName = scanner.nextLine();
                    college.setName(newName);
                    break;
                }
                case 2: {
                    System.out.println("Enter new College Location:");
                    String newAddress = scanner.nextLine();
                    college.setLocation(newAddress);
                    break;
                }
                default: {
                    System.out.println("Invalid choice");
                    scanner.close();
                    return;
                }
            }

            transaction.begin();
            manager.merge(college);
            transaction.commit();
            System.out.println("College Updated");
            scanner.close();
        } else {
            System.out.println("College not found");
        }
    }
}
