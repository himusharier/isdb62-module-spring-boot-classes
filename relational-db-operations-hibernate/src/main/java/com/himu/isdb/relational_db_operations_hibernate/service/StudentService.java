package com.himu.isdb.relational_db_operations_hibernate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.himu.isdb.relational_db_operations_hibernate.dto.StudentDto;
import com.himu.isdb.relational_db_operations_hibernate.model.Classroom;
import com.himu.isdb.relational_db_operations_hibernate.model.Student;
import com.himu.isdb.relational_db_operations_hibernate.repository.StudentRepository;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
	private final ClassroomService classroomService;

	public StudentService(StudentRepository studentRepository, ClassroomService classroomService) {
		this.studentRepository = studentRepository;
		this.classroomService = classroomService;
	}

	public Student saveStudent(StudentDto studentDto) {
		Integer clazzId = studentDto.getClassId();
		Classroom clazz = classroomService.getClassroom(clazzId);

		Student student = new Student();
		student.setName(studentDto.getName());
		student.setEmail(studentDto.getEmail());
		if (clazz != null) {
			student.setClazz(clazz);
		}
		student.setRoll(studentDto.getRoll());
		student.setPhone(studentDto.getPhone());
		student.setAddress(studentDto.getAddress());
		student.setGender(studentDto.getGender());
		student.setDob(studentDto.getDob());

		return studentRepository.save(student);
	}

    public Student getStudent(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Integer id, StudentDto studentDto) {
        Optional<Student> studentById = studentRepository.findById(id);

        if (studentById.isPresent()) {
            Student aStudent = new Student();
            if (studentDto.getName() != null) {
                aStudent.setName(studentDto.getName());
            }
            if (studentDto.getEmail() != null) {
                aStudent.setEmail(studentDto.getEmail());
            }
            if (studentDto.getClassId() != null) {
                Integer classId = studentDto.getClassId();
                Classroom clazz = classroomService.getClassroom(classId);
                if (clazz == null) {
                    throw new IllegalArgumentException("Class not found");
                }
                aStudent.setClazz(clazz);
            }
            if (studentDto.getRoll() != null) {
                aStudent.setRoll(studentDto.getRoll());
            }
            // if (studentDTO.getBookIds() != null) {
            // aStudent.setBooks(studentDTO.getBookIds());
            // }
            if (studentDto.getPhone() != null) {
                aStudent.setPhone(studentDto.getPhone());
            }
            if (studentDto.getAddress() != null) {
                aStudent.setAddress(studentDto.getAddress());
            }
            if (studentDto.getGender() != null) {
                aStudent.setGender(studentDto.getGender());
            }
            if (studentDto.getDob() != null) {
                aStudent.setDob(studentDto.getDob());
            }

            return studentRepository.save(aStudent);
        } else {
            throw new IllegalArgumentException("Student not found");
        }
    }
}
