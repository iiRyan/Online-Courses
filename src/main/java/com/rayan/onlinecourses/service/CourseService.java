package com.rayan.onlinecourses.service;

import java.util.List;

import com.rayan.onlinecourses.entity.Course;

public interface CourseService {

    Course loadCourseById(Long courseId);

    Course createCourse(String courseName, String courseDuration, String courseDescription, Long instructor);

    Course createOrUpdateCourse(Course course);

    List<Course> findCoursesByCourseName(String keyWord);

    void assignStudentToCourse(Long courseId, Long studentId);

    List<Course> fetchAll();

    List<Course> fetchCourseForStudent(Long studentId);

    void removeCourse(Long courseId);
}
