package com.wap.web;

import com.orderMeal.dao.AboutDao;
import com.orderMeal.domain.About;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AboutApiServlet",urlPatterns = {"/api/about"})
public class AboutApiServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         AboutDao aboutDao=new AboutDao();
        About about=aboutDao.getAboutInfo();
        String aboutContent=about.getContent();
        PrintWriter pw=response.getWriter();
        pw.print(aboutContent);
    }
}
