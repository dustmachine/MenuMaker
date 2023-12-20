package com.northwoodsfoundry.menumaker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataLoadingService {

    @Autowired
    SomeAsyncService someAsyncService;

    public void loadAllMyRecords() throws InterruptedException {
        if (System.currentTimeMillis() > 0) {
            // make a call to async method
            someAsyncService.asyncMethodWithVoidReturnType();
        }
    }

}
