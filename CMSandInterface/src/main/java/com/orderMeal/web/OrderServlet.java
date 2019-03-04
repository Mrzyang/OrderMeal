package com.orderMeal.web;

import com.orderMeal.domain.Order4week_class;
import com.orderMeal.domain.Order4week_school;
import com.orderMeal.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "OrderServlet", urlPatterns={"/admin/order"})
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        System.out.println("type = " + type);
        OrderService os = new OrderService();

        /*if("update".equals(type)){
            Map map=new HashMap();
            map.put("status",0);
            map.put("data","更新失败!");
            System.out.println("调用修改产品函数");
            int flag = 0;
            String productId = request.getParameter("productId");
            String productType = request.getParameter("productType");
            String productName = request.getParameter("productName");
            String productSign = request.getParameter("productSign");
            String pageNum = request.getParameter("pageNum");
            request.setAttribute("pageNum",pageNum);
            if(productName!=null&&as.findProductByName(productName,productId)==-1){
                map.put("data","已经添加过该菜品了！！");
                flag++;
            }
            if(!productType.equals("1") && !productType.equals("2") && !productType.equals("3") && !productType.equals("4") && !productType.equals(""))
            {
                map.put("data","请选择合法类型");
                System.out.println("productType = " + productType);
                System.out.println("请选择合法类型");
                flag++;
            }
            if(!productSign.equals("1") && !productSign.equals("2") && !productSign.equals("")){
                map.put("data","请选择正确的菜品状态");
                flag++;
            }
            int a = -1;
            if (flag == 0) {
                a = as.updateProduct(productId, productName, productType, productSign);
            }
            if(a!=-1){
                map.put("pageNum",pageNum);
                map.put("status",1);
                map.put("data","更新成功!");
            }
            JSONObject msg= JSONObject.fromObject(map);
            System.out.println(msg);
            response.setCharacterEncoding("UTF-8");
            PrintWriter out=response.getWriter();
            out.println(msg);
            return;
        }*/
        /*if("updateUI".equals(type)){
            System.out.println("调用修改产品函数入口");
            Product product = new Product();
            product.setProductId(Integer.parseInt(request.getParameter("productId")));
            product.setProductName(request.getParameter("productName"));
            product.setProductType(request.getParameter("productType"));
            request.setAttribute("product",product);
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/updateProduct.jsp").forward(request,response);
            return;
        }*/
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
        if("week_classListUI".equals(type)){

            System.out.println("调用周班级订餐汇总导向函数");
            request.setAttribute("schoolList",os.getSchools());
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/order4week_class.jsp").forward(request,response);

        }
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
            System.out.println("totalClass = " + totalClass);

            //获取一个班级数据
            int total[][] = os.getOrder4week_schoolPage(orderDelivery,cSchool,cGarde,customerClass);

            for(int i=0;i<7;i++){
                for(int j=0;j<5;j++){
                    System.out.print(total[i][j]+"  ");
                }
                System.out.println();
            }

            /*//数据会显
            request.setAttribute("pageNum",page);
            request.setAttribute("orderDelivery",orderDelivery);
            request.setAttribute("schoolId",schoolId);
            request.setAttribute("customerGrade",customerGrade);
            request.setAttribute("customerClass",customerClass);
            request.setAttribute("totalPage",totalPage);
            request.setAttribute("schoolList",os.getSchools());
            request.setAttribute("mistakeList",mistakeList);
            request.setAttribute("list",list);
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/order4week_class.jsp").forward(request,response)*/;
            return;

        }

        if("week_schoolListUI".equals(type)){

            System.out.println("调用周学校订餐汇总导向函数");
            request.setAttribute("schoolList",os.getSchools());
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/order4week_school.jsp").forward(request,response);

        }
        else{
            System.out.println("调用订单列表显示函数");
            String page = request.getParameter("pageNum");

            if(page==null||"".equals(page)){
                page = (String)request.getAttribute("pageNum");
            }

            /*
            order_status
            goods_price
            customer_num customer_id
            customer_class
            school_id
            goods_id
            */
            String orderStatus = request.getParameter("orderStatus");
            String orderDelivery = request.getParameter("orderDelivery");
            String customerNum = request.getParameter("customerNum");
            String customerGrade = request.getParameter("customerGrade");
            String customerClass = request.getParameter("customerClass");
            String schoolId = request.getParameter("schoolId");
            String goodsId = request.getParameter("goodsId");

            //总记录页数
            /*int total = -1;
            System.out.println("selectType  = " + selectType);
            switch (selectType){
                case "week-class":os.getOrder4WeekschoolTotalPage(orderDelivery,schoolId,customerGrade,customerClass);break;
            }

            if(total==-1){
                System.out.println("查询出错");
                request.setAttribute("message","查询出错");
                request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/orderList.jsp").forward(request,response);
            }*/

            /*List<Product> list = os.getOrderPage(page,keyword);


            if(page==null){
                request.setAttribute("pageNum",1);
            }else{
                request.setAttribute("pageNum",page);
            }
            request.setAttribute("totalPage",total);
            request.setAttribute("keyword",keyword);
            request.setAttribute("list",list);
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/orderList.jsp").forward(request,response);
            return;*/
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
