package com.orderMeal.domain;

public class Customer {
    private String customerId;//
    private String schoolId;//用户所在学校信息
    private String schoolName;
    private String customerNum;//编号（学号/工号）
    private String customerGrade; //新增年级属性
    private String customerClass;//班级
    private String customerName;
    private String customerPassword;
    private double customerAccount;
    private String customerType;
    private String customerPhone;    //新增电话号码属性

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Customer() {
    }

    public Customer(String customerId, String schoolId, String schoolName, String customerClass, String customerName, String customerPassword) {
        this.customerId = customerId;
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.customerClass = customerClass;
        this.customerName = customerName;
        this.customerPassword = customerPassword;
    }

    public Customer(String customerId, String schoolId, String schoolName, String customerNum, String customerClass, String customerName, String customerPassword, double customerAccount, String customerType, String customerPhone) {
        this.customerId = customerId;
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.customerNum = customerNum;
        this.customerClass = customerClass;
        this.customerName = customerName;
        this.customerPassword = customerPassword;
        this.customerAccount = customerAccount;
        this.customerType = customerType;
        this.customerPhone = customerPhone;
    }

    public double getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(double customerAccount) {
        this.customerAccount = customerAccount;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public String getCustomerClass() {
        return customerClass;
    }

    public void setCustomerClass(String customerClass) {
        this.customerClass = customerClass;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerGrade() {
        return customerGrade;
    }

    public void setCustomerGrade(String customerGrade) {
        this.customerGrade = customerGrade;
    }
}
