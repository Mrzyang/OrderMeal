package com.orderMeal.domain;

import com.orderMeal.dao.CustomerDao;

/**
 * @program: OrderMeal
 * @description: 用户的意见反馈
 * @author: Mr.Zhang
 * @create: 2018-06-09 16:53
 **/
public class Message {
    private int id;
    private int customerId;
    private String message;
    private String messageDate;
    private int status;
    private Customer customer;

    public Message() {
    }

    public Message(int id, int customerId, String message, String messageDate, int status) {
        this.id = id;
        this.customerId = customerId;
        this.message = message;
        this.messageDate = messageDate;
        this.status = status;
        this.customer= CustomerDao.getCustomerById(customerId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", message='" + message + '\'' +
                ", messageDate='" + messageDate + '\'' +
                ", status=" + status +
                '}';
    }
}
