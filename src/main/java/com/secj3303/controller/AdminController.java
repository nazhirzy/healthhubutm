package com.secj3303.controller;

import com.secj3303.dao.ProgramDao;
import com.secj3303.model.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProgramDao programDAO;

    private boolean isAdmin(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return "admin".equals(role);
    }
    
    @GetMapping("/dashboard")
    public String adminDashboard(HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login"; 
        }
        return "admin/admin-dashboard"; 
    }

    @GetMapping("/programs")
    public String listPrograms(HttpSession session, Model model) {
        if (!isAdmin(session)) return "redirect:/login";

        List<Program> programs = programDAO.findAll();
        model.addAttribute("programs", programs);
        return "admin/program-list"; // admin/program-list.html
    }

    @GetMapping("/programs/showFormForAdd")
    public String showProgramFormForAdd(HttpSession session, Model model) {
        if (!isAdmin(session)) return "redirect:/login";

        model.addAttribute("program", new Program());
        return "admin/program-form"; // admin/program-form.html
    }

    @GetMapping("/programs/showFormForUpdate")
    public String showProgramFormForUpdate(@RequestParam("programId") int id, HttpSession session, Model model) {
        if (!isAdmin(session)) return "redirect:/login";

        Program program = programDAO.findById(id);
        model.addAttribute("program", program);
        return "admin/program-form"; 
    }

    @PostMapping("/programs/save")
    public String saveProgram(@ModelAttribute("program") Program program, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) return "redirect:/login";

        programDAO.save(program);
        redirectAttributes.addFlashAttribute("message", "Program saved successfully!");
        return "redirect:/admin/programs";
    }

    @GetMapping("/programs/delete")
    public String deleteProgram(@RequestParam("programId") int id, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) return "redirect:/login";

        programDAO.delete(id);
        redirectAttributes.addFlashAttribute("message", "Program deleted successfully.");
        return "redirect:/admin/programs";
    }


    @GetMapping("/reports")
    public String showReports(HttpSession session, Model model) {
        if (!isAdmin(session)) return "redirect:/login";
        return "admin/system-reports"; 
    }
}