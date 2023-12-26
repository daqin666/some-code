package com.example.somecode.threadpool;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Component
public class HaveReturn2 {

    /** 使用 Future、ListenableFuture、CompletableFuture
     * Future、ListenableFuture、CompletableFuture区别
     * Future为异步任务调用的结果
     * ListenableFuture继承了Future，所以也为异步任务调用的结果，但是ListenableFuture还阔以添加两个回调函数，成功回调和异常回调
     * CompletableFuture也继承了Future，所以也为异步任务调用的结果，但是CompletableFuture阔以对异步任务进行编排
     */

    @Async
    public Future<String> invokeAsyncTest01() {
        System.out.println(Thread.currentThread() + "运行了invokeAsyncTest01方法！");
        return new AsyncResult<>("Hello World!");
    }

    @Async
    public ListenableFuture<String> invokeAsyncTest02() {
        System.out.println(Thread.currentThread() + "运行了invokeAsyncTest02方法！");
        return new AsyncResult<String>("Hello World!");

    }

    @Async
    public CompletableFuture<String> invokeAsyncTest03() {
        System.out.println(Thread.currentThread() + "运行了invokeAsyncTest03方法！");
        return CompletableFuture.completedFuture("Hello world!");
    }


}
