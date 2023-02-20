package com.example.controller;

import com.example.mvcseminar.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    @PostMapping("/v1/user")
    public User saveUserV1(User user) {
        return user;
    }

    @PostMapping("/v2/user")
    public User saveUserV2(@RequestBody User user) {
        return user;
    }

    @PostMapping("/v3/user")
    public User saveUserV3(@RequestBody @Validated User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            System.out.println("bindingResult = " + bindingResult);
            throw new IllegalArgumentException("Bad Request : " + user);
        }
        return user;
    }

    @GetMapping("/user")
    public Long getUser(Long userId) {
        return userId;
    }
}
