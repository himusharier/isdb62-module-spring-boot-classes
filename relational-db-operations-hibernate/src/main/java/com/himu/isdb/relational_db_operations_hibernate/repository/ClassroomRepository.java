package com.himu.isdb.relational_db_operations_hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.himu.isdb.relational_db_operations_hibernate.model.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {
    
}
