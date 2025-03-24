package com.himu.isdb.relational_db_operations_hibernate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.himu.isdb.relational_db_operations_hibernate.model.Student;
import com.himu.isdb.relational_db_operations_hibernate.repository.StudentRepository;

@Service
public class StudentService {
    private final StudentRepository repository;

	public StudentService(StudentRepository repository) {
		this.repository = repository;
	}

	public Student saveStudent(Student student) {
		if (student != null)
			return repository.save(student);
		else
			return null;
	}

	public List<Student> getStudents() {
		return repository.findAll();
	}

	public void deleteById(int id) {
		repository.deleteById(id);
	}

	public Optional<Student> findStudentById(int id) {
		return repository.findById(id);
	}

	public List<Student> getStudentsByName(String name) {
//		return repository.findAllByName(name);
		return null;
	}
}
