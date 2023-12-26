package com.example.somecode.timetask;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TimerTestTest extends TestCase {

    @Test
    public void testStartTask() {
        LocalTime localTime = LocalTime.now();
        long now = localTime.getLong(ChronoField.MILLI_OF_DAY);
        System.out.println(now);
    }

    public static void main(String[] args) {
        LocalTime nowTime = LocalTime.now();
        LocalTime targetTime = LocalTime.of(2, 0, 0);
        Duration between = Duration.between(nowTime, targetTime);
        System.out.println(between.toMillis());
        System.out.println(Duration.between(targetTime, nowTime));

    }
}