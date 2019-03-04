package com.orderMeal.web;

import com.orderMeal.domain.Order4week_class;
import com.orderMeal.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Order4week_classServlet", urlPatterns={"/admin/order4week_class"})
public class Order4week_classServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        System.out.println("type = " + type);
        OrderService os = new OrderService();

        if("week_classList".equals(type)){

            System.out.println("调用周班级订餐汇总函数");
            int flag = 0;//数据校验标记字段
            List mistakeList = new ArrayList<String>();//错误队列
            String orderDelivery = request.getParameter("orderDelivery");//配送日期
            String customerGrade = request.getParameter("customerGrade");//用户年级
            String customerClass = request.getParameter("customerClass");//用户班级
            String schoolId = request.getParameter("schoolId");//学校id

            int cGarde = 0;
            int cClass = 0;
            int cSchool = 0;

            System.out.println(orderDelivery);
            System.out.println(schoolId);
            System.out.println(customerGrade);
            System.out.println(customerClass);

            //数据校验
            if(orderDelivery==null||"".equals(orderDelivery)){
                flag++;
                mistakeList.add("请输入配送周次的星期一");
            }
            if(customerGrade==null||"".equals(customerGrade)){
                flag++;
                mistakeList.add("请输入用户的年级");
            }
            if(customerClass==null||"".equals(customerClass)){
                flag++;
                mistakeList.add("请输入用户的班级");
            }
            if(schoolId==null||"".equals(schoolId)){
                flag++;
                mistakeList.add("请选择正确的学校信息");
            }
            try {
                cSchool = Integer.parseInt(schoolId);
                cGarde = Integer.parseInt(customerGrade);
                cClass = Integer.parseInt(customerClass);
            }catch (Exception e){
                flag++;
                mistakeList.add("请输入正确的年级或班级信息");
            }

            //获取所有数据
            List<Order4week_class> list = os.getOrder4week_classPage(orderDelivery,cSchool,cGarde,cClass);

            //数据回显
            request.setAttribute("orderDelivery",orderDelivery);
            request.setAttribute("schoolId",schoolId);
            request.setAttribute("customerGrade",customerGrade);
            request.setAttribute("customerClass",customerClass);
            request.setAttribute("schoolList",os.getSchools());
            request.setAttribute("mistakeList",mistakeList);
            request.setAttribute("list",list);
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/order4week_class.jsp").forward(request,response);
            return;

        }
        else{

            System.out.println("调用周班级订餐汇总导向函数");
            request.setAttribute("schoolList",os.getSchools());
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/order4week_class.jsp").forward(request,response);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
