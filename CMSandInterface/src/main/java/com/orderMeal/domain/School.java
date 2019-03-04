package com.orderMeal.domain;

public class School {
    private String schoolId;
    private int schoolClasstotal1;
    private int schoolClasstotal2;
    private int schoolClasstotal3;
    private String schoolName;
    private String schoolPhone;
    private String schoolAddress;

    public School() {
    }

    public int getSchoolClasstotal1() {
        return schoolClasstotal1;
    }

    public void setSchoolClasstotal1(int schoolClasstotal1) {
        this.schoolClasstotal1 = schoolClasstotal1;
    }

    public int getSchoolClasstotal2() {
        return schoolClasstotal2;
    }

    public void setSchoolClasstotal2(int schoolClasstotal2) {
        this.schoolClasstotal2 = schoolClasstotal2;
    }

    public int getSchoolClasstotal3() {
        return schoolClasstotal3;
    }

    public void setSchoolClasstotal3(int schoolClasstotal3) {
        this.schoolClasstotal3 = schoolClasstotal3;
    }

    public String getSchoolPhone() {
        return schoolPhone;
    }

    public void setSchoolPhone(String schoolPhone) {
        this.schoolPhone = schoolPhone;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
