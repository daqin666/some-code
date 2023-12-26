package com.example.somecode.proxy;

public class ServiceA implements ServiceFather {

    public void test(){
        System.out.println("ServiceA test work....");
    }

    @Override
    public void test2() {
        System.out.println("ServiceA test2 work....");
    }
}
