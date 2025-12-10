package com.secj3303.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
// import org.springframework.security.access.prepost.PreAuthorize;

import com.secj3303.model.Person;

@Controller
public class AdminController {

    // @PreAuthorize("hasAnyRole('ADMIN','STUDENT','COUNCELLOR')")
    @GetMapping("/viewprofile")
    public ModelAndView method1() {
        ModelAndView mv = new ModelAndView("view-profile.th");
        Person person = new Person(1, "ahmad", 2000, 66, 1.66);
        mv.addObject("person", person);
        return mv;
    }

}
