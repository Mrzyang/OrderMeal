package com.orderMeal.web;

import com.orderMeal.domain.Product;
import com.orderMeal.domain.School;
import com.orderMeal.service.ProductService;
import com.orderMeal.service.SchoolService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SchoolServlet", urlPatterns={"/admin/school"})
public class SchoolServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        SchoolService ss = new SchoolService();

        if("add".equals(type)){

            System.out.println("调用添加学校函数");
            int flag = 0;
            List mistakeList = new ArrayList();
            String schoolName = request.getParameter("schoolName");
            String schoolPhone = request.getParameter("schoolPhone");
            String schoolAddress = request.getParameter("schoolAddress");
            String schoolClasstotal1 = request.getParameter("schoolClasstotal1");
            String schoolClasstotal2 = request.getParameter("schoolClasstotal2");
            String schoolClasstotal3 = request.getParameter("schoolClasstotal3");

            if("".equals(schoolName) || schoolName.equals(null)){
                mistakeList.add("学校名不能为空！！");
                flag++;
            }
            if(ss.findSchoolByName(schoolName,null)==-1){
                mistakeList.add("已经添加过该学校的信息了！！");
                flag++;
            }

            if("".equals(schoolClasstotal1) || schoolClasstotal1==null || "".equals(schoolClasstotal2) || schoolClasstotal2==null || "".equals(schoolClasstotal3) || schoolClasstotal3==null){
                mistakeList.add("请选择各年级的班级数");
                flag++;
            }

            try {
                Integer.parseInt(schoolClasstotal1);
                Integer.parseInt(schoolClasstotal2);
                Integer.parseInt(schoolClasstotal3);
            }catch (Exception e){
                mistakeList.add("请填写正确的班级数");
                flag++;
            }

            System.out.println("flag = " + flag);
            if(flag>0){
                request.setAttribute("mistakeList",mistakeList);
                request.setAttribute("schoolName",schoolName);
                request.setAttribute("schoolClasstotal1",schoolClasstotal1);
                request.setAttribute("schoolClasstotal2",schoolClasstotal2);
                request.setAttribute("schoolClasstotal3",schoolClasstotal3);
                request.setAttribute("schoolPhone",schoolPhone);
                request.setAttribute("schoolAddress",schoolAddress);
                request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/addSchool.jsp").forward(request,response);
                return;
            }
            if(ss.addSchool(schoolName,schoolClasstotal1,schoolClasstotal2,schoolClasstotal3,schoolPhone,schoolAddress)==-1){
                request.setAttribute("addMistake","对不起，添加出现错误");
            }else{
                request.setAttribute("addMistake","恭喜你，添加成功!!");
            }


            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/addSchool.jsp").forward(request,response);
            return;
        }
        if("addUI".equals(type)){
            System.out.println("调用添加学校函数入口");
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/addSchool.jsp").forward(request,response);
            return;
        }
        if("delete".equals(type)){
            Map map=new HashMap();
            map.put("status",0);
            map.put("data","删除失败!");
            System.out.println("调用删除产品函数");
            String schoolId = request.getParameter("schoolId");
            String pageNum = request.getParameter("pageNum");
            if (schoolId == null) {
                System.out.println("请选择要删除的菜品");
                map.put("date","请选择要删除的菜品");
            }
            int a = ss.deleteSchool(schoolId);
            if(a==-1){
                System.out.println("请选择合法的删除对象进行操作");
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
            Map map=new HashMap();
            map.put("status",0);
            map.put("data","更新失败!");
            System.out.println("调用修改学校信息函数");
            String schoolId = request.getParameter("schoolId");
            String schoolName = request.getParameter("schoolName");
            String schoolPhone = request.getParameter("schoolPhone");
            String schoolAddress = request.getParameter("schoolAddress");
            String schoolClasstotal1 = request.getParameter("schoolClasstotal1");
            String schoolClasstotal2 = request.getParameter("schoolClasstotal2");
            String schoolClasstotal3 = request.getParameter("schoolClasstotal3");

            String pageNum = request.getParameter("pageNum");
            request.setAttribute("pageNum",pageNum);
            System.out.println("schoolId  " + schoolId + "  schoolName  " + schoolName + "  schoolPhone  " + schoolPhone + "  schoolAddress  " + schoolAddress);
            if(schoolName!=null&&ss.findSchoolByName(schoolName,schoolId)==-1){
                map.put("data","已经添加过该学校的信息了！！");
                System.out.println("已经添加过该学校的信息了！！");
            }

            try {
                Integer.parseInt(schoolClasstotal1);
                Integer.parseInt(schoolClasstotal2);
                Integer.parseInt(schoolClasstotal3);
            }catch (Exception e){
                map.put("data","请填写正确的班级数");
            }
            if("".equals(schoolClasstotal1) || schoolClasstotal1==null || "".equals(schoolClasstotal2) || schoolClasstotal2==null || "".equals(schoolClasstotal3) || schoolClasstotal3==null){
                map.put("data","请选择各年级的班级数");
            }


            if(ss.updateSchool(schoolId, schoolName, schoolClasstotal1, schoolClasstotal2, schoolClasstotal3, schoolPhone, schoolAddress)==-1){
                map.put("data","更新数据出现问题，请检查重试");
                System.out.println("更新数据出现问题，请检查重试");
            }else{
                map.put("pageNum",pageNum);
                map.put("status",1);
                map.put("data","更新成功!");
                System.out.println("更新成功!");
            }
            JSONObject msg= JSONObject.fromObject(map);
            System.out.println(msg);
            response.setCharacterEncoding("UTF-8");
            PrintWriter out=response.getWriter();
            out.println(msg);
            return;
        }
        if("updateUI".equals(type)){
            System.out.println("调用修改产品函数入口");
            School school = new School();
            school.setSchoolClasstotal1(Integer.parseInt(request.getParameter("schoolClasstotal1")));
            school.setSchoolClasstotal2(Integer.parseInt(request.getParameter("schoolClasstotal2")));
            school.setSchoolClasstotal3(Integer.parseInt(request.getParameter("schoolClasstotal3")));
            school.setSchoolId(request.getParameter("schoolId"));
            school.setSchoolName(request.getParameter("schoolName"));
            school.setSchoolPhone(request.getParameter("schoolPhone"));
            school.setSchoolAddress(request.getParameter("schoolAddress"));
            request.setAttribute("school",school);
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/updateSchool.jsp").forward(request,response);
            return;
        }
        else{
            System.out.println("调用学校列表显示函数");
            String page = request.getParameter("pageNum");
            System.out.println("page ="+ page);
            if(page==null||page==""){
                page = (String)request.getAttribute("pageNum");
                System.out.println("page ="+ page);
            }
            String keyword = request.getParameter("keyword");
            System.out.println(" keyword = "+keyword);
            int total = ss.getSchoolTotalPage(keyword);
            if(total==-1){
                System.out.println("查询出错");
                request.setAttribute("message","查询出错");
                request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/schoolList.jsp").forward(request,response);
            }

            List<School> list = ss.getSchoolPage(page,keyword);


            if(page==null){
                request.setAttribute("pageNum",1);
            }else{
                request.setAttribute("pageNum",page);
            }
            request.setAttribute("totalPage",total);
            request.setAttribute("keyword",keyword);
            request.setAttribute("list",list);
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/schoolList.jsp").forward(request,response);
            return;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
