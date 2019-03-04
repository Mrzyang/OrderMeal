package com.orderMeal.web;



import com.orderMeal.service.ExportExcelService;
import com.orderMeal.utils.RequestUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ExcelServlet",urlPatterns = "/export.do")
public class ExcelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("导出服务！");
        RequestUtil util = new RequestUtil();
        //json字符串
        //String jsonStr = new String(req.getParameter("json").getBytes("iso-8859-1"),"utf8");
        int type = Integer.parseInt(req.getParameter("type"));
        String jsonStr = util.requestUtf8(req,"json");
        //表格表头
        String sheaders = util.requestUtf8(req,"headers");
        //表格标题名
        String title = req.getParameter("fileName");
        //表格文件名
        String fileName = req.getParameter("fileName")+".xlsx";
        System.out.println("json=====:  "+jsonStr+"\n+headers========:    "+sheaders+"\ntitle=====:   "+title+"\nfilename=====:"+fileName);
        ExportExcelService ex = new ExportExcelService();
        ex.export(jsonStr,sheaders,fileName,title, resp, req,type);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost");
        doGet(req,resp);
    }
}
