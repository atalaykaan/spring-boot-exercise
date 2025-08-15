package com.atalaykaan.springboot.cruddemo.dao;

import com.atalaykaan.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


//@RepositoryRestResource(path="members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
