package com.example.batch.external;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExternalService {

    public void serviceMethodWithNoParameter() {
        log.info("ExternalService serviceMethodWithNoParameter 실행! ");
    }

    public void serviceMethodWithParameter(String message) {
        log.info("ExternalService serviceMethod 실행! message:[{}]", message);
    }
}
