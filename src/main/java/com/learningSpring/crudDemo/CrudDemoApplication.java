package com.learningSpring.crudDemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.learningSpring.crudDemo.dao.StudentDAO;
import com.learningSpring.crudDemo.entity.Student;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);

	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			// CREATE student

			// createStudent(studentDAO);
			createMultipleStudent(studentDAO);

			// READ Student
			// readStudent(studentDAO);

			// READ by QUERY Student
			// queryForStudents(studentDAO);

			// READ by QUERY Student by lastName
			// queryForStudentsByLastName(studentDAO);

			// UPDATE Student
			// updateStudent(studentDAO);

			// DELETE Student
			// deleteStudent(studentDAO);
			// deleteAllStudent(studentDAO);

		};
	}

	private void deleteAllStudent(StudentDAO studentDAO) {
		// delete all students
		System.out.println("deleting all students...");

		int numRowDeleted = studentDAO.deleteAll();
		System.out.println("number of rows deleted: " + numRowDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		// delete student based on the id: primary key
		int studentId = 3;
		System.out.println("deleting student with id: " + studentId);
		studentDAO.deleteById(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		// retrieve student based on the id: primary key
		int studentId = 1;
		System.out.println("getting student with id: " + studentId);
		Student myStudent = studentDAO.findById(studentId);

		// change first name to "Updated"
		System.out.println("updating student...");
		myStudent.setFirstName("Duy");

		// update the Student
		studentDAO.update(myStudent);

		// display the Student
		System.out.println("Updated student: " + myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		// get a list of students
		List<Student> students = studentDAO.findByLastName("V");

		// display the list of students
		for (Student student : students) {
			System.out.println(student);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		// get a list of students
		List<Student> students = studentDAO.findAll();

		// display the list of students
		for (Student student : students) {
			System.out.println(student);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		// create a student object
		System.out.println("Creating new student object...");
		Student student = new Student("New", "Vo", "newVo@gmail.com");

		// save the student
		System.out.println("Saving the student...");
		studentDAO.save(student);

		// display id of the saved student
		int theId = student.getId();
		System.out.println("Saved student. Generated id: " + theId);

		// retrieve student based on the id: primary key
		System.out.println("\nRetriving student with id: " + theId);
		Student myStudent = studentDAO.findById(theId);

		// display the student
		System.out.println("Found the student: " + myStudent);
	}

	private void createMultipleStudent(StudentDAO studentDAO) {
		// create multiple students
		System.out.println("Creating 3 student objects...");
		Student student1 = new Student("ngan", "Vo", "nvo@gmail.com");
		Student student2 = new Student("suri", "Vo", "svo@gmail.com");
		Student student3 = new Student("hello", "Vo", "hvo@gmail.com");

		// save multiple students
		System.out.println("Saving the students...");
		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);

	}

	private void createStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating new student object...");
		Student student = new Student("Duy", "Vo", "dvo@gmail.com");

		// save the student object
		System.out.println("Saving the student...");
		studentDAO.save(student);

		// display the student id
		System.out.println("Saved student. Generated id: " + student.getId());
	}
}
