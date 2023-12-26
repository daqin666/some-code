package com.example.somecode.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@Slf4j
public class CommandLineRunnerDemo implements CommandLineRunner {

    /**
     * java -jar .\spring-boot-runner-demo-1.0-SNAPSHOT.jar --test=show p1 p2 p3
     * @param args incoming main method arguments
     * @throws Exception 业务异常
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("CommandLineRunnerDemo start...");
    }
}
