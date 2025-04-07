package com.himu.isdb.relational_db_operations_hibernate.dto;

public class ClassTeacherDto {
	 private String className;
     private String teacherName;
 
     public ClassTeacherDto(String className, String teacherName) {
         this.className = className;
         this.teacherName = teacherName;
     }
}
