package com.example.pati.retrofitappintro.util;

/**
 * Created by Pati on 12.12.2018.
 */


import android.support.annotation.Nullable;

import java.util.Calendar;
import java.util.TimeZone;

public class TimeHelper {

    private static CalendarFactory regularCalendarFactory = new CalendarFactory() {
        @Override
        public Calendar getNewInstance() {
            return Calendar.getInstance();
        }
    };
    private static CalendarFactory calendarFactory = regularCalendarFactory;

    public static Calendar getNow() {
        return calendarFactory.getNewInstance();
    }

    /**
     * Use this method to set mock factories in tests.
     *
     * @param calendarFactory new {@link CalendarFactory} to replace old one
     */
    public static void setCalendarFactory(final CalendarFactory calendarFactory) {
        TimeHelper.calendarFactory = calendarFactory;
    }

    public static void resetCalendarFactory() {
        calendarFactory = regularCalendarFactory;
    }

    /**
     * Returns new instance of {@link java.util.Calendar} set to last midnight.
     *
     * @param timeZone if not null, this timezone is applied
     */
    public static java.util.Calendar getTodayMidnightCalendar(@Nullable final TimeZone timeZone) {
        java.util.Calendar todayMidnight = TimeHelper.getNow();
        if (timeZone != null) {
            todayMidnight.setTimeZone(timeZone);
        }
        todayMidnight.set(java.util.Calendar.HOUR_OF_DAY, 0);
        todayMidnight.set(java.util.Calendar.MINUTE, 0);
        todayMidnight.set(java.util.Calendar.SECOND, 0);
        todayMidnight.set(java.util.Calendar.MILLISECOND, 0);
        return todayMidnight;
    }

    /**
     * Returns new instance of {@link java.util.Calendar} set to midnight.
     *
     * @param day      midnight is set for this day
     * @param timeZone if not null, this timezone is applied
     */
    public static java.util.Calendar getMidnightCalendar(final Calendar day, @Nullable final TimeZone timeZone) {
        java.util.Calendar midnight = day;
        if (timeZone != null) {
            midnight.setTimeZone(timeZone);
        }
        midnight.set(java.util.Calendar.HOUR_OF_DAY, 0);
        midnight.set(java.util.Calendar.MINUTE, 0);
        midnight.set(java.util.Calendar.SECOND, 0);
        midnight.set(java.util.Calendar.MILLISECOND, 0);
        return midnight;
    }

    /**
     * Returns new instance of {@link java.util.Calendar} set to today 8 PM.
     *
     * @param timeZone if not null, this timezone is applied
     */
    public static java.util.Calendar getToday8pm_Calendar(@Nullable final TimeZone timeZone) {
        java.util.Calendar today8pm = TimeHelper.getNow();
        if (timeZone != null) {
            today8pm.setTimeZone(timeZone);
        }
        today8pm.set(java.util.Calendar.HOUR_OF_DAY, 20);
        today8pm.set(java.util.Calendar.MINUTE, 0);
        today8pm.set(java.util.Calendar.SECOND, 0);
        today8pm.set(java.util.Calendar.MILLISECOND, 0);
        return today8pm;
    }

    /**
     * Returns new instance of {@link java.util.Calendar} set to tomorrow 8 PM.
     *
     * @param timeZone if not null, this timezone is applied
     */
    public static java.util.Calendar getTomorrow8pm_Calendar(@Nullable final TimeZone timeZone) {
        java.util.Calendar today8pm = TimeHelper.getNow();
        if (timeZone != null) {
            today8pm.setTimeZone(timeZone);
        }
        today8pm.set(java.util.Calendar.HOUR_OF_DAY, 20);
        today8pm.set(java.util.Calendar.MINUTE, 0);
        today8pm.set(java.util.Calendar.SECOND, 0);
        today8pm.set(java.util.Calendar.MILLISECOND, 0);
        today8pm.add(Calendar.DAY_OF_MONTH, 1);
        return today8pm;
    }

    /**
     * Returns new instance of {@link java.util.Calendar} set to tomorrow 7 AM.
     *
     * @param timeZone if not null, this timezone is applied
     */
    public static java.util.Calendar getTomorrow7am_Calendar(@Nullable final TimeZone timeZone) {
        java.util.Calendar today7am = TimeHelper.getNow();
        if (timeZone != null) {
            today7am.setTimeZone(timeZone);
        }
        today7am.set(java.util.Calendar.HOUR_OF_DAY, 7);
        today7am.set(java.util.Calendar.MINUTE, 0);
        today7am.set(java.util.Calendar.SECOND, 0);
        today7am.set(java.util.Calendar.MILLISECOND, 0);
        today7am.add(Calendar.DAY_OF_MONTH, 1);
        return today7am;
    }

    /**
     * Returns new instance of {@link java.util.Calendar} set to day after tomorrow 7 AM.
     *
     * @param timeZone if not null, this timezone is applied
     */
    public static java.util.Calendar getDayAfterTomorrow7am_Calendar(@Nullable final TimeZone timeZone) {
        java.util.Calendar today7am = TimeHelper.getNow();
        if (timeZone != null) {
            today7am.setTimeZone(timeZone);
        }
        today7am.set(java.util.Calendar.HOUR_OF_DAY, 7);
        today7am.set(java.util.Calendar.MINUTE, 0);
        today7am.set(java.util.Calendar.SECOND, 0);
        today7am.set(java.util.Calendar.MILLISECOND, 0);
        today7am.add(Calendar.DAY_OF_MONTH, 2);
        return today7am;
    }

    /**
     * Creates {@link java.util.Calendar} set to given date.
     *
     * @param year   year
     * @param month  month
     * @param day    day
     * @param hour   hour
     * @param minute minute
     */
    public static Calendar getCalendar(int year, int month, int day, int hour, int minute) {
        return TimeHelper.getCalendar(year, month, day, hour, minute, 0);
    }

    /**
     * Creates {@link java.util.Calendar} set to given date.
     *
     * @param year    year
     * @param month   month
     * @param day     day
     * @param hour    hour
     * @param minute  minute
     * @param seconds seconds
     */
    public static Calendar getCalendar(int year, int month, int day, int hour, int minute, int seconds) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(year, month, day, hour, minute, seconds);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * Creates {@link java.util.Calendar} that is shifted from time provided in parameter by given number of minutes and
     * seconds.
     *
     * @param time    time in UTC milliseconds from epoch
     * @param minutes minutes could be negative
     * @param seconds seconds could be negative
     */
    public static Calendar getShiftedTime(long time, int minutes, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time + minutes * 60000 + seconds * 1000);
        return calendar;
    }

    public static long getMilisecondsFromEpoch(int year, int month, int day, int hour, int minute) {
        return TimeHelper.getCalendar(year, month, day, hour, minute).getTimeInMillis();
    }

    public static long getMilisecondsFromEpoch(int year, int month, int day, int hour, int minute, int second) {
        return TimeHelper.getCalendar(year, month, day, hour, minute, second).getTimeInMillis();
    }

    public static String getTomorowMidnight() {
        return String.valueOf(getTodayMidnightCalendar(TimeZone.getTimeZone("UTC")).getTimeInMillis() + 1);
    }

    public static String getTodayMidnight() {
        return String.valueOf(TimeHelper.getTodayMidnightCalendar(TimeZone.getTimeZone("UTC")).getTimeInMillis());
    }

    public abstract static class CalendarFactory {

        public abstract Calendar getNewInstance();
    }
}
