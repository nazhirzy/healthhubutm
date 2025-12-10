package com.secj3303.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller()
class DemoController {

    @GetMapping("/demo/home")
    // @ResponseBody()
    public String home(Model model) {
        model.addAttribute("message", "This is SECJ3303.....");
        return "demo";

    }
}