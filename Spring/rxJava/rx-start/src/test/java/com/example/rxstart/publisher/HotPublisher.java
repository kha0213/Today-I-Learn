package com.example.rxstart.publisher;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.flowables.ConnectableFlowable;
import io.reactivex.rxjava3.processors.PublishProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HotPublisher {
    @Test
    @DisplayName("Hot Publisher 학습")
    void test() {
        // processor 는 Hot Publisher
        PublishProcessor<Integer> publishProcessor = PublishProcessor.create();
        publishProcessor.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        publishProcessor.onNext(1);
        publishProcessor.onNext(3);

        publishProcessor.subscribe(data -> System.out.println("Subscriber #2 => " + data));
        publishProcessor.onNext(5);
        publishProcessor.onNext(7);

        publishProcessor.onComplete();
    }
}
