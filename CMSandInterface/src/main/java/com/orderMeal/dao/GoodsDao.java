package com.orderMeal.dao;

import com.orderMeal.domain.Goods;
import com.orderMeal.domain.Product;
import com.orderMeal.utils.JdbcUtil;
import javafx.scene.media.SubtitleTrack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDao {

    //获得套餐总数
    public String getGoodsTotal(String keyword) throws SQLException {

        String sql = "select count(*) from goods";
        if(keyword != null) {
            keyword = "'%"+keyword+"%'";
            sql = "select count(*) from goods where goods_name like " + keyword;
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
            System.out.println("查询菜品总数出错");
            e.printStackTrace();
            throw new SQLException(e);
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }
        return "-1";

    }

    //获得一页套餐数据
    public List<Goods> getGoodsPage(int start, int size, String keyWord) throws SQLException {

        List<Goods> list=new ArrayList<Goods>();
        Connection conn = null;
        String sql="select * from goods limit ? ,? ";
        if(keyWord != null){
            keyWord = "'%"+keyWord+"%'";
            sql = "select * from goods where goods_name like "+keyWord+" limit ? ,?";
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

                Goods goods = new Goods();
                goods.setGoodsId(rs.getInt("goods_id"));
                goods.setGoodsName(rs.getString("goods_name"));
                goods.setGoodsPrice(rs.getDouble("goods_price"));
                goods.setProductNames(rs.getString("product_name"));
                list.add(goods);

            }
            return list;

        } catch (SQLException e) {
            System.out.println("查询套餐列表出错");
            e.printStackTrace();
            throw new SQLException(e);
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }

    }

    //得到对应类型的菜品集合
    public List getProduct(int i) throws SQLException {

        List<String> list = new ArrayList();
        Connection conn = null;
        String sql="select product_id,product_name from products where product_type = ? and product_sign = 1";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setInt(1,i);
            rs=ps.executeQuery();
            while(rs.next()){

                list.add(rs.getString("product_name"));

            }
            return list;

        } catch (SQLException e) {
            System.out.println("查询套餐（餐品）列表出错");
            e.printStackTrace();
            throw new SQLException(e);
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }

    }

    //验证套餐名是否存在
    public int findGoodsByName(String goodsName, String goodsId) throws SQLException {

        String sql = "select * from goods where goods_name = ?";
        Connection conn = null;
        PreparedStatement ps= null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,goodsName);
            rs=ps.executeQuery();
            if(rs.next()){
                if(goodsId==null) {
                    return -1;
                }
                if(goodsId.equals(rs.getString("goods_id"))){
                    return 1;
                }
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("查询菜品总数出错");
            e.printStackTrace();
            throw new SQLException(e);
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }
        return 1;

    }

    //添加套餐
    public int addGoods(String goodsName, double goodsPrice, String product, String path) {
        //路径中的反斜线改成顺斜线
        path=path.replace("\\","/");
        String sql = "insert into goods(goods_name,goods_price,product_name,product_path) values(?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps= null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,goodsName);
            ps.setDouble(2,goodsPrice);
            ps.setString(3,product);
            ps.setString(4,path);
            int rs=ps.executeUpdate();
            if(rs>0){
                return rs;
            }
            return -1;
        } catch (SQLException e) {
            System.out.println("添加套餐出错");
            e.printStackTrace();
            return -1;
        }finally {
            JdbcUtil.release(ps,conn);
        }

    }

    //删除套餐信息
    public int deleteGoods(String goodsId) {

        String sql = "delete from goods where goods_id = ?";
        Connection conn = null;
        PreparedStatement ps= null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,goodsId);
            int rs=ps.executeUpdate();
            System.out.println("rs = " + rs);
            if(rs>0){
                return rs;
            }
            return -1;
        } catch (SQLException e) {
            System.out.println("删除套餐出错");
            e.printStackTrace();
        }finally {
            JdbcUtil.release(ps,conn);
        }
        return -1;

    }

    //更新套餐价格参数
    public int updateGoodsPrice(String goodsId, double goodsPrice) {

        String sql = "update goods set goods_price = ? where goods_id=?";
        Connection conn = null;
        PreparedStatement ps= null;
        System.out.println("goodsId = " + goodsId + "  goodsPrice = " + goodsPrice);
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setDouble(1,goodsPrice);
            ps.setString(2,goodsId);
            int rs=ps.executeUpdate();
            if(rs>0){
                return rs;
            }
            return -1;
        } catch (SQLException e) {
            System.out.println("更新套餐状态失败");
            e.printStackTrace();
        }finally {
            JdbcUtil.release(ps,conn);
        }
        return -1;
    }

    //更新套餐所有参数
    public int updateGoodsOther(String goodsId, String goodsName, double goodsPrice, String productName) {

        String sql = "update goods set goods_name = ? , goods_price = ? , product_name = ? where goods_id=?";
        Connection conn = null;
        PreparedStatement ps= null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1,goodsName);
            ps.setDouble(2,goodsPrice);
            ps.setString(3,productName);
            ps.setString(4,goodsId);
            int rs=ps.executeUpdate();
            System.out.println("rs = " + rs);
            if(rs>0){
                return rs;
            }
            return -1;
        } catch (SQLException e) {
            System.out.println("更新套餐信息失败");
            e.printStackTrace();
        }finally {
            JdbcUtil.release(ps,conn);
        }
        return -1;

    }

    //根据id寻找套餐信息
    public static Goods findGoodsById(String goodsId) {

        String sql = "select * from goods where goods_id = ?";
        Connection conn = null;
        PreparedStatement ps= null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(goodsId));
            rs=ps.executeQuery();
            if(rs.next()){
                Goods goods = new Goods();
                goods.setGoodsId(Integer.parseInt(goodsId));
                goods.setGoodsName(rs.getString("goods_name"));
                goods.setGoodsPrice(rs.getDouble("goods_price"));
                goods.setProductNames(rs.getString("product_name"));
                goods.setProductPath(rs.getString("product_path"));
                return goods;
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
}
