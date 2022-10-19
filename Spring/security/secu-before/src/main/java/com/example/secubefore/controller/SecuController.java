package com.example.secubefore.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SecuController {

    @GetMapping("/")
    public String index() {
        log.info("/");
        return "basic home";
    }

    @GetMapping("/home")
    public String home() {
        log.info("/home");
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        log.info("login get");
        return "login Get";
    }

    @PostMapping("/login")
    public String loginPost() {
        log.info("login post");
        return "login Post";
    }

    @PostMapping("/logout")
    public String logoutPost() {
        log.info("logout post");
        return "logout Post";
    }

    @GetMapping("/expired")
    public String expired() {
        log.info("[GET] expired");
        return "expired";
    }

    @GetMapping("/invalid")
    public String expirinvalided() {
        log.info("[GET] invalid");
        return "invalid";
    }
}
