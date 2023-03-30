package com.example.rxstart.back;

import ch.qos.logback.core.util.TimeUtil;
import io.reactivex.rxjava3.core.BackpressureOverflowStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class BackPressureTest {

    @Test
    @DisplayName("통지된 데이터가 버퍼의 크기를 초과하면 MissingBackpressureException 에러 발생")
    void test() throws InterruptedException {
        Flowable.interval(1L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> print("doOnNext", data))
                .observeOn(Schedulers.computation())
                .subscribe(data -> {
                            print("subscribe before", data);
                            Thread.sleep(20L);
                            print("subscribe after", data);
                        }, error -> System.out.println("에러 = " + error)
                        , () -> System.out.println("완료"));
        Thread.sleep(1000L);
    }

    @Test
    @DisplayName("DROP_LATEST : 버퍼의 크기를 초과하는 데이터는 버림")
    void test2() throws InterruptedException {
        // 버퍼가 가득 찬 시점에 버퍼내 에서 가장 최근에 버퍼로 들어온 데이터를 DROP
        // DROP 된 빈 자리에 버퍼 밖에서 대기 하던 데이터를 채운다.
        System.out.println("# start : " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> print("interval doOnNext", data))
                .onBackpressureBuffer(2,
                        () -> System.out.println("overflow!"),
                        BackpressureOverflowStrategy.DROP_LATEST)
                .doOnNext(data -> print("onBackpressureBuffer doOnNext", data))
                .observeOn(Schedulers.computation(), false, 1)
                .subscribe(data -> {
                            print("subscribe before", data);
                            Thread.sleep(1000L);
                            print("subscribe after", data);
                        }, error -> System.out.println("에러 = " + error)
                        , () -> System.out.println("완료"));
        Thread.sleep(2800L);
    }



    private void print(String subscribe, Long data) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "[" + subscribe + "] : " + data);
    }
}

