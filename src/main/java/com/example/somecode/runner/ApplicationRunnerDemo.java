package com.example.somecode.runner;

import com.example.somecode.timetask.TimerTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * CommandLineRunner 和 ApplicationRunner这俩个类的执行时机都是一样的，都是在SpringBoot启动的倒数第二步开始执行。
 * 倒数第二步基本上等于SpringBoot已经启动完毕了。可以去看SpringApplication.run(Application.class, args);run方法的源码。
 *
 * CommandLineRunner 和 ApplicationRunner这俩个类谁先执行？
 * 同等优先级的情况下ApplicationRunner类先执行。
 * 不同等优先级的情况下：看@Order(1)的值，@Order(1)的值越小，越先执行。
 */
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
