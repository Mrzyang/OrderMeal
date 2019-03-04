package com.orderMeal.dao;

import com.orderMeal.domain.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 管理新闻公告工具
 */
public class NewsDao extends BaseDao{

    /**
     * 获取所有新闻项目
     * @return 新闻的json字符串 时间倒叙
     */
    public List<News> allNews(){
        String sql = "SELECT * FROM news ORDER BY release_time DESC";
        List<Map<String,Object>> list = execteQuery(sql,new Object[]{});
        List<News> newsList = new ArrayList<News>();
        for(int i=0;i<list.size();i++){
            Map<String,Object> map = list.get(i);
            int newsId = Integer.parseInt(map.get("news_id").toString());
            String newsTitle = map.get("news_title").toString();
            String newsContent = map.get("news_content").toString();
            String releaseTime = map.get("release_time").toString();
            News news = new News(newsId,newsTitle,newsContent,releaseTime);
            newsList.add(news);
        }
        return newsList;
    }

    /**
     * 发布新闻
     * @param news 待发布新闻
     * @return 是否发布成功
     */
    public boolean releaseNews(News news){

        System.out.println("发布公告SQL");
        String sql = "insert into news(news_title,news_content,release_time) values(?,?,?)";
        int count = executeUpdate(sql,new Object[]{news.getNewsTitle(),news.getNewsContent(),news.getReleaseTime()});
        return count>0;
    }

    /**
     * 修改新闻
     * @param news 新的新闻，id不变
     * @return 是否修改成功
     */
    public boolean updateNews(News news){
        String sql = "update news set news_title=?,news_content=?,release_time=? where news_id = ?";
        int count = executeUpdate(sql,new Object[]{news.getNewsTitle(),news.getNewsContent(),news.getReleaseTime(),news.getNewsId()});
        return count > 0;
    }

    /**
     * 根据id删除对应的新闻
     * @param newsId
     * @return
     */
    public boolean deleteNews(int newsId){
        String sql = "delete from news where news_id = ?";
        int count = executeUpdate(sql,new Object[]{newsId});
        return count > 0;
    }

    /**
     * 根据newsId获取一条公告信息
     * @param Id 目标newsID
     * @return 对应的新闻公告
     */
    public News News(int Id){
        String sql = "select * from news where news_id = ?";
        List<Map<String, Object>> list = execteQuery(sql,new Object[]{Id});
        if(list.size()>0){
            int newsId = Integer.parseInt(list.get(0).get("news_id").toString());
            String newsTitle = list.get(0).get("news_title").toString();
            String newsContent = list.get(0).get("news_content").toString();
            String releaseTime = list.get(0).get("release_time").toString();
            News news = new News(newsId,newsTitle,newsContent,releaseTime);
            return news;
        }
        return null;
    }

}
