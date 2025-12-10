package com.secj3303.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secj3303.model.Person;

@RestController
public class ApiController {
    @GetMapping("/api/person")
    public Person getPerson(){
        return new Person(1, "ali", 2002, 55, 1.6);
    }
}
