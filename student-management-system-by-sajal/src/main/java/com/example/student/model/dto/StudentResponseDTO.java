package com.example.student.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for student responses.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private LocalDateTime enrollmentDate;
}

