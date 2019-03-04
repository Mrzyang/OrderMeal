package com.orderMeal.dao;

import com.orderMeal.domain.About;
import com.orderMeal.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: OrderMeal
 * @description: 关于我们 ---数据库操作
 * @author: Mr.Zhang
 * @create: 2018-06-06 12:15
 **/
public class AboutDao {
    public About getAboutInfo() {                //这里我们规定about表中只允许有一条记录，id=1
        Connection conn = JdbcUtil.getConnection();
        String sql = "select * from about";
        About about = new About();
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                about.setContent(rs.getString("content"));
            }
            JdbcUtil.release(psmt, conn);
        } catch (SQLException e) {
            System.out.println("查询about表出了问题");
            e.printStackTrace();
        }
        if (about != null)
            return about;
        else
            return null;
    }


    public boolean updateAboutInfo(String content) {
        boolean isSuccessed = false;
        Connection conn = JdbcUtil.getConnection();
        String sql="update about set content=? where id=1";
        try {
            PreparedStatement psmt=conn.prepareStatement(sql);
            psmt.setString(1,content);
            if(psmt.executeUpdate()==1){
                isSuccessed=true;
            }
            JdbcUtil.release(psmt,conn);
        } catch (SQLException e) {
            System.out.println("修改about表出了问题");
            e.printStackTrace();
        }
        return isSuccessed;
    }
}
