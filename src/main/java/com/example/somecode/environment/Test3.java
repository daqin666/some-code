package com.example.somecode.environment;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Test3 implements EnvironmentAware {
    private static Environment environment;

    @Override
    public void setEnvironment(Environment env) {
        if(environment == null) {
            environment = env;
        }
    }

    public void test() {
        String java_home = environment.getProperty("JAVA_HOME");
        System.out.println(java_home);
    }
}
