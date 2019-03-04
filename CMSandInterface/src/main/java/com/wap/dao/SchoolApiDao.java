package com.wap.dao;

import com.google.gson.Gson;
import com.orderMeal.domain.School;
import com.orderMeal.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: OrderMeal
 * @description: 学校信息的相关数据接口
 * @author: Mr.Zhang
 * @create: 2018-06-05 11:32
 **/
public class SchoolApiDao {
    public Gson gson;
    public SchoolApiDao(){
        gson=new Gson();
    }
    public String getAllSchool(){
        Connection conn= null;
        String sql="select school_id,school_name from schools";
        PreparedStatement psmt= null;
        ResultSet rs=null;
        List list=new ArrayList<School>();
        try {
            conn= JdbcUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            rs=psmt.executeQuery();
            while (rs.next()){
                School school=new School();
                school.setSchoolId(rs.getString("school_id"));
                school.setSchoolName(rs.getString("school_name"));
                list.add(school);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.release(rs,psmt,conn);
        }
        return  gson.toJson(list);
    }
}
