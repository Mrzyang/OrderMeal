package com.orderMeal.web;

import com.orderMeal.domain.Admin;
import com.orderMeal.domain.Product;
import com.orderMeal.domain.School;
import com.orderMeal.service.AdminService;
import com.orderMeal.service.ProductService;
import com.orderMeal.service.SchoolService;
import com.sun.org.apache.xerces.internal.xs.StringList;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AdminServlet", urlPatterns={"/admin/administrator"})
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        AdminService as = new AdminService();

        if("login".equals(type)){
            System.out.println("调用管理员登陆函数");
            List list = new ArrayList<String>();
            int flag = 0;
            String adminNum = request.getParameter("adminNum");
            String adminPassword = request.getParameter("adminPassword");
            System.out.println("adminNum = " + adminNum + "   adminPassword = " + adminPassword);

            if("".equals(adminNum) || adminNum==null){
                list.add("工号不能为空！！");
                System.out.println("工号不能为空！！");
                flag++;
            }
            if("".equals(adminPassword) || adminPassword==null){
                list.add("请输入密码！！");
                System.out.println("请输入密码！！");
                flag++;
            }
            System.out.println("flag = " + flag);
            if(flag>0){
                request.setAttribute("Mistake",list);
                request.setAttribute("adminNum",adminNum);
                request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/adminLogin.jsp").forward(request,response);
                return;
            }
            Admin admin = as.findAdmin(adminNum, adminPassword);
            if(admin == null){
                list.add("对不起，用户名或密码出错！！");
                System.out.println("对不起，用户名或密码出错！！");
                request.setAttribute("Mistake",list);
            }else{
                System.out.println("恭喜你，登陆成功！！");
                HttpSession session = request.getSession();
                session.setAttribute("adminuser",admin);
                request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/index.jsp").forward(request,response);
            }

            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/adminLogin.jsp").forward(request,response);
            return;
        }
        if("loginUI".equals(type)){
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/adminLogin.jsp").forward(request,response);

        }
        if("add".equals(type)){

            System.out.println("调用添加管理员函数");
            List list = new ArrayList<String>();
            int flag = 0;
            String adminNum = request.getParameter("adminNum");
            String adminPassword = request.getParameter("adminPassword");
            String adminType = request.getParameter("adminType");
            System.out.println("adminNum = " + adminNum + "   adminPassword = " + adminPassword);

            if(adminNum.equals("") || adminNum==null){
                list.add("工号不能为空！！");
                System.out.println("工号不能为空！！");
                flag++;
            }
            if("".equals(adminPassword) || adminPassword==null || adminPassword.length()<6){
                list.add("请输入6位及6位以上的密码！！");
                System.out.println("请输入6位以上的密码！！");
                flag++;
            }
            if(adminType.equals("") || adminType.equals(null)){
                list.add("权限不能为空！！");
                System.out.println("权限不能为空！！");
                flag++;
            }
            if(as.findAdminByNum(adminNum)==1){
                list.add("已经添加过该员工的信息了！！");
                System.out.println("已经添加过该员工的信息了！！");
                flag++;
            }
            System.out.println("flag = " + flag);
            if(flag>0){
                request.setAttribute("Mistake",list);
                request.setAttribute("adminNum",adminNum);
                request.setAttribute("adminPassword",adminPassword);
                request.setAttribute("adminType",adminType);
                request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/addAdmin.jsp").forward(request,response);
                return;
            }
            if(as.addAdmin(adminNum,adminPassword,adminType)==-1){
                list.add("对不起，注册出现错误！！");
                System.out.println("对不起，注册出现错误！！");
                request.setAttribute("Mistake",list);
            }else{
                System.out.println("恭喜你，注册成功！！");
                HttpSession session = request.getSession();
                Admin admin = new Admin();
                admin.setAdminNum(adminNum);
                admin.setAdminType(Integer.parseInt(adminType));
                session.setAttribute("adminuser",admin);
            }

            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/addAdmin.jsp").forward(request,response);
            return;
        }
        if("addUI".equals(type)){
            System.out.println("调用添加学校函数入口");
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/addAdmin.jsp").forward(request,response);
            return;
        }
        /*if("delete".equals(type)){
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
        }*/
        /*if("update".equals(type)){
            Map map=new HashMap();
            map.put("status",0);
            map.put("data","更新失败!");
            System.out.println("调用修改学校信息函数");
            String schoolId = request.getParameter("schoolId");
            String schoolName = request.getParameter("schoolName");
            String schoolPhone = request.getParameter("schoolPhone");
            String schoolAddress = request.getParameter("schoolAddress");
            String pageNum = request.getParameter("pageNum");
            request.setAttribute("pageNum",pageNum);
            System.out.println("schoolId  " + schoolId + "  schoolName  " + schoolName + "  schoolPhone  " + schoolPhone + "  schoolAddress  " + schoolAddress);
            if(schoolName!=null&&ss.findSchoolByName(schoolName,schoolId)==-1){
                map.put("data","已经添加过该学校的信息了！！");
                System.out.println("已经添加过该学校的信息了！！");
            }
            if(ss.updateSchool(schoolId, schoolName, schoolPhone, schoolAddress)==-1){
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
        }*/
        /*if("updateUI".equals(type)){
            System.out.println("调用修改产品函数入口");
            School school = new School();
            school.setSchoolId(request.getParameter("schoolId"));
            school.setSchoolName(request.getParameter("schoolName"));
            school.setSchoolPhone(request.getParameter("schoolPhone"));
            school.setSchoolAddress(request.getParameter("schoolAddress"));
            request.setAttribute("school",school);
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/updateSchool.jsp").forward(request,response);
            return;
        }*/
        else{
            System.out.println("调用管理员列表显示函数");
            String page = request.getParameter("pageNum");
            System.out.println("page ="+ page);
            if(page==null||"".equals(page)){
                page = (String)request.getAttribute("pageNum");
                System.out.println("page ="+ page);
            }
            String keyword = request.getParameter("keyword");
            System.out.println(" keyword = "+keyword);
            int total = as.getAdminTotalPage(keyword);
            if(total==-1){
                System.out.println("查询出错");
                request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/adminList.jsp").forward(request,response);
                return;
            }

            List<Admin> list = as.getAdminPage(page,keyword);


            if(page==null){
                request.setAttribute("pageNum",1);
            }else{
                request.setAttribute("pageNum",page);
            }
            request.setAttribute("totalPage",total);
            request.setAttribute("keyword",keyword);
            request.setAttribute("list",list);
            request.getRequestDispatcher("/WEB-INF/view/BackEnd/jsp/adminList.jsp").forward(request,response);
            return;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
