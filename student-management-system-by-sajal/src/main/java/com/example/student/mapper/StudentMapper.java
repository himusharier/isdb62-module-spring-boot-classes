package com.example.student.mapper;

import com.example.student.model.dto.StudentRequestDTO;
import com.example.student.model.dto.StudentResponseDTO;
import com.example.student.model.entity.Student;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Mapper class for converting between Student entity and DTOs.
 */
@Component
public class StudentMapper {

    /**
     * Converts a StudentRequestDTO to a Student entity.
     *
     * @param dto the request DTO to convert
     * @return the converted Student entity
     */
    public Student toEntity(StudentRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setEnrollmentDate(LocalDateTime.now());
        
        return student;
    }

    /**
     * Converts a Student entity to a StudentResponseDTO.
     *
     * @param entity the Student entity to convert
     * @return the converted response DTO
     */
    public StudentResponseDTO toResponseDTO(Student entity) {
        if (entity == null) {
            return null;
        }
        
        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setEnrollmentDate(entity.getEnrollmentDate());
        
        return dto;
    }
    
    /**
     * Updates a Student entity from a StudentRequestDTO.
     * Does not modify the ID or enrollment date.
     *
     * @param dto the request DTO containing updated information
     * @param entity the entity to update
     */
    public void updateEntityFromDTO(StudentRequestDTO dto, Student entity) {
        if (dto == null || entity == null) {
            return;
        }
        
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setDateOfBirth(dto.getDateOfBirth());
    }
}

