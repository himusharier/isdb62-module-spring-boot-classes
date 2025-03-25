package com.himu.isdb.relational_db_operations_hibernate.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.himu.isdb.relational_db_operations_hibernate.dto.ClassroomDto;
import com.himu.isdb.relational_db_operations_hibernate.model.Classroom;
import com.himu.isdb.relational_db_operations_hibernate.service.ClassroomService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/class")
public class ClassroomController {
    private final ClassroomService classroomService;
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PostMapping
    public Classroom saveClassroom(@RequestBody ClassroomDto classroomDto) {
        Classroom  saved = classroomService.saveClassroom(classroomDto);
        return saved;
    }

    @GetMapping("/{id}")
    public Classroom getClassroom(@PathVariable Integer id) {
        Classroom byId = classroomService.getClassroom(id);
        return byId;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClassroom(@PathVariable Integer id) {
        classroomService.deleteClassroom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public List<Classroom> getAllClassrooms() {
        return classroomService.getAllClassroom();
    }

    @PutMapping("/{id}")
    public Classroom updateClassroom(@PathVariable Integer id, @RequestBody ClassroomDto classroomDto) {
        Classroom updated = classroomService.updateClassroom(id, classroomDto);
        return updated;
    }
    
}
