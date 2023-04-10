package com.example.rxstart.study;

import com.example.rxstart.util.Logger;
import com.example.rxstart.util.TimeUtil;
import io.reactivex.rxjava3.core.Observable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class QuizTest {
    @Test
    @DisplayName("quiz 1. interval, takeWhile을 이용하여 10 이전 데이터를 초당 1개씩 출력")
    void quiz1() {
        Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .takeWhile(data -> data < 10)
                .subscribe(Logger::log);

        TimeUtil.sleep(10000L);
    }

    @Test
    @DisplayName("quiz 2. interval, skipUntil, timer 을 이용하여 10 이전 데이터를 초당 1개씩 출력 (3초 이후부터)")
    void quiz2() {
        Observable.interval(1000L, TimeUnit.MILLISECONDS)
            .skipUntil(Observable.timer(3, TimeUnit.SECONDS))
            .subscribe(Logger::log);

        TimeUtil.sleep(10000L);
    }

    @Test
    @DisplayName("quiz 3. 1부터 15까지 중에 마지막 3개 빼고 출력")
    void quiz3() {
        Observable.range(1, 15)
            .skipLast(3)
            .subscribe(Logger::log);
        TimeUtil.sleep(1000L);
    }
}
