package com.himu.isdb.relational_db_operations_hibernate.dao;

import lombok.Getter;

@Getter
public class ClassTeacherDao {
	private String className;
	private String teacherName;

	public ClassTeacherDao(String className, String teacherName) {
		this.className = className;
		this.teacherName = teacherName;
	}
}
