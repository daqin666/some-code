package com.example.somecode.threadpool;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class HaveReturnTest extends TestCase {

    @Autowired
    @Qualifier("haveReturnExecutor")
    private ThreadPoolTaskExecutor haveReturnExecutor;

    @Test
    public void testTestB2() throws ExecutionException, InterruptedException {
        HaveReturn.Data data = new HaveReturn.Data();
        Future<?> submit = haveReturnExecutor.submit(new HaveReturn.MyRunnable(data));
        System.out.println(submit.get());
    }
}