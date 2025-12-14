package com.secj3303.controller;


import com.secj3303.dao.FitnessPlanDAO;
import com.secj3303.dao.PersonDao;
import com.secj3303.model.FitnessPlan;
import com.secj3303.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/trainer")
public class TrainerController {

    @Autowired
    private FitnessPlanDAO fitnessPlanDAO;

    @Autowired
    private PersonDao personDAO; 

    private boolean isTrainer(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return "trainer".equals(role);
    }
    
    @GetMapping("/dashboard")
    public String trainerDashboard(HttpSession session) {
        if (!isTrainer(session)) {
            return "redirect:/login";
        }
        return "trainer/trainer-dashboard";
    }


    @GetMapping("/plans")
    public String listPlans(HttpSession session, Model model) {
        if (!isTrainer(session)) return "redirect:/login";

        Person currentTrainer = (Person) session.getAttribute("loggedInUser");
        if (currentTrainer == null) return "redirect:/login";
        
        List<FitnessPlan> plans = fitnessPlanDAO.findPlansByTrainerId(currentTrainer.getId());
        model.addAttribute("plans", plans);
        return "trainer/plan-list"; 
    }

    @GetMapping("/plans/showFormForAdd")
    public String showFormForAdd(HttpSession session, Model model) {
        if (!isTrainer(session)) return "redirect:/login";

        model.addAttribute("plan", new FitnessPlan());
        return "trainer/plan-form";
    }

    @GetMapping("/plans/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("planId") int id, HttpSession session, Model model) {
        if (!isTrainer(session)) return "redirect:/login";

        FitnessPlan plan = fitnessPlanDAO.findById(id);
        model.addAttribute("plan", plan);
        return "trainer/plan-form";
    }

    @PostMapping("/plans/save")
    public String savePlan(@ModelAttribute("plan") FitnessPlan plan, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isTrainer(session)) return "redirect:/login";

        Person currentTrainer = (Person) session.getAttribute("loggedInUser");
        if (currentTrainer == null) return "redirect:/login";

        plan.setTrainer(currentTrainer); 
        fitnessPlanDAO.save(plan);
        redirectAttributes.addFlashAttribute("message", "Fitness Plan saved successfully!");
        return "redirect:/trainer/plans";
    }

    @GetMapping("/plans/delete")
    public String deletePlan(@RequestParam("planId") int id, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isTrainer(session)) return "redirect:/login";

        fitnessPlanDAO.delete(id);
        redirectAttributes.addFlashAttribute("message", "Fitness Plan deleted successfully.");
        return "redirect:/trainer/plans";
    }

    @GetMapping("/assign-plan")
    public String showAssignPlanForm(HttpSession session, Model model) {
        if (!isTrainer(session)) return "redirect:/login";

        List<Person> members = personDAO.findAllMembers();

        Person currentTrainer = (Person) session.getAttribute("loggedInUser");
        List<FitnessPlan> plans = fitnessPlanDAO.findPlansByTrainerId(currentTrainer.getId()); 

        model.addAttribute("members", members);
        model.addAttribute("plans", plans);

        return "trainer/assign-plan-form";
    }

    @PostMapping("/assign-plan")
    public String assignPlan(@RequestParam("memberId") int memberId, 
                             @RequestParam("planId") int planId, 
                             RedirectAttributes redirectAttributes) {
        

        redirectAttributes.addFlashAttribute("message", "Plan assigned successfully!");
        return "redirect:/trainer/dashboard";
    }



}
