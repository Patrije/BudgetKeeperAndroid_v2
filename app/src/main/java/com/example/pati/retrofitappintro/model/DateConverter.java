package com.example.pati.retrofitappintro.model;

/**
 * Created by Pati on 27.12.2018.
 */

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}