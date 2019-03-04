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

@WebServlet(name = "Order4week_schoolServlet", urlPatterns={"/admin/order4week_school"})
public class Order4week_schoolServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        System.out.println("type = " + type);
        OrderService os = new OrderService();

        if("week_schoolList".equals(type)){

            System.out.println("调用周学校订餐汇总函数");
            int flag = 0;//数据校验标记字段
            List mistakeList = new ArrayList<String>();//错误队列
            String orderDelivery = request.getParameter("orderDelivery");//周次
            String customerGrade = request.getParameter("customerGrade");//用户年级
            String customerClass = request.getParameter("customerClass");//用户班级
            String schoolId = request.getParameter("schoolId");//学校id

            //若无班号，则将班号置为1
            if(customerClass==null){
                customerClass = "1";
            }

            int cGarde = 0;
            int cClass = 0;
            int cSchool = 0;

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

            //获得该年级的班级总数
            int totalClass = os.getTotalClass(cSchool,cGarde);
            if(totalClass<0){
                flag++;
                mistakeList.add("对不起查询出现错误");
            }

            //获取一个班级数据
            int count[][] = os.getOrder4week_schoolPage(orderDelivery,cSchool,cGarde,customerClass);
            if(count==null){
                flag++;
                mistakeList.add("对不起查询出现错误");
            }

            //数据回显
            request.setAttribute("orderDelivery",orderDelivery);
            request.setAttribute("schoolId",schoolId);
            request.setAttribute("customerGrade",customerGrade);
            request.setAttribute("customerClass",customerClass);
            request.setAttribute("totalClass",totalClass);
            request.setAttribute("schoolList",os.getSchools());
            request.setAttribute("mistakeList",mistakeList);
            request.setAttribute("total",(count[7][0]+count[7][1])*15+(count[7][2]+count[7][3])*18);
            request.setAttribute("list",count);
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/order4week_school.jsp").forward(request,response);
            return;

        }

        else{

            System.out.println("调用周学校订餐汇总导向函数");
            request.setAttribute("schoolList",os.getSchools());
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/order4week_school.jsp").forward(request,response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
