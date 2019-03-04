package com.wap.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuApiServiceTest {

    @Test
    void getMenu() {
        MenuApiService menuApiService=new MenuApiService();
        String data=menuApiService.getMenu("1");
        System.out.println(data);
    }
}