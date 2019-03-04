package com.orderMeal.web;

import com.orderMeal.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Order4day_classServlet", urlPatterns={"/admin/order4day_class"})
public class Order4day_classServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        System.out.println("type = " + type);
        OrderService os = new OrderService();

        if("day_classList".equals(type)){

            System.out.println("调用日班级订餐汇总函数");
            int flag = 0;//数据校验标记字段
            List mistakeList = new ArrayList<String>();//错误队列
            String orderDelivery = request.getParameter("orderDelivery");//配送日期
            String customerGrade = request.getParameter("customerGrade");//用户年级
            String customerClass = request.getParameter("customerClass");//用户班级
            String schoolId = request.getParameter("schoolId");//学校id

            int cGarde = 0;
            int cSchool = 0;
            int cClass = 0;

            //数据校验
            if(orderDelivery==null||"".equals(orderDelivery)){
                flag++;
                mistakeList.add("请输入正确的配送日期");
            }
            if(customerGrade==null||"".equals(customerGrade)){
                flag++;
                mistakeList.add("请输入用户的年级");
            }
            if(customerClass==null||"".equals(customerClass)){
                flag++;
                mistakeList.add("请输入用户班级");
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
                mistakeList.add("请输入正确的学校、年级或班级信息");
            }

            //获取一个班级两种套餐的详细名单
            List list1 = os.getOrder4day_class(cSchool,cGarde,cClass,orderDelivery,15,1);
            List list2 = os.getOrder4day_class(cSchool,cGarde,cClass,orderDelivery,18,1);
            List list3 = os.getOrder4day_class(cSchool,cGarde,cClass,orderDelivery,15,2);
            List list4 = os.getOrder4day_class(cSchool,cGarde,cClass,orderDelivery,18,2);

            System.out.println(list1);
            System.out.println(list2);
            System.out.println(list3);
            System.out.println(list4);

            //数据回显
            request.setAttribute("orderDelivery",orderDelivery);
            request.setAttribute("schoolId",schoolId);
            request.setAttribute("customerGrade",customerGrade);
            request.setAttribute("customerClass",customerClass);
            request.setAttribute("schoolList",os.getSchools());
            request.setAttribute("mistakeList",mistakeList);
            request.setAttribute("list1",list1);
            request.setAttribute("list2",list2);
            request.setAttribute("list3",list3);
            request.setAttribute("list4",list4);
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/order4day_class.jsp").forward(request,response);
            return;

        }

        else{

            System.out.println("调用日班级订餐汇总导向函数");
            request.setAttribute("schoolList",os.getSchools());
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/order4day_class.jsp").forward(request,response);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
