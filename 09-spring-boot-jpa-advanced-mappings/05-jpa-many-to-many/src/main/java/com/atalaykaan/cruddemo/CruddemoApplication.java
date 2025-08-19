package com.atalaykaan.cruddemo;

import com.atalaykaan.cruddemo.dao.AppDAO;
import com.atalaykaan.cruddemo.entity.*;
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
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
         return runner -> {

             //createCourseWithStudents(appDAO);

             //findCourseAndStudents(appDAO);

             //findStudentsAndCourses(appDAO);

             //addMoreCoursesForStudent(appDAO);

             //deleteCourse(appDAO);

             deleteStudent(appDAO);
        };
    }

    private void deleteStudent(AppDAO appDAO) {

        int studentId = 1;

        System.out.println("Deleting student id: " + studentId);

        appDAO.deleteStudentById(studentId);

        System.out.println("Done!");
    }

    private void addMoreCoursesForStudent(AppDAO appDAO) {

        int studentId = 1;

        Student tempStudent = appDAO.findStudentAndCoursesByStudentId(studentId);

        Course tempCourse1 = new Course("Spring Boot Course");
        Course tempCourse2 = new Course("Piano Course");

        tempStudent.addCourse(tempCourse1);
        tempStudent.addCourse(tempCourse2);

        System.out.println("Updating student: " + tempStudent);
        System.out.println("asociated courses: " + tempStudent.getCourses());

        appDAO.update(tempStudent);

        System.out.println("Done!");

    }

    private void findStudentsAndCourses(AppDAO appDAO) {

        int studentId = 1;

        Student tempStudent = appDAO.findStudentAndCoursesByStudentId(studentId);

        System.out.println("tempStudent: " + tempStudent);

        System.out.println("associated courses: " + tempStudent.getCourses());

        System.out.println("Done!");
    }

    private void findCourseAndStudents(AppDAO appDAO) {

        int courseId = 10;

        Course tempCourse = appDAO.findCourseAndStudentByCourseId(courseId);

        System.out.println("tempCourse: " + tempCourse);

        System.out.println("associated students: " + tempCourse.getStudents());

        System.out.println("Done!");
    }

    private void createCourseWithStudents(AppDAO appDAO) {

        Course tempCourse = new Course("Violin Course");

        Student tempStudent1 = new Student("John", "Doe", "johndoe@gmail.com");
        Student tempStudent2 = new Student("Susan", "Public", "susanpublic@gmail.com");

        tempCourse.addStudent(tempStudent1);
        tempCourse.addStudent(tempStudent2);

        System.out.println("Saving the course: " + tempCourse);
        System.out.println("associated students: " + tempCourse.getStudents());

        appDAO.save(tempCourse);

        System.out.println("Done!");
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {

        int courseId = 10;

        System.out.println("Deleting course id: " + courseId);

        appDAO.deleteCourseById(courseId);

        System.out.println("Done!");

    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {

        int courseId = 10;
        Course tempCourse = appDAO.findCourseAndReviewsByCourseId(courseId);

        System.out.println(tempCourse);

        System.out.println(tempCourse.getReviews());

        System.out.println("Done!");
    }

    private void createCourseAndReviews(AppDAO appDAO) {

        Course tempCourse = new Course("Spring Boot course");

        tempCourse.addReview(new Review("Really good course."));
        tempCourse.addReview(new Review("I loved it!"));
        tempCourse.addReview(new Review("This course sucks!"));

        System.out.println("Saving the course...");
        System.out.println("tempCourse: " + tempCourse);
        System.out.println("Reviews: " + tempCourse.getReviews());

        appDAO.save(tempCourse);

        System.out.println("Done!");
    }

    private void deleteCourse(AppDAO appDAO) {

        int courseId = 10;

        System.out.println("Finding course id " + courseId);

        appDAO.deleteCourseById(courseId);

        System.out.println("Done!");
    }

    private void updateCourse(AppDAO appDAO) {

        int courseId = 10;

        System.out.println("Finding course id " + courseId);

        Course tempCourse = appDAO.findCourseById(courseId);

        System.out.println("Updating course...");

        tempCourse.setTitle("Piano");

        appDAO.update(tempCourse);

        System.out.println("Done!");
    }

    private void updateInstructor(AppDAO appDAO) {

        int instructorId = 1;

        System.out.println("Finding instructor id " + instructorId);

        Instructor tempInstructor = appDAO.findInstructorById(instructorId);

        System.out.println("Updating instructor...");

        tempInstructor.setLastName("TESTER");

        appDAO.update(tempInstructor);

        System.out.println("Done!");
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

        int instructorId = 1;

        System.out.println("Finding instructor with id " + instructorId);

        Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(instructorId);

        System.out.println("tempInstructor: " + tempInstructor);

        System.out.println("the associated courses: " + tempInstructor.getCourses());

        System.out.println("Done!");
    }

    private void findCoursesForInstructor(AppDAO appDAO) {

        int instructorId = 1;

        System.out.println("Finding instructor id " + instructorId);

        Instructor tempInstructor = appDAO.findInstructorById(instructorId);

        System.out.println("tempInstructor: " + tempInstructor);

        System.out.println("Finding courses for instructor id " + instructorId);

        List<Course> courses = appDAO.findCoursesByInstructorId(instructorId);

        tempInstructor.setCourses(courses);

        System.out.println("the associated courses: " + tempInstructor.getCourses());

        System.out.println("Done!");
    }

    private void findInstructorWithCourses(AppDAO appDAO) {

        int instructorId = 1;

        System.out.println("Finding instructor with id " + instructorId);
        Instructor tempInstructor = appDAO.findInstructorById(instructorId);

        System.out.println("tempInstructor: " + tempInstructor);

        System.out.println("Courses: " + tempInstructor.getCourses());

        System.out.println("Done!");

    }

    private void createInstructorWithCourses(AppDAO appDAO) {

        System.out.println("Creating new instructor...");
        Instructor instructor = new Instructor("Kaan", "Atalay", "kaanatalay@gmail.com");

        System.out.println("Creating new instructor detail...");
        InstructorDetail instructorDetail = new InstructorDetail("www.youtube.com", "Coding");

        instructor.setInstructorDetail(instructorDetail);

        Course tempCourse1 = new Course("Guitar");
        Course tempCourse2 = new Course("The pinball masterclass");

        instructor.add(tempCourse1);
        instructor.add(tempCourse2);

        System.out.println("Saving instructor...");
        System.out.println("Courses: " + instructor.getCourses());

        appDAO.save(instructor);

        System.out.println("Done!");

    }

    private void deleteInstructorDetail(AppDAO appDAO) {

        int instructorDetailId = 3;

        System.out.println("Deleting instructor detail id " + instructorDetailId);

        appDAO.deleteInstructorDetailById(instructorDetailId);

        System.out.println("Done!");
    }

    private void findInstructorDetail(AppDAO appDAO) {

        int instructorDetailId = 2;

        System.out.println("Finding instructor detail id " + instructorDetailId);

        InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(instructorDetailId);

        System.out.println("tempInstructorId: " + tempInstructorDetail);

        System.out.println("Instructor associated with instructor detail: " + tempInstructorDetail.getInstructor());
    }

    private void deleteInstructor(AppDAO appDAO) {

        int instructorId = 1;

        System.out.println("Deleting instructor id " + instructorId);

        appDAO.deleteInstructorById(instructorId);

        System.out.println("Deleted instructor id " + instructorId);
    }

    private void findInstructor(AppDAO appDAO) {

        int instructorId = 1;


        System.out.println("Finding instructor id: " + instructorId);
        Instructor tempInstructor = appDAO.findInstructorById(instructorId);
        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("The associated instructor detail only: " + tempInstructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {

/*
        System.out.println("Creating new instrcutor...");
        Instructor instructor = new Instructor("Kaan", "Atalay", "kaanefeatalay@gmail.com");

        System.out.println("Creating new instructor detail...");
        InstructorDetail instructorDetail = new InstructorDetail("www.youtube.com", "Coding");
*/

        System.out.println("Creating new instructor...");
        Instructor instructor = new Instructor("Ege", "Atalay", "egeatalay@gmail.com");

        System.out.println("Creating new instructor detail...");
        InstructorDetail instructorDetail = new InstructorDetail("www.unity.com", "Unity");

        instructor.setInstructorDetail(instructorDetail);

        System.out.println("Saving instructor...");
        appDAO.save(instructor);

        System.out.println("Done!");
    }

}
