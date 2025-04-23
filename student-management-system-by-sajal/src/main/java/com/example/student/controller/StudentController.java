package com.example.student.controller;

import com.example.student.model.dto.StudentRequestDTO;
import com.example.student.model.dto.StudentResponseDTO;
import com.example.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Student management operations.
 * Handles CRUD operations for Student entities.
 */
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Student Controller", description = "API for Student management")
public class StudentController {

    private final StudentService studentService;

    /**
     * Create a new student.
     *
     * @param studentDTO the student data to create
     * @return the created student with HTTP status 201 (Created)
     */
    @PostMapping
    @Operation(summary = "Create a new student", description = "Creates a new student with the provided information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created successfully",
                    content = @Content(schema = @Schema(implementation = StudentResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "Student with this email already exists")
    })
    public ResponseEntity<StudentResponseDTO> createStudent(
            @Valid @RequestBody StudentRequestDTO studentDTO) {
        
        log.info("REST request to create a new student");
        log.debug("Request data: {}", studentDTO);
        
        StudentResponseDTO createdStudent = studentService.createStudent(studentDTO);
        
        log.info("Student created successfully with ID: {}", createdStudent.getId());
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    /**
     * Get all students.
     *
     * @return list of all students with HTTP status 200 (OK)
     */
    @GetMapping
    @Operation(summary = "Get all students", description = "Retrieves a list of all students")
    @ApiResponse(responseCode = "200", description = "Students retrieved successfully",
            content = @Content(schema = @Schema(implementation = StudentResponseDTO.class)))
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        
        log.info("REST request to get all students");
        
        List<StudentResponseDTO> students = studentService.getAllStudents();
        
        log.info("Retrieved {} students", students.size());
        return ResponseEntity.ok(students);
    }

    /**
     * Get a student by ID.
     *
     * @param id the student ID
     * @return the student with the given ID with HTTP status 200 (OK)
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get student by ID", description = "Retrieves a student by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student found",
                    content = @Content(schema = @Schema(implementation = StudentResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<StudentResponseDTO> getStudentById(
            @Parameter(description = "ID of the student to retrieve", required = true)
            @PathVariable Long id) {
        
        log.info("REST request to get student with ID: {}", id);
        
        StudentResponseDTO student = studentService.getStudentById(id);
        
        log.info("Retrieved student with ID: {}", id);
        return ResponseEntity.ok(student);
    }

    /**
     * Update a student.
     *
     * @param id the student ID
     * @param studentDTO the updated student data
     * @return the updated student with HTTP status 200 (OK)
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update student", description = "Updates an existing student with the provided information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated successfully",
                    content = @Content(schema = @Schema(implementation = StudentResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "409", description = "Email already in use by another student")
    })
    public ResponseEntity<StudentResponseDTO> updateStudent(
            @Parameter(description = "ID of the student to update", required = true)
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDTO studentDTO) {
        
        log.info("REST request to update student with ID: {}", id);
        log.debug("Request data: {}", studentDTO);
        
        StudentResponseDTO updatedStudent = studentService.updateStudent(id, studentDTO);
        
        log.info("Student with ID: {} updated successfully", id);
        return ResponseEntity.ok(updatedStudent);
    }

    /**
     * Delete a student.
     *
     * @param id the student ID
     * @return empty response with HTTP status 204 (No Content)
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete student", description = "Deletes a student by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<Void> deleteStudent(
            @Parameter(description = "ID of the student to delete", required = true)
            @PathVariable Long id) {
        
        log.info("REST request to delete student with ID: {}", id);
        
        studentService.deleteStudent(id);
        
        log.info("Student with ID: {} deleted successfully", id);
        return ResponseEntity.noContent().build();
    }
}
