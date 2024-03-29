package com.rayan.onlinecourses.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rayan.onlinecourses.dao.InstructorDao;
import com.rayan.onlinecourses.entity.Course;
import com.rayan.onlinecourses.entity.Instructor;
import com.rayan.onlinecourses.entity.User;
import com.rayan.onlinecourses.service.CourseService;
import com.rayan.onlinecourses.service.InstructorService;
import com.rayan.onlinecourses.service.UserService;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {

    private InstructorDao instructorDao;
    private CourseService courseService;
    private UserService userService;

    public InstructorServiceImpl(InstructorDao instructorDao, CourseService courseService, UserService userService) {
        this.instructorDao = instructorDao;
        this.courseService = courseService;
        this.userService = userService;
    }

    @Override
    public Instructor loadInstructorById(Long instructorId) {
        return instructorDao.findById(instructorId)
                .orElseThrow(() -> new EntityNotFoundException("Instructor with Id - " + instructorId + " Not found"));
    }

    @Override
    public List<Instructor> findInstructorByName(String name) {
        return instructorDao.findInstructorByName(name);
    }

    @Override
    public Instructor findInstructorByEmail(String email) {
        return instructorDao.findInstructorByEmail(email);
    }

    @Override
    public Instructor createInstructor(String firstName, String lastName, String summary, String email,
            String password) {

        User user = userService.createUser(email, password);
        userService.assignRoleToUser(email, "Instructor");
        return instructorDao.save(new Instructor(firstName, lastName, summary, user));
    }

    @Override
    public Instructor updateInstructor(Instructor instructor) {
        return instructorDao.save(instructor);
    }

    @Override
    public List<Instructor> fetchInstructors() {
        return instructorDao.findAll();
    }

    @Override
    public void removeInstructor(Long instructorId) {
        // You have to delete the courses associated with instructor first.
        Instructor instructor = loadInstructorById(instructorId);
        for (Course course : instructor.getCourses()) {
            courseService.removeCourse(course.getCourseId());
        }
        instructorDao.deleteById(instructorId);
    }

}
