package com.example.rxstart.study;

import com.example.rxstart.common.SampleData;
import com.example.rxstart.util.Logger;
import com.example.rxstart.util.TimeUtil;
import io.reactivex.rxjava3.core.Observable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class DataCombinationTest {
    @Test
    @DisplayName("merge() 함수")
    void test() {
        /* 여러 개의 Observable을 하나의 Observable로 합치는 함수
         * 통지시점이 빠른 Observable의 데이터를 먼저 통지
         * 두 Observable이 동시에 통지하는 데이터는 순서를 보장하지 않는다.
         */
        Observable<Long> observable1 = Observable.interval(200L, TimeUnit.MILLISECONDS)
                .take(5);

        Observable<Long> observable2 = Observable.interval(400L, TimeUnit.MILLISECONDS)
                .take(5)
                .map(data -> data + 100L);

        Observable.merge(
                observable2,
                observable1
        ).subscribe(Logger::log);

        TimeUtil.sleep(3000L);
    }

    @Test
    @DisplayName("merge() 함수 [3가지 Observable을 하나로 합치기]")
    void test2() {
        Observable<String> speedA
                = SampleData.getSpeedPerSection("A", 55L, SampleData.speedOfSectionA);
        Observable<String> speedB
                = SampleData.getSpeedPerSection("B", 100L, SampleData.speedOfSectionB);
        Observable<String> speedC
                = SampleData.getSpeedPerSection("C", 77L, SampleData.speedOfSectionC);

        Observable.merge(speedA, speedB, speedC)
                .subscribe(Logger::log);

        TimeUtil.sleep(1000L);
    }

    @Test
    @DisplayName("concat() 함수")
    void test3() {
        /* 여러 개의 Observable을 하나의 Observable로 합치는 함수
         * 두 Observable이 동시에 통지하는 데이터는 순서를 보장하지 않는다.
         * 하나의 Observable이 통지가 완료된 이후에 다음 Observable이 통지를 시작한다.
         */
        Observable<Long> observable1 = Observable.interval(200L, TimeUnit.MILLISECONDS)
                .take(5);

        Observable<Long> observable2 = Observable.interval(400L, TimeUnit.MILLISECONDS)
                .take(5)
                .map(data -> data + 100L);

        Observable.concat(
                observable1,
                observable2
        ).subscribe(Logger::log);

        TimeUtil.sleep(3000L);
    }

    @Test
    @DisplayName("concat test2")
    void concatTest2() {
        // concat은 순서대로 통지한다.
        SampleData.getSpeedPerSection("A", 55L, SampleData.speedOfSectionA);

        Observable.concat(
                SampleData.getSpeedPerSection("A", 55L, SampleData.speedOfSectionA),
                SampleData.getSpeedPerSection("B", 100L, SampleData.speedOfSectionB),
                SampleData.getSpeedPerSection("C", 77L, SampleData.speedOfSectionC)
        ).subscribe(Logger::log);

        TimeUtil.sleep(2000L);
    }

    @Test
    @DisplayName("zip() 함수")
    void test4() {
        /* 여러 개의 Observable을 하나의 Observable로 합치는 함수
         * 각 Observable에서 통지된 데이터가 모두 모이면 각 Observable에서 동일한 [index]의 데이터로 새로운 데이터를 생성한 후 통지한다.
         * 통지하는 데이터 개수가 가장 적은 Observable의 통지 시점에 맞춰서 완료 통지 시점을 맞춘다.
         */
        Observable<String> observable1 = Observable.interval(200L, TimeUnit.MILLISECONDS)
                .take(3)
                .map(data -> "A" + data);

        Observable<String> observable2 = Observable.interval(400L, TimeUnit.MILLISECONDS)
                .take(5)
                .map(data -> "B" + data);

        Observable.zip(
                observable1,
                observable2,
                (a, b) -> a + " : " + b
        ).subscribe(Logger::log);

        // 두 개중 적은 3개에 맞춰서 데이터가 통지된다.

        TimeUtil.sleep(3000L);
    }

    @Test
    @DisplayName("combineLatest() 함수")
    void combineTest() {
        Observable<Long> observable1 = Observable.interval(200L, TimeUnit.MILLISECONDS)
                .take(5);
        Observable<Long> observable2 = Observable.interval(300L, TimeUnit.MILLISECONDS)
                .take(4);

        Observable.combineLatest(
                observable1,
                observable2,
                (a, b) -> a + " : " + b
        ).subscribe(Logger::log);

        TimeUtil.sleep(2000L);
    }

}
