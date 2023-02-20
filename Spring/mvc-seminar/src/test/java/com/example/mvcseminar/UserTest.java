package com.example.mvcseminar;

import com.example.mvcseminar.entity.Gender;
import com.example.mvcseminar.entity.User;
import org.json.JSONString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class UserTest {
    private final User testUser = new User(1L,"name", Gender.MALE, "kyl0327@greencar.co.kr");

    @Test
    @DisplayName("/v1/user 요청 테스트")
    void requestV1() throws MalformedURLException {
        // given
        UriComponents uri = UriComponentsBuilder.fromUriString("http://localhost:8080/v1/user")
                .queryParam("userId", testUser.getUserId())
                .queryParam("name", testUser.getName())
                .queryParam("gender", testUser.getGender())
                .queryParam("email", testUser.getEmail())
                .build();
        RequestEntity<Void> request = RequestEntity
                .post(uri.toUri())
                .build();

        RestTemplate restTemplate = new RestTemplateBuilder()
                .build();
        // when
        ResponseEntity<User> response = restTemplate.exchange(request, User.class);

        // then
        System.out.println("response = " + response);
    }

    @Test
    @DisplayName("/v2/user 요청 테스트")
    void requestV2() throws MalformedURLException, URISyntaxException {
        // given
        RequestEntity<User> build = RequestEntity
                .post("http://localhost:8080/v2/user")
                .body(testUser);

        // when
        // then
    }
}
