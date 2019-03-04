package com.orderMeal.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.orderMeal.domain.News;
import com.orderMeal.utils.JdbcUtil;

public class BaseDao {
    /**
     * 增删改操作
     * @param sql SQL命令语句
     * @param args 占位符
     * @return 影响的行数
     */
    public int executeUpdate(String sql,Object[] args){
        //获取数据库连接
        Connection conn = JdbcUtil.getConnection();
        //定义用于执行SQL的PrepareStatement
        PreparedStatement ps = null;
        try {
            //创建命令对象
            ps = conn.prepareCall(sql);
            for(int i=0;i<args.length;i++){
                //设置占位符
                ps.setObject(i+1,args[i]);
            }
            int count = ps.executeUpdate();//执行更新操作
            return count;//返回影响的行数
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.release(ps,conn);
        }
        return 0;
    }

    /**
     * 执行查询操作
     * @param sql SQL命令语句
     * @param args 占位符
     * @return 查询到的结果集
     */
    public List<Map<String,Object>> execteQuery(String sql,Object[] args){
        //获取数据库连接
        Connection conn = JdbcUtil.getConnection();
        //定义用于执行SQL语句的PrepareStatement
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String ,Object>> list = new ArrayList<Map<String,Object>>();
        //创建命定对象
        try {
            ps = conn.prepareCall(sql);
            for(int i=0;i<args.length;i++){
                //设置占位符
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            //下面是ResultSet转list
            ResultSetMetaData md = rs.getMetaData();//获取结果集结构信息
            int columnCount = md.getColumnCount();//获取行数
            while (rs.next()){
                Map<String,Object> rowData = new HashMap<String, Object>();
                for (int i=1;i<=columnCount;i++){
                    rowData.put(md.getColumnName(i),rs.getObject(i));
                }
                list.add(rowData);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }
        return null;
    }
}
