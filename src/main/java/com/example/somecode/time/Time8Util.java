package com.example.somecode.time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Time8Util {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * date转为字符串格式
     * @param date 时间
     * @return --
     */
    public static String dateToStr(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(),
                ZoneId.systemDefault());
        return DTF.format(localDateTime);
    }

    /**
     * LocalDateTime 转为字符串格式
     * @param localDateTime localDateTime
     * @return --
     */
    public static String localDateToStr(LocalDateTime localDateTime) {
        return DTF.format(localDateTime);
    }

    /**
     * 获取字符串格式时间
     * @return --
     */
    public static String getStrDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateToStr(localDateTime);
    }

    /**
     * 字符串格式时间转为LocalDateTime
     * @param str 时间字符串
     * @return LocalDateTime
     */
    public static LocalDateTime strToLocalDateTime(String str) {
        return LocalDateTime.parse(str, DTF);
    }

}
