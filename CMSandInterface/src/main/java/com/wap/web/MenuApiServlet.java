package com.wap.web;

import com.wap.service.MenuApiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MenuApiServlet",urlPatterns = {"/api/menu"})
public class MenuApiServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String schoolId=request.getParameter("schoolId");
        MenuApiService menuApiService=new MenuApiService();
        String data=menuApiService.getMenu(schoolId);
        PrintWriter pw=response.getWriter();
        pw.print(data);
    }
}
