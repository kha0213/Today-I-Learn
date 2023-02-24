package com.example.mvcseminar.entity;

import com.example.spring.v0.RentOrderRequestV0Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentOrder {
    private Long orderId;
    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private String phoneNumber;

    public static RentOrder of(RentOrderRequestV0Dto dto) {
        return new RentOrder(dto.getOrderId(), dto.getName(),
                dto.getStartDateTime(), dto.getEndDateTime(), dto.getPhoneNumber());
    }
}
