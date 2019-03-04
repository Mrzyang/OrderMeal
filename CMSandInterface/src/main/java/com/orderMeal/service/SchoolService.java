package com.orderMeal.service;

import com.orderMeal.dao.SchoolDao;
import com.orderMeal.domain.Product;
import com.orderMeal.domain.School;
import com.orderMeal.utils.PageInfo;

import java.sql.SQLException;
import java.util.List;

public class SchoolService {

    SchoolDao Sdao = new SchoolDao();

    //查验学校名是否重复
    public int findSchoolByName(String schoolName, String schoolId) {

        return Sdao.findSchoolByName(schoolName,schoolId);
    }

    //添加学校信息
    public int addSchool(String schoolName,String schoolClasstotal1, String schoolClasstotal2, String schoolClasstotal3, String schoolPhone, String schoolAddress) {

        return Sdao.addSchool(schoolName, schoolClasstotal1, schoolClasstotal2, schoolClasstotal3, schoolPhone, schoolAddress);

    }

    //获取学校列表的总页数
    public int getSchoolTotalPage(String keyword) {


        String s = Sdao.getSchoolTotal(keyword);
        int total = Integer.parseInt(s);
        if(total == -1){
            return -1;
        }
        if(total%10==0){
            return total/10;
        }else {
            return total/10+1;
        }

    }


    //获取一页学校记录信息
    public List<School> getSchoolPage(String p, String k) {


        //判断是否第一次访问列表
        if(p != null) {
            int total = Integer.parseInt(Sdao.getSchoolTotal(k));
            int page = Integer.parseInt(p);
            int pageInfo[] = PageInfo.getPageInfo(page, total, 10);
            List<School> list = Sdao.getSchoolPage(pageInfo[0], pageInfo[1], k);
            return list;
        }else{
            int total = Integer.parseInt(Sdao.getSchoolTotal(k));
            //判断菜品数量是否大于一页的规格
            if (total < 10) {
                List<School> list = Sdao.getSchoolPage(0, total, k);
                return list;
            } else {
                List<School> list = Sdao.getSchoolPage(0, 10, k);
                return list;
            }
        }


    }

    //删除学校（一个或多个
    public int deleteSchool(String schoolId) {

        String[] id = null;
        try {
            id = schoolId.split(",");//用逗号切割
        }catch (Exception e){
            System.out.println("请输入要删除菜品的id");
            return -1;
        }


        for(int i=0;i<id.length;i++){
            if(Sdao.deleteSchool(id[i])==-1){//根据id删除
                return -1;
            }
        }

        return 0;

    }

    //更新学校信息
    public int updateSchool(String schoolId, String schoolName,String schoolClasstotal1, String schoolClasstotal2, String schoolClasstotal3, String schoolPhone, String schoolAddress) {

        return Sdao.updateSchool(schoolId, schoolName, schoolClasstotal1, schoolClasstotal2, schoolClasstotal3, schoolPhone, schoolAddress);

    }
}
