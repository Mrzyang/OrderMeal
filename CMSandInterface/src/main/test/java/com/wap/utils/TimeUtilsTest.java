package com.wap.utils;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TimeUtilsTest {

    @Test
    void getDateAfter() {
        Map dateMap=TimeUtils.getProperMenuDate();
        String menuDate= (String) dateMap.get("menuDate");  //显示那周的菜单 返回的是该周周一
        Date dateOfMenu=TimeUtils.strToDate(menuDate);
        Date date=TimeUtils.getDateAfter(dateOfMenu,3);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        System.out.println(sdf.format(date));
    }
}