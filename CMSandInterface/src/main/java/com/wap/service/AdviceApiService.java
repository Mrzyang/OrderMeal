package com.wap.service;

import com.google.gson.Gson;
import com.wap.dao.AdviceApiDao;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: OrderMeal
 * @description: 关于用户提交advice相关的业务逻辑
 * @author: Mr.Zhang
 * @create: 2018-06-10 14:09
 **/
public class AdviceApiService {
    public AdviceApiDao adviceApiDao;
    public Gson gson;
    public AdviceApiService(){
        adviceApiDao=new AdviceApiDao();
        gson=new Gson();
    }
    public String submitMessage(int customerId,String message){
        Map map=new HashMap();
        if(adviceApiDao.submitMessage(customerId,message)){
            map.put("status",1);
            map.put("msg","提交成功!");
        }else{
            map.put("status",0);
            map.put("msg","提交失败!");
        }
        return gson.toJson(map);
    }
}
