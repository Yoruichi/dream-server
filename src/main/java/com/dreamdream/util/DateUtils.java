package com.dreamdream.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

public class DateUtils {

    // Protect constructor since it is a static only class
    protected DateUtils() {}

    public static String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String getTimeShow(Date time) {
        long t = time.getTime();
        long now = new Date().getTime();
        if (t >= now || t < (now - 60000)) {
            return "刚刚";
        } else {
            long interval = now - t;
            if (interval <= 3600000) {
                return (interval / 60000) + "分钟前";
            } else if (interval <= 86400000) {
                return (interval / 3600000) + "小时前";
            } else {
                return new SimpleDateFormat("dd/MM").format(time);
            }
        }
    }

    public static String getDateFormat(final DateTime dateTime, final String pattern) {
        return dateTime.toString(pattern);
    }

    public static DateTime getNowHour() {
        DateTime n = DateTime.now(DateTimeZone.UTC);
        return n.minuteOfHour().setCopy(0).secondOfMinute().setCopy(0).millisOfSecond().setCopy(0);
    }

    /*
     * Use Joda time library to get current time and always return UTC time
     */
    public static DateTime getCurrentDateTime() {
        DateTime dt = new DateTime(new Date());
        return dt;
    }

    public static DateTime getDateTime(Date date) {
        DateTime dt = new DateTime(date);
        return dt.toDateTime(DateTimeZone.UTC);
    }

    public static DateTime getDateTime(long timestamp) {
        DateTime dt = new DateTime(timestamp);
        return dt.toDateTime(DateTimeZone.UTC);
    }

    // Date passed in must be UTC time
    public static Long getMillis(final Date date) {
        if (date == null) {
            return -1L;
        }
        return getDateTime(date).getMillis();
    }

    /*
     * translate the string time format to DateTime
     */
    public static DateTime getDateTimeInUTC(final String date, String pattern) {
        return DateTime.parse(date, DateTimeFormat.forPattern(pattern));
    }

    public static DateTime getDateTimeInGMT8(final String date, String pattern) {
        return UTCtoGMT8(getDateTimeInUTC(date, pattern));
    }

    public static String getDateFormat(final DateTime dateTime) {
        return dateTime.toString("yyyy-MM-dd HH:mm:ss");
    }

    public static DateTime UTCtoGMT8(final DateTime dateTime) {
        DateTime dt = dateTime.withZone(DateTimeZone.forID("Etc/GMT-8"));
        return dt;
    }

    public static DateTime UTCtoGMT8(final Date date) {
        DateTime dt = new DateTime(date.getTime(), DateTimeZone.UTC);
        return UTCtoGMT8(dt);
    }

    public static DateTime GMT8toUTC(final DateTime dateTime) {
        return dateTime.withZone(DateTimeZone.forID("Etc/GMT"));
    }

    public static DateTime GMT8toUTC(final Date date) {
        DateTime dt = new DateTime(date).toDateTime(DateTimeZone.forID("Etc/GMT-8"));
        return GMT8toUTC(dt);
    }

    public static DateTime UTCtoCET(final DateTime dateTime) {
        DateTime dt = dateTime.withZone(DateTimeZone.forID("CET"));
        return dt;
    }

    public static DateTime getMidNightInGMT8() {
        DateTime current = getCurrentDateTime();
        current = UTCtoGMT8(current);
        int hours = current.getHourOfDay();
        int minutes = current.getMinuteOfHour();
        int seconds = current.getSecondOfMinute();
        return current.minusHours(hours).minusMinutes(minutes).minusSeconds(seconds);
    }

    public static DateTime calculateOrderEndTime(DateTime startTime, int count, int duration) {
        return startTime.plusDays(count * duration);
    }

    public static DateTime calculateSimcardExpireTime(DateTime startTime, int duration) {
        return startTime.plusDays(duration);
    }

    public static DateTime calculateTimeBefore(DateTime startTime, int duration) {
        return startTime.minusDays(duration);
    }

}
