/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dates;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author cmeehan
 */
public class ReportingDates {

    private String returnDate;

    public String lastWeek() {
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd 00:00");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        cal.add(Calendar.DATE, -7);
        //cal.add(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek()-14);
        returnDate = df.format(cal.getTime());
        System.out.println("Last Week: "+returnDate);
        return returnDate;
    }

    public String previousWeek() {

        return returnDate;
    }

    public String firstOfCurrentWeek() {
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd 00:00");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        String firstOfWeek = df.format(cal.getTime());
        System.out.println("FIrst of week: "+firstOfWeek);
        return firstOfWeek;
    }

    public String reportPeriod() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_YEAR, -1);
        SimpleDateFormat df = new SimpleDateFormat("w");
        String week = df.format(cal.getTime());
        return week;
    }
}
