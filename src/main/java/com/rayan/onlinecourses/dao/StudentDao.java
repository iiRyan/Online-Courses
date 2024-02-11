package com.rayan.onlinecourses.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rayan.onlinecourses.entity.Student;

public interface StudentDao extends JpaRepository<Student, Long> {

    @Query(value = "SELECT s FROM Student as s WHERE s.firstName like %:name% OR s.lastName like %:name%")
    List<Student> findStudentByName(@Param("name") String name);

    @Query(value = "SELECT s FROM Student as s WHERE s.user.email =:email")
    Student findInstructorByEmail(@Param("email") String email);
}
