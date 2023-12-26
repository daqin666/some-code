package com.example.somecode.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;


@Slf4j
@Service
public class ExportService {

    @Autowired
    @Qualifier("exportServiceExecutor")
    private ThreadPoolTaskExecutor exportExecutor;

    @Autowired
    @Qualifier("export2ServiceExecutor")
    private ThreadPoolTaskExecutor exportExecutor2;

    public void test1() {
        exportExecutor.execute(() -> System.out.println("aaa"));
    }

    public void test2() {
        exportExecutor2.execute(() -> System.out.println("bbb"));
    }

    @Async("syncExecutor")
    public void test3() {
        System.out.println("ccc");
    }


}
