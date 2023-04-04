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
    @DisplayName("DROP_LATEST 전략")
    void test2() throws InterruptedException {
        // 버퍼가 가득 찬 시점에 버퍼내 에서 가장 최근에 버퍼로 들어온 데이터를 DROP
        // DROP 된 빈 자리에 버퍼 밖에서 대기 하던 데이터를 채운다.
        System.out.println("# start : " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        flowablePrint(BackpressureOverflowStrategy.DROP_LATEST);

        /** 결과
         * RxComputationThreadPool-2[interval doOnNext] : 0             -- 0 데이터 발행 및 버퍼에 저장 (버퍼상태 [0])
         * RxComputationThreadPool-2[onBackpressureBuffer doOnNext] : 0 -- 0 데이터 처리 위임
         * RxComputationThreadPool-1[subscribe before] : 0              -- 0 데이터 처리 시작
         * RxComputationThreadPool-2[interval doOnNext] : 1             -- 1 데이터 발행 및 버퍼에 저장 (버퍼상태 [1])
         * RxComputationThreadPool-2[interval doOnNext] : 2             -- 2 데이터 발행 및 버퍼에 저장 (버퍼상태 [1,2])
         * RxComputationThreadPool-2[interval doOnNext] : 3             -- 3 데이터 발행 및 버퍼에 저장 시도 (버퍼상태 [1,2,3])
         * overflow!                                                    -- 오버플로우 발생! 최근에 들어온 2 데이터는 버림 (버퍼상태 [1,3])
         * RxComputationThreadPool-1[subscribe after] : 0               -- 0 데이터 처리 완료
         * RxComputationThreadPool-1[onBackpressureBuffer doOnNext] : 1 -- 1 데이터 처리 위임
         * RxComputationThreadPool-1[subscribe before] : 1              -- 1 데이터 처리 시작
         * RxComputationThreadPool-2[interval doOnNext] : 4             -- 4 데이터 발행 및 버퍼에 저장 (버퍼상태 [3,4])
         * RxComputationThreadPool-2[interval doOnNext] : 5             -- 5 데이터 발행 및 버퍼에 저장 시도 (버퍼상태 [3,4,5])
         * overflow!                                                    -- 오버플로우 발생! 최근에 들어온 4 데이터는 버림 (버퍼상태 [3,5])
         * RxComputationThreadPool-2[interval doOnNext] : 6             -- 6 데이터 발행 및 버퍼에 저장 시도 (버퍼상태 [3,5,6])
         * overflow!                                                    -- 오버플로우 발생! 최근에 들어온 5 데이터는 버림 (버퍼상태 [3,6])
         * RxComputationThreadPool-1[subscribe after] : 1               -- 1 데이터 처리 완료
         * RxComputationThreadPool-1[onBackpressureBuffer doOnNext] : 3 -- 3 데이터 처리 위임
         * RxComputationThreadPool-1[subscribe before] : 3              -- 3 데이터 처리 시작
         * RxComputationThreadPool-2[interval doOnNext] : 7             -- 7 데이터 발행 및 버퍼에 저장 (버퍼상태 [6,7])
         * RxComputationThreadPool-2[interval doOnNext] : 8             -- 8 데이터 발행 및 버퍼에 저장 시도 (버퍼상태 [6,7,8])
         * overflow!                                                    -- 오버플로우 발생! 최근에 들어온 7 데이터는 버림 (버퍼상태 [6,8])
         */
    }

    private void flowablePrint(BackpressureOverflowStrategy strategy) throws InterruptedException {
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> print("interval doOnNext", data))
                .onBackpressureBuffer(2,
                        () -> System.out.println("overflow!"),
                        strategy)
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

    @Test
    @DisplayName("DROP_OLDEST 전략")
    void dropOldestTest() throws InterruptedException {
        // 버퍼가 가득 찬 시점에 버퍼내 에서 가장 오래된 데이터를 DROP
        // DROP 된 빈 자리에 버퍼 밖에서 대기 하던 데이터를 채운다.

        System.out.println("# start : " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        flowablePrint(BackpressureOverflowStrategy.DROP_OLDEST);

        /**
         * RxComputationThreadPool-2[interval doOnNext] : 0             -- 0 데이터 발행 및 버퍼에 저장 (버퍼상태 [0])
         * RxComputationThreadPool-2[onBackpressureBuffer doOnNext] : 0 -- 0 데이터 처리 위임
         * RxComputationThreadPool-1[subscribe before] : 0              -- 0 데이터 처리 시작
         * RxComputationThreadPool-2[interval doOnNext] : 1             -- 1 데이터 발행 및 버퍼에 저장 (버퍼상태 [1])
         * RxComputationThreadPool-2[interval doOnNext] : 2             -- 2 데이터 발행 및 버퍼에 저장 (버퍼상태 [1,2])
         * RxComputationThreadPool-2[interval doOnNext] : 3             -- 3 데이터 발행 및 버퍼에 저장 시도 (버퍼상태 [1,2,3])
         * overflow!                                                    -- 오버플로우 발생! 가장 오래된 1 데이터는 버림 (버퍼상태 [2,3])
         * RxComputationThreadPool-1[subscribe after] : 0               -- 0 데이터 처리 완료
         * RxComputationThreadPool-1[onBackpressureBuffer doOnNext] : 2 -- 2 데이터 처리 위임 (버퍼상태 [3])
         * RxComputationThreadPool-1[subscribe before] : 2              -- 2 데이터 처리 시작
         * RxComputationThreadPool-2[interval doOnNext] : 4             -- 4 데이터 발행 및 버퍼에 저장 (버퍼상태 [3,4])
         * RxComputationThreadPool-2[interval doOnNext] : 5             -- 5 데이터 발행 및 버퍼에 저장 시도 (버퍼상태 [3,4,5])
         * overflow!                                                    -- 오버플로우 발생! 가장 오래된 3 데이터는 버림 (버퍼상태 [4,5])
         * RxComputationThreadPool-2[interval doOnNext] : 6             -- 6 데이터 발행 및 버퍼에 저장 시도 (버퍼상태 [4,5,6])
         * overflow!                                                    -- 오버플로우 발생! 가장 오래된 4 데이터는 버림 (버퍼상태 [5,6])
         * RxComputationThreadPool-1[subscribe after] : 2               -- 2 데이터 처리 완료
         * RxComputationThreadPool-1[onBackpressureBuffer doOnNext] : 5 -- 5 데이터 처리 위임 (버퍼상태 [6])
         * RxComputationThreadPool-1[subscribe before] : 5              -- 5 데이터 처리 시작
         * RxComputationThreadPool-2[interval doOnNext] : 7             -- 7 데이터 발행 및 버퍼에 저장 (버퍼상태 [6,7])
         * RxComputationThreadPool-2[interval doOnNext] : 8             -- 8 데이터 발행 및 버퍼에 저장 시도 (버퍼상태 [6,7,8])
         * overflow!                                                    -- 오버플로우 발생! 가장 오래된 6 데이터는 버림 (버퍼상태 [7,8])
         */
    }


    private void print(String subscribe, Long data) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "[" + subscribe + "] : " + data);
    }
}

