package com.example.somecode.threadlocal;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class Test1 {
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @SneakyThrows
    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            threadLocal.set(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + "完成了签名");
            try {
                // 睡眠2秒，以保证李四线程先执行完成
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "查看了自己的数据，签名：" + threadLocal.get());
        }, "张三");
        Thread threadB = new Thread(() -> {
            threadLocal.set(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + "完成了签名");
            System.out.println(Thread.currentThread().getName() + "查看了自己的数据，签名：" + threadLocal.get());
        }, "李四");
        threadA.start();
        // 先启动张三线程，再启动李四线程
        TimeUnit.SECONDS.sleep(1);
        threadB.start();
        // 释放内存，防止造成内存泄漏
        threadLocal.remove();
    }
}
