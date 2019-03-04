package com.orderMeal.service;

import com.orderMeal.dao.GoodsDao;
import com.orderMeal.domain.Goods;
import com.orderMeal.domain.Product;
import com.orderMeal.utils.PageInfo;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsService {

    GoodsDao Gdao = new GoodsDao();


    //获取总页数
    public int getGoodsTotalPage(String keyword) {
        try {
            String s = Gdao.getGoodsTotal(keyword);
            int total = Integer.parseInt(s);
            if(total == -1){
                return -1;
            }
            if(total%10==0){
                return total/10;
            }else {
                return total/10+1;
            }
        } catch (SQLException e) {
            return -1;
        }
    }

    //获取套餐列表的一页数据
    //一页的规格为10条数据
    public List<Goods> getGoodsPage(String p, String k) {

        try {
            //判断是否第一次访问列表
            if(p != null) {

                int total = Integer.parseInt(Gdao.getGoodsTotal(k));
                int page = Integer.parseInt(p);
                int pageInfo[] = PageInfo.getPageInfo(page, total, 10);
                List<Goods> list = Gdao.getGoodsPage(pageInfo[0], pageInfo[1], k);
                return list;
            }else{
                int total = Integer.parseInt(Gdao.getGoodsTotal(k));
                //判断菜品数量是否大于一页的规格
                if (total < 10) {
                    List<Goods> list = Gdao.getGoodsPage(0, total, k);
                    return list;
                } else {
                    List<Goods> list = Gdao.getGoodsPage(0, 10, k);
                    return list;
                }
            }

        } catch (SQLException e) {
            return null;
        }

    }


    public List getproduct(int i) {
        List list = null;
        try {
            list = Gdao.getProduct(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //根据套餐名查找套餐
    public int findGoodsByName(String goodsName, String goodsId) {

        try {
            return Gdao.findGoodsByName(goodsName,goodsId);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    //添加套餐
    public int addGoods(String goodsName, double goodsPrice, String product, String path) {

            return Gdao.addGoods(goodsName,goodsPrice,product,path);

    }

    //删除套餐
    public int deleteGoods(String goodsId) {

        String[] id = null;


        try {

            id = goodsId.split(",");//用逗号切割
        }catch(Exception e){
            System.out.println("请输入要删除套餐的id");
            return -1;
        }

        for(int i=0;i<id.length;i++){
            Goods goods = Gdao.findGoodsById(id[i]);
            String productPath = goods.getProductPath();
            try{
                String fileName = productPath.substring(productPath.lastIndexOf("\\")+1);
                String filePath = productPath.substring(0,productPath.lastIndexOf("\\"));
                System.out.println("fileName = " +fileName + "   filePath = " + filePath);
                java.io.File file = new java.io.File(filePath,fileName);
                if(file.exists())
                {
                    file.delete();
                    System.out.println("文件"+fileName+"已经删除！");
                }
                else
                {
                    System.out.println("文件"+fileName+"不存在！");
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                System.out.println("Delete File is error:"+ex);
            }
            if(Gdao.deleteGoods(id[i])==-1){//根据id删除
                return -1;
            }
        }
        return 0;

    }

    //更新套餐信息
    public int updateGoods(String updateType, String goodsId, String goodsName, double goodsPrice, String productName) {

        if(updateType.equals("price")){
            return Gdao.updateGoodsPrice(goodsId, goodsPrice);
        }
        productName = productName.substring(0,productName.length()-1);
        return Gdao.updateGoodsOther(goodsId, goodsName, goodsPrice, productName);
    }

    /*public Goods findGoodsById(String goodsId) {

        return Gdao.findGoodsById(goodsId);

    }*/
}
