package com.example.somecode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SomeCodeApplication {
    private static Logger LOGGER = LoggerFactory.getLogger(SomeCodeApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SomeCodeApplication.class, args);
        LOGGER.info("中文日志测试");
    }

}
