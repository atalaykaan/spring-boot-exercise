package com.atalaykaan.cruddemo.dao;

import com.atalaykaan.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student theStudent);

    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findByLastName(String name);

    void update(Student theStudent);

    void delete(Integer id);

    int deleteAll();
}
