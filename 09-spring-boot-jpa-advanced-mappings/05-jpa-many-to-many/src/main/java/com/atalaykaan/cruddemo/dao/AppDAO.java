package com.atalaykaan.cruddemo.dao;

import com.atalaykaan.cruddemo.entity.Course;
import com.atalaykaan.cruddemo.entity.Instructor;
import com.atalaykaan.cruddemo.entity.InstructorDetail;
import com.atalaykaan.cruddemo.entity.Student;

import java.util.List;

public interface AppDAO {

    void save(Instructor instructor);

    Instructor findInstructorById(int instructorId);

    void deleteInstructorById(int instructorID);

    InstructorDetail findInstructorDetailById(int instructorDetailId);

    void deleteInstructorDetailById(int instructorDetailId);

    List<Course> findCoursesByInstructorId(int instructorId);

    Instructor findInstructorByIdJoinFetch(int instructorId);

    void update(Instructor instructor);

    void update(Course course);

    Course findCourseById(int courseId);

    void deleteCourseById(int courseId);

    void save(Course course);

    Course findCourseAndReviewsByCourseId(int courseId);

    Course findCourseAndStudentByCourseId(int courseId);

    Student findStudentAndCoursesByStudentId(int studentId);

    void update(Student student);

    void deleteStudentById(int studentId);
}
