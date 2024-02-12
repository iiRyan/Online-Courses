package com.rayan.onlinecourses.service.impl;

import com.rayan.onlinecourses.dao.CourseDao;
import com.rayan.onlinecourses.dao.InstructorDao;
import com.rayan.onlinecourses.entity.Course;
import com.rayan.onlinecourses.entity.Instructor;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseDao courseDao;

    @Mock
    private InstructorDao instructorDao;

    @InjectMocks
    private CourseServiceImpl courseService;
    @Test
    void testLoadCourseById() {
        Course course = new Course();
        course.setCourseId(1L);

        when(courseDao.findById(any())).thenReturn(Optional.of(course));

        Course actualCourse = courseService.loadCourseById(1L);

        assertEquals(course,actualCourse);
    }

    @Test
    void testExceptionForNotFoundCourseById() {
        assertThrows(EntityNotFoundException.class,()->courseService.loadCourseById(2L));
    }

    @Test
    void createCourse() {
        Instructor instructor = new Instructor();
        instructor.setInstructorId(1L);

        when(instructorDao.findById(any())).thenReturn(Optional.of(instructor));
        courseService.createCourse("JPA","1h 30min","Hello JPA", 1L);

        verify(courseDao).save(any());
    }

    @Test
    void createOrUpdateCourse() {
    }

    @Test
    void findCoursesByCourseName() {
    }

    @Test
    void assignStudentToCourse() {
    }

    @Test
    void fetchAll() {
    }

    @Test
    void fetchCourseForStudent() {
    }

    @Test
    void removeCourse() {
    }
}