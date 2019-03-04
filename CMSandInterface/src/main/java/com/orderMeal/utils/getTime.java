package com.orderMeal.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 获取各种时间工具
 */
public class getTime {
    /**
     * 获取网络（百度）时间
     * @return 百度时间
     */
    public Date getNetTime() {
        String webUrl = "http://www.baidu.com";
        try {
            URL url = new URL(webUrl);
            URLConnection conn = url.openConnection();
            conn.connect();
            Date date = new Date(conn.getDate());
            return date;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 第一种时间格式
     * @param date 待转换的时间
     * @return 转换后的目标格式字符串
     */
    public String format1(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public String getDay(String dateStr,int i){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        try {
            Date date = format.parse(dateStr);
            System.out.println(date);
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.setTimeInMillis(date.getTime());
            //System.out.println("当前时间："+ format.format(calendar.getTime()));
            switch (i){
                case 1:calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);break;
                case 2:calendar.set(Calendar.DAY_OF_WEEK,Calendar.TUESDAY);break;
                case 3:calendar.set(Calendar.DAY_OF_WEEK,Calendar.WEDNESDAY);break;
                case 4:calendar.set(Calendar.DAY_OF_WEEK,Calendar.THURSDAY);break;
                case 5:calendar.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY);break;
                case 6:calendar.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);break;
                case 7:calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);break;
            }
            String date1 = format.format(calendar.getTime());
            System.out.println(date1);
            return date1;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getMondayDate(String dateStr){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        try {
            Date date = format.parse(dateStr);
            System.out.println(date);
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.setTimeInMillis(date.getTime());
            //System.out.println("当前时间："+ format.format(calendar.getTime()));
            calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
            String date1 = format.format(calendar.getTime());
            System.out.println(date1);
            return date1;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getSundayDate(String dateStr){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        try {
            Date date = format.parse(dateStr);
            System.out.println(date);
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.setTimeInMillis(date.getTime());
            //System.out.println("当前时间："+ format.format(calendar.getTime()));
            calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
            String date1 = format.format(calendar.getTime());
            System.out.println(date1);
            return date1;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static void main(String[] a){
        getTime time = new getTime();
        time.getMondayDate("2018-06-12");
        time.getSundayDate("2018-06-12");
    }
}
