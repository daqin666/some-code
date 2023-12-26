package com.example.somecode.timetask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class SpringTask {

    /** 1.启动类增加启动注解  @EnableScheduling 开启定时任务 **/

    /** 2.编写具体定时任务方法 **/

    /**
     * cron表达式：表示每2秒 执行任务
     */
    @Scheduled(cron = "0/2 * * * * ?")
    public void task() {
        System.out.println("task0-start");
        sleep(5);
        System.out.println("task0-end");
    }

    /**
     * fixedRate：每间隔2秒执行一次任务
     * 注意，默认情况下定时任务是在同一线程同步执行的，如果任务的执行时间（如5秒）大于间隔时间，
     * 则会等待任务执行结束后直接开始下次任务
     */
    @Scheduled(fixedRate = 2000)
    public void task0() {
        System.out.println("task0-start");
        sleep(5);
        System.out.println("task0-end");
    }

    /**
     * fixedDelay：每次延时2秒执行一次任务
     * 注意，这里是等待上次任务执行结束后，再延时固定时间后开始下次任务
     */
    @Scheduled(fixedDelay = 2000)
    public void task1() {
        System.out.println("task1-start");
        sleep(5);
        System.out.println("task1-end");
    }

    /**
     * initialDelay：首次任务启动的延时时间
     */
    @Scheduled(initialDelay = 2000, fixedDelay = 3000)
    public void task2() {
        System.out.println("task2-start");
        sleep(5);
        System.out.println("task2-end");
    }


    private void sleep(long time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
