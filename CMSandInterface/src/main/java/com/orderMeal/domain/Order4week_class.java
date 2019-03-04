package com.orderMeal.domain;

import java.util.Date;

public class Order4week_class {

    private String customerName;
    private int total;
    private String[] order4weekPrice;

    public Order4week_class(String customerName, String[] order4weekPrice) {
        this.customerName = customerName;
        this.order4weekPrice = order4weekPrice;
        this.total = 0;
        for(int i=0;i<14;i++) {
            try {
                this.total += Integer.parseInt(order4weekPrice[i]);
            }catch (Exception e){
                e.printStackTrace();
                this.total = -1;
                break;
            }
        }

    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String[] getOrder4weekPrice() {
        return order4weekPrice;
    }

    public void setOrder4weekPrice(String[] order4weekPrice) {
        this.order4weekPrice = order4weekPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
