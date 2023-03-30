package com.example.rxstart.sample;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Sample2Test {
    @Test
    @DisplayName("마블다이어그램")
    void test() {
        Observable.just(1, 25, 9, 15, 7, 30)
                .filter(num -> num > 10)
                .subscribe(System.out::println);
    }

    @Test
    @DisplayName("just() 함수")
    void test2() {
        Observable<Integer> observable =
                Observable.just(1, 25, 9, 15, 7, 30);

        observable.subscribe(System.out::println);
    }

    @Test
    @DisplayName("Hello RxJava")
    void test3() {
        Observable<String> observable =
                Observable.just("Hello", "RxJava");

        observable.subscribe(System.out::println);
    }
}
