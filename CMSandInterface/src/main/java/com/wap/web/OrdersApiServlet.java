package com.wap.web;

import com.wap.service.OrderApiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: OrdersApiServlet
 *
 * @description: 接口:订单的相关操作
 *
 * @author: Mr.Zhang
 *
 * @create: 2018-6-4
 **/
@WebServlet(name = "OrdersApiServlet",urlPatterns = {"/api/orders"})
public class OrdersApiServlet extends HttpServlet {
    public OrderApiService orderApiService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.orderApiService=new OrderApiService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type=request.getParameter("type");
        if(type.equals("getAll")){
            String customerId= (String) request.getAttribute("customerId");
            System.out.println("customerId="+customerId);
            String data=orderApiService.getOrdersByCusntomerId(Integer.parseInt(customerId));
            PrintWriter pw=response.getWriter();
            pw.print(data);
        }
        if(type.equals("getOne")){
            String orderId=request.getParameter("orderId");
            String data=orderApiService.getOrderById(Integer.parseInt(orderId));
            PrintWriter pw=response.getWriter();
            pw.print(data);
        }

        if(type.equals("cancel")){
            String orderId=request.getParameter("orderId");
            String goodsPrice=request.getParameter("goodsPrice");
            String customerId= (String) request.getAttribute("customerId");
            String data=orderApiService.cancelOrderById(Integer.parseInt(orderId), Double.parseDouble(goodsPrice), Integer.parseInt(customerId));
            PrintWriter pw=response.getWriter();
            pw.print(data);
        }
    }
}
