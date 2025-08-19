package com.atalaykaan.cruddemo.dao;

import com.atalaykaan.cruddemo.entity.Course;
import com.atalaykaan.cruddemo.entity.Instructor;
import com.atalaykaan.cruddemo.entity.InstructorDetail;
import com.atalaykaan.cruddemo.entity.Student;
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

    @Override
    @Transactional
    public void save(Course course) {

        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int courseId) {

        TypedQuery<Course> theQuery = entityManager.createQuery("select c from Course c "
                                        + "JOIN FETCH c.reviews "
                                        + "where c.id = :data", Course.class);

        theQuery.setParameter("data", courseId);

        return theQuery.getSingleResult();
    }

    @Override
    public Course findCourseAndStudentByCourseId(int courseId) {

        TypedQuery<Course> theQuery = entityManager.createQuery("select c from Course c "
                                                    + "JOIN FETCH c.students "
                                                    + "where c.id = :data", Course.class);

        theQuery.setParameter("data", courseId);

        return theQuery.getSingleResult();
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int studentId) {

        TypedQuery<Student> theQuery = entityManager.createQuery("select s from Student s "
                                                    + "JOIN FETCH s.courses "
                                                    + "where s.id = :data", Student.class);

        theQuery.setParameter("data", studentId);

        return theQuery.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Student student) {

        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int studentId) {

        Student tempStudent = entityManager.find(Student.class, studentId);

        if(tempStudent != null) {

            List<Course> courses = tempStudent.getCourses();

            for (Course course : courses) {
                course.getStudents().remove(tempStudent);
            }

            entityManager.remove(tempStudent);
        }
    }
}
