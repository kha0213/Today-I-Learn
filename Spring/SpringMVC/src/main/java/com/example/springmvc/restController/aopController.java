package com.example.springmvc.restController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kim Young Long.
 * Date : 2021-10-05.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
@RestController
public class aopController {
    @GetMapping("/hello")
    public String hello() {
        return "hello world!";
    }

    @GetMapping("/around")
    public String around() {
        return "[around]";
    }

    @GetMapping("/around2")
    public String around(String message) {
        return "[around2]" + message;
    }

    @GetMapping("/before")
    public String before() {
        return "[before]";
    }
}
