package com.himu.isdb.relational_db_operations_hibernate.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.himu.isdb.relational_db_operations_hibernate.dto.StudentDto;
import com.himu.isdb.relational_db_operations_hibernate.model.Student;
import com.himu.isdb.relational_db_operations_hibernate.service.StudentService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/student")
@Tag(name = "Student Controller", description = "API for student management")
public class StudentController {
	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping
	public ResponseEntity<Student> saveStudent(StudentDto studentDto) {
		Student saved = studentService.saveStudent(studentDto);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable Integer id) {
		Student student = studentService.getStudent(id);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
		studentService.deleteStudent(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping
	public ResponseEntity<List<Student>> getAllStudent() {
		List<Student> students = studentService.getAllStudent();
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @RequestBody StudentDto studentDto) {
		Student updated = studentService.updateStudent(id, studentDto);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}
}
