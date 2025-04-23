package com.example.student.service;

import com.example.student.model.dto.StudentRequestDTO;
import com.example.student.model.dto.StudentResponseDTO;

import java.util.List;

/**
 * Service interface for Student CRUD operations.
 */
public interface StudentService {
    
    /**
     * Create a new student.
     *
     * @param studentDTO the DTO containing student information
     * @return the created student response DTO
     */
    StudentResponseDTO createStudent(StudentRequestDTO studentDTO);
    
    /**
     * Get all students.
     *
     * @return list of all students
     */
    List<StudentResponseDTO> getAllStudents();
    
    /**
     * Get a student by ID.
     *
     * @param id the student ID
     * @return the student response DTO
     */
    StudentResponseDTO getStudentById(Long id);
    
    /**
     * Update an existing student.
     *
     * @param id the student ID
     * @param studentDTO the DTO containing updated student information
     * @return the updated student response DTO
     */
    StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentDTO);
    
    /**
     * Delete a student by ID.
     *
     * @param id the student ID
     */
    void deleteStudent(Long id);
}
