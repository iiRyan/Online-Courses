package com.rayan.onlinecourses.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

import com.rayan.onlinecourses.entity.Course;

public interface CourseDao extends JpaRepository<Course, Long> {

    List<Course> findCourseByCourseNameContains(String keyword);

    // the nativeQuery enables you to write query to the Database.
    @Query(value = "SELECT * from courses AS c WHERE c.course_id in (SELECT e.course_id FROM enrolled_in AS e WHERE e.student_id =:studentId)", nativeQuery = true)
    List<Course> getCoursesByStudentId(@Param("studentId") Long studentId);
}
