package com.api.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateUtil {

    public static Object[][] getNextWeekdays(int numberOfDays) {
        List<Object[]> resultList = new ArrayList<>();
        for (int i = 0; i < numberOfDays; i++) {
            LocalDate date = LocalDate.now().plusDays(i);
            if (isWeekday(date)) {
                resultList.add(new Object[]{ date.toString() });
            }
        }
        return resultList.toArray(new Object[0][]);
    }

    public static Object[][] getNextWeekends(int numberOfDays) {
        List<Object[]> resultList = new ArrayList<>();
        for (int i = 0; i < numberOfDays; i++) {
            LocalDate date = LocalDate.now().plusDays(i);
            if (isWeekend(date)) {
                resultList.add(new Object[]{ date.toString() });
            }
        }
        return resultList.toArray(new Object[0][]);
    }

    private static boolean isWeekday(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
    }

    private static boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }
}
