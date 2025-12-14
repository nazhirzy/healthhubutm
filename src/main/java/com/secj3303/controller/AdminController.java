package com.secj3303.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.secj3303.dao.EnrollmentDaoHibernate;
import com.secj3303.dao.PersonDaoHibernate;
import com.secj3303.dao.ProgramDaoHibernate;
import com.secj3303.model.Program;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PersonDaoHibernate personDao;

    @Autowired
    private ProgramDaoHibernate programDao;

    @Autowired
    private EnrollmentDaoHibernate eDao;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model){
        if (!"admin".equals(session.getAttribute("role"))) {
            return "redirect:/login";
        }

        String name = (String) session.getAttribute("name");
        model.addAttribute("name", name);

        return "admin-dashboard";
    }

    @RequestMapping("/getall")
    public String getall(Model model) {
        List<Program> plist = programDao.findAll();
        model.addAttribute("programs", plist);
        return "program-list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam int id){
        programDao.delete(id);
        return "redirect:/program/getall";
    }

    @RequestMapping("/user-report")
    public String showReport(HttpSession session, Model model){
        if (!"admin".equals(session.getAttribute("role"))) {
            return "redirect:/login";
        }

        

        return "admin-user-report";
    }
}
