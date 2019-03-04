package com.wap.web;

import com.wap.service.OrderMealApiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: MealApiServlet
 *
 * @description: 接口:订餐相关操作
 *
 * @author: Mr.Zhang
 *
 * @create: 2018-6-4
 **/
@WebServlet(name = "MealApiServlet",urlPatterns = {"/api/orderMeal"})
public class OrderMealApiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderMealApiService orderMealApiService=new OrderMealApiService();
        String customerId=request.getParameter("customerId");
        String customerPassword=request.getParameter("customerPassword");
        String totalMoney=request.getParameter("totalMoney");

        System.out.println("总共费用是:"+totalMoney);

        String goodsIdStr=request.getParameter("goodsIdStr");
        String dayNoStr=request.getParameter("dayNoStr");
        String mealTimeNoStr=request.getParameter("mealTimeNoStr");
        String data=orderMealApiService.doOrderMeal(customerId,customerPassword,totalMoney,goodsIdStr,dayNoStr,mealTimeNoStr);
        PrintWriter pw=response.getWriter();
        pw.print(data);
    }
}
