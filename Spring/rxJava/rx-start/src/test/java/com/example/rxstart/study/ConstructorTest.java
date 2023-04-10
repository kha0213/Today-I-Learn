package com.example.rxstart.study;

import com.example.rxstart.util.Logger;
import com.example.rxstart.util.TimeUtil;
import io.reactivex.rxjava3.core.Observable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


public class ConstructorTest {
    /**
     * polling 용도로 사용
     */
    @Test
    @DisplayName("interval 학습")
    void test() {
        Observable.interval(0L,500L, TimeUnit.MILLISECONDS)
                .map(data -> data + " count")
                .subscribe(Logger::log);

        TimeUtil.sleep(3000);
    }

    /**
     * range 는 interval 과 비슷하지만, 특정 범위를 지정해서 발행
     */
    @Test
    @DisplayName("range 학습")
    void rangeTest() {
        Observable.range(5, 3)
                .map(data -> data + " count")
                .subscribe(Logger::log);
        TimeUtil.sleep(1000);
    }

    /**
     * timer 는 특정 시간 후에 한번만 발행 (0을 발행하고 성공일 경우 onComplete 호출)
     */
    @Test
    @DisplayName("Timer 학습")
    void timerTest() {
        Logger.log("# start");
        Observable.timer(500L, TimeUnit.MILLISECONDS)
                .map(data -> data + " Do work!")
                .subscribe(Logger::log);
        TimeUtil.sleep(1000);
    }

    /**
     * defer 는 구독이 발생할 때마다 새로운 Observable 을 생성
     * 선언한 시점이 아닌 호출 시점에 데이터를 통지한다.
     * 데이터 생성을 미루는 효과가 있다. (최신 데이터를 통지)
     */
    @Test
    @DisplayName("Defer 학습")
    void deferTest() {
        Observable<LocalTime> observable = Observable.defer(
                () -> Observable.just(LocalTime.now())
        );

        Observable<LocalTime> justObservable = Observable.just(LocalTime.now());

        observable.subscribe(time -> Logger.log("defer() #1 => " + time)); // 08:15:13.083156
        justObservable.subscribe(time -> Logger.log("just() #1 => " + time)); // 08:15:13.081271

        TimeUtil.sleep(3000);

        observable.subscribe(time -> Logger.log("defer() #2 => " + time)); // 08:15:16.096005
        justObservable.subscribe(time -> Logger.log("just() #2 => " + time)); // 08:15:13.081271
    }

    /**
     * fromIterable 은 Iterable 을 받아서 Observable 로 변환
     */
    @Test
    @DisplayName("FromIterable 학습")
    void fromIterableTest() {
        List<String> fruit = List.of("Apple", "Banana", "Orange", "Grape", "Watermelon");

        Observable.fromIterable(fruit)
                .subscribe(Logger::log);
    }

    @Test
    @DisplayName("future 학습")
    void futureTest() throws ExecutionException, InterruptedException {
        Logger.log("# start");
        Future<Integer> future = CompletableFuture.supplyAsync(() -> {
            TimeUtil.sleep(2000);
            return 100;
        });
        Logger.log(future.get());
        Logger.log("# end");

        TimeUtil.sleep(3000);
    }

    @Test
    @DisplayName("FromFuture 학습")
    void fromFutureTest() {
        Logger.log("# start");

        Future<Long> future = getLongTimeWork();

        executeShortTimeWork();

        Observable.fromFuture(future)
                .subscribe(data -> Logger.log("# 긴 처리 결과: " + data));

        Logger.log("# end");
    }

    private void executeShortTimeWork() {
        Logger.log("# 짧게 걸리는 작업 시작");
        TimeUtil.sleep(2000);
        Logger.log("# 짧게 걸리는 작업 종료");
    }

    private Future<Long> getLongTimeWork() {
        return CompletableFuture.supplyAsync(() -> {
            Logger.log("# 오래 걸리는 작업 시작");
            TimeUtil.sleep(5000);
            Logger.log("# 오래 걸리는 작업 종료");
            return 100L;
        });
    }
}
