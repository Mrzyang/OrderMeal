package com.orderMeal.service;

import com.orderMeal.dao.OrderDao;
import com.orderMeal.domain.Order4week_class;
import com.orderMeal.domain.Order4week_school;
import com.orderMeal.domain.School;
import com.orderMeal.utils.PageInfo;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderService {

    OrderDao Odao = new OrderDao();


    //获得周班级订单记录总页数
    public int getOrder4week_classTotalPage(String orderDelivery, int schoolId, int customerGrade, int customerClass) {

        String s = Odao.getOrder4week_classTotal(orderDelivery,schoolId,customerGrade,customerClass);
        int total = Integer.parseInt(s);
        if(total == -1){
            return -1;
        }
        if(total%10==0){
            return total/10;
        }else {
            return total/10+1;
        }

    }

    //获得一页周班级订单记录总页数
    public List<Order4week_class> getOrder4week_classPage(String orderDelivery, int schoolId, int customerGrade, int customerClass) {



        List<Order4week_class> list = Odao.getOrder4week_classPage(orderDelivery,schoolId,customerGrade,customerClass);
        return list;

    }

    //获得学校列表
    public ArrayList<School> getSchools() {
        return Odao.getSchool();
    }

    //获取当前学校年级的班级数
    public int getTotalClass(int cSchool, int cGarde) {

        return Odao.getClassTotal(cSchool,cGarde);

    }

    //获得一页周学校订单记录
    public int[][] getOrder4week_schoolPage(String orderDelivery, int cSchool, int cGarde, String customerClass) {

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            Date menuDate = f.parse(orderDelivery);
            c.setTime(menuDate);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        c.add(Calendar.DAY_OF_MONTH, -1);// -1天

        //获取一个表单的数据
        int[][] count = new int[8][];
        for(int i=0;i<7;i++){
            //获得套餐统计
            c.add(Calendar.DAY_OF_MONTH, 1);// +1天
            Date date = c.getTime();
            try {
                count[i] = Odao.getorderTotals(f.format(date),cSchool,cGarde,customerClass);
            } catch (SQLException e) {
                return null;
            }
        }

        //合计数据
        count[7] = new int[]{0, 0, 0, 0, 0};
        for(int i=0;i<5;i++){
            for(int j=0;j<7;j++){
                count[7][i]+=count[j][i];
            }
        }

        return count;
    }

    public int[][] getOrder4day_school(String orderDelivery, int cSchool, int cGarde, int totalClass) {

        //获取一个表单的数据并封装到一个数组中并计算出合计的数据
        int[][] count = new int[totalClass+1][];
        //合计数据初始化
        count[totalClass] = new int[]{-1,0,0,0,0};
        for(int i=0;i<totalClass;i++){
            //获得套餐统计
            try {
                count[i] = Odao.getOrder4day_school(orderDelivery,cSchool,cGarde,i+1);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
            //合计数据
            count[totalClass][1]+=count[i][1];
            count[totalClass][2]+=count[i][2];
            count[totalClass][3]+=count[i][3];
            count[totalClass][4]+=count[i][4];
        }

        return count;
    }

    public List getOrder4day_class(int cSchool, int cGarde, int cClass, String orderDelivery, int price, int orderStatus) {

        return Odao.getOrder4day_class(cSchool,cGarde,cClass,orderDelivery,price,orderStatus);

    }
}
