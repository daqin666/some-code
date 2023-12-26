package com.example.somecode.proxy;

import org.springframework.cglib.core.DebuggingClassWriter;

public class Client {

    public static void main(String[] args) {
        // jkd 动态代理
        JdkProxy jdkProxy = new JdkProxy();
        ServiceFather jdkServiceFather = (ServiceFather) jdkProxy.getProxy(new ServiceA());
        jdkServiceFather.test2();
        System.out.println();

        ServiceFather jdkServiceFather2 = (ServiceFather) jdkProxy.getProxy(new ServiceB());
        jdkServiceFather2.test2();
        System.out.println();


        // cglib 动态代理
//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./cglib");
        CglibProxy cglibProxy = new CglibProxy();
        ServiceA cglibServiceA = (ServiceA) cglibProxy.getProxy(ServiceA.class);
        cglibServiceA.test();
        System.out.println();
        cglibServiceA.test2();
        System.out.println("===========");

        ServiceB cglibServiceB = (ServiceB) cglibProxy.getProxy(ServiceB.class);
        cglibServiceB.test();
        System.out.println();
        cglibServiceB.test2();
        System.out.println("===========");
    }
}
