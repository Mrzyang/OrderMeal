package com.wap.web;

import com.wap.dao.SchoolApiDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "SchoolApiServlet", urlPatterns = {"/api/schools"})
public class SchoolApiServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SchoolApiDao schoolApiDao = new SchoolApiDao();
        PrintWriter pw = response.getWriter();
        pw.print(schoolApiDao.getAllSchool());
    }
}
