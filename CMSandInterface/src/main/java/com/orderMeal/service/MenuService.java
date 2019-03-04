package com.orderMeal.service;

import com.orderMeal.dao.MenuDao;
import com.orderMeal.domain.Goods;
import com.orderMeal.domain.Menu;
import com.orderMeal.domain.School;
import com.orderMeal.utils.GetID;
import com.orderMeal.utils.PageInfo;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.sql.SQLException;
import java.util.*;

public class MenuService {

    MenuDao Mdao = new MenuDao();

    //获得套餐列表
    public ArrayList<Goods> getGoods() {

        try {
            return Mdao.getGoods();
        } catch (SQLException e) {
            System.out.println("查询菜单（套餐）列表出错");
            e.printStackTrace();
            return null;
        }

    }

    //获得学校列表
    public ArrayList<School> getSchools() {
        try {
            return Mdao.getSchool();
        } catch (SQLException e) {
            System.out.println("查询菜单（学校）列表出错");
            e.printStackTrace();
            return null;
        }
    }

    //新建一张菜单
    public int addMenu(String schoolId, String schoolName, String menuId, Date menuDate, HashMap goodsValue) {


        System.out.println("添加一张菜谱 id为 " + menuId);
        HashMap goodsMap = new HashMap<Integer,Goods>();
        for(int i=1; i<29; i++) {
            String value = (String) goodsValue.get(i);
            if(!value.equals("-1")) {
                String[] g = value.split("-");
                Goods goods = new Goods();
                goods.setGoodsId(Integer.parseInt(g[0]));
                goods.setGoodsName(g[1]);
                goods.setGoodsPrice(Double.parseDouble(g[2]));
                goods.setProductNames(g[3]);
                goodsMap.put(i,goods);
            }
        }

        if(Mdao.addMenu(schoolId,schoolName,menuDate,goodsMap,menuId)<=0){
            System.out.println("添加菜单信息失败");
            return -1;
        }
        System.out.println("添加完成");
        return 1;

    }

    //修改菜单的套餐信息
    public int updateMenu2Goods(String menuId, HashMap goodsValue, Date menuDate, String schoolId, String schoolName) {

        //过滤空的记录信息并把有效信息分割封装
        HashMap goodsMap = new HashMap<Integer,Goods>();
        for(int i=1; i<29; i++) {
            String value = (String) goodsValue.get(i);
            if(!value.equals("-1")) {
                String[] g = value.split("-");
                Goods goods = new Goods();
                goods.setGoodsId(Integer.parseInt(g[0]));
                goods.setGoodsName(g[1]);
                goods.setGoodsPrice(Double.parseDouble(g[2]));
                goods.setProductNames(g[3]);
                goodsMap.put(i,goods);
            }
        }

        return Mdao.updateMenu2Goods(menuId,goodsMap,menuDate,schoolId,schoolName);

    }

    //获取菜单列表的总页数
    public int getMenuTotalPage(String keyword) {

            String s = Mdao.getMenuTotal(keyword);
            int total = Integer.parseInt(s);
            if(total == -1){
                return -1;
            }
            if(total%10==0){
                return total/10;
            }else {
                return total/10+1;
            }

    }

    //获取一页菜谱记录
    public List<Menu> getMenuPage(String p, String k) {

        try {
            //判断是否第一次访问列表
            if(p != null) {

                int total = Integer.parseInt(Mdao.getMenuTotal(k));
                int page = Integer.parseInt(p);
                int pageInfo[] = PageInfo.getPageInfo(page, total, 10);
                List<Menu> list = Mdao.getMenuPage(pageInfo[0], pageInfo[1], k);
                return list;
            }else{
                int total = Integer.parseInt(Mdao.getMenuTotal(k));
                //判断菜品数量是否大于一页的规格
                if (total < 10) {
                    List<Menu> list = Mdao.getMenuPage(0, total, k);
                    return list;
                } else {
                    List<Menu> list = Mdao.getMenuPage(0, 10, k);
                    return list;
                }
            }

        } catch (SQLException e) {
            return null;
        }

    }

    //根据学校与日期得到菜单
    public Map<Integer,Goods> getMenu2Goods(String schoolId, String menuDate) {

        String menuId = Mdao.getMenuId(schoolId,menuDate);
        System.out.println("menuId = " + menuId );
        if("-1".equals(menuId)){
            System.out.println("fanh");
            return null;
        }
        System.out.println("111");
        return Mdao.getMenu2GoodsById(menuId);

    }

    //获取所有菜单的套餐信息
    public Map<Integer,Goods> getMenu2GoodsById(String menuId) {

        return Mdao.getMenu2GoodsById(menuId);

    }

    //删除菜单
    public int deleteMenu(String menuId) {
        String[] id = null;

        try {
            id = menuId.split(",");//用逗号切割
        }catch(Exception e){
            System.out.println("请输入要删除套餐的id");
            return -1;
        }

        for(int i=0;i<id.length;i++){
            if(Mdao.deleteMenu(id[i])==-1){//根据id删除
                return -1;
            }
        }

        return 0;

    }


}
