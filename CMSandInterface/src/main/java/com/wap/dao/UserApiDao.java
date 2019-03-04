package com.wap.dao;

import com.orderMeal.domain.Customer;
import com.orderMeal.utils.JdbcUtil;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: OrderMeal
 * @description: 用户dao接口
 * @author: Mr.Zhang
 * @create: 2018-06-05 00:15
 **/
public class UserApiDao {
    public Customer isRegistered(String num, String password,String schoolId) {
        Connection conn= null;
        String sql="SELECT * FROM customers WHERE customer_num=? AND customer_password=? AND school_id=?";
        PreparedStatement psmt= null;
        ResultSet rs=null;
        try {
            conn=JdbcUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1,num);
            psmt.setString(2,password);
            psmt.setString(3,schoolId);
            rs=psmt.executeQuery();
            while(rs.next()){
                conn= JdbcUtil.getConnection();
                Customer customer=new Customer();
                customer.setCustomerId(rs.getString("customer_id"));
                customer.setCustomerNum(rs.getString("customer_num"));
                customer.setSchoolId(rs.getString("school_id"));
                customer.setSchoolName(rs.getString("school_name"));
                customer.setCustomerClass(rs.getString("customer_class"));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setCustomerPassword(rs.getString("customer_password"));
                customer.setCustomerType(rs.getString("customer_type"));
                customer.setCustomerAccount(rs.getDouble("customer_account"));
                customer.setCustomerPhone(rs.getString("customer_phone"));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.release(rs,psmt,conn);
        }
        return null;
    }

    public Customer getCustomerById(int customerId){
        Connection conn=null;
        String sql="SELECT * FROM customers WHERE customer_id=?";
        PreparedStatement psmt= null;
        ResultSet rs=null;
        try {
            conn=JdbcUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,customerId);
            rs=psmt.executeQuery();
            while(rs.next()){
                conn= JdbcUtil.getConnection();
                Customer customer=new Customer();
                customer.setCustomerId(rs.getString("customer_id"));
                customer.setCustomerNum(rs.getString("customer_num"));
                customer.setSchoolId(rs.getString("school_id"));
                customer.setSchoolName(rs.getString("school_name"));
                customer.setCustomerGrade(rs.getString("customer_grade"));
                customer.setCustomerClass(rs.getString("customer_class"));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setCustomerPassword(rs.getString("customer_password"));
                customer.setCustomerType(rs.getString("customer_type"));
                customer.setCustomerAccount(rs.getDouble("customer_account"));
                customer.setCustomerPhone(rs.getString("customer_phone"));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.release(rs,psmt,conn);
        }
        return null;
    }

    public boolean modifyInfo(String customerId,String newPassword,String customerPhone){
        boolean isSuccessed=false;
        Connection conn=null;
        String sql="update customers set customer_password=?,customer_phone=? where customer_id=?";
        PreparedStatement psmt= null;
        ResultSet rs=null;
        try {
            conn=JdbcUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, newPassword);
            psmt.setString(2,customerPhone);
            psmt.setInt(3, Integer.parseInt(customerId));
            int row= psmt.executeUpdate();
            if(row==1) isSuccessed=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.release(psmt,conn);
        }
        return isSuccessed;
    }
}
