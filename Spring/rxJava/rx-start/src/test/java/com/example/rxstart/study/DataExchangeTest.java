package com.example.rxstart.study;

import com.example.rxstart.util.Logger;
import com.example.rxstart.util.TimeUtil;
import io.reactivex.rxjava3.core.Observable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

public class DataExchangeTest {
    @Test
    @DisplayName("map 함수")
    void mapTest() {
        Observable.fromIterable(List.of(1,3,5,7))
                .map(num -> num * 100)
                .subscribe(Logger::log);
    }

    @Test
    @DisplayName("flatMap 함수 1:M으로 데이터 변환")
    void flatMapTest() {
        Observable.fromArray("영롱","성준","수빈")
                .flatMap(name -> Observable.fromArray(name + ":Java",name + ":Spring",name + ":Node"))
                .subscribe(Logger::log);
    }

    @Test
    @DisplayName("flatMap 함수 파라미터가 하나인 경우")
    void flatMapGugudanTest() {
        // flatMap은 처리 순서를 보장하지 않는다.
        Observable.range(2, 9)
                .flatMap(num -> Observable.range(1, 9)
                        .map(num2 -> num + " * " + num2 + " = " + num * num2))
                .subscribe(Logger::log);
    }

    @Test
    @DisplayName("flatMap 함수 파라미터가 2개인 경우")
    void flatMapGugudanTest2() {
        Observable.range(2, 9)
                .flatMap(num -> Observable.range(1, 9),
                        (num, num2) -> num + " * " + num2 + " = " + num * num2)
                .subscribe(Logger::log);
    }

    @Test
    @DisplayName("concatMap 함수")
    void concatMapTest() {
        // concatMap 처리 순서를 보장한다.
        Observable.fromArray("영롱","성준","수빈")
                .concatMap(name -> Observable.fromArray(name + ":Java",name + ":Spring",name + ":Node"))
                .subscribe(Logger::log);
    }

    @Test
    @DisplayName("FlatMap : 순서대로 가져오지 않는다. (더 빠르다)")
    void flatMapTest22() {
        System.out.println("# FlatMap start : 순서대로 가져오지 않는다.");
        Observable.interval(200L, TimeUnit.MILLISECONDS)
                .take(5)
                .flatMap(name -> Observable.interval(100L, TimeUnit.MILLISECONDS).take(3))
                .subscribe(Logger::log, Logger::log, () -> System.out.println("# FlatMap end"));

        TimeUtil.sleep(3000L);
    }

    @Test
    @DisplayName("ConcatMap : 순서대로 가져온다. (더 느리다)")
    void concatMapTest22() {
        System.out.println("# concatMap start : 순서대로 온다.");
        Observable.interval(200L, TimeUnit.MILLISECONDS)
                .take(5)
                .concatMap(name -> Observable.interval(100L, TimeUnit.MILLISECONDS).take(3))
                .subscribe(Logger::log, Logger::log, () -> System.out.println("# concatMap end"));
        TimeUtil.sleep(3000L);
    }

    @Test
    @DisplayName("swithMap : 순서대로 가져오지 않는다. 다만 새로운 데이터가 통지되면 현재 처리중이던 작업을 중단한다.")
    void switchMapTest22() {
        System.out.println("# switchMap start.");
        Observable.interval(200L, TimeUnit.MILLISECONDS)
                .take(5)
                .switchMap(name -> Observable.interval(80L, TimeUnit.MILLISECONDS).take(3))
                .subscribe(Logger::log, Logger::log, () -> System.out.println("# switchMap end"));
        TimeUtil.sleep(5000L);
    }
}
