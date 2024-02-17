package com.rayan.onlinecourses.web;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import com.rayan.onlinecourses.entity.Instructor;
import com.rayan.onlinecourses.entity.User;
import com.rayan.onlinecourses.service.InstructorService;
import com.rayan.onlinecourses.service.UserService;
import static com.rayan.onlinecourses.constants.onlineCoursesConstants.INSTRUCTORS_LIST;
import static com.rayan.onlinecourses.constants.onlineCoursesConstants.KEYWORD;

@Controller
@RequestMapping("/instructors")
public class InstructorController {

    private InstructorService instructorService;
    private UserService userService;
    private final String path = "instructor-view/";

    public InstructorController(InstructorService instructorService, UserService userService) {
        this.instructorService = instructorService;
        this.userService = userService;

    }

    @GetMapping("/index")
    @PreAuthorize("hasAuthority('Admin')")
    public String instructors(@RequestParam(name = KEYWORD, defaultValue = "") String keyword, Model theModel) {

        List<Instructor> instructorsList = instructorService.findInstructorByName(keyword);
        theModel.addAttribute(INSTRUCTORS_LIST, instructorsList);
        theModel.addAttribute(KEYWORD, keyword);

        return path + "instructors";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('Admin')")
    public String deleteInstructor(Long instructorId, String keyword) {
        instructorService.removeInstructor(instructorId);
        return "redirect:/instructors/index?keyword" + keyword;
    }

    @GetMapping("/formUpdate")
    @PreAuthorize("hasAuthority('Instructor')")
    public String updateInstructor(Model theModel, Long instructorId, Principal principal) {
        Instructor theInstructor = instructorService.findInstructorByEmail(principal.getName());
        theModel.addAttribute("theInstructor", theInstructor);
        return path + "update-form";
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('Instructor')")
    public String saveUpdate(Instructor theInstructor) {
        instructorService.updateInstructor(theInstructor);
        return "redirect:/courses/index/instructor";
    }

    @GetMapping("/formCreate")
    @PreAuthorize("hasAuthority('Admin')")
    public String createForm(Model theModel) {

        theModel.addAttribute("instructor", new Instructor());

        return path + "create-form";
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('Admin')")
    public String save(@Valid Instructor theInstructor, BindingResult bindingResult) {
        // I had weird problem , When i use theInstructor in the view
        // the validation doesn't work! unless i change it into Instructor!

        User user = userService.loadUserByEmail(theInstructor.getUser().getEmail());
        if (user != null)
            bindingResult.rejectValue("user.email", null, "Email already Exist");
        if (bindingResult.hasErrors())
            return path + "create-form";
        instructorService.createInstructor(theInstructor.getFirstName(),
                theInstructor.getLastName(),
                theInstructor.getSummary(), theInstructor.getUser().getEmail(),
                theInstructor.getUser().getPassword());
        return "redirect:/instructors/index";
    }

}
