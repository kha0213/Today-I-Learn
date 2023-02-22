package com.example.mvcseminar.controller;

import com.example.mvcseminar.entity.User;
import com.example.mvcseminar.entity.UserRequestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public interface UserController {

    /**
     * hello world 를 출력한다.
     */
    @GetMapping("/hello")
    String hello();

    @PostMapping("/v0/user")
    User saveUserV0_request_param_object(UserRequestDto dto);

    /**
     * 맵 파라미터로 넘긴다.
     */
    @PostMapping("/v1/user")
    User saveUserV1_request_param_map(Map<String, String> map);

    /**
     * UserRequestDto 타입으로 요청한다.
     */
    @PostMapping("/v2/user")
    User saveUserV2_request_body_object(UserRequestDto dto);


    /**
     * 유효성 체크를 한다.
     */
    @PostMapping("/v3/user")
    User saveUserV3_request_body_valid(UserRequestDto dto);
}
