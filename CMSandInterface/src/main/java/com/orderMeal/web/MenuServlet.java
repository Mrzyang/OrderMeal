package com.orderMeal.web;

import com.orderMeal.domain.Goods;
import com.orderMeal.service.MenuService;
import com.orderMeal.utils.GetID;
import com.orderMeal.domain.Menu;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "menuServlet", urlPatterns={"/admin/menu"})
public class MenuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        System.out.println("type = " + type);
        MenuService ms = new MenuService();

        if("info".equals(type)){
            System.out.println("调用菜单详细信息显示函数");
            String menuId = request.getParameter("menuId");
            System.out.println("menuId = " + menuId);
            String schoolId = request.getParameter("schoolId");
            System.out.println(schoolId);
            String schoolName = request.getParameter("schoolName");
            System.out.println(schoolName);
            String menuDate = request.getParameter("menuDate");
            System.out.println(menuDate);
            Map<Integer, Goods> goods = null;
            if(menuId==null || "".equals(menuId)){
                goods = ms.getMenu2Goods(schoolId,menuDate);
            }else {
                goods = ms.getMenu2GoodsById(menuId);
            }
            Map map1 = new HashMap<Integer,Goods>();
            Map map2 = new HashMap<Integer,Goods>();
            Map map3 = new HashMap<Integer,Goods>();
            Map map4 = new HashMap<Integer,Goods>();

            for (Map.Entry<Integer, Goods> entry : goods.entrySet()) {
                switch (entry.getKey()){
                    case 1: case 2:case 3:case 4:case 5:case 6:case 7: map1.put(entry.getKey(),entry.getValue()); break;
                    case 8: case 9:case 10:case 11:case 12:case 13:case 14: map2.put(entry.getKey()-7,entry.getValue()); break;
                    case 15: case 16:case 17:case 18:case 19:case 20:case 21: map3.put(entry.getKey()-14,entry.getValue()); break;
                    case 22: case 23:case 24:case 25:case 26:case 27:case 28: map4.put(entry.getKey()-21,entry.getValue()); break;
                }
            }
            /*Map map = new HashMap<String,Goods>();
            Goods goods = new Goods();
            goods.setGoodsId(123);
            goods.setGoodsName("awd");
            goods.setGoodsPrice(12);
            goods.setProductNames("qweqwe+qweqwwe+qweqweq+qwewr");
            for(int i=1; i<5; i++){
                map.put(i,goods);
            }*/
            request.setAttribute("map1",map1);
            request.setAttribute("map2",map2);
            request.setAttribute("map3",map3);
            request.setAttribute("map4",map4);
            request.setAttribute("schoolName",schoolName);
            request.setAttribute("menuDate",menuDate);
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/weekMenu.jsp").forward(request,response);
            return;
        }
        if("add".equals(type)){

            System.out.println("调用添加菜单函数");
            int flag = 0;
            int week = -1;
            String schoolValue = request.getParameter("schoolValue");
            System.out.println("school = " + schoolValue);
            String[] sv = schoolValue.split(",");
            String menuId = GetID.getUUID();
            //String schoolName = request.getParameter("schoolName");
            String menuDate = "";
            Date md = null;
            //int schoolId = ms.getSchoolId("schoolName");
            //定义队列装载菜单信息
            HashMap map = new HashMap();

            if(schoolValue==null){
                System.out.println("请输入学校信息");
                flag++;
            }

            //循环取出菜单信息
            for(int i=1; i<29; i++) {
                String value = "value"+i;
                System.out.println("value" + i + " = " + request.getParameter(value));
                map.put(i, request.getParameter(value));
            }

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                menuDate = request.getParameter("menuDate");
                md = sdf.parse(menuDate);
                Calendar cal=Calendar.getInstance();
                cal.setTime(md);
                week=cal.get(Calendar.DAY_OF_WEEK)-1;
                System.out.println(md + "是星期" + week);

            } catch (ParseException e) {
                System.out.println("请输入有效时间");
                flag++;
                //e.printStackTrace();
            }


            if(week != 1){
                System.out.println("对不起,请选择菜单周次的星期一");
                request.setAttribute("dataMistake","对不起,请选择菜单周次的星期一");
                flag++;
            }

            System.out.println("flag = " + flag);
            if(flag>0){
                request.setAttribute("schoolList",ms.getSchools());
                request.setAttribute("goodsList",ms.getGoods());
                System.out.println("输入不合法");
                request.setAttribute("addMistake","对不起，输入不合法");
                request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/addMenu.jsp").forward(request,response);
                return;
            }
            //新建一张菜单失败
            if(ms.addMenu(sv[0],sv[1],menuId,md,map)==-1){
                request.setAttribute("schoolList",ms.getSchools());
                request.setAttribute("goodsList",ms.getGoods());
                request.setAttribute("addMistake","对不起，添加菜谱信息出错");
                System.out.println("对不起，添加菜谱信息出错");
                request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/addMenu.jsp").forward(request,response);
            }

            request.setAttribute("menuId",menuId);
            request.setAttribute("schoolName",sv[1]);
            request.setAttribute("schoolId",sv[0]);
            request.setAttribute("menuDate",menuDate);
            System.out.println("请求转发到info页面");
            request.getRequestDispatcher("/admin/menu?type=info&menuId=" + menuId + "&schoolId=" + sv[0] + "&schoolName=" + sv[1] +"&menuDate=" + menuDate).forward(request,response);
            return;
        }
        if("addUI".equals(type)){
            System.out.println("调用添加菜谱函数入口");
            request.setAttribute("schoolList",ms.getSchools());
            request.setAttribute("goodsList",ms.getGoods());
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/addMenu.jsp").forward(request,response);
            return;
        }
        if("delete".equals(type)){
            Map map = new HashMap();
            map.put("status",0);
            map.put("data","删除失败!");
            System.out.println("调用删除菜单函数");
            String menuId = request.getParameter("menuId");
            String pageNum = request.getParameter("pageNum");
            System.out.println("menuId = " + menuId + "   pageNum = " + pageNum);
            if (menuId == null) {
                System.out.println("请选择要删除的菜单");
                map.put("date","请选择要删除的菜单");
            }
            int a = ms.deleteMenu(menuId);
            if(a==-1){
                System.out.println("请选择合法的删除操作");
            }else {
                System.out.println("删除成功！！");
                map.put("pageNum",pageNum);
                map.put("status",1);
                map.put("data","删除成功!");
            }
            JSONObject msg= JSONObject.fromObject(map);
            System.out.println("========================" + msg);
            response.setCharacterEncoding("UTF-8");
            PrintWriter out=response.getWriter();
            out.println(msg);
            return;
            //request.getRequestDispatcher("/admin/product").forward(request,response);
        }
        if("update".equals(type)){

            int flag = 0;
            int week = -1;
            String schoolValue = request.getParameter("schoolValue");
            String schoolName = request.getParameter("schoolName");
            String menuId = request.getParameter("menuId");
            System.out.println("school = " + schoolValue);
            String[] sv = schoolValue.split(",");
            String menuDate = "";
            Date md = null;


            //定义队列装载菜单信息
            HashMap map = new HashMap();

            if(schoolValue==null){
                System.out.println("请输入学校信息");
                request.setAttribute("dataMistake1","请输入学校信息   ");
                flag++;
            }

            //循环取出菜单信息
            for(int i=1; i<29; i++) {
                String value = "value"+i;
                System.out.println("value" + i + " = " + request.getParameter(value));
                map.put(i, request.getParameter(value));
            }

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                menuDate = request.getParameter("menuDate");
                md = sdf.parse(menuDate);
                Calendar cal=Calendar.getInstance();
                cal.setTime(md);
                week=cal.get(Calendar.DAY_OF_WEEK)-1;
                System.out.println(md + "是星期" + week);

            } catch (ParseException e) {
                request.setAttribute("dataMistake2","请输入有效时间   ");
                System.out.println("请输入有效时间");
                flag++;
                //e.printStackTrace();
            }


            if(week != 1){
                System.out.println("对不起,请选择菜单周次的星期一");
                request.setAttribute("dataMistake3","对不起,请选择菜单周次的星期一   ");
                flag++;
            }

            System.out.println("flag = " + flag);
            if(flag>0) {
                System.out.println("调用菜单修改函数");
                System.out.println("menuId = " + menuId);


                Map<Integer, Goods> goods = ms.getMenu2GoodsById(menuId);
                Map map1 = new HashMap<Integer, Goods>();
                Map map2 = new HashMap<Integer, Goods>();
                Map map3 = new HashMap<Integer, Goods>();
                Map map4 = new HashMap<Integer, Goods>();

                for (Map.Entry<Integer, Goods> entry : goods.entrySet()) {
                    switch (entry.getKey()) {
                        case 1: case 2: case 3: case 4: case 5: case 6: case 7: map1.put(entry.getKey(), entry.getValue());break;
                        case 8: case 9: case 10: case 11: case 12: case 13: case 14: map2.put(entry.getKey() - 7, entry.getValue());break;
                        case 15: case 16: case 17: case 18: case 19: case 20: case 21: map3.put(entry.getKey() - 14, entry.getValue());break;
                        case 22: case 23: case 24: case 25: case 26: case 27: case 28: map4.put(entry.getKey() - 21, entry.getValue());break;
                    }
                }

                request.setAttribute("map1", map1);
                request.setAttribute("map2", map2);
                request.setAttribute("map3", map3);
                request.setAttribute("map4", map4);
                request.setAttribute("schoolList", ms.getSchools());
                request.setAttribute("goodsList", ms.getGoods());
                request.setAttribute("schoolName", sv[1]);
                request.setAttribute("menuDate", menuDate);
                request.setAttribute("menuId",menuId);
                request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/updateMenu.jsp").forward(request, response);
                return;
            }
            if(ms.updateMenu2Goods(menuId, map, md, sv[0],sv[1])==1){
                System.out.println("修改成功");
                System.out.println("请求转发到info页面");
                request.getRequestDispatcher("/admin/menu?type=info&menuId=" + menuId + "&schoolName=" + sv[1] + "&schoolId=" + sv[0] +"&menuDate=" + menuDate).forward(request,response);
                return;

            }
        }
        if("updateUI".equals(type)){
            System.out.println("调用修改菜谱函数入口");
            String menuId = request.getParameter("menuId");
            String schoolName = request.getParameter("schoolName");
            String schoolId = request.getParameter("schoolId");
            String menuDate = request.getParameter("menuDate");
            Map<Integer,Goods> goods = ms.getMenu2GoodsById(menuId);
            Map map1 = new HashMap<Integer,Goods>();
            Map map2 = new HashMap<Integer,Goods>();
            Map map3 = new HashMap<Integer,Goods>();
            Map map4 = new HashMap<Integer,Goods>();

            for (Map.Entry<Integer, Goods> entry : goods.entrySet()) {
                switch (entry.getKey()){
                    case 1: case 2:case 3:case 4:case 5:case 6:case 7: map1.put(entry.getKey(),entry.getValue()); break;
                    case 8: case 9:case 10:case 11:case 12:case 13:case 14: map2.put(entry.getKey()-7,entry.getValue()); break;
                    case 15: case 16:case 17:case 18:case 19:case 20:case 21: map3.put(entry.getKey()-14,entry.getValue()); break;
                    case 22: case 23:case 24:case 25:case 26:case 27:case 28: map4.put(entry.getKey()-21,entry.getValue()); break;
                }
            }

            request.setAttribute("map1",map1);
            request.setAttribute("map2",map2);
            request.setAttribute("map3",map3);
            request.setAttribute("map4",map4);
            request.setAttribute("schoolList",ms.getSchools());
            request.setAttribute("goodsList",ms.getGoods());
            request.setAttribute("schoolName",schoolName);
            request.setAttribute("schoolId",schoolId);
            request.setAttribute("menuDate",menuDate);
            request.setAttribute("menuId",menuId);
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/updateMenu.jsp").forward(request,response);
            return;
        }
        else{
            System.out.println("调用菜单记录显示函数");
            String page = request.getParameter("pageNum");
            System.out.println("page ="+ page);
            if(page==null||page==""){
                page = (String)request.getAttribute("pageNum");
            }
            String keyword = request.getParameter("keyword");
            System.out.println(" keyword = "+keyword);
            int total = ms.getMenuTotalPage(keyword);
            if(total==-1){
                System.out.println("查询出错");
                request.setAttribute("message","查询出错");
                request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/menuList.jsp").forward(request,response);
            }

            List<Menu> list = ms.getMenuPage(page,keyword);


            if(page==null){
                request.setAttribute("pageNum",1);
            }else{
                request.setAttribute("pageNum",page);
            }
            request.setAttribute("totalPage",total);
            request.setAttribute("keyword",keyword);
            request.setAttribute("list",list);
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/menuList.jsp").forward(request,response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
