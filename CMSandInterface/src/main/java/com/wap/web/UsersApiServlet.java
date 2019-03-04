package com.wap.web;

import com.wap.service.UserApiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "UsersApiServlet", urlPatterns = {"/api/user"})
public class UsersApiServlet extends HttpServlet {
    public UserApiService userApiService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type=request.getParameter("type");

        if(type.equals("modifyInfo")){
            String customerId= (String) request.getAttribute("customerId");
            System.out.println("customerId="+customerId);
            String newPassword=request.getParameter("newPassword");
            String customerPhone=request.getParameter("customerPhone");

            System.out.println(newPassword);
            System.out.println(customerPhone);

            //这里我们给客户端返回一个 {msg:"",status:""} 就可以了，不需要返回token
            String data= userApiService.modifyInfo(customerId,newPassword,customerPhone);
            System.out.println(123);
            System.out.println(data);
            PrintWriter pw=response.getWriter();
            pw.print(data);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("132");
        String type=request.getParameter("type");
        System.out.println(type);
        if(type.equals("getCustomerById")){
            String customerId= (String) request.getAttribute("customerId");
            System.out.println(customerId);
            String data=userApiService.getCusomerById(Integer.parseInt(customerId));
            System.out.println(data);
            PrintWriter pw=response.getWriter();
            pw.print(data);
        }
    }
    public void init() throws ServletException {
        super.init();
        this.userApiService = new UserApiService();
    }
}

