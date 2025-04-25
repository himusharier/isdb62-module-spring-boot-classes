package com.isdb.teacher.controller;

import com.isdb.teacher.model.Teacher;
import com.isdb.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
@CrossOrigin(origins = "*")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // Create or Update Teacher
    @PostMapping
    public Teacher saveTeacher(@RequestBody Teacher teacher) {
        if (!teacher.getName().isEmpty()
                && !teacher.getSchoolName().isEmpty()
                && !teacher.getAssignedSubject().isEmpty())
            return teacherService.saveTeacher(teacher);
        else
            return null;
    }

    // Get All Teachers
    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    // Get Teacher by ID
    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable int id) {
        return teacherService.getTeacherById(id);
    }

    // Delete Teacher by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTeacher(@PathVariable int id) {
        teacherService.deleteTeacher(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Teacher with ID " + id + " deleted successfully!");
        return ResponseEntity.ok(response); // Returns JSON instead of plain text
    }


    @PostMapping("/bulk")
    public List<Teacher> saveTeachers(@RequestBody List<Teacher> teachers) {
        return teacherService.saveTeachers(teachers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable int id, @RequestBody Teacher updatedTeacher) {
        Optional<Teacher> teacher = teacherService.findById(id);
        if (teacher.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Teacher t = teacher.get();

        t.setName(updatedTeacher.getName());
        t.setSchoolName(updatedTeacher.getSchoolName());
        t.setHeadTeacher(updatedTeacher.isHeadTeacher());
        t.setAssignedSubject(updatedTeacher.getAssignedSubject());
        t.setSalary(updatedTeacher.getSalary());
        t.setJoiningDate(updatedTeacher.getJoiningDate());
        t.setAggressive(updatedTeacher.isAggressive());

        Teacher savedTeacher = teacherService.saveTeacher(t);
        return ResponseEntity.ok(savedTeacher);
    }
}
