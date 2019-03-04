package com.orderMeal.service;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MenuServiceTest {

    @Test
    void getMenu2Goods() {
        Gson gson=new Gson();
        MenuService menuService=new MenuService();
         Map menus=menuService.getMenu2Goods("1","2018-06-11");
        System.out.println(gson.toJson(menus));
    }
}