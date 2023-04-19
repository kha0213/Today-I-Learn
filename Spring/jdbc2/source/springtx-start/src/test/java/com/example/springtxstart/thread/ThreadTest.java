package com.example.springtxstart.thread;

import com.zaxxer.hikari.HikariPoolMXBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@SpringBootTest
@Import(ThreadTest.QueryService.class)
@Slf4j
public class ThreadTest {

    HikariPoolMXBean poolProxy;

    /** 차종 ID */
    List<Integer> carIds = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    @Service
    @Transactional
    static class QueryService {
        /** 요금 계산 쿼리가 1초 걸린다 가정 */
        public int cal(int carId) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return carId * 1000;
        }
    }

    @BeforeEach
    void setUp() throws MalformedObjectNameException {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName poolName = new ObjectName("com.zaxxer.hikari:type=Pool HikariPool-1");
        poolProxy = JMX.newMXBeanProxy(mBeanServer, poolName, HikariPoolMXBean.class);
    }

    @Autowired
    QueryService queryService;

    @Test
    @DisplayName("단일 쓰레드 방식")
    void single_thread_test() {
        long start = System.currentTimeMillis();
        List<Integer> result = single_thread();
        long end = System.currentTimeMillis();
        log.info("time = " + (end - start));
        log.info("result = " + result);
    }

    @Test
    @DisplayName("멀티 쓰레드 방식")
    void multi_thread_test() {
        long start = System.currentTimeMillis();
        List<Integer> result = multi_thread();
        long end = System.currentTimeMillis();
        log.info("time = " + (end - start));
        log.info("result = " + result);
    }

    @Test
    @DisplayName("퓨쳐 방식")
    void future_test() {
        long start = System.currentTimeMillis();
        List<Integer> result = run_future();
        long end = System.currentTimeMillis();
        log.info("time = " + (end - start));
        log.info("result = " + result);
    }

    /** 퓨처 방식 */
    List<Integer> run_future() {
        CompletableFuture<Integer>[] tasks = new CompletableFuture[carIds.size()];

        for (int i = 0; i < tasks.length; i++) {
            int carId = carIds.get(i);
            tasks[i] = CompletableFuture.supplyAsync((() -> queryService.cal(carId)));
        }

        CompletableFuture.allOf(tasks).join();

        return Arrays.stream(tasks)
                .map(task -> {
                    try {
                        return task.get();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    /** 단일 쓰레드 방식 */
    List<Integer> single_thread() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < carIds.size(); i++) {
            result.add(queryService.cal(carIds.get(i)));
        }
        return result;
    }

    /** 멀티 쓰레드 방식 */
    List<Integer> multi_thread() {
        return carIds.parallelStream()
                .map(queryService::cal)
                .collect(Collectors.toList());
    }
}
