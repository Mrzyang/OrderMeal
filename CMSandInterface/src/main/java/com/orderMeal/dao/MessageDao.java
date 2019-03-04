package com.orderMeal.dao;

import com.orderMeal.domain.Message;
import com.orderMeal.utils.JdbcUtil;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: OrderMeal
 * @description: message的数据访问层
 * @author: Mr.Zhang
 * @create: 2018-06-09 16:57
 **/
public class MessageDao {
    public List<Message> listAllOf(int startIndex, int offset, String sql) {
        List<Message> messages = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, startIndex);
            psmt.setInt(2, offset);
            rs = psmt.executeQuery();
            while (rs.next()) {
                Message message = new Message(
                        rs.getInt(1),
                        rs.getInt("customer_id"),
                        rs.getString("message"),
                        rs.getString("message_date"),
                        rs.getInt("status")
                );
                messages.add(message);
            }

        } catch (SQLException e) {
            System.out.println("您的分页查询出现了错误");
            e.printStackTrace();
        } finally {
            JdbcUtil.release(rs, psmt, conn);
        }

        return messages;
    }

    //获取某条message
    public Message getMessageById(int id){
        Message message=new Message();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql="SELECT  * from messages WHERE id=?";
        try {
            conn = JdbcUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            rs = psmt.executeQuery();
            while (rs.next()) {
                        message = new Message(
                        rs.getInt(1),
                        rs.getInt("customer_id"),
                        rs.getString("message"),
                        rs.getString("message_date"),
                        rs.getInt("status")
                        );
                        return message;
            }
        } catch (SQLException e) {
            System.out.println("您的分页查询出现了错误");
            e.printStackTrace();
        } finally {
            JdbcUtil.release(rs, psmt, conn);
        }
        return  message;
    }


    public static int count() {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = "select count(*) from messages";
        int count = 0;
        try {
            conn = JdbcUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
                return count;
            }
        } catch (SQLException e) {
            System.out.println("查询message总数失败");
            e.printStackTrace();
        } finally {
            JdbcUtil.release(rs, psmt, conn);
        }
        return 0;
    }

    //分页查询所有message
    public List<Message> getAllMessages(int startIndex, int offet) {
        List<Message> messages = new ArrayList<>();
        String sql = "select *from (select * from messages order by message_date desc) m limit ?,?";
        messages = listAllOf(startIndex, offet, sql);
        return messages;
    }

    //删除一条message
    public boolean deleteMessageById(int id) {
        boolean isSuccessed = false;
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = "DELETE  FROM messages WHERE id =?";
        try {
            conn = JdbcUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            int row = psmt.executeUpdate();
            if (row == 1) {
                isSuccessed = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(rs, psmt, conn);
        }
        return isSuccessed;
    }

    //批量删除message
    public boolean batchDeleteMessages(int[] ids) {
        boolean isSuccess = false;
        for (int id : ids) {
            isSuccess = deleteMessageById(id);
        }
        return isSuccess;
    }

    //标记message为已处理
    public boolean dealMessage(int id) {
        boolean isSuccessed = false;
        String sql = "update messages set status=1 where id=?";
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            if (psmt.executeUpdate() == 1)
                isSuccessed = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(rs, psmt, conn);
        }
        return isSuccessed;
    }
}
