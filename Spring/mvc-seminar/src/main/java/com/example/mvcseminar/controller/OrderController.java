package com.example.mvcseminar.controller;

import com.example.mvcseminar.entity.RentOrder;
import com.example.spring.v0.RentOrderRequestV0Dto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface OrderController {

    /**
     * 요구사항!
     * 1. RentOrderRequestDto의 필드 유효성 검사에 실패하면 400 에러를 반환한다.
     * 2. RentOrderRequestDto의 name 필드에 "error"가 포함되어 있으면 비즈니스 에러를 반환한다.
     * 3. 그 외이면 성공 (200) 응답에 RentOrder를 반환한다.
     */

    @PostMapping("/order/save")
    RentOrder saveRentOrder(@RequestBody RentOrderRequestV0Dto dto);

    /**
     * 요구사항!
     * 1. 위와 같지만 RentOrderRequestDto의 orderId가 존재해야 한다.
     */
    @PostMapping("/order/update")
    RentOrder updateRentOrder(@RequestBody RentOrderRequestV0Dto dto);
}
