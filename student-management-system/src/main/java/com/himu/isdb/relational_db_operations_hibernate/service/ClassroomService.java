package com.himu.isdb.relational_db_operations_hibernate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.himu.isdb.relational_db_operations_hibernate.dao.ClassTeacherDao;
import com.himu.isdb.relational_db_operations_hibernate.dto.ClassroomDto;
import com.himu.isdb.relational_db_operations_hibernate.model.Classroom;
import com.himu.isdb.relational_db_operations_hibernate.model.Teacher;
import com.himu.isdb.relational_db_operations_hibernate.repository.ClassroomRepository;

@Service
public class ClassroomService {
	private final ClassroomRepository classroomRepository;
	private final TeacherService teacherService;

	public ClassroomService(ClassroomRepository classroomRepository, TeacherService teacherService) {
		this.classroomRepository = classroomRepository;
		this.teacherService = teacherService;
	}

	public Classroom saveClassroom(ClassroomDto classroomDto) {
		Integer teacherId = classroomDto.getClassTeacherId();
		Teacher teacher = teacherService.getTeacher(teacherId);
		if (teacher == null) {
			throw new IllegalArgumentException("Teacher not found");
		}
		Classroom classroom = new Classroom();
		classroom.setName(classroomDto.getName());
		classroom.setRoomNumber(classroomDto.getRoomNumber());
		classroom.setClassTeacher(teacher);

		return classroomRepository.save(classroom);
	}

	public Classroom getClassroom(Integer id) {
		return classroomRepository.findById(id).orElse(null);
	}

	public void deleteClassroom(Integer id) {
		classroomRepository.deleteById(id);
	}

	public List<Classroom> getAllClassroom() {
		return classroomRepository.findAll();
	}

	public Classroom updateClassroom(Integer id, ClassroomDto classroomDto) {
		Optional<Classroom> classroomById = classroomRepository.findById(id);

		if (classroomById.isPresent()) {
			Classroom classroom = new Classroom();

			if (classroomDto.getName() != null) {
				classroom.setName(classroomDto.getName());
			}
			if (classroomDto.getRoomNumber() != null) {
				classroom.setRoomNumber(classroomDto.getRoomNumber());
			}
			if (classroomDto.getClassTeacherId() != null) {
				Integer teacherId = classroomDto.getClassTeacherId();
				Teacher teacher = teacherService.getTeacher(teacherId);
				if (teacher == null) {
					throw new IllegalArgumentException("Teacher not found");
				}
				classroom.setClassTeacher(teacher);
			}
			return classroomRepository.save(classroom);

		} else {
			throw new IllegalArgumentException("Class not found");
		}
	}

	public List<ClassTeacherDao> getAllClassTeacher() {
		// TODO Auto-generated method stub
		return null;
	}

}
