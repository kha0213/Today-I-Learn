package com.study.secu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @PostMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/login")
    public String login() {
        return "login";
    }
}
