package com.orderMeal.web;


import com.google.gson.Gson;
import com.orderMeal.domain.News;
import com.orderMeal.service.NewsService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name="NewsServlet",urlPatterns = "/admin/news")
public class NewsServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        String type = req.getParameter("type");
        System.out.println("newsTYPE = "+type);

        NewsService newsService = new NewsService();

        if("releaseUI".equals(type)){
            System.out.println("发布公告界面！");
            req.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/addNews.jsp").forward(req,resp);
            return ;
        }
        else if("release".equals(type)){
            System.out.println("调用发布新闻函数！");
            String newsTitle = req.getParameter("newsTitle");
            String newsContent = req.getParameter("newsContent");
            int flag = 0;
            if(newsTitle.isEmpty()){
                req.setAttribute("titleMistake","标题不能为空！");
                flag++;
            }
            if(newsContent.isEmpty()){
                req.setAttribute("contentMistake","公告内容不能为空！");
                flag++;
            }
            if(flag>0){
                req.setAttribute("newsTitle",newsTitle);
                req.setAttribute("newsContent",newsContent);
                req.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/addNews.jsp").forward(req,resp);
                return;
            }
            boolean result = newsService.releaseNews(newsTitle,newsContent);
            PrintWriter pw = resp.getWriter();
            pw.write(""+result);
            pw.flush();
            pw.close();
            return;
        }
        else if("delete".equals(type)){
            Map map = new HashMap();
            System.out.println("删除公告服务！");
            String nId = req.getParameter("newsId");
            int newsId = Integer.parseInt(nId);
            System.out.println("nnnnId==="+newsId);
            String page = req.getParameter("pageNum");
            int pageNum = Integer.parseInt(page);
            System.out.println(pageNum);
            boolean result = newsService.deleteNews(newsId);
            map.put("result",result);
            if(result){

                int totalPage = newsService.getTotalNewsPage();
                if(totalPage >pageNum){
                    map.put("pageNum",pageNum);
                }else {
                    map.put("pageNum",totalPage);
                }
                //req.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/addNews.jsp").forward(req,resp);
            }
            JSONObject msg = JSONObject.fromObject(map);
            PrintWriter pw =  resp.getWriter();
            pw.println(msg);
            pw.flush();
            pw.close();
        }else if("deleteMore".equals(type)){
            Map map = new HashMap();
            String ids = req.getParameter("newsId");
            int pageNum = Integer.parseInt(req.getParameter("pageNum"));
            boolean result = newsService.deleteNewses(ids);
            map.put("result",result);
            int totalPage = newsService.getTotalNewsPage();
            if(totalPage >pageNum){
                map.put("pageNum",pageNum);
            }else {
                map.put("pageNum",totalPage);
            }
            JSONObject msg = JSONObject.fromObject(map);
            PrintWriter pw = resp.getWriter();
            pw.println(msg);
            pw.flush();
            pw.close();
            return ;

        }
        else if("details".equals(type)){
            int newsId = Integer.parseInt(req.getParameter("newsId"));
            News news = newsService.detailsNews(newsId);
            if(news != null){
                req.setAttribute("news",news);
                req.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/newsDetails.jsp").forward(req,resp);
            }

        }else if("updateUI".equals(type)){
            int newsId = Integer.parseInt(req.getParameter("newsId"));
            String pageNum = req.getParameter("pageNum");
            News news = newsService.detailsNews(newsId);
            if(news != null){
                req.setAttribute("news",news);
                req.setAttribute("pageNum",pageNum);
                req.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/updateNews.jsp").forward(req,resp);
            }
        }else if("update".equals(type)){
            int newsId = Integer.parseInt(req.getParameter("newsId"));
            String newsTitle = req.getParameter("newsTitle");
            String newsContent = req.getParameter("newsContent");
            String pageNum = req.getParameter("pageNum");
            System.out.println(newsId+newsTitle+newsContent);
            boolean result = newsService.updateNews(newsId,newsTitle,newsContent);
            PrintWriter pw = resp.getWriter();
            pw.flush();
            if(result){
                pw.println("<script>");
                pw.println("alert('恭喜您修改成功！');");
                if(pageNum!=null){
                    pw.println("window.location.href('news?pageNum="+pageNum+"')");
                }
                else {
                    pw.println("window.location.href('news')");
                }
                pw.println("</script>");
                pw.close();
            }else {
                pw.println("<script>");
                pw.println("alert('修改失败！');");
                pw.println("</script>");
                pw.flush();
                return ;
            }

        }
        else {
            System.out.println("公告列表");
            String pageStr = req.getParameter("pageNum");
            int page;
            if(pageStr==null||pageStr==""){
                page = 1;
                req.setAttribute("pageNum","1");
            }else {
                page = Integer.parseInt(pageStr);
                req.setAttribute("pageNum",page);
            }
            int totalPage = newsService.getTotalNewsPage();
            if(totalPage<=0){
                req.setAttribute("message","查询出错");
                req.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/newsBulletin.jsp").forward(req,resp);
            }else {
                req.setAttribute("totalPage",totalPage);
            }

            List<News> list = newsService.getNewsPage(page);
            req.setAttribute("list",list);
            Gson gson = new Gson();
            String json = gson.toJson(list);
            //System.out.println(json);
            req.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/newsBulletin.jsp").forward(req,resp);
            return ;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
