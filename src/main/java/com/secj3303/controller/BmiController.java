package com.secj3303.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.secj3303.model.Person;

@Controller
public class BmiController {

    @GetMapping("/bmi")
    public String bmi() {
        return "bmi-form";
    }

    // @PostMapping("/bmi")
    // public String processBmi(
    // @RequestParam String name,
    // @RequestParam int yob,
    // @RequestParam double weight,
    // @RequestParam double height,
    // @RequestParam(required = false) String[] interest,
    // Model model) {

    // Person person = new Person(name, yob, weight, height, interest);
    // model.addAttribute("person", person);
    // return "bmi-result";
    // }

    // @PostMapping("/bmi")
    // public String processBmi(HttpServletRequest req, Model model) {
    // String name = req.getParameter("name");
    // int yob = Integer.parseInt(req.getParameter("yob"));
    // double weight = Double.parseDouble(req.getParameter("weight"));
    // double height = Double.parseDouble(req.getParameter("height"));
    // String[] interest = req.getParameterValues("interest");

    // Person person = new Person(name, yob, weight, height, interest);

    // model.addAttribute("person", person);
    // return "bmi-result";
    // }

    @PostMapping("/bmi")
    public ModelAndView processBmi(@ModelAttribute Person p) {
        ModelAndView mv = new ModelAndView("bmi-result");
        mv.addObject("person", p);
        return mv;
    }

}
