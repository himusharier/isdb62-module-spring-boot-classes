package com.himu.isdb.relational_db_operations_hibernate.dto;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private String name;
    private String email;
    private Integer classId;
    private Integer roll;
    private List<Integer> bookIds;
    private String phone;
    private String address;
    private String gender;
    private Instant dob;
}
