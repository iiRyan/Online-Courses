package com.rayan.onlinecourses.web;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rayan.onlinecourses.entity.Course;
import com.rayan.onlinecourses.entity.Instructor;
import com.rayan.onlinecourses.service.CourseService;
import com.rayan.onlinecourses.service.InstructorService;

@Controller
@RequestMapping(value = "/courses")
public class CourseController {

    private CourseService courseService;
    private InstructorService instructorService;

    private final String path = "course-view/";

    public CourseController(CourseService courseService, InstructorService instructorService) {
        this.courseService = courseService;
        this.instructorService = instructorService;
    }

    /* Controllers */

    @GetMapping("/index")
    public String courses(Model theModel, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        List<Course> listCourses = courseService.findCoursesByCourseName(keyword);
        theModel.addAttribute("listCourses", listCourses);
        theModel.addAttribute("keyword", keyword);

        return path + "courses";
    }

    @GetMapping("/delete")
    public String deleteCourse(Long courseId, String keyword) {
        courseService.removeCourse(courseId);

        return "redirect:/courses/index?keyword" + keyword;
    }

    @GetMapping("/formUpdate")
    public String formUpdate(Long courseId, Model theModel) {
        // Retrieve the course & associated instructors
        Course course = courseService.loadCourseById(courseId);
        List<Instructor> instructors = instructorService.fetchInstructors();
        // set course as a model attribute to pre-populate the form
        theModel.addAttribute("theCourse", course);
        theModel.addAttribute("listInstructors", instructors);

        return path + "update-form";
    }

    @PostMapping("/save")
    public String updateCourse(Course theCourse) {
        courseService.createOrUpdateCourse(theCourse);
        return "redirect:/courses/index";
    }

    // expose an endpoint /form-add and prepare the object
    // create the view
    // create the controller to handle the object --> save it to the database

    @GetMapping("/formCreate")
    public String updateCourse(Model theModel) {

        List<Instructor> instructors = instructorService.fetchInstructors();
        theModel.addAttribute("listInstructors", instructors);
        Course theCourse = new Course();
        theModel.addAttribute("theCourse", theCourse);

        return path + "form-create";
    }

    @GetMapping("/index/student")
    public String coursesForCurrentStudent(Model theModel) {
        Long studentId = 4L;
        List<Course> subscribedCourses = courseService.fetchCourseForStudent(studentId);
        List<Course> otherCourses = courseService.fetchAll().stream()
                .filter(course -> !subscribedCourses.contains(course)).collect(Collectors.toList());
        theModel.addAttribute("listCourses", subscribedCourses);
        theModel.addAttribute("otherCourses", otherCourses);

        return path + "/student-courses";
    }

    @GetMapping("/enrollStudent")
    public String enrollCurrentStudentInCourse(Long courseId) {
        Long studentId = 4L;
        courseService.assignStudentToCourse(courseId, studentId);
        return "redirect:/courses/index/student";

    }
}
