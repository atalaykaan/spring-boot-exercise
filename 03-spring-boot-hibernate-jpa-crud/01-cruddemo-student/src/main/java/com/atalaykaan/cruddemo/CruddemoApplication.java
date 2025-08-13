package com.atalaykaan.cruddemo;

import com.atalaykaan.cruddemo.dao.StudentDAO;
import com.atalaykaan.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
            //createStudent(studentDAO);

            createMultipleStudents(studentDAO);

            //readStudent(studentDAO);

            //queryForStudents(studentDAO);

            //queryForStudentsByLastName(studentDAO);

            //updateStudent(studentDAO);

            //removeStudent(studentDAO);

            //removeAllStudents(studentDAO);
        };
    }

    private void removeAllStudents(StudentDAO studentDAO) {
        System.out.println("Deleting all students...");
        int numRowsDeleted = studentDAO.deleteAll();
        System.out.println("Deleted row count: " + numRowsDeleted);
    }

    private void removeStudent(StudentDAO studentDAO) {
        int studentId = 5;

        System.out.println("Deleting student with id: " + studentId);

        studentDAO.delete(5);
    }

    private void updateStudent(StudentDAO studentDAO) {
        int studentId = 5;
        System.out.println("Finding student with id: " + studentId);

        Student myStudent = studentDAO.findById(studentId);

        System.out.println("Updating student...");

        myStudent.setFirstName("NotDaffy");
        studentDAO.update(myStudent);

        System.out.println("Updated student: " + myStudent);
    }

    private void queryForStudentsByLastName(StudentDAO studentDAO) {
        List<Student> theStudents = studentDAO.findByLastName("Duck");

        for(Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }

    private void queryForStudents(StudentDAO studentDAO) {
        List<Student> theStudents = studentDAO.findAll();

        for(Student tempStudent: theStudents) {
            System.out.println(tempStudent);
        }
    }

    private void readStudent(StudentDAO studentDAO) {

        //create new student
        System.out.println("Creating new student...");
        Student tempStudent = new Student("Daffy", "Duck", "daffyduck@gmail.com");

        //save the student
        System.out.println("Saving student...");
        studentDAO.save(tempStudent);

        int theId = tempStudent.getId();
        System.out.println("Saved the student generated id=" + theId);

        //find the student by id
        System.out.println("Finding student with id: " + theId);
        System.out.println(studentDAO.findById(theId));

    }

    private void createMultipleStudents(StudentDAO studentDAO) {

        System.out.println("Creating 3 students...");
        Student tempStudent1 = new Student("Ali", "Yilmaz", "aliyilmaz@gmail.com");
        Student tempStudent2 = new Student("Mehmet", "Yildirim", "mehmetyildirim@gmail.com");
        Student tempStudent3 = new Student("John", "Doe", "johndoe@gmail.com");

        System.out.println("Saving the students...");
        studentDAO.save(tempStudent1);
        studentDAO.save(tempStudent2);
        studentDAO.save(tempStudent3);

    }

    private void createStudent(StudentDAO studentDAO) {

        System.out.println("Creating new student...");
        Student tempStudent = new Student("Kaan", "Atalay", "kaanefeatalay@gmail.com");

        System.out.println("Saving student...");
        studentDAO.save(tempStudent);

        System.out.println("Saved student. Generated id: " + tempStudent.getId());
    }
}
