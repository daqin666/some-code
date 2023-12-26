package com.example.somecode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler {


    private Object target;
    public Object getProxy(Object target){
        this.target = target;
        ClassLoader classLoader = this.target.getClass().getClassLoader(); // 目标类的类加载
        Class<?>[] interfaces = this.target.getClass().getInterfaces(); // 代理需要实现的接口，可指定多个
        Object proxyInstance = Proxy.newProxyInstance(classLoader, interfaces, this);
        return proxyInstance;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //调用方法之前，我们可以添加自己的操作
        System.out.println("before method " + method.getName());
        Object result = method.invoke(target, args);
        //调用方法之后，我们同样可以添加自己的操作
        System.out.println("after method " + method.getName());
        return result;
    }
}
