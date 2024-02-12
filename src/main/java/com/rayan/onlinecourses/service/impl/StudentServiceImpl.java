package com.rayan.onlinecourses.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rayan.onlinecourses.dao.StudentDao;
import com.rayan.onlinecourses.entity.Course;
import com.rayan.onlinecourses.entity.Student;
import com.rayan.onlinecourses.entity.User;
import com.rayan.onlinecourses.service.StudentService;
import com.rayan.onlinecourses.service.UserService;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;
    private UserService userService;

    public StudentServiceImpl(StudentDao studentDao, UserService userService) {
        this.studentDao = studentDao;
        this.userService = userService;
    }

    @Override
    public Student loadStudentById(Long studentId) {
        return studentDao.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student with Id - " + studentId + " Not Found "));
    }

    @Override
    public List<Student> findStudentByName(String name) {
        return studentDao.findStudentByName(name);
    }

    @Override
    public Student loadStudentByEmail(String email) {
        return studentDao.findStudentByEmail(email);
    }

    @Override
    public Student createStudent(String firstNName, String lastName, String level, String email, String password) {
        User user = userService.createUser(email, password);
        userService.assignRoleToUser(email, "Student");
        return studentDao.save(new Student(firstNName, lastName, level, user));
    }

    @Override
    public Student updateStudent(Student student) {
        return studentDao.save(student);
    }

    @Override
    public List<Student> fetchStudents() {
        return studentDao.findAll();
    }

    @Override
    public void removeStudent(Long studentId) {
        Student student = loadStudentById(studentId);
        // First Remove courses that the student enroll in.
        Iterator<Course> iterator = student.getCourses().iterator();
        if (iterator.hasNext()) {
            Course course = iterator.next();
            course.removeStudentFromCourse(student);
        }
        studentDao.deleteById(studentId);
    }

}
