package com.example.secubefore.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@Slf4j
@RestController
public class AuthController {
    @GetMapping("/")
    public String index() {
        return "auth home";
    }

    @GetMapping("/user")
    public String user() {
        return "auth user";
    }

    @GetMapping("/admin/pay")
    public String adminPay() {
        return "auth admin pay";
    }

    @GetMapping("/admin/home")
    public String adminHome () {
        return "auth admin home";
    }
}
