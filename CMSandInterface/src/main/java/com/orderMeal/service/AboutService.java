package com.orderMeal.service;

import com.google.gson.Gson;
import com.orderMeal.dao.AboutDao;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: OrderMeal
 * @description: about表的相关service
 * @author: Mr.Zhang
 * @create: 2018-06-06 12:44
 **/
public class AboutService {
    public AboutDao aboutDao;
    public Gson gson;
    public AboutService(){
        aboutDao=new AboutDao();
        gson=new Gson();
    }
    public String updateAboutInfo(String content){
        Map data=new HashMap<>();
        if(aboutDao.updateAboutInfo(content)){
            data.put("status",1);
            data.put("msg","修改成功！");
        }else {
            data.put("status",0);
            data.put("msg","修改失败！");
        }
        return gson.toJson(data);
    }
}
