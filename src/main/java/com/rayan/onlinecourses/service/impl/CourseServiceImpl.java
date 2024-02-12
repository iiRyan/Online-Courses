package com.rayan.onlinecourses.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rayan.onlinecourses.dao.CourseDao;
import com.rayan.onlinecourses.dao.InstructorDao;
import com.rayan.onlinecourses.dao.StudentDao;
import com.rayan.onlinecourses.entity.Course;
import com.rayan.onlinecourses.entity.Instructor;
import com.rayan.onlinecourses.entity.Student;
import com.rayan.onlinecourses.service.CourseService;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao;
    private InstructorDao instructorDao;
    private StudentDao studentDao;

    public CourseServiceImpl(CourseDao courseDao, InstructorDao instructorDao, StudentDao studentDao) {
        this.courseDao = courseDao;
        this.instructorDao = instructorDao;
        this.studentDao = studentDao;
    }

    @Override
    public Course loadCourseById(Long courseId) {
        Course course = courseDao.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course with Id - " + courseId + " Not found"));

        return course;
    }

    @Override
    public Course createCourse(String courseName, String courseDuration, String courseDescription, Long instructorId) {
        Instructor instructor = instructorDao.findById(instructorId)
                .orElseThrow(() -> new EntityNotFoundException("Instructor with Id - " + instructorId + " Not found"));

        return courseDao.save(new Course(courseName, courseDuration, courseDescription, instructor));
    }

    @Override
    public Course createOrUpdateCourse(Course course) {
        return courseDao.save(course);
    }

    @Override
    public List<Course> findCoursesByCourseName(String keyWord) {

        return courseDao.findCourseByCourseNameContains(keyWord);
    }

    @Override
    public void assignStudentToCourse(Long courseId, Long studentId) {

        Student student = studentDao.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student with Id - " + studentId + " Not found"));
        Course course = courseDao.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course with Id - " + courseId + " Not found"));

        course.assignStudentToCourse(student);
    }

    @Override
    public List<Course> fetchAll() {
        return courseDao.findAll();
    }

    @Override
    public List<Course> fetchCourseForStudent(Long studentId) {
        return courseDao.getCoursesByStudentId(studentId);
    }

    @Override
    public void removeCourse(Long courseId) {
        courseDao.deleteById(courseId);
    }

}
