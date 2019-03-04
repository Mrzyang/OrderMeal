package com.orderMeal.domain;

import java.util.Date;

public class Product {
    private int productId;
    private String productName;
    private String productType;//1、主荤 2、素菜 3、辅荤 4、饮品水果
    private String productSign;//菜品有效条件

    public Product() {
    }

    public Product(int productId, String productName, String productType) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
    }

    public String getProductSign() {
        return productSign;
    }

    public void setProductSign(String productSign) {
        this.productSign = productSign;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
