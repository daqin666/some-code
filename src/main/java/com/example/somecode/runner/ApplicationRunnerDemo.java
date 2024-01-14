package com.example.somecode.runner;

import com.example.somecode.timetask.TimerTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//@Order(Ordered.HIGHEST_PRECEDENCE)
@Order(2)
@Component
@Slf4j
public class ApplicationRunnerDemo implements ApplicationRunner {

    @Autowired
    private TimerTest timerTest;

    /**
     * java -jar spring-boot-runner-demo-1.0-SNAPSHOT.jar --test=show p1 p2 p3
     * @param args incoming application arguments
     * @throws Exception 业务异常
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("ApplicationRunnerDemo start....");
//        timerTest.startTask();
//        timerTest.startTask1DayTest();
    }
}
