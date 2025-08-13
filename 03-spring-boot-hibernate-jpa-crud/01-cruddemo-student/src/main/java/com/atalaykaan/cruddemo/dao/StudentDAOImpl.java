package com.atalaykaan.cruddemo.dao;

import com.atalaykaan.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    private EntityManager entityManager;

    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "FROM Student order by lastName", Student.class);

        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "FROM Student WHERE lastName=:theData", Student.class);

        theQuery.setParameter("theData", theLastName);

        return theQuery.getResultList();
    }

    @Transactional
    @Override
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Student theStudent = entityManager.find(Student.class, id);

        entityManager.remove(theStudent);
    }

    @Transactional
    @Override
    public int deleteAll() {
        int numRowsDeleted =  entityManager.createQuery(
                "DELETE FROM Student").executeUpdate();

        return numRowsDeleted;
    }
}
