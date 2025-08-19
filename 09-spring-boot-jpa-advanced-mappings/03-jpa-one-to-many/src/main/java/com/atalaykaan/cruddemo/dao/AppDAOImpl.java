package com.atalaykaan.cruddemo.dao;

import com.atalaykaan.cruddemo.entity.Course;
import com.atalaykaan.cruddemo.entity.Instructor;
import com.atalaykaan.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class AppDAOImpl implements AppDAO {

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {

        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int instructorId) {

        return entityManager.find(Instructor.class, instructorId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int instructorId) {

        Instructor instructor = entityManager.find(Instructor.class, instructorId);

        List<Course> courses = instructor.getCourses();

        for (Course course : courses) {

            course.setInstructor(null);
        }

        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int instructorDetailId) {
        return entityManager.find(InstructorDetail.class, instructorDetailId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int instructorDetailId) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, instructorDetailId);

        instructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int instructorId) {

        TypedQuery<Course> theQuery = entityManager.createQuery("from Course where instructor.id = :data", Course.class);

        theQuery.setParameter("data", instructorId);

        return theQuery.getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int instructorId) {

        TypedQuery<Instructor> theQuery = entityManager.createQuery(
                                                "select i from Instructor i "
                                                        + "JOIN FETCH i.courses "
                                                        + "JOIN FETCH i.instructorDetail "
                                                        + "where i.id = :data", Instructor.class);

        theQuery.setParameter("data", instructorId);

        Instructor instructor = theQuery.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {

        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {

        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int courseId) {

        Course course = entityManager.find(Course.class, courseId);

        return course;
    }

    @Override
    @Transactional
    public void deleteCourseById(int courseId) {

        Course course = entityManager.find(Course.class, courseId);

        entityManager.remove(course);
    }
}
