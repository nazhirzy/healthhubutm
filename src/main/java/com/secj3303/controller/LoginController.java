package com.secj3303.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.secj3303.dao.PersonDaoHibernate;
import com.secj3303.model.Person;

@Controller
public class LoginController {
    @Autowired
    private PersonDaoHibernate pDao;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String pass, HttpSession session, Model model){
        String role = null;
        Person p = pDao.findByUsernameAndPassword(username, pass);

        if (p == null) {
            model.addAttribute("error", "Invalid login");
            return "login";
        }

        if (username.contains("member")) {
            role = "member";
        } else if (username.contains("trainer")) {
            role = "trainer";
        } else if (username.contains("admin")) {
            role = "admin";
        }

        session.setAttribute("username", username);
        session.setAttribute("role", role);
        session.setAttribute("id", p.getId());

        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
