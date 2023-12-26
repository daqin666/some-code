package com.example.somecode.timetask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTask {

    public void startTask() {
        // 创建任务队列；10为线程数量
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        //执行任务
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
            String dateStr = sdf.format(new Date());
            System.out.println("ScheduledExecutorService执行定时任务：" + dateStr);
        },1,2, TimeUnit.SECONDS);// 1s 后开始执行，每 2s 执行一次
    }
}
