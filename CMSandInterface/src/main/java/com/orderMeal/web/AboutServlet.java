package com.orderMeal.web;

import com.orderMeal.dao.AboutDao;
import com.orderMeal.domain.About;
import com.orderMeal.service.AboutService;
import com.wap.service.UserApiService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AboutServlet", urlPatterns = {"/admin/about"})
public class AboutServlet extends HttpServlet {
    public AboutDao aboutDao;
    public AboutService aboutService;
    public void init() throws ServletException {
        super.init();
        this.aboutDao = new AboutDao();
        aboutService=new AboutService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post请求用于更新操作
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        String content=request.getParameter("content");
        String data=aboutService.updateAboutInfo(content);
        PrintWriter pw=response.getWriter();
        pw.print(data);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get请求用于显示操作
        String type=request.getParameter("type");
        if(type.equals("show")){
            About about=aboutDao.getAboutInfo();
            request.setAttribute("about",about);
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/about.jsp").forward(request,response);
        }
        if (type.equals("update")){
            About about=aboutDao.getAboutInfo();
            request.setAttribute("about",about);
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/aboutUpdate.jsp").forward(request,response);
        }
    }
}
