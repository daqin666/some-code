package com.example.somecode.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test2 {
    private static String[] dateArray = {"2023-01-01 09:00:00", "2023-02-01 10:00:00", "2023-03-01 11:00:00"};

    // 前面我们知道直接调用get()，返回的值为null，因此我们需要提前设置初始化值
    private static ThreadLocal<SimpleDateFormat> threadLocal =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(() -> {
                SimpleDateFormat simpleDateFormat = threadLocal.get();
                try {
                    Date date = simpleDateFormat.parse(dateArray[finalI]);
                    System.out.println(simpleDateFormat.format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
