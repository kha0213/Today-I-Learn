package com.example.rxstart.sample;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TodoSampleTest {
    @Test
    @DisplayName("rxJava 기본 흐름 테스트")
    void test() throws InterruptedException {
        Observable.just(100,200,300,400,500)
                .doOnNext(data -> print("doOnNext", data))
                .subscribeOn(Schedulers.io()) // 데이터의 흐름을 결정짓는 쓰레드 지정
                .observeOn(Schedulers.computation()) // 발행된 데이터를 가공하고 처리할 쓰레드 지정
                .filter(num -> num > 300)
                .subscribe(num -> print("subscribe", num));

        Thread.sleep(500);
    }

    void print(String behavior, Integer num) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "[" + behavior + "] : " + num);
    }
}
