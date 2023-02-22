package com.example.mvcseminar.controller;

import com.example.mvcseminar.entity.User;
import com.example.mvcseminar.entity.UserRequestDto;

import java.util.Map;


public interface UserController {

    /**
     * hello world 를 출력한다.
     */

    String hello();


    User saveUserV0_request_param_object(UserRequestDto dto);

    /**
     * 맵 파라미터로 넘긴다.
     */

    User saveUserV1_request_param_map(Map<String, String> map);

    /**
     * UserRequestDto 타입으로 요청한다.
     */

    User saveUserV2_request_body_object(UserRequestDto dto);


    /**
     * 유효성 체크를 한다.
     */

    User saveUserV3_request_body_valid(UserRequestDto dto);
}
