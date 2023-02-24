package com.example.mvcseminar.controller;

import com.example.spring.v0.RentOrderRequestV0Dto;
import com.example.mvcseminar.entity.RentOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RentOrderSaveRequestTest {
    TestRestTemplate restTemplate;

    final String SAVE_URI = "/order/save";

    final String UPDATE_URI = "/order/update";

    @BeforeEach
    void setUp() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder().rootUri("http://localhost:8080");
        restTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    @DisplayName("[비즈니스] 성공 요청 테스트")
    void successRequestTest() {
        // given
        RentOrderRequestV0Dto successRequest =
                RentOrderRequestV0Dto.builder()
                        .orderId(null)
                        .name("youngLong")
                        .startDateTime(LocalDateTime.now().minusDays(3))
                        .endDateTime(LocalDateTime.now())
                        .phoneNumber("010-2344-8628")
                        .build();
        // when
        ResponseEntity<RentOrder> res =
                restTemplate.postForEntity(SAVE_URI, successRequest, RentOrder.class);
        // then
        RentOrder rentOrder = res.getBody();
        assert rentOrder != null;
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(rentOrder.getOrderId()).isEqualTo(successRequest.getOrderId());
        assertThat(rentOrder.getName()).isEqualTo(successRequest.getName());
        assertThat(rentOrder.getStartDateTime()).isEqualTo(successRequest.getStartDateTime());
        assertThat(rentOrder.getEndDateTime()).isEqualTo(successRequest.getEndDateTime());
        assertThat(rentOrder.getPhoneNumber()).isEqualTo(successRequest.getPhoneNumber());
    }

    @Test
    @DisplayName("[비즈니스] 에러 요청 테스트")
    void customerErrorTest() {
        // given
        RentOrderRequestV0Dto customExceptionUser =
                RentOrderRequestV0Dto.builder()
                        .orderId(null)
                        .name("error유저") // Fail
                        .startDateTime(LocalDateTime.now().minusDays(3))
                        .endDateTime(LocalDateTime.now())
                        .phoneNumber("010-2344-8628")
                        .build();
        // when
        ResponseEntity<String> res = restTemplate.postForEntity(SAVE_URI, customExceptionUser, String.class);

        // then
        assertThat(res.getStatusCode().is4xxClientError()).isTrue();
        assertThat(res.getBody()).isEqualTo("error 예약!!");
    }

    @Nested
    @DisplayName("Validation 작동 테스트")
    class VaildTest {

        @Test
        @DisplayName("OrderId 유효성 에러 테스트")
        void orderIdErrorTest() {
            // given
            RentOrderRequestV0Dto fieldErrorRequest =
                    RentOrderRequestV0Dto.builder()
                            .orderId(1L) // Fail!!!
                            .name("youngLong")
                            .startDateTime(LocalDateTime.now().minusDays(3))
                            .endDateTime(LocalDateTime.now())
                            .phoneNumber("01023448628")
                            .build();
            // when
            ResponseEntity<String> res =
                    restTemplate.postForEntity(SAVE_URI, fieldErrorRequest, String.class);
            // then
            assertThat(res.getStatusCode().is4xxClientError()).isTrue();
            assertThat(res.getBody()).isEqualTo("orderId는 Null이어야 합니다.");
        }

        @Test
        @DisplayName("전화번호 유효성 에러 테스트")
        void phoneErrorTest() {
            // given
            RentOrderRequestV0Dto fieldErrorRequest =
                    RentOrderRequestV0Dto.builder()
                            .orderId(null)
                            .name("youngLong")
                            .startDateTime(LocalDateTime.now().minusDays(3))
                            .endDateTime(LocalDateTime.now())
                            .phoneNumber("010234486282") // Fail!!!
                            .build();
            // when
            ResponseEntity<String> res =
                    restTemplate.postForEntity(SAVE_URI, fieldErrorRequest, String.class);
            // then
            assertThat(res.getStatusCode().is4xxClientError()).isTrue();
            assertThat(res.getBody()).isEqualTo("전화번호 형식이 아닙니다.");
        }
        @Test
        @DisplayName("복합 필드 에러 테스트")
        void globalErrorTest() {
            // given
            RentOrderRequestV0Dto globalErrorRequest =
                    RentOrderRequestV0Dto.builder()
                            .orderId(null)
                            .name("youngLong")
                            .startDateTime(LocalDateTime.now().minusDays(3)) // Fail!!!
                            .endDateTime(LocalDateTime.now().minusDays(4)) // Fail!!!
                            .phoneNumber("010-2344-8628")
                            .build();

            // when
            ResponseEntity<String> res =
                    restTemplate.postForEntity(SAVE_URI, globalErrorRequest, String.class);
            // then
            assertThat(res.getStatusCode().is4xxClientError()).isTrue();
            assertThat(res.getBody()).isEqualTo("시작일이 종료일보다 늦습니다.");
        }

        @Test
        @DisplayName("업데이트 요청 시 orderId가 Null인 경우 에러")
        void updateOrderIdNullTest() {
            // given
            RentOrderRequestV0Dto updateRequest =
                    RentOrderRequestV0Dto.builder()
                            .orderId(null) // Fail!!!
                            .name("youngLong")
                            .startDateTime(LocalDateTime.now().minusDays(3))
                            .endDateTime(LocalDateTime.now())
                            .phoneNumber("010-2344-8628")
                            .build();
            // when
            ResponseEntity<String> res =
                    restTemplate.postForEntity(UPDATE_URI, updateRequest, String.class);
            // then
            assertThat(res.getStatusCode().is4xxClientError()).isTrue();
            assertThat(res.getBody()).isEqualTo("orderId는 Null이 아니어야 합니다.");
        }
    }
}
