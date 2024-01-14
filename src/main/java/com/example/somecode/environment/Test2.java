package com.example.somecode.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Test2 {
    @Autowired
    private ApplicationContext context;

    public void test() {
        Environment environment = context.getEnvironment();
        String port = environment.getProperty("server.port");
        System.out.println(port);
        String[] activeProfiles = environment.getActiveProfiles();
        String[] defaultProfiles = environment.getDefaultProfiles();
    }
}
