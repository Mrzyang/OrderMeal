package com.wap.dao;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderApiDaoTest {

    @Test
    void getOrdersByCusntomerId() {
        OrderApiDao orderApiDao=new OrderApiDao();
        List list=orderApiDao.getOrdersByCusntomerId(3);
        System.out.println(list);
    }
}