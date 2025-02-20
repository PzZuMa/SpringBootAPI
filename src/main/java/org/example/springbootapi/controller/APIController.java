package org.example.springbootapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class APIController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
