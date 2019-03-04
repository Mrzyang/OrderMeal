package com.wap.service;

import com.google.gson.Gson;
import com.orderMeal.domain.Customer;
import com.wap.dao.UserApiDao;
import com.wap.filter.JWT;
import com.wap.utils.ResponseData;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: OrderMeal
 * @description: 用户相关的业务逻辑
 * @author: Mr.Wang
 * @create: 2018-06-05 00:42
 **/
public class UserApiService {
    public UserApiDao userDao;
    public Gson gson;
    public UserApiService(){
        userDao=new UserApiDao();
        gson=new Gson();
    }
    public String checkLogin(String num,String password,String schoolId){
        ResponseData responseData = ResponseData.ok();
            Customer customer = userDao.isRegistered(num,password,schoolId);
            if(customer!=null){
                String token= JWT.sign(customer,300*1000L*30L);   //60*1000L*30L
                //封装成对象返回给客户端
                responseData.putDataValue("loginId",customer.getCustomerId());
                responseData.putDataValue("token",token);
                responseData.putDataValue("user",customer);
            }else {
                responseData= ResponseData.customerError();
            }
        return gson.toJson(responseData);
    }
    public String modifyInfo(String customerId,String newPassword,String customerPhone)  {
        System.out.println("UserApiService");
        Map map=new HashMap<>();
            if(userDao.modifyInfo(customerId,newPassword,customerPhone)){
                map.put("status",1);
                map.put("msg","修改成功！");
                System.out.println("if--- UserApiService");
            }else {
                map.put("status",0);
                map.put("msg","修改失败！");
                System.out.println("else---UserApiService");
            }
        return  gson.toJson(map);
    }

    public String getCusomerById(int customerId){
        Customer customer=null;
        customer=userDao.getCustomerById(customerId);
        System.out.println(customer);
        return gson.toJson(customer);
    }
}
