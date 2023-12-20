package com.northwoodsfoundry.menumaker.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SomeAsyncServiceTest {

    @Autowired
    DataLoadingService dataLoadingService;

    @Test
    void loadAllMyRecords() throws InterruptedException {
        System.out.println("Start - invoking the load records method " + Thread.currentThread().getName());
        dataLoadingService.loadAllMyRecords();
        System.out.println("End - finished invoking the load records method " + Thread.currentThread().getName());
    }
}