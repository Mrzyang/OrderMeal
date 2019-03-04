package com.wap.service;

import com.google.gson.Gson;

import com.orderMeal.domain.News;
import com.wap.dao.NewsApiDao;

import java.sql.SQLException;
import java.util.List;

public class NewsApiService {
    public NewsApiDao newsApiDao;
    public Gson gson;
    public NewsApiService(){
        this.newsApiDao=new NewsApiDao();
        this.gson=new Gson();
    }
    public String getAllNews(){
        List list=newsApiDao.getAllNewsTitles();
        return  gson.toJson(list);
    }
    public String getNewsById(int newsId){
        News news=newsApiDao.getNewsById(newsId);
        return gson.toJson(news);
    }
}
