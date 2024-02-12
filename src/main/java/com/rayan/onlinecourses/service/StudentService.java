package com.rayan.onlinecourses.service;

import java.util.List;

import com.rayan.onlinecourses.entity.Student;

public interface StudentService {

    Student loadStudentById(Long studentId);

    List<Student> findStudentByName(String name);

    Student loadStudentByEmail(String email);

    Student createStudent(String firstNName, String lastName, String level, String email, String password);

    Student updateStudent(Student student);

    List<Student> fetchStudents();

    void removeStudent(Long studentId);
}
