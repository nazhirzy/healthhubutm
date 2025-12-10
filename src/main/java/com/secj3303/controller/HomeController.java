package com.secj3303.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.secj3303.model.Person;

@Controller
public class HomeController {
    @RequestMapping("/bmi")
    @ResponseBody()
    public String home(Model model) {
        Person person = new Person(101, "ahmad", 2001, 65, 1.7);

        model.addAttribute("person", person);

        return "index";
    }
}
