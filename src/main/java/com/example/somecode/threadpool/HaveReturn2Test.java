package com.example.somecode.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
@Slf4j
public class HaveReturn2Test {

    @Autowired
    private HaveReturn2 haveReturn2;

    public void test() throws ExecutionException, InterruptedException {
        // Future 方式
        Future<String> future = haveReturn2.invokeAsyncTest01();
        String str = future.get();
        log.info(str);

        // ListenableFuture 方式
        ListenableFuture<String> listenableFuture = haveReturn2.invokeAsyncTest02();
        listenableFuture.addCallback(new SuccessCallback<String>() {
            @Override
            public void onSuccess(String s) {
                log.info(s);
            }
        }, new FailureCallback() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error(throwable.getMessage());
            }
        });

        // CompletableFuture 方式
        CompletableFuture<String> completableFuture = haveReturn2.invokeAsyncTest03();
        completableFuture.thenAccept(ac -> System.out.println(ac));
        String s = completableFuture.get();
        log.info(s);
    }
}
