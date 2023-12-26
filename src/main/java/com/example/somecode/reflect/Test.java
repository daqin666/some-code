package com.example.somecode.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        /** 三种方式 **/
        Class<?> c1 = Class.forName("com.example.somecode.reflect.Person");
        Class<?> c2 = Person.class;

        Person person = new Person();
        Class<?> c3 = person.getClass();

        /** 常用方法 **/
        Object[] signers = c1.getSigners(); // /ˈsaɪnərz/ 签名
        String canonicalName = c1.getCanonicalName(); // 用于返回基础类的授权名称
        String name = c1.getName();
        String simpleName = c1.getSimpleName();
        String typeName = c1.getTypeName();
        Type[] genericInterfaces = c1.getGenericInterfaces(); // 用于获取由该实体直接实现的接口的类型
        Type genericSuperclass = c1.getGenericSuperclass();
        Class<?>[] interfaces = c1.getInterfaces();
        Class<?> superclass = c1.getSuperclass();
        Annotation[] annotations = c1.getAnnotations();
        Constructor<?>[] constructors = c1.getConstructors();
        Method[] methods = c1.getMethods();
        Field[] fields = c1.getFields();

        Annotation[] declaredAnnotations = c1.getDeclaredAnnotations();
        Constructor<?>[] declaredConstructors = c1.getDeclaredConstructors();
        Method[] declaredMethods = c1.getDeclaredMethods();
        Field[] declaredFields = c1.getDeclaredFields();

        /** 暴力访问 **/
//        constructor.setAccessible(true);
//        method.setAccessible(true);
//        filed.setAccessible(true);

        // 普通类
//        System.out.println(AAA.class.getName()); // lang.reflect.AAA
//        System.out.println(AAA.class.getCanonicalName()); // lang.reflect.AAA
//        System.out.println(AAA.class.getSimpleName()); // AAA
//        System.out.println(AAA.class.getTypeName()); // lang.reflect.AAA
    }
}
