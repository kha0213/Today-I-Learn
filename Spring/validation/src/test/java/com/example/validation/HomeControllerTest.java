package com.example.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HomeControllerTest {
    @Test
    void success() {
        TestRestTemplate rest = new TestRestTemplate();

        URI uri = new DefaultUriBuilderFactory()
                .uriString("http://localhost:8080/")
                .queryParam("orderId", "1")
                .queryParam("hphone", "010-1234-5678")
                .queryParam("startDt", LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .queryParam("endDt", LocalDateTime.now().plusDays(5).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();

        ResponseEntity<String> res =
                rest.getForEntity(uri, String.class);

        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(res.getBody()).isEqualTo("1");
    }

    @Test
    void fail_dt() {
        TestRestTemplate rest = new TestRestTemplate();

        URI uri = new DefaultUriBuilderFactory()
                .uriString("http://localhost:8080/")
                .queryParam("orderId", "1")
                .queryParam("hphone", "010-1234-5678")
                .queryParam("startDt", LocalDateTime.now().plusDays(5).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .queryParam("endDt", LocalDateTime.now().plusDays(5).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();

        ResponseEntity<String> res =
                rest.getForEntity(uri, String.class);

        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(res.getBody()).isEqualTo("1");
    }

    @Test
    void fail_hphone() {
        TestRestTemplate rest = new TestRestTemplate();
        String format = LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(format);
        URI uri = new DefaultUriBuilderFactory()
                .uriString("http://localhost:8080/")
                .queryParam("orderId", "1")
                .queryParam("hphone", "01012341234")
                .queryParam("startDt", LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .queryParam("endDt", LocalDateTime.now().plusDays(5).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();

        ResponseEntity<String> res =
                rest.getForEntity(uri, String.class);
        assertThat(res.getBody()).startsWith("error");
    }
}