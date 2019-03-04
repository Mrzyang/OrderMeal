package com.wap.dao;

import com.orderMeal.domain.News;
import com.orderMeal.utils.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsApiDao {
    public List getAllNewsTitles(){
        Connection conn=null;
        String sql="select news_id,news_title from news";
        PreparedStatement stmt= null;
        ResultSet rs=null;
        List list=new ArrayList<News>();
        try {
            conn= JdbcUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs= stmt.executeQuery();
            while(rs.next()){
                News news=new News();
                news.setNewsId(rs.getInt("news_id"));
                news.setNewsTitle(rs.getString("news_title"));
                list.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.release(stmt,conn);
        }
        return list;
    }
    public News getNewsById(int newId){
        Connection conn=null;
        String sql="select * from news where news_id=?";
        PreparedStatement psmt=null;
        ResultSet rs=null;
        News news=new News();
        try {
            conn= JdbcUtil.getConnection();
            psmt=conn.prepareStatement(sql);
            psmt.setInt(1,newId);
            rs=psmt.executeQuery();
            while(rs.next()){
                news.setNewsId(rs.getInt("news_id"));
                news.setNewsTitle(rs.getString("news_title"));
                news.setNewsContent(rs.getString("news_content"));
                news.setReleaseTime(rs.getString("release_time"));
            }
            return news;
        } catch (SQLException e) {
            System.out.println("获取news出错!");
            e.printStackTrace();
        }finally {
            JdbcUtil.release(rs,psmt,conn);
        }
        return  null;
    }
}
