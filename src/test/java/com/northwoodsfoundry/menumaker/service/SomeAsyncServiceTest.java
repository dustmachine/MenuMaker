package com.northwoodsfoundry.menumaker.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SomeAsyncServiceTestingTest {

    @Autowired
    SomeAsyncServiceTesting someAsyncServiceTesting;

    @Test
    void loadAllMyRecords() {
        System.out.println("Start - invoking the load records method " + Thread.currentThread().getName());
        someAsyncServiceTesting.loadAllMyRecords();
        System.out.println("End - finished invoking the load records method " + Thread.currentThread().getName());
    }
}