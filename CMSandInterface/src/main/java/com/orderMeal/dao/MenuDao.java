package com.orderMeal.dao;

import com.orderMeal.domain.Goods;
import com.orderMeal.domain.Menu;
import com.orderMeal.domain.School;
import com.orderMeal.utils.JdbcUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class MenuDao {

    //获得所有套餐的信息
    public ArrayList<Goods> getGoods() throws SQLException {

        ArrayList<Goods> list = new ArrayList();
        Connection conn = null;
        String sql="select goods_id,goods_name,goods_price,product_name from goods";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps=conn.prepareStatement(sql);
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
            System.out.println("查询菜单（套餐）列表出错");
            e.printStackTrace();
            throw new SQLException(e);
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }
    }

    //获得所有学校信息
    public ArrayList<School> getSchool() throws SQLException {
        ArrayList<School> list = new ArrayList();
        Connection conn = null;
        String sql="select school_id,school_name from schools";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){

                School school = new School();
                school.setSchoolId(rs.getString("school_id"));
                school.setSchoolName(rs.getString("school_name"));
                list.add(school);

            }
            return list;

        } catch (SQLException e) {
            System.out.println("查询菜单（学校）列表出错");
            e.printStackTrace();
            throw new SQLException(e);
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }
    }

    //新建一张菜单
    public int addMenu(String schoolId, String schoolName, Date menuDate, Map<Integer,Goods> goods, String menuId) {

        String sql = "insert into menus(menu_id,school_id,school_name,menu_date) values(?,?,?,?)";
        String sql2 = "insert into menu2goods(menu_id,goods_id,goods_name,goods_price,product_name,delivery_date) values(?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps= null;
        ResultSet rs = null;
        int flag = 1;
        try {
            conn = JdbcUtil.getConnection();
            //开启事务
            conn.setAutoCommit(false);

            //添加一张菜谱
            ps = conn.prepareStatement(sql);
            ps.setString(1,menuId);
            ps.setInt(2,Integer.parseInt(schoolId));
            ps.setString(3,schoolName);
            ps.setDate(4, new java.sql.Date(menuDate.getTime()));
            int s=ps.executeUpdate();
            if(s<=0){
               flag = -1;
                System.out.println("添加菜谱失败");
            }

            //添加菜谱的信息
            for (Map.Entry<Integer, Goods> entry : goods.entrySet()) {

                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                Goods g = entry.getValue();
                ps = conn.prepareStatement(sql2);
                ps.setString(1,menuId);
                ps.setInt(2,g.getGoodsId());
                ps.setString(3,g.getGoodsName());
                ps.setDouble(4,g.getGoodsPrice());
                ps.setString(5,g.getProductNames());
                ps.setInt(6,entry.getKey());
                s =-1;
                s = ps.executeUpdate();
                if(s<=0){
                    flag = -1;
                    System.out.println("添加菜谱关系失败");
                }
                System.out.println("flag = " + flag);
            }



            //判断是否提交事务
            if(flag == 1){
                System.out.println("提交事务");
                conn.commit();
                return 1;
            }else{
                conn.rollback();
                return -1;
            }

        } catch (SQLException e) {
            System.out.println("添加菜单出错");
            e.printStackTrace();
            return -1;
        }finally {
            System.out.println("关闭链接");
            JdbcUtil.release(ps,conn);
        }

    }

    //获取菜单记录的总记录条数
    public String getMenuTotal(String keyword) {

        String sql = "select count(*) from menus";
        if(keyword != null) {
            keyword = "'%"+keyword+"%'";
            sql = "select count(*) from menus where school_name like " + keyword;
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
            System.out.println("查询菜单记录总数出错");
            e.printStackTrace();
            return "-1";
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }
        return "-1";

    }

    //获取一页菜谱信息
    public List<Menu> getMenuPage(int start, int size, String keyWord) throws SQLException {

        List<Menu> list = new ArrayList<Menu>();
        Connection conn = null;
        String sql="select * from menus limit ? ,? ";
        if(keyWord != null){
            keyWord = "'%"+keyWord+"%'";
            sql = "select * from menus where school_name like "+keyWord+" limit ? ,?";
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

                Menu menu = new Menu();
                menu.setMenuId(rs.getString("menu_id"));
                menu.setSchoolId(rs.getInt("school_id"));
                menu.setSchoolName(rs.getString("school_name"));
                menu.setMenuDate(rs.getDate("menu_date"));
                list.add(menu);

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

    //根据id获得菜单关系
    public Map<Integer,Goods> getMenu2GoodsById(String menuId) {

        Map<Integer,Goods> map = new HashMap<Integer, Goods>();
        Connection conn = null;
        String sql="select menu2goods.goods_id,menu2goods.goods_name,menu2goods.goods_price,delivery_date,menu2goods.product_name,product_path from menu2goods,goods where menu_id = ? AND menu2goods.goods_id=goods.goods_id";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1,menuId);
            rs=ps.executeQuery();
            while(rs.next()){

                Goods goods = new Goods();
                goods.setGoodsId(rs.getInt("goods_id"));
                goods.setGoodsName(rs.getString("goods_name"));
                goods.setGoodsPrice(rs.getDouble("goods_price"));
                goods.setProductNames(rs.getString("product_name"));
                goods.setProductPath(rs.getString("product_path"));
                map.put(rs.getInt("delivery_date"),goods);

            }
            return map;

        } catch (SQLException e) {
            System.out.println("查询菜谱中套餐信息出错");
            e.printStackTrace();
            return null;
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }

    }

    //更新菜谱信息
    public int updateMenu2Goods(String menuId, HashMap<Integer,Goods> goods, Date menuDate, String schoolId, String schoolName) {

        String sql = "delete from menu2goods where menu_id = ?";
        String sql1 = "insert into menu2goods(menu_id,goods_id,goods_name,goods_price,product_name,delivery_date) values(?,?,?,?,?,?)";
        String sql2 = "update menus set menu_date = ?,school_id = ?,school_name = ? where menu_id=?";
        Connection conn = null;
        PreparedStatement ps= null;
        ResultSet rs = null;
        int flag = 1;
        try {
            conn = JdbcUtil.getConnection();
            //开启事务
            conn.setAutoCommit(false);

            //删除之前的关系信息
            ps = conn.prepareStatement(sql);
            System.out.println("menuId = " + menuId);
            ps.setString(1,menuId);
            int r=ps.executeUpdate();
            System.out.println("rs = " + rs);
            if(r<=0){
                flag = -1;
                System.out.println("删除菜谱关系失败");
            }

            //添加新的关系信息
            for (Map.Entry<Integer, Goods> entry : goods.entrySet()) {

                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                Goods g = entry.getValue();
                ps = conn.prepareStatement(sql1);
                ps.setString(1,menuId);
                ps.setInt(2,g.getGoodsId());
                ps.setString(3,g.getGoodsName());
                ps.setDouble(4,g.getGoodsPrice());
                ps.setString(5,g.getProductNames());
                ps.setInt(6,entry.getKey());
                int s = ps.executeUpdate();
                if(s<=0){
                    flag = -1;
                    System.out.println("添加新的菜谱关系失败");
                }
            }

            //更新菜单信息
            ps = conn.prepareStatement(sql2);

            ps.setDate(1,new java.sql.Date(menuDate.getTime()));
            ps.setInt(2,Integer.parseInt(schoolId));
            ps.setString(3,schoolName);
            ps.setString(4,menuId);
            int s=ps.executeUpdate();
            System.out.println("rs = " + rs);
            if(s<=0){
                flag = -1;
                System.out.println("更新菜单信息失败");
            }

            //判断是否提交事务
            if(flag == 1){
                System.out.println("提交事务");
                conn.commit();
                return 1;
            }else{
                conn.rollback();
                return -1;
            }

        } catch (SQLException e) {
            System.out.println("更新菜单出错");
            e.printStackTrace();
            return -1;
        }finally {
            System.out.println("关闭链接");
            JdbcUtil.release(ps,conn);
        }


    }

    public int deleteMenu(String menuId) {

        String sql = "delete from menu2goods where menu_id = ?";
        String sql2 = "delete from menus where menu_id = ?";
        Connection conn = null;
        PreparedStatement ps= null;
        int flag = 1;
        try {
            conn = JdbcUtil.getConnection();
            //开启事务
            conn.setAutoCommit(false);

            //删除菜单的套餐信息
            ps = conn.prepareStatement(sql);
            ps.setString(1,menuId);
            int r=ps.executeUpdate();
            if(r<=0){
                flag = -1;
            }

            //删除菜单信息
            ps = conn.prepareStatement(sql2);
            ps.setString(1,menuId);
            int s=ps.executeUpdate();
            if(s<=0){
                flag = -1;
            }

            //判断是否提交事务
            if(flag == 1){
                System.out.println("提交事务");
                conn.commit();
                return 1;
            }else{
                conn.rollback();
                return -1;
            }

        } catch (SQLException e) {
            System.out.println("删除套餐出错");
            e.printStackTrace();
            return -1;
        }finally {
            JdbcUtil.release(ps,conn);
        }

    }


    //根据学校id与菜单日期确定菜单
    public String getMenuId(String schoolId, String menuDate) {

        Connection conn = null;
        String sql="select menu_id from menus where school_id = ? and menu_date = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1,schoolId);
            ps.setString(2,menuDate);
            rs=ps.executeQuery();
            if(rs.next()){

                return rs.getString("menu_id");

            }
            return "-1";

        } catch (SQLException e) {
            System.out.println("查询菜谱中套餐信息出错");
            e.printStackTrace();
            return "-1";
        }finally {
            JdbcUtil.release(rs,ps,conn);
        }

    }
}
