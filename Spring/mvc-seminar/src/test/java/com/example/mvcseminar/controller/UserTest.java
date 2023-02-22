package com.example.mvcseminar.controller;

import com.example.mvcseminar.entity.UserRequestDto;
import com.example.mvcseminar.entity.Gender;
import com.example.mvcseminar.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserTest {
    private final UserRequestDto successUser =
            new UserRequestDto(1L,"name", "M","loginId", "kyl0327@greencar.co.kr");

    private final UserRequestDto failUser =
            new UserRequestDto(1L,"error유저", "MALE","kyl0327@greencar.co.kr", "kyl0327@greencar.co.kr");

    private final UserRequestDto validFailUser =
            new UserRequestDto(1L,"", "MALE","kyl0327@greencar.co.kr", "kyl0327@greencar.co.kr");

    TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder().rootUri("http://localhost:8080");
        restTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    @DisplayName("/v0/user 요청 테스트")
    void requestV0() {
        // given
        UriComponents uri = UriComponentsBuilder.fromUriString("/v0/user")
                .queryParam("userId", successUser.getUserId())
                .queryParam("name", successUser.getName())
                .queryParam("gender", successUser.getGender())
                .queryParam("loginId", successUser.getLoginId())
                .queryParam("email", successUser.getEmail())
                .build();

        // when
        ResponseEntity<User> res =
                restTemplate.postForEntity(uri.toUri(), null, User.class);

        // then
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        User user = res.getBody();
        assert user != null;
        assertThat(user.getUserId()).isEqualTo(successUser.getUserId());
        assertThat(user.getName()).isEqualTo(successUser.getName());
        assertThat(user.getEmail()).isEqualTo(successUser.getEmail());
        assertThat(user.getLoginId()).isEqualTo(successUser.getLoginId());
        assertThat(user.getGender()).isEqualTo(Gender.of(successUser.getGender()));
    }

    @Test
    @DisplayName("/v1/user 요청 테스트")
    void requestV1() {
        // given
        UriComponents uri = UriComponentsBuilder.fromUriString("/v1/user")
                .queryParam("userId", successUser.getUserId())
                .queryParam("name", successUser.getName())
                .queryParam("gender", successUser.getGender())
                .queryParam("loginId", successUser.getLoginId())
                .queryParam("email", successUser.getEmail())
                .build();

        // when
        ResponseEntity<User> res =
                restTemplate.postForEntity(uri.toUri(), null, User.class);

        // then
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        User user = res.getBody();
        assert user != null;
        assertThat(user.getUserId()).isEqualTo(successUser.getUserId());
        assertThat(user.getName()).isEqualTo(successUser.getName());
        assertThat(user.getEmail()).isEqualTo(successUser.getEmail());
        assertThat(user.getLoginId()).isEqualTo(successUser.getLoginId());
        assertThat(user.getGender()).isEqualTo(Gender.of(successUser.getGender()));
    }

    @Test
    @DisplayName("/v2/user 요청 테스트")
    void requestV2() {
        // when
        ResponseEntity<User> res =
                restTemplate.postForEntity("/v2/user", successUser, User.class);
        // then
        User user = res.getBody();
        assert user != null;
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(user.getUserId()).isEqualTo(successUser.getUserId());
        assertThat(user.getName()).isEqualTo(successUser.getName());
        assertThat(user.getEmail()).isEqualTo(successUser.getEmail());
        assertThat(user.getGender()).isEqualTo(Gender.of(successUser.getGender()));
    }

    @Test
    @DisplayName("/v3/user 요청 성공 테스트")
    void requestV3() {
        // when
        ResponseEntity<User> res =
                restTemplate.postForEntity("/v3/user", successUser, User.class);
        // then

        User user = res.getBody();
        assert user != null;
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(user.getUserId()).isEqualTo(successUser.getUserId());
        assertThat(user.getName()).isEqualTo(successUser.getName());
        assertThat(user.getEmail()).isEqualTo(successUser.getEmail());
        assertThat(user.getLoginId()).isEqualTo(successUser.getLoginId());
        assertThat(user.getGender()).isEqualTo(Gender.of(successUser.getGender()));
    }

    @Test
    @DisplayName("/v3/user 요청 비정상 유저 실패 테스트")
    void requestV4_error_fail() {
        // when
        ResponseEntity<User> res =
                restTemplate.postForEntity("/v3/user", failUser, User.class);
        // then
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("/v3/user 요청 유효성 검사 실패 테스트")
    void requestV4_fail() {
        // when
        ResponseEntity<User> res =
                restTemplate.postForEntity("/v3/user", validFailUser, User.class);
        // then
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
