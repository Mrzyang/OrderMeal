package com.wap.web;

import com.wap.service.AdviceApiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AdviceServlet",urlPatterns = {"/api/advice"})
public class AdviceApiServlet extends HttpServlet {
    public AdviceApiService adviceApiService;

    @Override
    public void init() throws ServletException {
        super.init();
        adviceApiService=new AdviceApiService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerId= (String) request.getAttribute("customerId");
        String message=request.getParameter("message");
        String data=adviceApiService.submitMessage(Integer.parseInt(customerId),message);
        PrintWriter pw=response.getWriter();
        pw.print(data);
    }
}
