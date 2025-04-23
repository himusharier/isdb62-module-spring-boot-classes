package com.example.student.service.impl;

import com.example.student.exception.DuplicateResourceException;
import com.example.student.exception.ResourceNotFoundException;
import com.example.student.model.dto.StudentRequestDTO;
import com.example.student.model.dto.StudentResponseDTO;
import com.example.student.model.entity.Student;
import com.example.student.repository.StudentRepository;
import com.example.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the StudentService interface.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public StudentResponseDTO createStudent(StudentRequestDTO studentDTO) {
        log.debug("Creating new student with email: {}", studentDTO.getEmail());
        
        // Check if email is already in use
        if (studentRepository.existsByEmail(studentDTO.getEmail())) {
            log.error("Email already in use: {}", studentDTO.getEmail());
            throw new DuplicateResourceException("Email already in use: " + studentDTO.getEmail());
        }
        
        // Convert DTO to entity
        Student student = mapToEntity(studentDTO);
        
        // Save student to database
        Student savedStudent = studentRepository.save(student);
        log.info("Student created successfully with ID: {}", savedStudent.getId());
        
        // Convert entity to response DTO
        return mapToResponseDTO(savedStudent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<StudentResponseDTO> getAllStudents() {
        log.debug("Retrieving all students");
        
        List<Student> students = studentRepository.findAll();
        log.info("Retrieved {} students", students.size());
        
        return students.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public StudentResponseDTO getStudentById(Long id) {
        log.debug("Retrieving student with ID: {}", id);
        
        Student student = findStudentById(id);
        log.info("Retrieved student with ID: {}", id);
        
        return mapToResponseDTO(student);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentDTO) {
        log.debug("Updating student with ID: {}", id);
        
        // Find student by ID
        Student existingStudent = findStudentById(id);
        
        // Check if email is already in use by another student
        if (!existingStudent.getEmail().equals(studentDTO.getEmail()) && 
            studentRepository.existsByEmail(studentDTO.getEmail())) {
            log.error("Email already in use by another student: {}", studentDTO.getEmail());
            throw new DuplicateResourceException("Email already in use: " + studentDTO.getEmail());
        }
        
        // Update student fields
        existingStudent.setFirstName(studentDTO.getFirstName());
        existingStudent.setLastName(studentDTO.getLastName());
        existingStudent.setEmail(studentDTO.getEmail());
        existingStudent.setDateOfBirth(studentDTO.getDateOfBirth());
        
        // Save updated student
        Student updatedStudent = studentRepository.save(existingStudent);
        log.info("Student updated successfully with ID: {}", updatedStudent.getId());
        
        return mapToResponseDTO(updatedStudent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteStudent(Long id) {
        log.debug("Deleting student with ID: {}", id);
        
        // Find student by ID
        Student student = findStudentById(id);
        
        // Delete student
        studentRepository.delete(student);
        log.info("Student deleted successfully with ID: {}", id);
    }
    
    /**
     * Helper method to find a student by ID and throw an exception if not found.
     *
     * @param id the ID of the student to find
     * @return the student with the given ID
     * @throws ResourceNotFoundException if no student is found with the given ID
     */
    private Student findStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Student not found with ID: {}", id);
                    return new ResourceNotFoundException("Student", "id", id);
                });
    }
    
    /**
     * Maps a StudentRequestDTO to a Student entity.
     *
     * @param studentDTO the DTO to map
     * @return the mapped entity
     */
    private Student mapToEntity(StudentRequestDTO studentDTO) {
        Student student = new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setEnrollmentDate(LocalDateTime.now());
        return student;
    }
    
    /**
     * Maps a Student entity to a StudentResponseDTO.
     *
     * @param student the entity to map
     * @return the mapped DTO
     */
    private StudentResponseDTO mapToResponseDTO(Student student) {
        StudentResponseDTO responseDTO = new StudentResponseDTO();
        responseDTO.setId(student.getId());
        responseDTO.setFirstName(student.getFirstName());
        responseDTO.setLastName(student.getLastName());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setDateOfBirth(student.getDateOfBirth());
        responseDTO.setEnrollmentDate(student.getEnrollmentDate());
        return responseDTO;
    }
}

