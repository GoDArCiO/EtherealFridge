package com.example.EtherealFridge;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.lang.Integer;


public class CalendarHelper {


    Calendar calendar= Calendar.getInstance();
    String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

    public Integer GetDeltaDate(String inputDate) {

        String dateStart = inputDate;
        String dateStop = currentDate;
        calendar.add(Calendar.DATE, -1);
        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat("MM.dd.yyyy");

        Date d1 = null;
        Date d2 = null;
        long diffDays=-1;
        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");

        } catch (Exception e) {
            e.printStackTrace();
        }

Integer i=0;//konwertuj long na int
        while(diffDays>0){
            i++;
            diffDays--;
        }

return i;
    }
}
