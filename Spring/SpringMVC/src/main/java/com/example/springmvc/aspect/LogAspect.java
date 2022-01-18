package com.example.springmvc.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by Kim Young Long.
 * Date : 2021-10-05.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
@Aspect
@Slf4j
@Component
public class LogAspect {
    //@Around("execution(* com.example.springmvc.restController.aopController.*(..))")
    //@Around("execution(public String hello())")
    @Around("helloPoint()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("start - " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature().getName());
        Object result = pjp.proceed();
        log.info("finished - " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature().getName());
        return result;
    }

    @After("execution(* com.example.springmvc..*.*(..))")
    public void before2() throws Throwable {
        log.info("before2!!! ");
    }

    @Pointcut("execution(* hello(..))")
    public void helloPoint() {}
}
