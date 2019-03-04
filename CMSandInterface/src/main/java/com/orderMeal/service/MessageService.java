package com.orderMeal.service;

import com.google.gson.Gson;
import com.orderMeal.dao.MessageDao;
import com.orderMeal.domain.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: OrderMeal
 * @description: message的service
 * @author: Mr.Zhang
 * @create: 2018-06-09 17:00
 **/
public class MessageService {
    public MessageDao messageDao;
    public Gson gson;
    public MessageService(){
        messageDao=new MessageDao();
        gson=new Gson();
    }
    public Map getAllMessages(int page){
        //message的总数
        int count=messageDao.count();
        System.out.println("总记录数="+count);
        //每页的记录数
        int offset=10;
        //总页数
        int totalPages=count%offset==0 ? count / offset : count / offset+1;
        //本页起始序号
        int beginIndex=(page-1)*offset;
        List<Message> messages=messageDao.getAllMessages(beginIndex,offset);
        Map map=new HashMap();
        map.put("totalPages",totalPages);
        map.put("messages",messages);
        return map;
    }

    public String deleteMessageById(int id){
        Map map=new HashMap();
        if(messageDao.deleteMessageById(id)){
            map.put("status",1);
            map.put("msg","删除成功！");
        }else{
            map.put("status",0);
            map.put("msg","删除失败!");
        }
        return gson.toJson(map);
    }

    public String batchDeleteMessages(int[] ids){
        Map map=new HashMap();
        if(messageDao.batchDeleteMessages(ids)){
            map.put("status",1);
            map.put("msg","删除成功！");
        }else{
            map.put("status",0);
            map.put("msg","删除失败!");
        }
        return gson.toJson(map);
    }

    public String dealMessage(int id){
        Map map=new HashMap();
        if(messageDao.dealMessage(id)){
            map.put("status",1);
            map.put("msg","已标记为已处理！");
        }else{
            map.put("status",0);
            map.put("msg","操作失败！");
        }
        return gson.toJson(map);
    }
    public Message getMessageById(int id){
        return messageDao.getMessageById(id);
    }

}
