package com.wap.service;

import com.google.gson.Gson;
import com.orderMeal.service.MenuService;
import com.wap.utils.TimeUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * @program: OrderMeal
 * @description: 获取菜单的业务逻辑
 * @author: Mr.Zhang
 * @create: 2018-06-11 13:03
 **/
public class MenuApiService {
    public String getMenu(String schoolId){
        //这里我规定下，周末显示下周的菜单(可预订)，周一到周五显示本周的菜单(不可预定)
        Gson gson=new Gson();
        MenuService menuService=new MenuService();
        Map dateMap=TimeUtils.getProperMenuDate();
        String menuDate= (String) dateMap.get("menuDate");  //显示那周的菜单 返回的是该周周一

        int status= (int) dateMap.get("status");            //是否可以订餐，周末可以，周一到周五不可订餐  0--不可   1--可以
        Map menus=menuService.getMenu2Goods(schoolId,menuDate);
        Map weekMenu=new HashMap();
        weekMenu.put("status",status);
        weekMenu.put("menu",menus);
        return gson.toJson(weekMenu);
    }
}
