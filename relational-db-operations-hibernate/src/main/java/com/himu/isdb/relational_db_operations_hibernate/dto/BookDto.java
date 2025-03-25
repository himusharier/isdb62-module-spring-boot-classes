package com.himu.isdb.relational_db_operations_hibernate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String name;
    private String author;
    private String publisher;
	private Integer clazzId;
}
