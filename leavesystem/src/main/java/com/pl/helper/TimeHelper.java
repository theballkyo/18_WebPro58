/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pl.helper;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author theba
 */
public class TimeHelper {

    private static Format formatter = new SimpleDateFormat("dd-MMMM-yyyy HH:mm:ss");
    
    private static Format formatterNoTime = new SimpleDateFormat("dd-MMMM-yyyy");
    
    public static Date strToDate(String date) {
        try {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            Date newDate;
            newDate = df.parse(date);
            return newDate;
            /*
            String newDateString = df.format(startDate);
            System.out.println(newDateString);
             */
        } catch (java.text.ParseException ex) {
            Logger.getLogger(TimeHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static int countDayNoWeekEnd(String start_at, String end_at) {
        return countDayNoWeekEnd(strToDate(start_at), strToDate(end_at));
    }

    public static int countDayNoWeekEnd(Date start, Date end) {
        //Ignore argument check
        if (start == null || end == null || start.getTime() > end.getTime()) {
            return -1;
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(start);
        int w1 = c1.get(Calendar.DAY_OF_WEEK);
        c1.add(Calendar.DAY_OF_WEEK, -w1);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(end);
        int w2 = c2.get(Calendar.DAY_OF_WEEK);
        c2.add(Calendar.DAY_OF_WEEK, -w2);

        //end Saturday to start Saturday 
        long days = (c2.getTimeInMillis() - c1.getTimeInMillis()) / (1000 * 60 * 60 * 24);
        int daysWithoutWeekendDays = (int) (days - (days * 2 / 7));

        // Adjust days to add on (w2) and days to subtract (w1) so that Saturday
        // and Sunday are not included
        if (w1 == Calendar.SUNDAY && w2 != Calendar.SATURDAY) {
            w1 = Calendar.MONDAY;
        } else if (w1 == Calendar.SATURDAY && w2 != Calendar.SUNDAY) {
            w1 = Calendar.FRIDAY;
        }

        if (w2 == Calendar.SUNDAY) {
            w2 = Calendar.MONDAY;
        } else if (w2 == Calendar.SATURDAY) {
            w2 = Calendar.FRIDAY;
        }

        return daysWithoutWeekendDays - w1 + w2 + 1;
    }

    public static String formatDate(Date date) {
        return formatter.format(date);
    }
    
    public static String formatDateNoTime(Date date) {
        return formatterNoTime.format(date);
    }
}
