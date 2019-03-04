package com.orderMeal.dao;

import com.orderMeal.domain.Product;
import com.orderMeal.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {


    //取出一页菜品信息
    public List<Product> getProductPage(int start, int size, String keyWord) throws SQLException {

        List<Product> list=new ArrayList<Product>();
        Connection conn = null;
        String sql="select * from products limit ? ,? ";
        if(keyWord != null){
            keyWord = "'%"+keyWord+"%'";
            sql = "select * from products where product_name like "+keyWord+" limit ? ,?";
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

                Product product=new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductType(rs.getString("product_type"));
                product.setProductSign(rs.getString("product_sign"));
                list.add(product);

            }
            return list;

        } catch (SQLException e) {
            System.out.println("查询餐品列表出错");
            e.printStackTrace();
            throw new SQLException(e);
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }


    }

    //获取菜品的总记录条数
    public String getProductTotal(String keyWord) {

        String sql = "select count(*) from products";
        if(keyWord != null) {
            keyWord = "'%"+keyWord+"%'";
            sql = "select count(*) from products where product_name like " + keyWord;
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
            return "-1";
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }
        return "-1";

    }

    //添加菜品
    public int addProduct(String productName, String productType) throws SQLException {

        String sql = "insert into products(product_name,product_type,product_sign) values(?,?,?)";
        Connection conn = null;
        PreparedStatement ps= null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,productName);
            ps.setString(2,productType);
            ps.setString(3,"1");
            int rs=ps.executeUpdate();
            if(rs>0){
                return rs;
            }
            return -1;
        } catch (SQLException e) {
            System.out.println("查询菜品总数出错");
            e.printStackTrace();
            throw new SQLException(e);
        }finally {
            JdbcUtil.release(ps,conn);
        }

    }

    //验证菜品名是否存在
    public int findProductByName(String productName, String productId) throws SQLException {

        String sql = "select * from products where product_name = ?";
        Connection conn = null;
        PreparedStatement ps= null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,productName);
            rs=ps.executeQuery();
            if(rs.next()){
                if(productId==null) {
                    return -1;
                }
                if(productId.equals(rs.getString("product_id"))){
                    System.out.println("productId = " + productId + "  product_id = " + rs.getString("product_id"));
                    return 1;
                }
                return -1;
            }
            return 1;
        } catch (SQLException e) {
            System.out.println("查询菜品总数出错");
            e.printStackTrace();
            throw new SQLException(e);
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }
    }

    //删除一项菜品
    public int deleteProduct(String productId) {

        String sql = "delete from products where product_id = ?";
        Connection conn = null;
        PreparedStatement ps= null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,productId);
            int rs=ps.executeUpdate();
            System.out.println("rs = " + rs);
            if(rs>0){
                return rs;
            }
            return -1;
        } catch (SQLException e) {
            System.out.println("删除菜品出错");
            e.printStackTrace();
        }finally {
            JdbcUtil.release(ps,conn);
        }
        return -1;
    }

    //更新菜品的Sign字段
    public int updateProductSign(String productId, String productSign) {

        String sql = "update products set product_sign = ? where product_id=?";
        Connection conn = null;
        PreparedStatement ps= null;
        System.out.println("productId = " + productId + "  productSign = " + productSign);
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,productSign);
            ps.setString(2,productId);
            int rs=ps.executeUpdate();
            if(rs>0){
                return rs;
            }
            return -1;
        } catch (SQLException e) {
            System.out.println("更新菜品状态失败");
            e.printStackTrace();
        }finally {
            JdbcUtil.release(ps,conn);
        }
        return -1;

    }

    //更新菜品的name和type字段
    public int updateProductOther(String productId, String productName, String productType) {

        String sql = "update products set product_name = ? , product_type = ? where product_id=?";
        Connection conn = null;
        PreparedStatement ps= null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,productName);
            ps.setString(2,productType);
            ps.setString(3,productId);
            int rs=ps.executeUpdate();
            System.out.println("rs = " + rs );
            if(rs>0){
                return rs;
            }
            return -1;
        } catch (SQLException e) {
            System.out.println("更新菜品信息失败");
            e.printStackTrace();
        }finally {
            JdbcUtil.release(ps,conn);
        }
        return -1;

    }
}
