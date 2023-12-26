package com.example.somecode.proxy;

public class ServiceB implements ServiceFather{

    public void test(){
        System.out.println("ServiceB test work....");
    }

    @Override
    public void test2() {
        System.out.println("ServiceB test2 work....");
    }
}
