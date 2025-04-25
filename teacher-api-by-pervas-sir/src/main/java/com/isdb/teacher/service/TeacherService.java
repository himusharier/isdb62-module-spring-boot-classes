package com.isdb.teacher.service;

import com.isdb.teacher.model.Teacher;
import com.isdb.teacher.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepo teacherRepository;

    // Create or Update Teacher
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    // Get All Teachers
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    // Get Teacher by ID
    public Teacher getTeacherById(int id) {
        return teacherRepository.findById(id).orElse(null);
    }

    // Delete Teacher by ID
    public void deleteTeacher(int id) {
        teacherRepository.deleteById(id);
    }

    public List<Teacher> saveTeachers(List<Teacher> teachers) {
        return teacherRepository.saveAll(teachers);
    }

    public Optional<Teacher> findById(int id) {
        return teacherRepository.findById(id);
    }
}

