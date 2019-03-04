package com.orderMeal.service;

import com.orderMeal.dao.ProductDao;
import com.orderMeal.domain.Product;
import com.orderMeal.utils.PageInfo;

import java.sql.SQLException;
import java.util.List;

public class ProductService {

    ProductDao Pdao = new ProductDao();

    //获取餐品的总页数
    public int getProductTotalPage(String keyword){

        String s = Pdao.getProductTotal(keyword);
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


    //获取餐品列表的一页数据
    //一页的规格为10条数据
    public List<Product> getProductPage(String p, String k){

        try {
            //判断是否第一次访问列表
            if(p != null) {

                int total = Integer.parseInt(Pdao.getProductTotal(k));
                int page = Integer.parseInt(p);
                int pageInfo[] = PageInfo.getPageInfo(page, total, 10);
                List<Product> list = Pdao.getProductPage(pageInfo[0], pageInfo[1], k);
                return list;
            }else{
                int total = Integer.parseInt(Pdao.getProductTotal(k));
                //判断菜品数量是否大于一页的规格
                if (total < 10) {
                    List<Product> list = Pdao.getProductPage(0, total, k);
                    return list;
                } else {
                    List<Product> list = Pdao.getProductPage(0, 10, k);
                    return list;
                }
            }

        } catch (SQLException e) {
            return null;
        }

    }

    //添加菜品
    public int addProduct(String productName, String productType) {

        try {
            return Pdao.addProduct(productName,productType);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }

    //查验菜品名是否重复
    public int findProductByName(String productName, String productId) {

        try {
            return Pdao.findProductByName(productName,productId);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }

    //删除菜品（一种或多种
    public int deleteProduct(String productId) {

        String[] id = null;
        try {
            id = productId.split(",");//用逗号切割
        }catch (Exception e){
            System.out.println("请输入要删除菜品的id");
            return -1;
        }


        for(int i=0;i<id.length;i++){
            if(Pdao.deleteProduct(id[i])==-1){//根据id删除
                return -1;
            }
        }

        return 0;
    }

    //更新菜品信息
    public int updateProduct(String productId, String productName, String productType, String productSign) {

        if(productSign!=null && productSign!=""){
            System.out.println("111");
            return Pdao.updateProductSign(productId, productSign);
        }
        return Pdao.updateProductOther(productId, productName, productType);
    }

}
