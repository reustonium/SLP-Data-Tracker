package com.reustonium.slpdatatracker.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Andrew on 6/6/2014.
 */
public class SLPUtil {

    public static boolean sameDate(Date date1, Date date2){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }
}
