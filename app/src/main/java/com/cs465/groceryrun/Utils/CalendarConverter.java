package com.cs465.groceryrun.Utils;

import java.util.Calendar;

/**
 * Created by tdw6193 on 11/30/2015.
 */
public class CalendarConverter {

    public static int DAY = 1, MONTH = 2, YEAR = 3;

    public static int convertCalendarToInt (Calendar c, int extract, boolean androidCalendar) {
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        if(androidCalendar)
            month += 1;
        int day = c.get(Calendar.DAY_OF_MONTH);

        if(extract == DAY)
            return day;
        else if (extract == MONTH)
            return month;
        else if (extract == YEAR)
            return year;

        return 0;
    }

    public static String convertCalendarToString(Calendar c, boolean androidCalendar) {
        int year = convertCalendarToInt(c, YEAR, androidCalendar);
        int month = convertCalendarToInt(c, MONTH, androidCalendar);
        int day = convertCalendarToInt(c, DAY, androidCalendar);;

        return convertCalendarIntToString(year, month, day);
    }

    public static String convertCalendarIntToString(int year, int month, int day) {
        String yearStr = Integer.toString(year);
        String monthStr = Integer.toString(month);
        if(month < 10)
            monthStr = "0" + monthStr;
        String dayStr = Integer.toString(day);
        if(day < 10)
            dayStr = "0" + dayStr;

        return monthStr + '/' + dayStr + '/' + yearStr;
    }

    public static int convertCalendarStrToInt (String c, int extract) {
        String monthStr = c.substring(0, 2);
        String dayStr = c.substring(3, 5);
        String yearStr = c.substring(6, 10);

        if(extract == DAY)
            return Integer.parseInt(dayStr);
        else if (extract == MONTH)
            return Integer.parseInt(monthStr);
        else if (extract == YEAR)
            return Integer.parseInt(yearStr);

        return 0;
    }
}
