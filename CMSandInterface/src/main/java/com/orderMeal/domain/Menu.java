package com.orderMeal.domain;

import java.util.Date;

public class Menu {
    private String menuId;
    private int schoolId;
    private String schoolName;
    private Date menuDate;//每周星期一

    public Menu() {
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menusId) {
        this.menuId = menusId;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public Date getMenuDate() {
        return menuDate;
    }

    public void setMenuDate(Date menuDate) {
        this.menuDate = menuDate;
    }
}
