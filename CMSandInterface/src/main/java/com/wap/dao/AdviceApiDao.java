package com.wap.dao;

import com.orderMeal.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

/**
 * @program: OrderMeal
 * @description: 关于用户提交advice相关的dao处理
 * @author: Mr.Zhang
 * @create: 2018-06-10 14:10
 **/
public class AdviceApiDao {
    public boolean submitMessage(int customerId,String message){
        boolean isSuccessed=false;
        Connection conn=null;
        PreparedStatement psmt=null;
        Date date=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String messageDate=df.format(date);
        String sql="insert into messages values (null,?,?,?,0)";
        try {
            conn= JdbcUtil.getConnection();
            psmt=conn.prepareStatement(sql);
            psmt.setInt(1,customerId);
            psmt.setString(2,message);
            psmt.setString(3,messageDate);
            if(psmt.executeUpdate()==1) isSuccessed=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.release(psmt,conn);
        }
        return isSuccessed;
    }
}
