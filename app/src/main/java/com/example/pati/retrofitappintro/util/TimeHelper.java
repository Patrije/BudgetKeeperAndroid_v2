package com.example.pati.retrofitappintro.util;

/**
 * Created by Pati on 12.12.2018.
 */
import java.util.Calendar;

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

    public abstract static class CalendarFactory {

        public abstract Calendar getNewInstance();
    }
}
