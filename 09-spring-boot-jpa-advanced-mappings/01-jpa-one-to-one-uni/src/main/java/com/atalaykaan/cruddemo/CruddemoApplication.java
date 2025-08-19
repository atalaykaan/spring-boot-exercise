package com.atalaykaan.cruddemo;

import com.atalaykaan.cruddemo.dao.AppDAO;
import com.atalaykaan.cruddemo.entity.Instructor;
import com.atalaykaan.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
         return runner -> {

             //createInstructor(appDAO);

             //findInstructor(appDAO);

             //deleteInstructor(appDAO);

        };
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

        System.out.println("Creating new instrcutor...");
        Instructor instructor = new Instructor("Ege", "Atalay", "egeatalay@gmail.com");

        System.out.println("Creating new instructor detail...");
        InstructorDetail instructorDetail = new InstructorDetail("www.unity.com", "Unity");

        instructor.setInstructorDetail(instructorDetail);

        System.out.println("Saving instructor...");
        appDAO.save(instructor);

        System.out.println("Done!");
    }

}
