package com.rayan.onlinecourses.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    // @GetMapping("/login")
    // public String showLoginPage() {
    // return "login";
    // }

    // // add request mapping for / access-denied
    // @GetMapping("/access-denied")
    // public String accessDenied() {
    // return "access-denied";
    // }

    @GetMapping("/403")
    public String notAuthorized() {
        return "403";
    }

}
