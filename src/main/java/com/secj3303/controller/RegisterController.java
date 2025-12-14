package com.secj3303.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.secj3303.dao.PersonDaoHibernate;
import com.secj3303.model.Person;

@Controller
public class RegisterController{
    @Autowired
    private PersonDaoHibernate personDao;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register"){
    public String registerPost(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("email") String email,
                               Model model,
                               HttpSession session) {
        if (personDao.findByUsername(username) != null) {
            model.addAttribute("error", "Username already exists.");
            return "register";
        }

        Person newPerson = new Person();
        newPerson.setUsername(username);
        newPerson.setPassword(password);
        newPerson.setEmail(email);
        newPerson.setRole("member");
        personDao.save(newPerson);

        return "redirect:/login";
    }
    }
}