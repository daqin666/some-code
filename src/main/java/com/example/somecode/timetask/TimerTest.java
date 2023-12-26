package com.example.somecode.timetask;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

@Component
@Slf4j
public class TimerTest {
    private static final long PERIOD_10_MIN = 600000; // 10分钟=10 * 60 * 1000
    private static final long PERIOD_1_DAY = 86400000; // 一天=24 * 60 * 60 * 1000;
    private static final long PERIOD_2_HOUR = 7200000; // 2小时=2 * 60 * 60 * 1000

    private static final long DELAY_DEFAULT = 5000; // 5秒=5 * 1000;

    /**
     * 每天执行一次的定时任务获取delay
     * @param targetTime 启动时间
     */
    @Deprecated
    public long getDelay4PerDay(long targetTime) {
        long delayTime;
        LocalTime localTime = LocalTime.now();
        long now = localTime.getLong(ChronoField.MILLI_OF_DAY);
        long period = now - targetTime;
        if(period < 0){
            delayTime = Math.abs(period);
        } else {
            delayTime = PERIOD_1_DAY - period;
        }
        log.info("delayTime========={} ", delayTime);
        return delayTime;
    }

    /**
     * 每天执行一次的定时任务获取delay
     * @param targetHour 启动时间
     */
    public long getDelay4PerDay(int targetHour) {
        long delay;
        LocalTime nowTime = LocalTime.now();
        LocalTime targetTime = LocalTime.of(targetHour, 0, 0);
        Duration between = Duration.between(nowTime, targetTime);
        long diffMillis = between.toMillis();
        if(diffMillis > 0) {
            delay = diffMillis;
        } else {
            delay = PERIOD_1_DAY + diffMillis;
        }
        return delay;
    }

    /**
     * 获取每周运行一次定时任务的延迟时间
     * @param dayOfWeek dayOfWeek
     * @param targetHour 目标小时
     * @return 延迟时间
     */
    public long getDelay4PerWeek(DayOfWeek dayOfWeek, int targetHour) {
        // 忽略周日0-2点之前启动任务时候的判断，依然下个周日再执行任务
        long delayTime;
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime nextSundayTime = localDateTime.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        LocalDateTime targetTime = nextSundayTime.withHour(targetHour).withMinute(0).withSecond(0).withNano(0);
        Duration duration = Duration.between(localDateTime, targetTime);
        delayTime = duration.toMillis();
        return delayTime;
    }

    /**
     * 获取每周运行一次定时任务的延迟时间
     * @param targetWeekDay 目标周几
     * @param targetHour 目标小时
     * @return 延迟时间
     * eg: targetWeekDay=1, targetHour=2, 表示每周日凌晨两点
     */
    @Deprecated
    public long getDelay4PerWeek(int targetWeekDay, int targetHour) {
        long delayTime;
        Calendar calendar = Calendar.getInstance();
        long nowTimeInMillis = calendar.getTimeInMillis();
        int nowDay = calendar.get(Calendar.DAY_OF_WEEK);
        int periodDay = 7 - nowDay + targetWeekDay;

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        calendar.set(Calendar.HOUR_OF_DAY, 2);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if(periodDay == 7 && hour < targetHour) {
            long targetTimeMillis = calendar.getTimeInMillis();
            delayTime = targetTimeMillis - nowTimeInMillis;
            return delayTime;
        }
        calendar.add(Calendar.DAY_OF_YEAR, periodDay);
        long targetTimeMillis = calendar.getTimeInMillis();
        delayTime = targetTimeMillis - nowTimeInMillis;
        return delayTime;
    }


    public void startTask() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                // 具体业务方法
                System.out.println("aa");
            }
        };

        // 计时器
        Timer timer = new Timer();

        /**
         * schedule侧重保持间隔时间的稳定
         * scheduleAtFixedRate侧重保持执行频率的稳定
         * schedule的策略是错过了就错过了，后续按照新的节奏来走；
         * scheduleAtFixedRate的策略是如果错过了，就努力追上原来的节奏（制定好的节奏）。
         */
        // 添加执行任务（延迟 1s 执行，每 3s 执行一次）
//        timer.schedule(timerTask, 1000, 3000);
        timer.scheduleAtFixedRate(timerTask, DELAY_DEFAULT, 3000);
    }

    /**
     * 每10分钟一次的定时任务
     */
    public void startTask10Min() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                // 具体业务方法
                System.out.println("bb");
            }
        };

        // 计时器
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, DELAY_DEFAULT, PERIOD_10_MIN);
    }

    /**
     * 每天凌晨两点的定时任务
     */
    public void startTask1Day() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                // 具体业务方法
                System.out.println("bb");
            }
        };

        // 计时器
        Timer timer = new Timer();
//        long delay = getDelay4PerDay(PERIOD_2_HOUR);
        long delay = getDelay4PerDay(2);
        timer.scheduleAtFixedRate(timerTask, delay, PERIOD_1_DAY);
    }

    /**
     * 每周日凌晨两点的定时任务
     */
    public void startTask1Week() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                // 具体业务方法
                System.out.println("bb");
            }
        };

        // 计时器
        Timer timer = new Timer();
//        int weekDay = 1; // 周日
//        int hour = 2; // 2点
//        long delay = getDelay4PerWeek(weekDay, hour);

        DayOfWeek dayOfWeek = DayOfWeek.SUNDAY; // 周日
        int hour = 2; // 2点
        long delay = getDelay4PerWeek(dayOfWeek, hour);
        timer.scheduleAtFixedRate(timerTask, delay, PERIOD_1_DAY);
    }


}
