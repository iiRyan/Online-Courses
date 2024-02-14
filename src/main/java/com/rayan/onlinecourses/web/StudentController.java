package com.rayan.onlinecourses.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import static com.rayan.onlinecourses.constants.onlineCoursesConstants.STUDENTS_LIST;
import static com.rayan.onlinecourses.constants.onlineCoursesConstants.KEYWORD;

import com.rayan.onlinecourses.entity.Instructor;
import com.rayan.onlinecourses.entity.Student;
import com.rayan.onlinecourses.entity.User;
import com.rayan.onlinecourses.service.StudentService;
import com.rayan.onlinecourses.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;
    private UserService userService;

    private final String path = "student-view/";

    public StudentController(StudentService studentService, UserService userService) {
        this.studentService = studentService;
        this.userService = userService;
    }

    @GetMapping("/index")
    public String students(Model theModel, @RequestParam(name = KEYWORD, defaultValue = "") String keyword) {
        List<Student> studentsList = studentService.findStudentByName(keyword);
        theModel.addAttribute(STUDENTS_LIST, studentsList);
        theModel.addAttribute(KEYWORD, keyword);
        return path + "students";
    }

    @GetMapping("/delete")
    public String delete(Long studentId, String keyword) {
        studentService.removeStudent(studentId);
        return "redirect:/students/index?keyword" + keyword;
    }

    @GetMapping("/formUpdate")
    public String fromUpdate(Model theModel, Long studentId) {
        Student student = studentService.loadStudentById(studentId);
        theModel.addAttribute("student", student);

        return path + "update-form";
    }

    @PostMapping("/update")
    public String update(Student student) {

        studentService.updateStudent(student);
        return "redirect:/students/index";
    }

    @GetMapping("formCreate")
    public String formCreate(Model theModel) {
        Student student = new Student();

        theModel.addAttribute("student", student);
        return path + "create-form";
    }

    @PostMapping("/save")
    public String save(@Valid Student student, BindingResult bindingResult) {

        User user = userService.loadUserByEmail(student.getUser().getEmail());
        if (user != null)
            bindingResult.rejectValue("user.email", null, "Email already Exist");
        if (bindingResult.hasErrors())
            return path + "create-form";
        studentService.createStudent(student.getFirstName(),
                student.getLastName(),
                student.getLevel(), student.getUser().getEmail(),
                student.getUser().getPassword());
        return "redirect:/students/index";
    }

}
