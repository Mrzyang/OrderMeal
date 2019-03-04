package com.wap.web;

import com.google.gson.Gson;
import com.wap.utils.ResponseData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ErrorServlet", urlPatterns = {"/api/error"})
public class ErrorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw=response.getWriter();
        ResponseData responseData=ResponseData.forbidden();
        Gson gson=new Gson();
        pw.print(responseData);
    }
}
