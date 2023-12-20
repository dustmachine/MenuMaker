package com.northwoodsfoundry.menumaker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class SomeAsyncServiceTesting {

    private Logger log = LoggerFactory.getLogger(SomeAsyncServiceTesting.class);

    @Async
    public void asyncMethodWithVoidReturnType() {
        log.info("Now executing this method asyncrhonously. {}", Thread.currentThread().getName());
    }

    public void loadAllMyRecords() {
        if (System.currentTimeMillis() > 0) {
            // make a call to async method
            asyncMethodWithVoidReturnType();
        }
    }

}
