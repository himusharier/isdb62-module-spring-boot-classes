package com.himu.isdb.relational_db_operations_hibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.himu.isdb.relational_db_operations_hibernate.dao.ClassTeacherDao;
import com.himu.isdb.relational_db_operations_hibernate.dao.ClassTeacherProjection;
import com.himu.isdb.relational_db_operations_hibernate.dao.ClassTeacherRecord;
import com.himu.isdb.relational_db_operations_hibernate.model.Classroom;

// JPA specification
// How to call store procedure

public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {

	@Query("SELECT sc.name AS className, sc.classTeacher.name AS teacherName FROM Classroom sc")
	List<ClassTeacherProjection> getAllClassTeacher();

	@Query("SELECT new com.himu.isdb.relational_db_operations_hibernate.dao.ClassTeacherDao(sc.name, sc.classTeacher.name) FROM Classroom sc")
	List<ClassTeacherDao> fetchAllClassTeacherDaos();

	@Query("SELECT new com.himu.isdb.relational_db_operations_hibernate.dao.ClassTeacherRecord(sc.name, sc.classTeacher.name) FROM Classroom sc")
	List<ClassTeacherRecord> fetchAllClassTeacherRecords();

}
