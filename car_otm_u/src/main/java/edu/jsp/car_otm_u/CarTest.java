package edu.jsp.car_otm_u;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CarTest {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("company");
    EntityManager manager = factory.createEntityManager();
    EntityTransaction transaction = manager.getTransaction();

    public static void main(String[] args) {
        CarTest test = new CarTest();
//      Car car = new Car("Camry", "Toyota");
//      List<Variant> variants = new ArrayList<Variant>();
//      Variant variant1 = new Variant("LE", 25000);
//      Variant variant2 = new Variant("XLE", 30000);
//
//      variants.add(variant1);
//      variants.add(variant2);
//
//      test.insert(car, variants);
//
      test.display(1);
//
//      test.removeCar(1);
    }

    public void insert(Car car, List<Variant> variants) {

        car.setVariants(variants);

        transaction.begin();
        manager.persist(car);
        transaction.commit();
        System.out.println("Data inserted");
    }

    public void display(int carId) {
        Car car = manager.find(Car.class, carId);

        if (car != null) {
            System.out.println("Car Name: " + car.getName());
            System.out.println("Car Manufacturer: " + car.getManufacturer());
            System.out.println("Variants:");

            for (Variant variant : car.getVariants()) {
                System.out.println("Variant ID: " + variant.getId());
                System.out.println("Variant Name: " + variant.getName());
                System.out.println("Variant Price: " + variant.getPrice());
                System.out.println("----------------------");
            }
        } else {
            System.out.println("Car not found");
        }
    }

    public void removeCar(int carId) {
        Car car = manager.find(Car.class, carId);

        if (car != null) {
            transaction.begin();
            manager.remove(car);
            transaction.commit();
            System.out.println("Car removed");
        } else {
            System.out.println("Car not found");
        }
    }

    public void updateCar(int carId) {
        Car car = manager.find(Car.class, carId);

        if (car != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select attribute to update:");
            System.out.println("1. Car Name\n");
            System.out.println("2. Car Manufacturer\n");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: {
                    System.out.println("Enter new Car Name:");
                    String newMake = scanner.nextLine();
                    car.setName(newMake);
                    break;
                }
                case 2: {
                    System.out.println("Enter new Car Manufacturer:");
                    String newModel = scanner.nextLine();
                    car.setManufacturer(newModel);
                    break;
                }
                default: {
                    System.out.println("Invalid choice");
                    scanner.close();
                    return;
                }
            }

            transaction.begin();
            manager.merge(car);
            transaction.commit();
            System.out.println("Car Updated");
            scanner.close();
        } else {
            System.out.println("Car not found");
        }
    }
}
