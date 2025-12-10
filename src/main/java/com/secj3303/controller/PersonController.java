package com.secj3303.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.secj3303.dao.PersonDao;
import com.secj3303.model.Person;

@Controller
@RequestMapping("/person")
public class PersonController {
    private final PersonDao personDao;

    @Autowired
    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping("/list")
    public String listPersons(Model model) {
        model.addAttribute("persons", personDao.findAll());
        return "person-list";
    }

    @GetMapping("/add")
    public String AddForm(Model model) {
        model.addAttribute("person", new Person());
        return "person-form";
    }

    @PostMapping("/insert")
    public String InsertPerson(@ModelAttribute Person person) {
        if (person.getId() == 0) {
            personDao.insert(person);  
        } else {
            personDao.update(person);  
        }

        return "redirect:/person/list";
    }

    @GetMapping("/update")
    public String editPerson(@RequestParam int id, Model model) {

    Person person = personDao.findById(id);
    model.addAttribute("person", person);

    return "person-form";
    }

    @GetMapping("/delete")
    public String deletePerson(@RequestParam int id) {

    personDao.delete(id);
    return "redirect:/person/list";
    }
}
