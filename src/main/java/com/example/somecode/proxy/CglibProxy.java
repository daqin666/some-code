package com.example.somecode.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    private Object target;

    public Object getProxy(Class<?> clazz) {
        this.target = target;
        /** 通过cg11b动态代理获取代理对象的过程，创建调用的对象 */
        Enhancer enhancer = new Enhancer();
        /** 设置enhancer对象的父类 */
        enhancer.setSuperclass(clazz);
        /** 设置enhancer的回调对象 */
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("这是前置增强");
        Object res = methodProxy.invokeSuper(o, objects);
        System.out.println("这是后置增强");
        return res;
    }

}
