package com.orderMeal.dao;

import com.orderMeal.domain.Admin;
import com.orderMeal.domain.School;
import com.orderMeal.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    //根据员工号查找是否存在
    public int findAdminByNum(String adminNum) {

        String sql = "select * from admins where admin_num = ?";
        Connection conn = null;
        PreparedStatement ps= null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,adminNum);
            rs=ps.executeQuery();
            if(rs.next()){
                return 1;
            }
        } catch (SQLException e) {
            System.out.println("查询菜品总数出错");
            e.printStackTrace();
            return -1;
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }
        return -1;

    }


    //添加管理员信息
    public int addAdmin(String adminNum, String adminPassword, String adminType) {

        String sql = "insert into admins(admin_num,admin_password,admin_type) values(?,?,?)";
        Connection conn = null;
        PreparedStatement ps= null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,adminNum);
            ps.setString(2,adminPassword);
            ps.setString(3,adminType);
            int rs=ps.executeUpdate();
            if(rs>0){
                return rs;
            }

        } catch (SQLException e) {
            System.out.println("查询菜品总数出错");
            e.printStackTrace();
            return -1;
        }finally {
            JdbcUtil.release(ps,conn);
        }
        return -1;

    }

    //校验管理员账号密码
    public Admin findAdmin(String adminNum, String adminPassword) {

        String sql = "select * from admins where admin_num = ? and admin_password = ? ";
        Connection conn = null;
        PreparedStatement ps= null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,adminNum);
            ps.setString(2,adminPassword);
            rs=ps.executeQuery();
            if(rs.next()){
                Admin admin = new Admin();
                admin.setAdminNum(adminNum);
                admin.setAdminType(rs.getInt("admin_type"));
                return  admin;
            }
        } catch (SQLException e) {
            System.out.println("查询菜品总数出错");
            e.printStackTrace();
            return null;
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }
        return null;

    }

    //获得管理员信息的总数
    public String getAdminTotal(String keyWord) {

        String sql = "select count(*) from admins";
        if(keyWord != null) {
            keyWord = "'%"+keyWord+"%'";
            sql = "select count(*) from admins where admin_num like " + keyWord;
        }
        Connection conn = null;
        PreparedStatement ps= null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){

                return rs.getString("count(*)");

            }
        } catch (SQLException e) {
            System.out.println("查询学校记录总数出错");
            e.printStackTrace();
            return  "-1";
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }
        return "-1";

    }

    //获得一页管理员数据
    public List<Admin> getAdminPage(int start, int size, String keyWord) {

        List<Admin> list = new ArrayList();
        Connection conn = null;
        String sql="select * from admins limit ? ,? ";
        if(keyWord != null){
            keyWord = "'%"+keyWord+"%'";
            sql = "select * from admins where admin_num like "+keyWord+" limit ? ,?";
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setInt(1,start);
            ps.setInt(2,size);
            rs=ps.executeQuery();
            while(rs.next()){

                Admin admin = new Admin();
                admin.setAdminId(rs.getInt("admin_id"));
                admin.setAdminNum(rs.getString("admin_num"));
                admin.setAdminPassword(rs.getString("admin_password"));
                admin.setAdminType(rs.getInt("admin_type"));
                list.add(admin);

            }
            return list;

        } catch (SQLException e) {
            System.out.println("查询餐品列表出错");
            e.printStackTrace();
            return null;
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }

    }
}
