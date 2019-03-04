package com.orderMeal.domain;

/**
 * @program: OrderMeal
 * @description: 关于本平台
 * @author: Mr.Zhang
 * @create: 2018-06-06 12:13
 **/
public class About {
    private int id;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "About{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
