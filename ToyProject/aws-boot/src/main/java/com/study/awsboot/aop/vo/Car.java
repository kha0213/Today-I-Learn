package com.study.awsboot.aop.vo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Kim Young Long.
 * Date : 2021-09-01.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
@RequiredArgsConstructor
@Slf4j
public class Car implements Vehicle {

    private final int peopleCount;

    @Override
    public void getOn() {
        log.info("Class : [{}], getOn! {} people " , this.getClass().getName(), peopleCount);
    }

    @Override
    public void run() throws Throwable {
        if(peopleCount < 1){ throw new Exception();}
        log.info("Class : [{}], running!! {} people " , this.getClass().getName(), peopleCount);
    }

    @Override
    public void getOut() {
        log.info("Class : [{}], getOut! {} people " , this.getClass().getName(), peopleCount);
    }
}
