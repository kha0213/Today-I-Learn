package com.example.jpashop.vo;

import com.example.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {
    private OrderStatus orderStatus;
    private String memberName;
}
