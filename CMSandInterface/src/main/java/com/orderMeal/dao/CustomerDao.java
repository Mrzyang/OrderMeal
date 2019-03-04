package com.orderMeal.dao;

import com.google.gson.Gson;
import com.orderMeal.domain.Customer;
import com.orderMeal.domain.Goods;
import com.orderMeal.utils.JdbcUtil;

import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

public class CustomerDao extends BaseDao {

    /**
     * 顾客登录
     * @param customerNum 学生学号
     * @param customerPassword 学生密码，初始值设为学号
     * @return 找到择返回顾客
     */
    public String login(String customerNum,String customerPassword){
        String customerId = null;
        String sql = "select customer_id from customers where customer_num = ? and customer_password = ?";
        List<Map<String,Object>> list = execteQuery(sql,new Object[] {customerNum,customerPassword});
        if(!list.isEmpty()){
            Map<String,Object> map = list.get(0);
            customerId = map.get("customerId").toString();
        }
        return customerId;
    }

    public boolean register(String customerNum,String customerPassword){
        int count = 0;
        String sql = "insert into customers(customer_num,customer_password) value(?,?)";
        count = executeUpdate(sql,new Object[] { customerNum,customerPassword });
        return count>0;
    }


    public String showWeekMenu(){
        //获取本周星期一时间（服务器上时间），这里是本机时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTimeInMillis(System.currentTimeMillis());
        System.out.println("当前时间："+ format.format(calendar.getTime()));
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        String date = format.format(calendar.getTime());
        System.out.println("本周一："+ format.format(calendar.getTime()));

        //定义存储本周所有套餐的list
        List<Goods> goodsList = new ArrayList<Goods>();
        //转换为json返回
        String json = null;
        //查找本周菜单中的所有套餐信息 SQL语句
        String sql2 = "select * from goods";
        String sql = "SELECT * from goods where goods_id IN (SELECT goods_id FROM m2g where m2g.menu_id=" +
                "(SELECT menu_id from menus where menu_date LIKE '%"+date+"%') ORDER BY m2g.m2g_type)";
        List<Map<String,Object>> list = execteQuery(sql,new Object[]{});
        for(int i=0;i<list.size();i++){
            Map<String,Object> map = list.get(i);
            int goodsId = Integer.parseInt(map.get("goods_id").toString());
            String goodsName = map.get("goods_name").toString();
            double goodsPrice = Double.parseDouble(map.get("goods_price").toString());
            String productNames = map.get("product_name").toString();
            String productIds = map.get("product_id").toString();
            Goods goods = new Goods(goodsId,goodsName,goodsPrice,productNames,productIds);
            goodsList.add(goods);
        }
        Gson gson = new Gson();
        json = gson.toJson(goodsList);
        return json;
    }

    public static Customer getCustomerById(int id){
        Connection conn= null;
        PreparedStatement psmt=null;
        ResultSet rs=null;
        String sql="select * from customers where customer_id=?";
        Customer customer=new Customer();
        try {
            conn= JdbcUtil.getConnection();
            psmt=conn.prepareStatement(sql);
            psmt.setInt(1,id);
            rs=psmt.executeQuery();
            while(rs.next()){
                customer.setCustomerId(rs.getString("customer_id"));
                customer.setCustomerNum(rs.getString("customer_num"));
                customer.setSchoolId(rs.getString("school_id"));
                customer.setSchoolName(rs.getString("school_name"));
                customer.setCustomerClass(rs.getString("customer_class"));
                customer.setCustomerGrade(rs.getString("customer_grade"));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setCustomerPassword(rs.getString("customer_password"));
                customer.setCustomerType(rs.getString("customer_type"));
                customer.setCustomerAccount(rs.getDouble("customer_account"));
                customer.setCustomerPhone(rs.getString("customer_phone"));
                return customer;
            }
        } catch (SQLException e) {
            System.out.println("customer信息查询失败");
            e.printStackTrace();
        }finally {
            JdbcUtil.release(rs,psmt,conn);
        }
        return null;
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        CustomerDao dao =new CustomerDao();

        Customer customer=dao.getCustomerById(3);
        System.out.println(customer);
    }

}
