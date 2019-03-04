package com.orderMeal.service;

import com.orderMeal.dao.NewsDao;
import com.orderMeal.domain.News;
import com.orderMeal.utils.getTime;

import java.util.ArrayList;
import java.util.List;

public class NewsService {
    NewsDao newsDao = new NewsDao();
    getTime time = new getTime();

    /**
     * 发布新闻服务函数
     * @param newsTitle 标题
     * @param newsContent 正文
     * @return 是否发布成功
     */
    public boolean releaseNews(String newsTitle,String newsContent){
        String releaseTime = time.format1(time.getNetTime());
        News news = new News(0,newsTitle,newsContent,releaseTime);
        return newsDao.releaseNews(news);
    }
    public boolean updateNews(int newsId,String newsTitle,String newsContent){
        String releaseTime = time.format1(time.getNetTime());
        News news = new News(newsId,newsTitle,newsContent,releaseTime);
        return newsDao.updateNews(news);
    }
    /**
     * 获取一页的公告
     * @param page 第几页
     * @return 第page页的数据
     */
    public List<News> getNewsPage(int page){
        List<News> newsList = newsDao.allNews();
        List<News> list = new ArrayList<News>();
        if(newsList.size()<=0){
            return null;
        }else if(newsList.size()<page*10){
            list = newsList.subList((page-1)*10,newsList.size());
        }else {
            list = newsList.subList((page-1)*10,page*10);
        }
        return list;
    }

    /**
     * 获取页面总数
     * @return
     */
    public int getTotalNewsPage(){
        List<News> newsList = newsDao.allNews();
        return (newsList.size()-1)/10+1;
    }

    /**
     * 根据newsId删除新闻公告
     * @param newsId
     * @return
     */
    public boolean deleteNews(int newsId){
        return newsDao.deleteNews(newsId);
    }

    public boolean deleteNewses(String ids){
        String[] newsIds = null;
        newsIds = ids.split(",");
        //System.out.println(newsIds.length);
        for (int i=0;i<newsIds.length;i++){
            if(!newsDao.deleteNews(Integer.parseInt(newsIds[i]))){
                return false;
            }
        }
        return true;
    }
    /**
     * 根据newsId显示公告详情
     * @param newsId
     * @return 是否
     */
    public News detailsNews(int newsId){
        if(newsDao.News(newsId)!=null){
            return newsDao.News(newsId);
        }
        return null;
    }

}
