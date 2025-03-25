package com.himu.isdb.relational_db_operations_hibernate.dto;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomDto {
    @NonNull
    @Size(min = 3, max = 30, message = "name must be between 3 to 30 chatacters")
    private String name;

    @NonNull
    private Integer ClassTeacherId;

    @NonNull
    private Integer roomNumber;
}
