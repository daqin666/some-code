package com.example.somecode.time;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Slf4j
public class simple {

    public static void main(String[] args) {

    }

    private static SimpleDateFormat SIMPLE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 根据传入的日期和参数将日期对应字段后面所有日期字段清零 参数对应字段说明：1=毫秒, 2=秒, 3=分钟, 4=小时, 5=天, 6=月份
     * 返回的是Calendar类型
     *
     * 样例: 1 Thu Mar 04 10:38:25 CST 2021 2 Thu Mar 04 10:38:00 CST 2021 3 Thu Mar
     * 04 10:00:00 CST 2021 4 Thu Mar 04 00:00:00 CST 2021 5 Mon Mar 01 00:00:00 CST
     * 2021 6 Fri Jan 01 00:00:00 CST 2021
     *
     * @param date     传入的日期时间
     * @param clearNum 1=毫秒, 2=秒, 3=分钟, 4=小时, 5=天, 6=月份
     * @return
     */
    public static Calendar clearDate(Date date, int clearNum) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        // 毫秒
        if (clearNum > 0) {
            cal.set(Calendar.MILLISECOND, 0);
        }
        // 秒

        if (clearNum > 1) {
            cal.set(Calendar.SECOND, 0);
        }
        // 分钟
        if (clearNum > 2) {
            cal.set(Calendar.MINUTE, 0);
        }
        // 小时
        if (clearNum > 3) {
            cal.set(Calendar.HOUR_OF_DAY, 0);
        }
        // 天

        if (clearNum > 4) {
            cal.set(Calendar.DATE, 1);
        }
        // 月份
        if (clearNum > 5) {
            cal.set(Calendar.MONTH, 0);
        }
        return cal;
    }

    /**
     * 将传入的Date格式化为"yyyy-MM-dd HH:mm:ss"形式，Date没有非空判断
     *
     * @param date --
     * @return --
     */
    public static String dateToStr(Date date) {
        return SIMPLE_FORMAT.format(date);
    }

    /**
     * 获取字符串格式的时间
     * @return --
     */
    public static String getDateStr() {
        return dateToStr(new Date());
    }

    /**
     * 将传入的Date格式化为"yyyy-MM-dd HH:mm:ss"形式，Date没有非空判断
     *
     * @param dateStr --
     * @return --
     */
    public static Date StrToDate(String dateStr) {
        Date date = null;
        try {
            date = SIMPLE_FORMAT.parse(dateStr);
        } catch (ParseException ex) {
            log.error("StrToDate error...", ex);
        }
        return date;
    }


}
