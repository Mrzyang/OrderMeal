package com.wap.web;

import com.wap.service.NewsApiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @program: NewsApiServlet
 *
 * @description: 接口:首页公告相关操作
 *
 * @author: Mr.Zhang
 *
 * @create: 2018-6-4
 **/
@WebServlet(name = "NewsApiServlet", urlPatterns = {"/api/news"})
public class NewsApiServlet extends HttpServlet {
    public NewsApiService newsApiService;

    @Override
    public void init() throws ServletException {
        super.init();
        newsApiService = new NewsApiService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if (type.equals("getAll")) {
            String newsList = null;
            newsList = newsApiService.getAllNews();
            PrintWriter pw = response.getWriter();
            pw.print(newsList);
        }
        if (type.equals("getNewsById")) {
            System.out.println("请求我了");
            String newsId=request.getParameter("newsId");
            System.out.println(newsId);
            String data=newsApiService.getNewsById(Integer.parseInt(newsId));
            PrintWriter pw = response.getWriter();
            pw.print(data);
        }
    }
}
