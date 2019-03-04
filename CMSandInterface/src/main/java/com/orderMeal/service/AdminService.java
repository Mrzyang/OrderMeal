package com.orderMeal.service;

import com.orderMeal.dao.AdminDao;
import com.orderMeal.domain.Admin;
import com.orderMeal.domain.School;
import com.orderMeal.utils.Md5;
import com.orderMeal.utils.PageInfo;

import java.sql.SQLException;
import java.util.List;

public class AdminService {

    AdminDao Adao = new AdminDao();

    //根据工号寻找管理员
    public int findAdminByNum(String adminNum) {
        return Adao.findAdminByNum(adminNum);
    }

    //添加管理员信息
    public int addAdmin(String adminNum, String adminPassword, String adminType) {

        String password = Md5.md5Password(adminPassword);
        if ("".equals(password)) {
            return -1;
        }
        return Adao.addAdmin(adminNum, password, adminType);

    }

    //校验管理员账号密码
    public Admin findAdmin(String adminNum, String adminPassword) {

        String password = Md5.md5Password(adminPassword);
        if ("".equals(password)) {
            return null;
        }
        return Adao.findAdmin(adminNum, password);

    }

    //获得管理员记录的总页数
    //每页十条信息
    public int getAdminTotalPage(String keyword) {

        String s = Adao.getAdminTotal(keyword);
        int total = Integer.parseInt(s);
        if (total == -1) {
            return -1;
        }
        if (total % 10 == 0) {
            return total / 10;
        } else {
            return total / 10 + 1;
        }


    }

    //获得一页管理员数据
    public List<Admin> getAdminPage(String p, String k) {


        //判断是否第一次访问列表
        if (p != null) {

            int total = Integer.parseInt(Adao.getAdminTotal(k));
            int page = Integer.parseInt(p);
            int pageInfo[] = PageInfo.getPageInfo(page, total, 10);
            List<Admin> list = Adao.getAdminPage(pageInfo[0], pageInfo[1], k);
            return list;
        } else {
            int total = Integer.parseInt(Adao.getAdminTotal(k));
            //判断菜品数量是否大于一页的规格
            if (total < 10) {
                List<Admin> list = Adao.getAdminPage(0, total, k);
                return list;
            } else {
                List<Admin> list = Adao.getAdminPage(0, 10, k);
                return list;
            }
        }


    }
}
