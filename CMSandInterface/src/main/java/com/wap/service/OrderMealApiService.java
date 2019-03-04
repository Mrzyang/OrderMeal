package com.wap.service;

import com.google.gson.Gson;
import com.wap.dao.OrderMealApiDao;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: OrderMeal
 * @description: 下单相关的业务逻辑
 * @author: Mr.Zhang
 * @create: 2018-06-12 16:47
 **/
public class OrderMealApiService {
    public String doOrderMeal(String customerId,String customerPassword,String totalMoney,String goodsIdStr,String dayNoStr,String mealTimeNoStr){
        Gson gson=new Gson();
        OrderMealApiDao orderMealApiDao=new OrderMealApiDao();
        Map map=new HashMap<>();

        String[] goodsIds=goodsIdStr.split(",");
        String[] dayNos=dayNoStr.split(",");
        String[] mealTimeNos=mealTimeNoStr.split(",");

        int[] goodIdArray=stringArraytoInt(goodsIds);
        int[] dayNoArray=stringArraytoInt(dayNos);
        int[] mealTimeNoArray=stringArraytoInt(mealTimeNos);

        double totalmoney= Double.parseDouble(totalMoney);
        int result=orderMealApiDao.doOrderMeal(customerId,customerPassword,totalmoney,goodIdArray,dayNoArray,mealTimeNoArray);
        if(result==1){
            map.put("status",1);
            map.put("msg","下单成功！");
        }else if(result==0){
            map.put("status",0);
            map.put("msg","账户余额不足！");
        }else if(result==2) {
            map.put("status",2);
            map.put("msg","密码输入错误！");
        }else {
            map.put("status",-1);
            map.put("msg","交易出错！");
        }
        return gson.toJson(map);
    }


    //String型数组转化为int型数组
    public int[] stringArraytoInt(String[] array){
        int lenth=array.length;
        int[] intArray=new int[lenth];
        for(int i=0;i<lenth;i++){
            intArray[i]= Integer.parseInt(array[i]);
        }
        return intArray;
    }
}
