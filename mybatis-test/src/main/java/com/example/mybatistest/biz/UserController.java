package com.example.mybatistest.biz;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user/{id}")
    public Users getUser(@PathVariable Long id) throws Exception {
        return userService.getUser(id);
    }

    @GetMapping("/user")
    public List<Users> getUserListAll() throws Exception {
        return userService.getUserListAll();
    }

    @PostMapping("/user")
    public Users getUser(@RequestBody Users user) throws Exception {
        return userService.addUser(user);
    }
}
