package com.example.spring;

import com.example.mvcseminar.entity.User;
import com.example.mvcseminar.entity.UserRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.example.mvcseminar.entity.User.of;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    @PostMapping("/v0/user")
    public User saveUserV0(UserRequestDto dto) {
        return of(dto);
    }
    @PostMapping("/v1/user")
    public User saveUserV1(@RequestParam UserRequestDto dto) {
        return of(dto);
    }

    @PostMapping("/v2/user")
    public User saveUserV2(@RequestBody UserRequestDto dto) {
        return of(dto);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/v3/user")
    public User saveUserV3(@RequestBody @Valid UserRequestDto dto) {
        return of(dto);
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
