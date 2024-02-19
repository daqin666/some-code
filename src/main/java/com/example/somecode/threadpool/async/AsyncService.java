package com.example.somecode.threadpool.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
public class AsyncService {

    /**
     * 1.异步方法上添加 @Async，启动类或者线程池配置类增加 @EnableAsync， 最好是使用自定义的线程池启动
     * 2.线程池启动线程
     * 3.基本的三种线程创建方式执行方法 - 一般不这么干
     * 4.CompletableFuture实现异步
     * 5.Spring ApplicationEvent事件实现异步 - 如 AsyncSendEmailEventHandler 类
     * 6.消息队列
     * 7.ThreadUtil异步工具类
     */



    @Async("syncExecutor")
    public void test1(){
        System.out.println("test...");
    }

    /**
     * 不需要显示的使用ExecutorService，CompletableFuture内部使用了ForkJoinPool来处理异步任务，
     * 如果在某些业务场景我们想自定义自己的异步线程池也是可以的。
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void test4() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread()+"cf1 do something....");
            return 1;
        });

        CompletableFuture<Void> cf2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread()+"cf2 do something....");
            return null;
        });
        //等待任务1执行完成
        System.out.println("cf1结果->"+cf1.get());
        //等待任务2执行完成
        System.out.println("cf2结果->"+cf2.get());
    }

    public void test7(){
//        for (int i = 0; i < 3; i++) {
//            ThreadUtil.execAsync(()->{
//                ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
//                int num= threadLocalRandom.next(20)+1;
//                System.out.println(num);
//            });
//            log.info("当前第"+i+"个线程");
//        }
//        log.info("task finish!");
    }
}


