package com.example.somecode.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BeanUtil2 {

    @Autowired
    private ApplicationContext context;

    public void test() {
        Person bean = context.getBean(Person.class);
    }
}
