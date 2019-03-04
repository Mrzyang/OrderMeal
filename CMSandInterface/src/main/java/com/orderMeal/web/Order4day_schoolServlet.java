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

@WebServlet(name = "Order4day_schoolServlet", urlPatterns={"/admin/order4day_school"})
public class Order4day_schoolServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        System.out.println("type = " + type);
        OrderService os = new OrderService();

        if("day_schoolList".equals(type)){

            System.out.println("调用周学校订餐汇总函数");
            int flag = 0;//数据校验标记字段
            List mistakeList = new ArrayList<String>();//错误队列
            String orderDelivery = request.getParameter("orderDelivery");//配送日期
            String customerGrade = request.getParameter("customerGrade");//用户年级
            String schoolId = request.getParameter("schoolId");//学校id

            int cGarde = 0;
            int cSchool = 0;

            //数据校验
            if(orderDelivery==null||"".equals(orderDelivery)){
                flag++;
                mistakeList.add("请输入正确的配送日期");
            }
            if(customerGrade==null||"".equals(customerGrade)){
                flag++;
                mistakeList.add("请输入用户的年级");
            }
            if(schoolId==null||"".equals(schoolId)){
                flag++;
                mistakeList.add("请选择正确的学校信息");
            }
            try {
                cSchool = Integer.parseInt(schoolId);
                cGarde = Integer.parseInt(customerGrade);
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

            //获取一个年级的配送信息数据
            int count[][] = os.getOrder4day_school(orderDelivery,cSchool,cGarde,totalClass);
            for(int i =0;i<=totalClass;i++){
                for (int j=0;j<5;j++){
                    System.out.print(count[i][j]+"   ");
                }
                System.out.println();
            }
            if(count==null){
                flag++;
                mistakeList.add("对不起查询出现错误");
            }

            //数据回显
            request.setAttribute("orderDelivery",orderDelivery);
            request.setAttribute("schoolId",schoolId);
            request.setAttribute("customerGrade",customerGrade);
            request.setAttribute("totalClass",totalClass);
            request.setAttribute("schoolList",os.getSchools());
            request.setAttribute("mistakeList",mistakeList);
            request.setAttribute("list",count);
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/order4day_school.jsp").forward(request,response);
            return;

        }

        else{

            System.out.println("调用周学校订餐汇总导向函数");
            request.setAttribute("schoolList",os.getSchools());
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/order4day_school.jsp").forward(request,response);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
