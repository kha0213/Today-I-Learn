package com.example.springbasic.api;

import com.example.springbasic.api.discountPolicy.DiscountPolicy;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
}
