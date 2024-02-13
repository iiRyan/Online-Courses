package com.rayan.onlinecourses.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rayan.onlinecourses.entity.Course;
import com.rayan.onlinecourses.service.CourseService;

@Controller
@RequestMapping(value = "/courses")
public class CourseController {

    CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /* Controllers */

    @GetMapping("/index")
    public String courses(Model theModel, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        List<Course> listCourses = courseService.findCoursesByCourseName(keyword);
        System.out.println("LIST OF COUSRES SIZE ====> " + listCourses.size());
        theModel.addAttribute("listCourses", listCourses);
        theModel.addAttribute("keyword", keyword);

        return "course-view/courses";

    }
}
