package com.secj3303.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.secj3303.dao.EnrollmentDaoHibernate;
import com.secj3303.dao.PersonDaoHibernate;
import com.secj3303.dao.ProgramDaoHibernate;
import com.secj3303.model.Enrollment;
import com.secj3303.model.Person;
import com.secj3303.model.Program;

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
        if (!"member".equals(session.getAttribute("role"))) {
            return "redirect:/login";
        }

        String name = (String) session.getAttribute("name");
        model.addAttribute("name", name);

        return "member-dashboard";
    }

    @GetMapping("/programs")
    public String memberProgram(HttpSession session, Model model){
        if (!"member".equals(session.getAttribute("role"))) {
            return "redirect:/login";
        }

        List<Program> plist = programDao.findAll();
        model.addAttribute("programs", plist);
        return "member-program-list";
    }

    @GetMapping("/bmi")
    public String bmiForm(HttpSession session){
        if (!"member".equals(session.getAttribute("role"))) {
            return "redirect:/login";
        }


        return "bmi-form";
    }

    @PostMapping("/enroll")
    public String enrollProgram(HttpSession session, @RequestParam int programId){
        int memberId = (Integer) session.getAttribute("id");
        Program p = programDao.findById(programId);
        Person m = personDao.findById(memberId);

        Enrollment e = new Enrollment(m, p);
        eDao.save(e);
        return "redirect:/member/my-programs";
    }

    @GetMapping("/my-programs")
    public String viewPrograms(HttpSession session, Model model){
        int memberId = (Integer) session.getAttribute("id");
        List<Enrollment> enrollments = eDao.findByMemberId(memberId);
        model.addAttribute("enrollments", enrollments);

        return "member-programs";
    }

    @PostMapping("/unenroll")
    public String unenrollProgram(HttpSession session, @RequestParam int programId){
        int memberId = (Integer) session.getAttribute("id");
        Program p = programDao.findById(programId);
        Person m = personDao.findById(memberId);

        
        return "redirect:/member/my-programs";
    }
}
