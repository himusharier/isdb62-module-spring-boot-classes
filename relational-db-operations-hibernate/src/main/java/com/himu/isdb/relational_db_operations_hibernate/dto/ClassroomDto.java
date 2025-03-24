package com.himu.isdb.relational_db_operations_hibernate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomDto {
    private String name;
    private Integer ClassTeacherId;
    private Integer roomNumber;
}
