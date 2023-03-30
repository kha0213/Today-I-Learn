package com.example.rxstart.publisher;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ColdPublisherTest {
    @Test
    @DisplayName("Cold Publisher 학습")
    void test() {
        // Flowable, Observable 은 Cold Publisher
        Flowable<Integer> flowable = Flowable.just(1, 3, 5, 7);

        flowable.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        flowable.subscribe(data -> System.out.println("Subscriber #2 => " + data));
    }
}
