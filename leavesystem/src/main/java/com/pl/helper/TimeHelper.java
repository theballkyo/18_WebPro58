/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pl.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import ognl.ParseException;

/**
 *
 * @author theba
 */
public class TimeHelper {

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
    
    public static int countDayNoWeekEnd(Date start_at, Date end_at) {
        int count = 0;
        return count;
    }
}
