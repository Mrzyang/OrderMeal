package com.wap.dao;

import com.orderMeal.domain.Orders;
import com.orderMeal.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: OrderMeal
 * @description:  获取用户orders 的dao层
 * @author: Mr.Zhang
 * @create: 2018-06-13 18:45
 **/
public class OrderApiDao {
    public List getOrdersByCusntomerId(int customerId){
        Connection conn=null;
        PreparedStatement psmt=null;
        ResultSet rs=null;
        String sql="select * from orders  where customer_id=? order by order_date desc";
        List list=new ArrayList<Orders>();
        try {
            conn= JdbcUtil.getConnection();
            psmt=conn.prepareStatement(sql);
            psmt.setInt(1,customerId);
            rs=psmt.executeQuery();
            while(rs.next()){
                Orders orders=new Orders();
                orders.setOrderId(rs.getInt("order_id"));
                orders.setGoodsId(rs.getInt("goods_id"));
                orders.setGoodsPrice(rs.getDouble("goods_price"));
                orders.setProductName(rs.getString("product_name"));
                orders.setOrderDelivery(rs.getString("order_delivery"));
                orders.setDeliveryDate(rs.getInt("delivery_date"));
                orders.setOrderDate(rs.getString("order_date"));
                orders.setOrderStatus(rs.getInt("order_status"));
                orders.setGoods(rs.getInt("goods_id"));
                System.out.println("goodsId="+orders.getGoodsId());
                list.add(orders);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.release(rs,psmt,conn);
        }

        return  list;
    }

    public Orders getOrderById(int orderId){
        Connection conn=null;
        PreparedStatement psmt=null;
        ResultSet rs=null;
        String sql="select * from orders  where order_id=?";
        Orders orders=new Orders();
        try {
            conn= JdbcUtil.getConnection();
            psmt=conn.prepareStatement(sql);
            psmt.setInt(1,orderId);
            rs=psmt.executeQuery();
            while(rs.next()){
                orders.setGoodsId(rs.getInt("goods_id"));
                orders.setGoodsPrice(rs.getDouble("goods_price"));
                orders.setProductName(rs.getString("product_name"));
                orders.setOrderDelivery(rs.getString("order_delivery"));
                orders.setDeliveryDate(rs.getInt("delivery_date"));
                orders.setOrderDate(rs.getString("order_date"));
                orders.setOrderStatus(rs.getInt("order_status"));
                orders.setGoods(rs.getInt("goods_id"));
                System.out.println("goodsId="+orders.getGoodsId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.release(rs,psmt,conn);
        }
        return  orders;
    }
    public boolean cancleOrderById(int orderId,double goodsPrice,int customerId){
        boolean isSuccess=false;
        Connection conn=null;
        PreparedStatement psmt=null;
        ResultSet rs=null;
        String sql="update orders set order_status=0 where order_id=?";
        try {
            conn=JdbcUtil.getConnection();
            psmt=conn.prepareStatement(sql);
            psmt.setInt(1,orderId);
            int result=psmt.executeUpdate();

            //退订单导致账户的余额增加
            sql="update customers set customer_account=customer_account+? where customer_id=?";
            psmt=conn.prepareStatement(sql);
            psmt.setDouble(1,goodsPrice);
            psmt.setInt(2,customerId);
            int result2=psmt.executeUpdate();
            if(result==1&&result2==1) isSuccess=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  isSuccess;
    }
}

