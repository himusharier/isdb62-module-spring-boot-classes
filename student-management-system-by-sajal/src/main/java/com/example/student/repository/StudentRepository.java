package com.example.student.repository;

import com.example.student.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for managing Student entities.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    /**
     * Checks if a student exists with the given email.
     *
     * @param email the email to check
     * @return true if a student with the given email exists, false otherwise
     */
    boolean existsByEmail(String email);
    
    /**
     * Finds a student by email.
     *
     * @param email the email to search for
     * @return an Optional containing the student if found, empty otherwise
     */
    Optional<Student> findByEmail(String email);
}

