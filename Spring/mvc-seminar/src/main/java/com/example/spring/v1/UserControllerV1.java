package com.example.spring.v1;

import com.example.mvcseminar.controller.UserController;
import com.example.mvcseminar.entity.User;
import com.example.mvcseminar.entity.UserRequestDto;

import java.util.Map;

public class UserControllerV1 implements UserController {


    @Override
    public String hello() {
        return null;
    }

    @Override
    public User saveUserV0_request_param_object(UserRequestDto dto) {
        return null;
    }

    @Override
    public User saveUserV1_request_param_map(Map<String, String> map) {
        return null;
    }

    @Override
    public User saveUserV2_request_body_object(UserRequestDto dto) {
        return null;
    }

    @Override
    public User saveUserV3_request_body_valid(UserRequestDto dto) {
        return null;
    }
}
