package edu.jsp.student_mtm_u;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StudentSubjectTest {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("company");
	EntityManager em = emf.createEntityManager();
	EntityTransaction transaction = em.getTransaction();

	public static void main(String[] args) {
		StudentSubjectTest test = new StudentSubjectTest();

		Student student1 = new Student("John", "Doe", 20);
		Student student2 = new Student("Jane", "Smith", 22);

		Subject mathSubject = new Subject("Mathematics", "MATH101");
		Subject scienceSubject = new Subject("Science", "SCI201");

		test.insert(student2,  scienceSubject,mathSubject);

        test.displayStudentSubjects(2);
//        test.updateStudentAge(student2.getId(), 23);
//        test.removeStudentWithSubjects(student1.getId());

		test.em.close();
		test.emf.close();
	}

	public void insert(Student student1,  Subject...subjects) {
		
		for (Subject subject : subjects) {
			student1.getSubjects().add(subject);
			subject.getStudents().add(student1);
		}
		
		transaction.begin();
		em.merge(student1);
		transaction.commit();
		System.out.println("Student and Subjects inserted");
	}

	public void displayStudentSubjects(int studentId) {
		Student student = em.find(Student.class, studentId);

		if (student != null) {
			List<Subject> subjects = student.getSubjects();
			System.out.println("Subjects taken by " + student.getFirstName() + " " + student.getLastName() + ":");
			for (Subject subject : subjects) {
				System.out.println("Subject: " + subject.getName() + ", Code: " + subject.getCode());
			}
		} else {
			System.out.println("Student not found");
		}
	}

	public void updateStudentAndSubject(int studentId) {
		Student student = em.find(Student.class, studentId);

		if (student != null) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Select attribute to update for Student:");
			System.out.println("1. First Name");
			System.out.println("2. Last Name");
			System.out.println("3. Age");
			int studentChoice = scanner.nextInt();
			scanner.nextLine();

			switch (studentChoice) {
			case 1: {
				System.out.println("Enter new First Name:");
				String newFirstName = scanner.nextLine();
				student.setFirstName(newFirstName);
				break;
			}
			case 2: {
				System.out.println("Enter new Last Name:");
				String newLastName = scanner.nextLine();
				student.setLastName(newLastName);
				break;
			}
			case 3: {
				System.out.println("Enter new Age:");
				int newAge = scanner.nextInt();
				student.setAge(newAge);
				break;
			}
			default: {
				System.out.println("Invalid choice for Student attributes");
				scanner.close();
				return;
			}
			}

			System.out.println("Select attribute to update for Subject:");
			System.out.println("1. Subject Name");
			System.out.println("2. Subject Code");
			int subjectChoice = scanner.nextInt();
			scanner.nextLine();

			Subject subject = student.getSubjects().get(0); // Assuming only one subject for simplicity

			switch (subjectChoice) {
			case 1: {
				System.out.println("Enter new Subject Name:");
				String newSubjectName = scanner.nextLine();
				subject.setName(newSubjectName);
				break;
			}
			case 2: {
				System.out.println("Enter new Subject Code:");
				String newSubjectCode = scanner.nextLine();
				subject.setCode(newSubjectCode);
				break;
			}
			default: {
				System.out.println("Invalid choice for Subject attributes");
				scanner.close();
				return;
			}
			}

			transaction.begin();
			em.merge(student);
			transaction.commit();
			System.out.println("Student and Subject attributes updated");
			scanner.close();
		} else {
			System.out.println("Student not found");
		}
	}

	public void removeStudentWithSubjects(int studentId) {
		Student student = em.find(Student.class, studentId);

		if (student != null) {
			transaction.begin();
			em.remove(student);
			transaction.commit();
			System.out.println("Student and their subjects removed");
		} else {
			System.out.println("Student not found");
		}
	}
}