package com.example.somecode.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Test1 {
    @Autowired
    private Environment environment;

    public void test() {
        String port = environment.getProperty("server.port");
        System.out.println(port);
        String[] activeProfiles = environment.getActiveProfiles();
        String[] defaultProfiles = environment.getDefaultProfiles();
    }
}
