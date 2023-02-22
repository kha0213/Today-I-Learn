package com.example.spring;

import com.example.mvcseminar.controller.UserController;
import com.example.mvcseminar.entity.User;
import com.example.mvcseminar.entity.UserRequestDto;
import com.example.mvcseminar.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.example.mvcseminar.entity.User.of;

@RequiredArgsConstructor
public class UserControllerV0 implements UserController {
    private final UserRepository userRepository;

    @Override
    public String hello() {
        return "hello world";
    }

    @Override
    public User saveUserV0_request_param_object(UserRequestDto dto) {
        User user = of(dto);
        userRepository.save(user);
        return user;
    }

    @Override
    public User saveUserV1_request_param_map(@RequestParam Map<String, String> map) {
        User user = of(map);
        userRepository.save(user);
        return user;
    }

    @Override
    public User saveUserV2_request_body_object(@RequestBody UserRequestDto dto) {
        User user = of(dto);
        userRepository.save(user);
        return user;
    }

    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    public User saveUserV3_request_body_valid(@RequestBody @Valid UserRequestDto dto) {
        User user = of(dto);
        userRepository.save(user);
        return user;
    }
}
