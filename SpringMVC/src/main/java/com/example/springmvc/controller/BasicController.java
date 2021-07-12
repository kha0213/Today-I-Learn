package com.example.springmvc.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BasicController {

    @GetMapping(value = "/basic/text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "Hello Spring!");
        return "/basic/text-basic";
    }

    @GetMapping(value = "/basic/text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "Hello <b>Spring!</b>");
        return "/basic/text-unescaped";
    }

    @GetMapping(value = "/basic/variable")
    public String variable(Model model) {
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "/basic/variable";

    }

    @GetMapping("/basic/basic-objects")
    public String basicObjects(HttpSession session) {
        session.setAttribute("sessionData", "Hello Session");
        return "basic/basic-objects";
    }

    @GetMapping("/basic/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    @GetMapping("/basic/link")
    public String link(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }
    @GetMapping("/basic/literal")
    public String literal(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/literal";
    }
    @GetMapping("/basic/operation")
    public String operation(Model model) {
        model.addAttribute("nullData", null);
        model.addAttribute("emptyData", "");
        model.addAttribute("data", "Spring!");
        return "basic/operation";
    }

    @GetMapping("/basic/attribute")
    public String attribute() {
        return "basic/attribute";
    }

    @GetMapping("/basic/each")
    public String each(Model model) {
        addUsers(model);
        return "basic/each";
    }

    @GetMapping("/basic/condition")
    public String condition(Model model) {
        addUsers(model);
        return "basic/condition";
    }

    @GetMapping("/basic/comments")
    public String comments(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/comments";
    }

    @GetMapping("/basic/block")
    public String block(Model model) {
        addUsers(model);
        return "basic/block";
    }

    private void addUsers(Model model) {
        List<User> list = new ArrayList<>();
        list.add(new User("userA", 10));
        list.add(new User("userB", 20));
        list.add(new User("userC", 30));
        model.addAttribute("users", list);
    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hello " + data;
        }
    }
    @Component("multiBean")
    static class MultiBean {
        public int mulitple(int a, int b) {
            return a * b;
        }
    }

    @Data
    @AllArgsConstructor
    static class User {
        private String name;
        private int age;
    }

}
