package com.himu.isdb.relational_db_operations_hibernate.service;

import java.util.List;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudent'");
    }

    public void deleteStudent(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteStudent'");
    }

    public List<Student> getAllStudent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllStudent'");
    }

    public Student updateStudent(Integer id, StudentDto studentDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStudent'");
    }
}
