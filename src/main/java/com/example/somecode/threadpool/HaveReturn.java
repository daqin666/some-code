package com.example.somecode.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
@Slf4j
public class HaveReturn {

    @Autowired
    @Qualifier("haveReturnExecutor")
    private ThreadPoolTaskExecutor haveReturnExecutor;

    /**
     * 使用 CountDownLatch 同步工具类
     */
    public void testA() {
        List<String> listTask = new ArrayList<>(Arrays.asList("a", "b"));
        List<String> listReturn = new ArrayList<>();
        CountDownLatch downLatch = new CountDownLatch(listTask.size());
        for (String task : listTask) {
            haveReturnExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        listReturn.add(task);
                    } catch (Exception ex) {
                        log.error("循环开启线多线程报错，调用下游系统出现错误，异常：" + ex);
                    } finally {
                        // 业务逻辑处理完毕，计数器减一【当前线程处理任务完毕，线程释放进入线程池，等待处理下一个任务】
                        downLatch.countDown();
                    }
                }
            });
        }

        // 主线程需要等待子任务线程执行完，结果汇总之后，主线程继续往下执行
        try {
            downLatch.await();
        } catch (Exception e) {
            log.error("等待超时", e);
            throw new RuntimeException("系统处理超时，请稍后再试");
        }

        // 涉及返回值的业务
        for (String str : listReturn) {
            System.out.println(str);
        }
    }

    /**
     * 使用 Callable
     */
    public void testB1() {
        Future<String> submit = haveReturnExecutor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        });

        ListenableFuture<String> listenableFuture = haveReturnExecutor.submitListenable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        });
    }

    /**
     * 使用 runnable ,不行，没必要
     */
    public void testB2() throws ExecutionException, InterruptedException {
        Data data = new Data();
        Future<?> submit = haveReturnExecutor.submit(new MyRunnable(data));
        System.out.println(submit.get());

        ListenableFuture<?> listenableFuture = haveReturnExecutor.submitListenable(new MyRunnable(data));
        System.out.println(listenableFuture.get());
    }

    static class Data {
        private String name;

        public void setName(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class MyRunnable implements Runnable {
        Data data;

        public MyRunnable(Data data){
            this.data = data;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                data.setName("aa");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
