package com.example.springbasic;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

public class MyBean2 implements InitializingBean, DisposableBean {
    public void print() {
        System.out.println("MyBean2.print : " + this.getClass().getName());
    }

    @Override
    public void destroy() {
        System.out.println("MyBean2.destroy 종료");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("MyBean2.afterPropertiesSet 시작");
    }
}
