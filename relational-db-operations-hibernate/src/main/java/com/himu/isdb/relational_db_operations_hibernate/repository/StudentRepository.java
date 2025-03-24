package com.himu.isdb.relational_db_operations_hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.himu.isdb.relational_db_operations_hibernate.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    
}
