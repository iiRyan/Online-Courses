package com.rayan.onlinecourses.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.rayan.onlinecourses.entity.Course;
import com.rayan.onlinecourses.entity.Instructor;
import com.rayan.onlinecourses.entity.Student;
import com.rayan.onlinecourses.entity.User;
import com.rayan.onlinecourses.service.CourseService;
import com.rayan.onlinecourses.service.InstructorService;
import com.rayan.onlinecourses.service.RoleService;
import com.rayan.onlinecourses.service.StudentService;
import com.rayan.onlinecourses.service.UserService;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private InstructorService instructorService;

    public static final String ADMIN = "Admin";
    public static final String INSTRUCTOR = "Instructor";
    public static final String STUDENT = "Student";

    @Override
    public void run(String... args) throws Exception {
        // User user1 = userService.createUser("user1@gmail.com", "pass1");
        // User user2 = userService.createUser("user2@gmail.com", "pass2");

        // roleService.createRole(ADMIN);
        // roleService.createRole(INSTRUCTOR);
        // roleService.createRole(STUDENT);

        // userService.assignRoleToUser(user1.getEmail(), ADMIN);
        // userService.assignRoleToUser(user1.getEmail(), INSTRUCTOR);
        // userService.assignRoleToUser(user2.getEmail(), STUDENT);

        // Instructor instructor1 = instructorService.createInstructor("instructor1FN",
        // "instructor1LN",
        // "Experienced Instructor", "instructorUser1@gmail.com", "pass1");
        // Instructor instructor2 = instructorService.createInstructor("instructor2FN",
        // "instructor2LN",
        // "Senior Instructor", "instructorUser2@gmail.com", "pass2");

        // Student student1 = studentService.createStudent("std1FN", "std1LN",
        // "beginner", "stdUser1@gmail.com",
        // "pass1");
        // Student student2 = studentService.createStudent("std2FN", "std2LN",
        // "master degree",
        // "stdUser2@gmail.com",
        // "pass2");

        // Course course1 = courseService.createCourse("Spring Service", "2 Hours",
        // "Master Spring Service",
        // instructor1.getInstructorId());
        // Course course2 = courseService.createCourse("Spring Data JPA", "4 Hours",
        // "Introduction to JPA",
        // instructor2.getInstructorId());

        // courseService.assignStudentToCourse(course1.getCourseId(),
        // student1.getStudentId());
        // courseService.assignStudentToCourse(course2.getCourseId(),
        // student2.getStudentId());
    }
}
