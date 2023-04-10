package com.example.rxstart.study;

import com.example.rxstart.common.CarMaker;
import com.example.rxstart.common.SampleData;
import com.example.rxstart.util.Logger;
import com.example.rxstart.util.TimeUtil;
import io.reactivex.rxjava3.core.Observable;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import javax.print.DocFlavor;
import java.util.concurrent.TimeUnit;

public class FilteringTest {
    @Test
    @DisplayName("filter 학습")
    void filterTest() {
        Observable.fromIterable(SampleData.carList)
                .filter(data -> data.getCarMaker() == CarMaker.SSANGYOUNG)
                .subscribe(System.out::println);
    }

    @Test
    @DisplayName("filter2 학습")
    void filterTest2() {
        Observable.fromIterable(SampleData.carList)
                .filter(data -> data.getCarPrice() > 30000000)
                .subscribe(System.out::println);
    }

    @Test
    @DisplayName("distinct 학습")
    void distinctTest() {
        Observable.fromArray(SampleData.carMakersDuplicated)
                .distinct()
                .subscribe(System.out::println);
    }

    @Test
    @DisplayName("take 학습")
    void takeTest() {
        // 파라미터에서 지정한 갯수나 기간 될 때까지 통지한다.
        Observable.just("A", "B", "C", "D", "E", "F")
                .take(3)
                .subscribe(System.out::println);
        // A, B, C 까지만 통지된다.

        Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .take(3, TimeUnit.SECONDS)
                .subscribe(System.out::println);
        // 0, 1, 2 까지만 통지된다.

        TimeUtil.sleep(4000L);
    }

    @Test
    @DisplayName("takeUntil 학습 1. 파라미터로 지정한 조건이 TRUE가 될 때까지 통지한다.")
    void takeUntilTest() {
        Observable.just("A", "B", "C", "D", "E", "F")
                .takeUntil(s -> s.length() == 1 && "C".equals(s))
                .subscribe(Logger::log);
        // A, B, C 까지만 통지된다.
    }

    @Test
    @DisplayName("takeUntil 학습 2. 파라미터로 지정한 Observable이 통지할 때까지 통지한다.")
    void takeUntilTest2() {
        Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .takeUntil(Observable.timer(3000L, TimeUnit.MILLISECONDS))
                .subscribe(System.out::println);
        // 0, 1, 2 까지만 통지된다.

        TimeUtil.sleep(4000L);
    }

    @Test
    @DisplayName("skip 학습 1. 앞의 것을 스킵하고 통지한다.")
    void skipTest() {
        Observable.range(1, 10)
                .skip(3)
                .subscribe(System.out::println);
        // 4, 5, 6, 7, 8, 9, 10 만 통지된다.
    }

    @Test
    @DisplayName("skip 학습 2. 시간으로 스킵한다. ")
    void skipTest2() {
        Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .skip(2, TimeUnit.SECONDS)
                .subscribe(System.out::println);
        // 2초까지의 0, 1은 스킵되고
        // 2, 3 만 통지된다.

        TimeUtil.sleep(4000L);
    }
}
