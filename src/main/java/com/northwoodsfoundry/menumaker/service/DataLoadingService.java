package com.northwoodsfoundry.menumaker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class SomeAsyncService {

    private Logger log = LoggerFactory.getLogger(SomeAsyncService.class);

    @Async
    public CompletableFuture<Void> asyncMethodWithVoidReturnType() {
        System.out.println("Now executing on thread: " + Thread.currentThread().getName());
        return CompletableFuture.completedFuture(null);
    }

    public void loadAllMyRecords() throws InterruptedException {
        if (System.currentTimeMillis() > 0) {
            // make a call to async method
            asyncMethodWithVoidReturnType();
        }
    }

}
