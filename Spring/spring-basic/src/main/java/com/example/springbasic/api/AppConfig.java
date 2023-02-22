package com.example.springbasic.api;

import com.example.springbasic.api.discountPolicy.DiscountPolicy;
import com.example.springbasic.api.discountPolicy.RateDiscountPolicy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Slf4j
@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository!!!");
        return new MemberRepositoryImpl();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                disCountPolicy()
        );
    }

    @Bean
    public DiscountPolicy disCountPolicy() {
        return new RateDiscountPolicy();
    }
}
