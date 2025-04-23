package com.example.student;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Student Management System.
 * Starting point for the Spring Boot application.
 */
@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Student Management System API",
        version = "1.0",
        description = "REST API for managing student information"
    )
)
public class StudentManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementSystemApplication.class, args);
    }
}

