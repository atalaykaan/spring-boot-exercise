package com.atalaykaan.cruddemo.dao;

import com.atalaykaan.cruddemo.entity.Instructor;
import com.atalaykaan.cruddemo.entity.InstructorDetail;

public interface AppDAO {

    void save(Instructor instructor);

    Instructor findInstructorById(int instructorId);

    void deleteInstructorById(int instructorID);
}
