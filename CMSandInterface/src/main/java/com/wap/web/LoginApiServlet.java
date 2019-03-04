package com.wap.web;


import com.wap.service.UserApiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @program: UserApiServlet
 * @description: 接口:用户的相关操作，以及建议反馈的提交
 * @author: Mr.Zhang
 * @create: 2018-6-4
 **/
@WebServlet(name = "LoginApiServlet", urlPatterns = {"/api/login"})
public class LoginApiServlet extends HttpServlet {
    public UserApiService userApiService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String customer_num = request.getParameter("customer_num");
        String customer_passord = request.getParameter("customer_password");
        String school_id = request.getParameter("school_id");
        System.out.println(customer_num);
        System.out.println(customer_passord);
        String msg = userApiService.checkLogin(customer_num, customer_passord, school_id);
        PrintWriter pw = response.getWriter();
        pw.print(msg);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void init() throws ServletException {
        super.init();
        this.userApiService = new UserApiService();
    }
}
