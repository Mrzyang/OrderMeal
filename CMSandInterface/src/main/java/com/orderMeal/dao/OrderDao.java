package com.orderMeal.dao;

import com.orderMeal.domain.Order4week_class;
import com.orderMeal.domain.Orders;
import com.orderMeal.domain.School;
import com.orderMeal.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    //获得订单记录总条数
    public String getOrder4week_classTotal(String orderDelivery, int schoolId, int customerGrade, int customerClass) {

        String sql = "select count(*) from order4week where school_id = ? and customer_grade = ? and customer_class = ? and order_delivery = ?";
        Connection conn = null;
        PreparedStatement ps= null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,schoolId);
            ps.setInt(2,customerGrade);
            ps.setInt(3,customerClass);
            ps.setString(4,orderDelivery);
            rs = ps.executeQuery();
            if(rs.next()){

                return rs.getString("count(*)");

            }
        } catch (SQLException e) {
            System.out.println("查询订单总数出错");
            e.printStackTrace();
            return "-1";
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }
        return "-1";

    }

    //获得所有学校信息
    public ArrayList<School> getSchool(){
        ArrayList<School> list = new ArrayList();
        Connection conn = null;
        String sql="select school_id,school_name from schools";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){

                School school = new School();
                school.setSchoolId(rs.getString("school_id"));
                school.setSchoolName(rs.getString("school_name"));
                list.add(school);

            }
            return list;

        } catch (SQLException e) {
            System.out.println("查询菜单（学校）列表出错");
            e.printStackTrace();
            return null;
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }
    }

    //获得一页周班级订单数据
    public List<Order4week_class> getOrder4week_classPage(String orderDelivery, int schoolId, int customerGrade, int customerClass) {

        List<Order4week_class> list = new ArrayList();
        Connection conn = null;
        String sql = "select customer_name,order4week_price from order4week where school_id = ? and customer_grade = ? and customer_class = ? and order_delivery = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setInt(1,schoolId);
            ps.setInt(2,customerGrade);
            ps.setInt(3,customerClass);
            ps.setString(4,orderDelivery);
            rs=ps.executeQuery();
            while(rs.next()){

                Order4week_class order4week_class=new Order4week_class(rs.getString("customer_name"),rs.getString("order4week_price").split(","));
                list.add(order4week_class);

            }
            return list;

        } catch (SQLException e) {
            System.out.println("查询周班级订单出错");
            e.printStackTrace();
            return null;
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }

    }

    //获得一个学校对应年级的班级数
    public int getClassTotal(int cSchool, int cGarde) {

        Connection conn = null;
        String sql = "select * from schools where school_id = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setInt(1,cSchool);
            rs=ps.executeQuery();
            if (rs.next()){

                return rs.getInt("school_classtotal"+cGarde);

            }
            return -1;
        } catch (SQLException e) {
            System.out.println("查询周班级订单出错");
            e.printStackTrace();
            return -1;
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }

    }

    //获得一个班级一天的订餐统计
    public int[] getorderTotals(String date, int cSchool, int cGarde, String customerClass) throws SQLException {

        int[] total = new int[5];
        total[4] = 0;
        Connection conn = null;
        String sql = "select count(*) from orders where school_id = ? and customer_grade = ? and customer_class = ? and order_delivery = ? and delivery_date = ? and goods_price = ? and order_status >'1' " ;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setInt(1,cSchool);
            ps.setInt(2,cGarde);
            ps.setInt(3,Integer.parseInt(customerClass));
            ps.setString(4,date);
            ps.setInt(5,1);
            ps.setDouble(6,15);
            rs=ps.executeQuery();
            if(rs.next()){

                total[0] = Integer.parseInt(rs.getString("count(*)"));
                total[4]+=total[0];

            }

            ps=conn.prepareStatement(sql);
            ps.setInt(1,cSchool);
            ps.setInt(2,cGarde);
            ps.setInt(3,Integer.parseInt(customerClass));
            ps.setString(4,date);
            ps.setInt(5,2);
            ps.setDouble(6,15);
            rs=ps.executeQuery();
            if(rs.next()){

                total[1] = Integer.parseInt(rs.getString("count(*)"));
                total[4]+=total[1];

            }

            ps=conn.prepareStatement(sql);
            ps.setInt(1,cSchool);
            ps.setInt(2,cGarde);
            ps.setInt(3,Integer.parseInt(customerClass));
            ps.setString(4,date);
            ps.setInt(5,1);
            ps.setDouble(6,18);
            rs=ps.executeQuery();
            if(rs.next()){

                total[2] = Integer.parseInt(rs.getString("count(*)"));
                total[4]+=total[2];

            }

            ps=conn.prepareStatement(sql);
            ps.setInt(1,cSchool);
            ps.setInt(2,cGarde);
            ps.setInt(3,Integer.parseInt(customerClass));
            ps.setString(4,date);
            ps.setInt(5,2);
            ps.setDouble(6,18);
            rs=ps.executeQuery();
            if(rs.next()){

                total[3] = Integer.parseInt(rs.getString("count(*)"));
                total[4]+=total[3];

            }
            return total;

        } catch (SQLException e) {
            System.out.println("查询周班级订餐统计出错");
            e.printStackTrace();
            throw new SQLException (e);
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }


    }

    //查询学校配送统计
    public int[] getOrder4day_school(String orderDelivery, int cSchool, int cGarde, int customerClass) throws SQLException {

        int[] total = new int[5];
        total[0] = customerClass;
        Connection conn = null;
        String sql = "select count(*) from orders where school_id = ? and customer_grade = ? and customer_class = ? and order_delivery = ? and delivery_date = ? and goods_price = ? and order_status >'1' " ;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();

            //15元午餐
            ps=conn.prepareStatement(sql);
            ps.setInt(1,cSchool);
            ps.setInt(2,cGarde);
            ps.setInt(3,customerClass);
            ps.setString(4,orderDelivery);
            ps.setInt(5,1);
            ps.setDouble(6,15);
            rs=ps.executeQuery();
            if(rs.next()){

                total[1] = Integer.parseInt(rs.getString("count(*)"));

            }

            //18元午餐
            ps=conn.prepareStatement(sql);
            ps.setInt(1,cSchool);
            ps.setInt(2,cGarde);
            ps.setInt(3,customerClass);
            ps.setString(4,orderDelivery);
            ps.setInt(5,1);
            ps.setDouble(6,18);
            rs=ps.executeQuery();
            if(rs.next()){

                total[2] = Integer.parseInt(rs.getString("count(*)"));

            }

            //15元晚餐
            ps=conn.prepareStatement(sql);
            ps.setInt(1,cSchool);
            ps.setInt(2,cGarde);
            ps.setInt(3,customerClass);
            ps.setString(4,orderDelivery);
            ps.setInt(5,2);
            ps.setDouble(6,15);
            rs=ps.executeQuery();
            if(rs.next()){

                total[3] = Integer.parseInt(rs.getString("count(*)"));

            }

            //18元晚餐
            ps=conn.prepareStatement(sql);
            ps.setInt(1,cSchool);
            ps.setInt(2,cGarde);
            ps.setInt(3,customerClass);
            ps.setString(4,orderDelivery);
            ps.setInt(5,2);
            ps.setDouble(6,18);
            rs=ps.executeQuery();
            if(rs.next()){

                total[4] = Integer.parseInt(rs.getString("count(*)"));

            }
            return total;

        } catch (SQLException e) {
            System.out.println("查询学校配送统计出错");
            e.printStackTrace();
            throw new SQLException (e);
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }

    }

    //获取固定日期固定班级的订单名单
    public List getOrder4day_class(int cSchool, int cGarde, int cClass, String orderDelivery, int price, int orderStatus) {

        List<String> list = new ArrayList();
        Connection conn = null;
        String sql = "select customer_name from orders where school_id = ? and customer_grade = ? and customer_class = ? and order_delivery = ? and goods_price = ? and delivery_date = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setInt(1,cSchool);
            ps.setInt(2,cGarde);
            ps.setInt(3,cClass);
            ps.setString(4,orderDelivery);
            ps.setInt(5,price);
            ps.setInt(6,orderStatus);
            rs=ps.executeQuery();
            while(rs.next()){

                list.add(rs.getString("customer_name"));

            }
            return list;

        } catch (SQLException e) {
            System.out.println("查询周班级订单出错");
            e.printStackTrace();
            return null;
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }

    }
}
