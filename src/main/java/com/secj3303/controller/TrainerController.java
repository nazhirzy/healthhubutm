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
    private PersonDao personDAO; // Assuming this DAO can fetch members

    // --- Role Check Method ---
    private boolean isTrainer(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return "trainer".equals(role);
    }
    
    // --- Trainer Dashboard ---
    @GetMapping("/dashboard")
    public String trainerDashboard(HttpSession session) {
        if (!isTrainer(session)) {
            return "redirect:/login"; // Redirect if not logged in as a trainer
        }
        return "trainer/trainer-dashboard"; // trainer/trainer-dashboard.html
    }

    // -------------------------------------------------------------------
    // --- Fitness Plan Management (CRUD) ---
    // -------------------------------------------------------------------

    @GetMapping("/plans")
    public String listPlans(HttpSession session, Model model) {
        if (!isTrainer(session)) return "redirect:/login";

        // Get the current trainer's ID from session (assuming Person object is stored)
        Person currentTrainer = (Person) session.getAttribute("loggedInUser");
        if (currentTrainer == null) return "redirect:/login";
        
        List<FitnessPlan> plans = fitnessPlanDAO.findPlansByTrainerId(currentTrainer.getId());
        model.addAttribute("plans", plans);
        return "trainer/plan-list"; // trainer/plan-list.html
    }

    @GetMapping("/plans/showFormForAdd")
    public String showFormForAdd(HttpSession session, Model model) {
        if (!isTrainer(session)) return "redirect:/login";

        model.addAttribute("plan", new FitnessPlan());
        return "trainer/plan-form"; // trainer/plan-form.html
    }

    @GetMapping("/plans/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("planId") int id, HttpSession session, Model model) {
        if (!isTrainer(session)) return "redirect:/login";

        FitnessPlan plan = fitnessPlanDAO.findById(id);
        model.addAttribute("plan", plan);
        return "trainer/plan-form"; // Reusing the form
    }

    @PostMapping("/plans/save")
    public String savePlan(@ModelAttribute("plan") FitnessPlan plan, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isTrainer(session)) return "redirect:/login";

        Person currentTrainer = (Person) session.getAttribute("loggedInUser");
        if (currentTrainer == null) return "redirect:/login";

        // Set the trainer for the plan
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

    // -------------------------------------------------------------------
    // --- Assign Plan to Members (Simplified view for assignment) ---
    // -------------------------------------------------------------------

    @GetMapping("/assign-plan")
    public String showAssignPlanForm(HttpSession session, Model model) {
        if (!isTrainer(session)) return "redirect:/login";

        // Fetch all members (Assuming a method in PersonDAO: findAllMembers())
        List<Person> members = personDAO.findAllMembers();
        // Fetch all plans created by this trainer
        Person currentTrainer = (Person) session.getAttribute("loggedInUser");
        List<FitnessPlan> plans = fitnessPlanDAO.findPlansByTrainerId(currentTrainer.getId()); 

        model.addAttribute("members", members);
        model.addAttribute("plans", plans);
        // Note: For actual assignment, you would need an intermediary entity (e.g., MemberPlanAssignment)
        return "trainer/assign-plan-form"; // trainer/assign-plan-form.html
    }

    @PostMapping("/assign-plan")
    public String assignPlan(@RequestParam("memberId") int memberId, 
                             @RequestParam("planId") int planId, 
                             RedirectAttributes redirectAttributes) {
        
        // This is where you would implement the logic to save the assignment
        // E.g., memberPlanAssignmentDAO.saveAssignment(memberId, planId);
        
        // Simulating success:
        redirectAttributes.addFlashAttribute("message", "Plan assigned successfully!");
        return "redirect:/trainer/dashboard";
    }

    // -------------------------------------------------------------------
    // --- Schedule Sessions (Simplified) ---
    // -------------------------------------------------------------------
    // ... Implement similar CRUD methods for Session using a SessionDAO ...

}
