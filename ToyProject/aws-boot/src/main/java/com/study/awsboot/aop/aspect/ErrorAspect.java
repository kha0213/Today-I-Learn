package com.study.awsboot.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by Kim Young Long.
 * Date : 2021-09-01.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
@Slf4j
@Aspect
@Component
public class ErrorAspect {
    // Vehicle 클래스의 run 메서드에 실행
    @AfterThrowing("execution(* com.study.awsboot.aop.vo.Vehicle.*(..))")
    public void logging() throws Throwable {
        log.info("ErrorAspect!! ");
    }
}
