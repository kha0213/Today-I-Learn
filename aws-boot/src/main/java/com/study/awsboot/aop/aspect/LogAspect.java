package com.study.awsboot.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

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
public class LogAspect {
    // Vehicle 클래스의 모든 메서드에 실행
    @Around("execution(* com.study.awsboot.aop.vo.Vehicle.*(..))")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        log.info("start - [{}/{}]", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName());
        Object result = pjp.proceed();
        log.info("end - [{}/{}]", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName());
        return result;
    }
}
