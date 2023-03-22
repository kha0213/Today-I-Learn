package com.example.secubook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        log.info("home");
        return "Hello, World!";
    }

    @GetMapping("/hello")
    public String hello() {
        log.info("hello");
        return "Hello hello, World!";
    }
}
