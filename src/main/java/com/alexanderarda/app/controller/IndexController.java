package com.alexanderarda.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String showHome(){
        return "app/index";
    }

    @GetMapping("/dashboard")
    public String showDashboard(){
        return "app/index";
    }

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "/error/access-denied";
    }

}
