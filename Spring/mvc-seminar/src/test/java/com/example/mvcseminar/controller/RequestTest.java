package com.example.mvcseminar.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestTest {
    @Test
    @DisplayName("hello 테스트")
    void requestV1() {
        // given
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder().rootUri("http://localhost:8080");
        TestRestTemplate restTemplate = new TestRestTemplate(restTemplateBuilder);

        // when
        ResponseEntity<String> res =
                restTemplate.getForEntity("/hello", String.class, new Object());

        // then
        assertThat(res.getBody()).isEqualTo("hello world");
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
