package com.isdb.teacher.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TEACHERS")
@SequenceGenerator(name = "teacher_seq", sequenceName = "TEACHER_SEQ", allocationSize = 1)
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_seq")
    private int id;

    @Column
    private String name;
    private String schoolName;
    @JsonProperty("isHeadTeacher")
    private boolean isHeadTeacher;
    private String assignedSubject;
    private double salary;
    private LocalDate joiningDate;
    @JsonProperty("isAggressive")
    private boolean isAggressive;
}

