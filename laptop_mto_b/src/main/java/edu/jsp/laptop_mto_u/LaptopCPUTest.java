package edu.jsp.laptop_mto_u;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class LaptopCPUTest {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("company");
	EntityManager em = emf.createEntityManager();
	EntityTransaction transaction = em.getTransaction();

	public static void main(String[] args) {
		LaptopCPUTest test = new LaptopCPUTest();

		CPU cpu1 = new CPU("Intel", "i7", 8, 3.5);
		Laptop laptop1 = new Laptop("Dell", "XPS 15", 1500.0, cpu1);

		List<Laptop> laptops = new ArrayList<Laptop>();
		laptops.add(laptop1);
		test.insertLaptopCPU(laptop1, cpu1, laptops);
//        test.displayLaptopCPU(1);
//        test.updateLaptopPrice(1, 1600.0);
//        test.displayLaptopCPU(1);
//        test.removeLaptopCPU(1);
//        test.displayLaptopCPU(1);

		test.em.close();
		test.emf.close();
	}

	public void insertLaptopCPU(Laptop laptop, CPU cpu, List<Laptop> laptops) {

		laptop.setCpu(cpu);

		for (Laptop laptop2 : laptops) {
			laptop2.setCpu(cpu);
		}
		transaction.begin();
		em.persist(cpu);
		em.persist(laptop);
		transaction.commit();
		System.out.println("Laptop and CPU Data inserted");
	}

	public void displayLaptopCPU(int laptopId) {
		Laptop laptop = em.find(Laptop.class, laptopId);

		if (laptop != null) {
			System.out.println("Laptop Brand: " + laptop.getBrand());
			System.out.println("Laptop Model: " + laptop.getModel());
			System.out.println("Laptop Price: " + laptop.getPrice());
			System.out.println("CPU Brand: " + laptop.getCpu().getBrand());
			System.out.println("CPU Model: " + laptop.getCpu().getModel());
			System.out.println("CPU Cores: " + laptop.getCpu().getCores());
			System.out.println("CPU Clock Speed: " + laptop.getCpu().getClockSpeed());
		} else {
			System.out.println("Laptop not found");
		}
	}

	public void updateLaptopCPU(int laptopId) {
		Laptop laptop = em.find(Laptop.class, laptopId);

		if (laptop != null) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Select attribute to update:");
			System.out.println("1. Laptop Brand\n");
			System.out.println("2. Laptop Model\n");
			System.out.println("3. Laptop Price\n");
			System.out.println("4. CPU Brand\n");
			System.out.println("5. CPU Model\n");
			System.out.println("6. CPU Cores\n");
			System.out.println("7. CPU Clock Speed\n");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1: {
				System.out.println("Enter new Laptop Brand:");
				String newBrand = scanner.nextLine();
				laptop.setBrand(newBrand);
				break;
			}
			case 2: {
				System.out.println("Enter new Laptop Model:");
				String newModel = scanner.nextLine();
				laptop.setModel(newModel);
				break;
			}
			case 3: {
				System.out.println("Enter new Laptop Price:");
				double newPrice = scanner.nextDouble();
				laptop.setPrice(newPrice);
				break;
			}
			case 4: {
				System.out.println("Enter new CPU Brand:");
				String newCpuBrand = scanner.nextLine();
				laptop.getCpu().setBrand(newCpuBrand);
				break;
			}
			case 5: {
				System.out.println("Enter new CPU Model:");
				String newCpuModel = scanner.nextLine();
				laptop.getCpu().setModel(newCpuModel);
				break;
			}
			case 6: {
				System.out.println("Enter new CPU Cores:");
				int newCores = scanner.nextInt();
				laptop.getCpu().setCores(newCores);
				break;
			}
			case 7: {
				System.out.println("Enter new CPU Clock Speed:");
				double newClockSpeed = scanner.nextDouble();
				laptop.getCpu().setClockSpeed(newClockSpeed);
				break;
			}
			default: {
				System.out.println("Invalid choice");
				scanner.close();
				return;
			}
			}

			transaction.begin();
			em.merge(laptop);
			transaction.commit();
			System.out.println("Laptop and CPU Updated");
			scanner.close();
		} else {
			System.out.println("Laptop not found");
		}
	}

	public void removeLaptopCPU(int laptopId) {
		Laptop laptop = em.find(Laptop.class, laptopId);

		if (laptop != null) {
			transaction.begin();
			em.remove(laptop);
			em.remove(laptop.getCpu());
			transaction.commit();
			System.out.println("Laptop and CPU removed");
		} else {
			System.out.println("Laptop not found");
		}
	}
}