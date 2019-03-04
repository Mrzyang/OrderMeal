package com.orderMeal.web;

import com.orderMeal.dao.RulesDao;
import com.orderMeal.domain.Rules;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RulesServlet",urlPatterns = "/admin/rules")
public class RulesServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("in rulesServlet");
        RulesDao dao = new RulesDao();
        String type = req.getParameter("type");
        System.out.println("AAAAAAAAAAAA"+type);
        if("save".equals(type)){
            System.out.println("aaaaaaa=="+type);
            int orderTime = Integer.parseInt(req.getParameter("orderTime"));
            int orderTimes = Integer.parseInt(req.getParameter("orderTimes"));
            int changeTime = Integer.parseInt(req.getParameter("changeTime"));
            int changeTimes = Integer.parseInt(req.getParameter("changeTimes"));
            int returnTime = Integer.parseInt(req.getParameter("returnTime"));
            int returnTimes = Integer.parseInt(req.getParameter("returnTimes"));
            Rules rules = new Rules(2,orderTime,orderTimes,changeTime,changeTimes,returnTime,returnTimes);
            System.out.println("BBBBBB\n"+rules.toString());
            boolean result = dao.updateRules(rules);
            PrintWriter pw = resp.getWriter();
            pw.write(""+result);
            pw.flush();
            pw.close();
        }else if("default".equals(type)){
            Rules rules = dao.getRules(1);
            rules.setRuleId(2);
            System.out.println(rules.toString());
            boolean result = dao.updateRules(rules);
            PrintWriter pw = resp.getWriter();
            pw.write(""+result);
            pw.flush();
            pw.close();
        }
        else {
            Rules rules = dao.getRules(2);
            req.setAttribute("rules",rules);
            req.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/orderRules.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
