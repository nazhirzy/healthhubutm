package com.secj3303.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.secj3303.dao.EnrollmentDaoHibernate;
import com.secj3303.dao.PersonDaoHibernate;
import com.secj3303.dao.ProgramDaoHibernate;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private PersonDaoHibernate personDao;

    @Autowired
    private ProgramDaoHibernate programDao;

    @Autowired
    private EnrollmentDaoHibernate eDao;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model){
        
    }
}
