package com.example.somecode.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

public class Time8 {
    public static void main(String[] args) {

    }

    /**
     * java.time.LocalDate：表示一个ISO-8601格式的日期，不包含时间信息
     * java.time.LocalTime：表示一个ISO-8601格式的时间，不包含日期信息
     * java.time.LocalDateTime：表示一个ISO-8601格式的日期时间
     * java.time.Instant：表示从‘1970-01-01T00:00:00Z’开始的秒数
     * java.time.Duration：表示两个时刻之间的持续时间或时间段
     * java.time.Period：表示两个日期之间的天数、月数或年数
     * java.time.ZonedDateTime：表示带时区的日期时间信息
     * java.time.DateTimeFormatter：用于格式化和解析日期时间信息
     */


    public void localDate() {
        // 使用 of() 方法创建 LocalDate 对象
        LocalDate date = LocalDate.of(2023, 5, 13);
        System.out.println(date); // 输出：2023-05-13

        // 使用 now() 方法获取当前日期
        LocalDate today = LocalDate.now();
        System.out.println(today); // 输出当前日期，例如：2023-05-13

        /**
         * getYear()：获取当前日期的年份。
         * getMonth()：获取当前日期的月份。
         * getDayOfMonth()：获取当前日期的日子。
         * plusDays(long daysToAdd)：增加指定天数后的日期对象。
         * minusDays(long daysToSubtract)：减去指定天数后的日期对象。
         * isLeapYear()：判断当前年份是否是闰年。
         */
        int year = date.getYear();
        Month month = date.getMonth();
        int dayOfMonth = date.getDayOfMonth();

        System.out.printf("year: %d, month: %s, day: %d\n", year, month, dayOfMonth);

        LocalDate nextDay = date.plusDays(1);
        System.out.println(nextDay); // 输出：2023-05-14

        LocalDate previousDay = date.minusDays(1);
        System.out.println(previousDay); // 输出：2023-05-12

        boolean isLeapYear = date.isLeapYear();
        System.out.println(isLeapYear); // 输出：false
    }



    public void localTime() {
        // 使用 of() 方法创建 LocalTime 对象
        LocalTime time = LocalTime.of(20, 15, 30);
        System.out.println(time); // 输出：20:15:30

        // 使用 now() 方法获取当前时间
        LocalTime now = LocalTime.now();
        System.out.println(now); // 输出当前时间，例如：10:47:28.130091

        /**
         * getHour()：获取当前时间的小时数。
         * getMinute()：获取当前时间的分钟数。
         * getSecond()：获取当前时间的秒数。
         * plusHours(long hoursToAdd)：增加指定小时数后的时间对象。
         * minusMinutes(long minutesToSubtract)：减去指定分钟数后的时间对象。
         */
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();

        System.out.printf("hour: %d, minute: %d, second: %d\n", hour, minute, second);

        LocalTime nextHour = time.plusHours(1);
        System.out.println(nextHour); // 输出：21:15:30

        LocalTime previousMinute = time.minusMinutes(1);
        System.out.println(previousMinute); // 输出：20:14:30
    }


    public void localDateTime() {
        // 使用 of() 方法创建 LocalDateTime 对象
        LocalDateTime dateTime = LocalDateTime.of(2023, 5, 13, 20, 15, 30);
        System.out.println(dateTime); // 输出：2023-05-13T20:15:30

        // 使用 now() 方法获取当前日期时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now); // 输出当前日期时间，例如：2023-05-13T10:47:28.130091


        /**
         * toLocalDate()：将当前日期时间转换为日期对象。
         * toLocalTime()：将当前日期时间转换为时间对象。
         * plusDays(long daysToAdd)：增加指定天数后的日期时间对象。
         * minusMonths(long monthsToSubtract)：减去指定月份数后的日期时间对象。
         */
        LocalDate date = dateTime.toLocalDate();
        LocalTime time = dateTime.toLocalTime();

        System.out.printf("date: %s, time: %s\n", date, time);

        LocalDateTime nextDay = dateTime.plusDays(1);
        System.out.println(nextDay); // 输出：2023-05-14T20:15:30

        LocalDateTime previousMonth = dateTime.minusMonths(1);
        System.out.println(previousMonth); // 输出：2023-04-13T20:15:30
    }

    public void instant() {
        // 使用 ofEpochSecond() 方法创建 Instant 对象
        Instant instant = Instant.ofEpochSecond(1620868180);
        System.out.println(instant); // 输出：2021-05-13T08:43:00Z

        // 使用 now() 方法获取当前日期时间
        Instant now = Instant.now();
        System.out.println(now); // 输出当前日期时间，例如：2023-05-13T10:47:28.130091Z

        /**
         * getEpochSecond()：获取当前 Instant 对象自 1970 年 1 月 1 日开始的秒数。
         * plusMillis(long millisToAdd)：增加指定毫秒数后的 Instant 对象。
         * minusNanos(long nanosToSubtract)：减去指定纳秒数后的 Instant 对象
         */
        long epochSecond = instant.getEpochSecond();
        System.out.println(epochSecond); // 输出：1620868180

        Instant nextMilli = instant.plusMillis(1000);
        System.out.println(nextMilli); // 输出：2021-05-13T08:43:01Z

        Instant previousNanos = instant.minusNanos(1000);
        System.out.println(previousNanos); // 输出：2021-05-13T08:42:59.999999Z
    }


    /**
     * Period类表示一个时间段，可以表示年、月、日之间的差异
     */
    public void period() {
        // 创建一个1年2个月3天的时间段
        Period p0 = Period.of(1, 2, 3);

        // Period类还可以通过between()方法来计算两个日期之间的时间差
        LocalDate d1 = LocalDate.of(2023, 1, 1);
        LocalDate d2 = LocalDate.now();
        Period p = Period.between(d1, d2);

        /**
         * getYears()：获取Period实例中的年份差
         * getMonths()：获取Period实例中的月份差
         * getDays()：获取Period实例中的天数差
         * plusYears(long yearsToAdd)：将指定的年数添加到该Period实例
         * plusMonths(long monthsToAdd)：将指定的月数添加到该Period实例
         * plusDays(long daysToAdd)：将指定的天数添加到该Period实例
         */
        System.out.println("时间差为：" + p.getYears() + "年" + p.getMonths() + "月" + p.getDays() + "天");

        Period p2 = p.plusYears(1).plusMonths(2).plusDays(3);
        System.out.println("加上1年2月3天后的时间差为：" + p2.getYears() + "年" + p2.getMonths() + "月" + p2.getDays() + "天");
    }


    /**
     * Duration类表示一个持续时间，可以表示小时、分钟、秒、毫秒和纳秒之间的差异
     */
    public void duration() {
        // 创建了一个2小时的持续时间
        Duration d0 = Duration.ofHours(2);

        // Duration类还可以通过between()方法来计算两个日期时间之间的时间差
        LocalDateTime dt1 = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
        LocalDateTime dt2 = LocalDateTime.now();
        Duration d = Duration.between(dt1, dt2);


        /**
         * getSeconds()：获取Duration实例中的总秒数
         * toDays()：获取Duration实例中的总天数
         * plus(long amountToAdd, TemporalUnit unit)：将指定的时间单元添加到该Duration实例
         * plusHours(long hoursToAdd)：将指定的小时数添加到该Duration实例
         * plusMinutes(long minutesToAdd)：将指定的分钟数添加到该Duration实例
         */

        System.out.println("时间差为：" + d.getSeconds() + "秒");
        System.out.println("时间差为：" + d.toDays() + "天");

        Duration d2 = d.plusHours(2).plusMinutes(30).plusSeconds(10);
        System.out.println("加上2小时30分10秒后的时间差为：" + d2.getSeconds() + "秒");
    }

    /**
     * TemporalAdjusters类提供了很多有用的方法，可以将一个日期调整到指定的日期或时间，
     * 例如将一个日期调整到该月的最后一天、下一个周日等
     */
    public void temporalAdjusters() {
        // 创建了一个TemporalAdjuster实例，将日期调整到该月的最后一天，
        // 并将其应用于指定的日期date上，得到一个新的日期lastDay
        TemporalAdjuster lastDayOfMonth = TemporalAdjusters.lastDayOfMonth();
        LocalDate date = LocalDate.parse("2023-01-01");
        LocalDate lastDay = date.with(lastDayOfMonth);

        /**
         * firstDayOfMonth()：将日期调整为该月的第一天
         * lastDayOfMonth()：将日期调整为该月的最后一天
         * next(DayOfWeek dayOfWeek)：将日期调整到下一个指定的星期几
         * previous(DayOfWeek dayOfWeek)：将日期调整到上一个指定的星期几
         */

        LocalDate firstDay = date.with(TemporalAdjusters.firstDayOfMonth());

        System.out.println("该月的第一天为：" + firstDay);
        System.out.println("该月的最后一天为：" + lastDay);

        LocalDate nextSunday = date.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println("下一个周日为：" + nextSunday);
    }


    public void zone() {
        // 创建了一个代表"Asia/Shanghai"时区的ZoneId实例
        ZoneId zoneId1 = ZoneId.of("Asia/Shanghai");

        // ZoneOffset类是一个表示偏移量的类，可以用来表示与UTC或格林尼治标准时间（GMT）之间的时间差。
        // 可以通过静态方法of()来创建一个ZoneOffset实例
        // 如下创建了一个代表东八区的ZoneOffset实例
        ZoneOffset zoneOffset1 = ZoneOffset.ofHours(8);

        /**
         * getAvailableZoneIds()：获取所有可用的时区ID
         * getRules()：获取此时区的时区规则
         * normalized()：将ZoneOffset标准化为最接近UTC的偏移量
         */

        // 获取所有可用的时区ID
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        System.out.println("所有可用的时区ID：" + availableZoneIds);

        // 获取指定时区的规则
        System.out.println("上海的时区规则：" + zoneId1.getRules());

        // 将一个ZoneOffset标准化为最接近UTC的偏移量
        ZoneOffset zoneOffset2 = ZoneOffset.ofHoursMinutes(-3, 30);
        ZoneId normalized = zoneOffset2.normalized();
        System.out.println("标准化后的时区ID：" + normalized);
    }

    /**
     * ZonedDateTime类表示带时区的日期时间
     */
    public void zonedDateTime() {
        // 创建了一个北京时间2023年5月14日12点0分的ZonedDateTime实例
        ZonedDateTime zdt = ZonedDateTime.of(2023, 5, 14, 12, 0, 0, 0, ZoneId.of("Asia/Shanghai"));

        // 获取了当前时间的ZonedDateTime实例
        ZonedDateTime now = ZonedDateTime.now();

        /**
         * getYear()：获取年份
         * getMonth()：获取月份
         * getDayOfMonth()：获取月中的日期
         * getHour()：获取小时数
         * getMinute()：获取分钟数
         * getSecond()：获取秒数
         * withZoneSameInstant(ZoneId zone)：将时区调整为指定的时区，并以UTC时间表示
         * withZoneSameLocal(ZoneId zone)：将时区调整为指定的时区，并以本地时间表示
         * plus(Duration amountToAdd)：将指定的持续时间添加到该ZonedDateTime实例
         */

        System.out.println("北京时间：" + zdt);
        System.out.println("年份：" + zdt.getYear());
        System.out.println("月份：" + zdt.getMonthValue());
        System.out.println("日期：" + zdt.getDayOfMonth());
        System.out.println("小时数：" + zdt.getHour());
        System.out.println("分钟数：" + zdt.getMinute());
        System.out.println("秒数：" + zdt.getSecond());

        ZonedDateTime zdt2 = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("调整时区后的UTC时间：" + zdt2);

        ZonedDateTime zdt3 = zdt.plus(Duration.ofHours(5));
        System.out.println("加上5小时后的时间：" + zdt3);
    }

    /**
     * DateTimeFormatter类用于格式化日期、时间和日期时间
     */
    public void DateTimeFormatter() {
        // 创建了一个将日期时间格式化为"yyyy-MM-dd HH:mm:ss"格式的DateTimeFormatter实例
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime dateTime = LocalDateTime.parse("2023-05-14T12:00:00");
        String formattedDateTime = dateTime.format(formatter1);
        System.out.println("格式化后的日期时间：" + formattedDateTime);

        /**
         * ofLocalizedDate(FormatStyle dateStyle)：获取指定格式样式的本地日期格式化器
         * ofLocalizedTime(FormatStyle timeStyle)：获取指定格式样式的本地时间格式化器
         * ofLocalizedDateTime(FormatStyle dateTimeStyle)：获取指定格式样式的本地日期时间格式化器
         * parse(CharSequence text, TemporalQuery query)：将指定的日期时间字符串解析为指定类型的Temporal对象
         * parse(CharSequence text)：将指定的日期时间字符串解析为LocalDateTime对象
         *
         * 另外，DateTimeFormatter还支持一些预定义的日期时间格式模板，例如：
         *
         * "yyyy-MM-dd"：代表年-月-日的格式
         * "HH:mm:ss"：代表小时:分钟:秒的格式
         * "yyyy-MM-dd HH:mm:ss"：代表年-月-日 小时:分钟:秒的格式
         */

        // 本地化日期格式
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        String formattedDate = dateTime.format(dateFormatter);
        System.out.println("格式化后的日期：" + formattedDate); // 格式化后的日期：2023年05月14日

        // 本地化时间格式
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = dateTime.format(timeFormatter);
        System.out.println("格式化后的时间：" + formattedTime); // 格式化后的时间：12:00:00

        // 本地化日期时间格式
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String formattedDateTime2 = dateTime.format(dateTimeFormatter);
        System.out.println("格式化后的日期时间：" + formattedDateTime2); // 格式化后的日期时间：2023年05月14日 12:00:00

        // 解析日期时间字符串为LocalDateTime对象
        String strDateTime = "2023-05-14 12:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parsedDateTime = LocalDateTime.parse(strDateTime, formatter);
        System.out.println("解析后的日期时间：" + parsedDateTime); // 解析后的日期时间：2023-05-14T12:00

    }


    /**
     * 将Date转换为Instant、LocalDateTime等类型
     */
    public void dateToInstantLocal() {
        Calendar calendar = Calendar.getInstance();
        Date date0 = calendar.getTime();
        Instant instant0 = calendar.toInstant();

        Date date = new Date();
        Instant instant = date.toInstant();

        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());

        // ZoneId.systemDefault()方法获取的是系统默认的时区
        // 如果需要指定其它时区，可以使用ZoneId.of()方法
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        ZonedDateTime zonedDateTime2 = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
    }


    /**
     * 将Instant、LocalDateTime等类型转换为Date
     */
    public void InstantLocalToDate() {
        // 使用toEpochMilli()方法将Instant、LocalDateTime等类型转换为毫秒数，再通过毫秒数创建Date类型
        LocalDateTime localDateTime = LocalDateTime.now();
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = new Date(instant.toEpochMilli());

        // 使用toInstant()方法将Instant、LocalDateTime等类型转换为Instant类型，再通过Instant类型创建Date类型
        Date date1 = Date.from(instant);
    }

    /**
     * 将LocalDate转换为Date
     */
    public void localDateToDate() {
        LocalDate localDate = LocalDate.now();
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zoneId).toInstant();
        Date date = Date.from(instant);
    }

    /**
     * 将LocalTime转换为Date
     */
    public void localTimeToDate() {
        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = LocalDateTime.of(localDate, localTime).atZone(zoneId).toInstant();
        Date date = Date.from(instant);
    }

    /**
     * 将LocalDateTime转换为Date
     */
    public void localDateTimeToDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zoneId).toInstant();
        Date date = Date.from(instant);
    }

    /**
     * 将LocalDateTime转换为Date
     */
    public void calendarToLocalTimeDate() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * DateTimeFormatter支持更加灵活、直观的格式化字符，例如：
     *
     * u表示年代，y表示年份；
     * M表示月份，m表示分钟；
     * d表示天数，D表示年中的第几天。
     */
    public void dateTimeFormatter() throws ParseException {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String str = localDateTime.format(dtf); // 将LocalDateTime格式化为字符串
        LocalDateTime localDateTime2 = LocalDateTime.parse(str, dtf); // 将字符串解析为LocalDateTime

        /**
         * SimpleDateFormat的格式化字符集同样可以用于解析日期时间字符串，但有些字符集不支持解析操作
         */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = sdf.parse("2023年05月14日"); // 解析时报错
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        LocalDate localDate = LocalDate.parse("2023年05月14日", dtf2); // 解析成功

        /**
         * 另外，DateTimeFormatter还提供了一些其它特性，例如：
         *
         * withZone()：修改时区
         * withLocale()：修改本地化设置
         * withResolverStyle()：修改解析方式
         */

        DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.of("Asia/Shanghai"))
                .withLocale(Locale.CHINA)
                .withResolverStyle(ResolverStyle.STRICT);
    }

    public void simpleAndDateFormat() throws ParseException {
        // 将SimpleDateFormat转换为DateTimeFormatter
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 将String转换为Date
        String strTime = "2023-05-14 12:00:00";
        Date date = sdf.parse(strTime);
        System.out.println("SimpleDateFormat解析后的时间：" + date);

        // 将Date转换为LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(),
                java.time.ZoneId.systemDefault());
        System.out.println("Date转换为LocalDateTime：" + localDateTime);

        // 将LocalDateTime转换为String
        String strDateTime = dtf.format(localDateTime);
        System.out.println("DateTimeFormatter格式化后的时间：" + strDateTime);

        // 将String转换为LocalDateTime
        LocalDateTime localDateTime1 = LocalDateTime.parse(strTime, dtf);
        System.out.println("DateTimeFormatter解析后的时间：" + localDateTime1);

        // 将LocalDateTime转换为Date
        Date date1 = Date.from(localDateTime1.atZone(java.time.ZoneId.systemDefault()).toInstant());
        System.out.println("LocalDateTime转换为Date：" + date1);

        // 将DateTimeFormatter转换为SimpleDateFormat
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat(dtf1.toString());
        System.out.println("DateTimeFormatter转换为SimpleDateFormat：" + sdf2.toString());
    }
}
