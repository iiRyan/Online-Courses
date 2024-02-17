package com.rayan.onlinecourses.web;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.rayan.onlinecourses.constants.onlineCoursesConstants.*;

import com.rayan.onlinecourses.entity.Course;
import com.rayan.onlinecourses.entity.Instructor;
import com.rayan.onlinecourses.entity.User;
import com.rayan.onlinecourses.service.CourseService;
import com.rayan.onlinecourses.service.InstructorService;
import com.rayan.onlinecourses.service.UserService;

@Controller
@RequestMapping(value = "/courses")
public class CourseController {

    private CourseService courseService;
    private InstructorService instructorService;
    private UserService userService;
    private final String path = "course-view/";

    public CourseController(CourseService courseService, InstructorService instructorService, UserService userService) {
        this.courseService = courseService;
        this.instructorService = instructorService;
        this.userService = userService;
    }

    @GetMapping("/index")
    @PreAuthorize("hasAuthority('Admin')")
    public String courses(Model theModel, @RequestParam(name = KEYWORD, defaultValue = "") String keyword) {
        List<Course> corsesList = courseService.findCoursesByCourseName(keyword);
        theModel.addAttribute(COURSES_LIST, corsesList);
        theModel.addAttribute(KEYWORD, keyword);

        return path + "courses";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('Admin')")
    public String deleteCourse(Long courseId, String keyword) {
        courseService.removeCourse(courseId);

        return "redirect:/courses/index?keyword" + keyword;
    }

    @GetMapping("/formUpdate")
    @PreAuthorize("hasAnyAuthority('Admin','Instructor')")
    public String formUpdate(Long courseId, Model theModel, Principal principal) {
        if (userService.doesCurrentUserHasRole(INSTRUCTOR)) {
            Instructor instructor = instructorService.findInstructorByEmail(principal.getName());
            theModel.addAttribute(CURRENT_INSTRUCTOR, instructor);
        }

        // Retrieve the course & associated instructors
        Course course = courseService.loadCourseById(courseId);
        List<Instructor> instructors = instructorService.fetchInstructors();
        // set course as a model attribute to pre-populate the form
        theModel.addAttribute("theCourse", course);
        theModel.addAttribute(INSTRUCTORS_LIST, instructors);

        return path + "update-form";
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('Admin','Instructor')")
    public String updateCourse(Course theCourse) {
        courseService.createOrUpdateCourse(theCourse);
        return userService.doesCurrentUserHasRole(INSTRUCTOR)
                ? "redirect:/courses/index/instructor"
                : "redirect:/courses/index";

    }

    // expose an endpoint /form-add and prepare the object
    // create the view
    // create the controller to handle the object --> save it to the database

    @GetMapping("/formCreate")
    @PreAuthorize("hasAnyAuthority('Admin','Instructor')")
    public String updateCourse(Model theModel, Principal principal) {
        if (userService.doesCurrentUserHasRole(INSTRUCTOR)) {
            Instructor instructor = instructorService.findInstructorByEmail(principal.getName());
            theModel.addAttribute(CURRENT_INSTRUCTOR, instructor);
        }
        List<Instructor> instructors = instructorService.fetchInstructors();
        theModel.addAttribute("instructorsList", instructors);
        Course theCourse = new Course();
        theModel.addAttribute("theCourse", theCourse);

        return path + "form-create";
    }

    @GetMapping("/index/student")
    @PreAuthorize("hasAuthority('Student')")
    public String coursesForCurrentStudent(Model theModel, Principal principal) {
        User user = userService.loadUserByEmail(principal.getName());
        List<Course> subscribedCourses = courseService.fetchCourseForStudent(user.getStudent().getStudentId());
        List<Course> otherCourses = courseService.fetchAll().stream()
                .filter(course -> !subscribedCourses.contains(course)).collect(Collectors.toList());
        theModel.addAttribute(COURSES_LIST, subscribedCourses);
        theModel.addAttribute("otherCourses", otherCourses);

        theModel.addAttribute(FIRST_NAME, user.getStudent().getFirstName());
        theModel.addAttribute(LAST_NAME, user.getStudent().getLastName());
        return path + "student-courses";
    }

    @GetMapping("/enrollStudent")
    public String enrollCurrentStudentInCourse(Long courseId, Principal principal) {

        User user = userService.loadUserByEmail(principal.getName());
        courseService.assignStudentToCourse(courseId, user.getStudent().getStudentId());
        return "redirect:/courses/index/student";

    }

    @GetMapping("/index/instructor")
    @PreAuthorize("hasAuthority('Instructor')")
    public String coursesForCurrentInstructor(Model theModel, Principal principal) {
        User user = userService.loadUserByEmail(principal.getName());

        Instructor instructor = instructorService.loadInstructorById(user.getInstructor().getInstructorId());
        // Get the instructor's Courses.
        theModel.addAttribute(COURSES_LIST, instructor.getCourses());
        theModel.addAttribute(FIRST_NAME, instructor.getFirstName());
        theModel.addAttribute(LAST_NAME, instructor.getLastName());
        return path + "instructor-courses";
    }

    @GetMapping("/instructor")
    @PreAuthorize("hasAuthority('Admin')")
    public String coursesByInstructorId(Model theModel, Long instructorId) {
        Instructor instructor = instructorService.loadInstructorById(instructorId);
        // Get the instructor's Courses.
        theModel.addAttribute(COURSES_LIST, instructor.getCourses());
        theModel.addAttribute(FIRST_NAME, instructor.getFirstName());
        theModel.addAttribute(LAST_NAME, instructor.getLastName());
        return path + "instructor-courses";
    }

}
