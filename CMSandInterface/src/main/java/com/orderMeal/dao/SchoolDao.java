package com.orderMeal.dao;

import com.orderMeal.domain.Product;
import com.orderMeal.domain.School;
import com.orderMeal.utils.JdbcUtil;
import com.sun.org.apache.bcel.internal.generic.RET;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchoolDao {


    //根据学校名查找学校
    public int findSchoolByName(String schoolName, String schoolId) {

        String sql = "select * from schools where school_name = ?";
        Connection conn = null;
        PreparedStatement ps= null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,schoolName);
            rs=ps.executeQuery();
            if(rs.next()){
                if(schoolId==null) {
                    return -1;
                }
                if(schoolId.equals(rs.getString("school_id"))){
                    return 1;
                }
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("查询菜品总数出错");
            e.printStackTrace();
            return 1;
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }
        return 1;

    }

    //添加学校信息
    public int addSchool(String schoolName,String schoolClasstotal1, String schoolClasstotal2, String schoolClasstotal3, String schoolPhone, String schoolAddress) {

        String sql = "insert into schools(school_name,school_classtotal1,school_classtotal2,school_classtotal3,school_phone,school_address) values(?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps= null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,schoolName);
            ps.setInt(2,Integer.parseInt(schoolClasstotal1));
            ps.setInt(3,Integer.parseInt(schoolClasstotal2));
            ps.setInt(4,Integer.parseInt(schoolClasstotal3));
            ps.setString(5,schoolPhone);
            ps.setString(6,schoolAddress);
            int rs=ps.executeUpdate();
            if(rs>0){
                return rs;
            }
            return -1;
        } catch (SQLException e) {
            System.out.println("查询菜品总数出错");
            e.printStackTrace();
            return -1;
        }finally {
            JdbcUtil.release(ps,conn);
        }

    }

    //获取学校记录总数
    public String getSchoolTotal(String keyWord) {

        String sql = "select count(*) from schools";
        if(keyWord != null) {
            keyWord = "'%"+keyWord+"%'";
            sql = "select count(*) from schools where school_name like " + keyWord;
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
            return "-1";
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }
        return "-1";

    }

    //获取一页学校记录
    public List<School> getSchoolPage(int start, int size, String keyWord){

        List<School> list=new ArrayList();
        Connection conn = null;
        String sql="select * from schools limit ? ,? ";
        if(keyWord != null){
            keyWord = "'%"+keyWord+"%'";
            sql = "select * from schools where school_name like "+keyWord+" limit ? ,?";
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

                School school = new School();
                school.setSchoolId(rs.getString("school_id"));
                school.setSchoolName(rs.getString("school_name"));
                school.setSchoolPhone(rs.getString("school_phone"));
                school.setSchoolAddress(rs.getString("school_address"));
                school.setSchoolClasstotal1(rs.getInt("school_classtotal1"));
                school.setSchoolClasstotal2(rs.getInt("school_classtotal2"));
                school.setSchoolClasstotal3(rs.getInt("school_classtotal3"));
                list.add(school);

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

    //删除一条学校记录
    public int deleteSchool(String schoolId) {

        String sql = "delete from schools where school_id = ?";
        Connection conn = null;
        PreparedStatement ps= null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,schoolId);
            int rs=ps.executeUpdate();
            System.out.println("rs = " + rs);
            if(rs>0){
                return rs;
            }
            return -1;
        } catch (SQLException e) {
            System.out.println("删除学校信息出错");
            e.printStackTrace();
            return -1;
        }finally {
            JdbcUtil.release(ps,conn);
        }

    }

    //更新学校信息
    public int updateSchool(String schoolId, String schoolName, String schoolClasstotal1, String schoolClasstotal2, String schoolClasstotal3, String schoolPhone, String schoolAddress) {

        String sql = "update schools set school_name = ? , school_phone = ? , school_address = ? , school_classtotal1 = ? , school_classtotal2 = ? , school_classtotal3 = ? where school_id=?";
        Connection conn = null;
        PreparedStatement ps= null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,schoolName);
            ps.setString(2,schoolPhone);
            ps.setString(3,schoolAddress);
            ps.setInt(4,Integer.parseInt(schoolClasstotal1));
            ps.setInt(5,Integer.parseInt(schoolClasstotal2));
            ps.setInt(6,Integer.parseInt(schoolClasstotal3));
            ps.setString(7,schoolId);
            int rs=ps.executeUpdate();
            System.out.println("rs = " + rs );
            if(rs>0){
                return rs;
            }
            return -1;
        } catch (SQLException e) {
            System.out.println("更新学校信息失败");
            e.printStackTrace();
            return -1;
        }finally {
            JdbcUtil.release(ps,conn);
        }

    }
}
