package com.wap.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: OrderMeal
 * @description: 关于时间获取与判断相关的工具类
 * @author: Mr.Zhang
 * @create: 2018-06-11 13:17
 **/
public class TimeUtils {
    public static Map<String, Date> getDays() {  //Map<String,Date>             //获取本周周一与下周周一
        Map<String, Date> map = new HashMap<String, Date>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        try {
            cal.setTime(sdf.parse(sdf.format(date)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //System.out.println("要计算日期为:"+sdf.format(cal.getTime())); //输出要计算日期

        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }

        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一

        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        //System.out.println("所在周星期一的日期："+sdf.format(cal.getTime()));
        map.put("Monday", cal.getTime());
        //System.out.println(cal.getFirstDayOfWeek()+"-"+day+"+6="+(cal.getFirstDayOfWeek()-day+6));
        cal.add(Calendar.DATE, 7);
        // System.out.println("所在下周一的日期："+sdf.format(cal.getTime()));
        map.put("nextMonday", cal.getTime());
        return map;
    }


    //根据今天的星期决定展示给前台的菜单的周次(星期一)
    public static Map getProperMenuDate() {
        Date menuDate = null;
        Map dateMap = new HashMap();
        String str[] = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};//字符串数组
        Calendar rightNow = Calendar.getInstance();
        int day = rightNow.get(rightNow.DAY_OF_WEEK);//获取时间
        //System.out.println("今天是"+str[day-1]);//通过数组把周几输出
        String today = str[day - 1];
        Map map = getDays();
        if (today.equals("星期六") || today.equals("星期日")) {
            menuDate = (Date) map.get("nextMonday");
            dateMap.put("status", 1);    //周末可订餐
        } else {
            menuDate = (Date) map.get("Monday");
            dateMap.put("status", 0);    //周一到周五不可订餐
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        System.out.println("今天应该显示：" + sdf.format(menuDate) + "菜单");
        dateMap.put("menuDate", sdf.format(menuDate));
        return dateMap;
    }

    //获取某个日期后几天的日期
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    //将短时间格式转换为 Date
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    //返回菜单后面第几天的日期，这里用于把 "配送日期" 存在数据库中
    public static String getDateAfterMenu(int offset){
        Map dateMap=TimeUtils.getProperMenuDate();
        String menuDate= (String) dateMap.get("menuDate");  //显示那周的菜单 返回的是该周周一
        Date dateOfMenu=TimeUtils.strToDate(menuDate);
        Date date=TimeUtils.getDateAfter(dateOfMenu,offset);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        System.out.println(sdf.format(date));
        return sdf.format(date);
    }

}
