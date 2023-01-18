package com.example.myapplication.model;

import java.io.Serializable;

public class Orders implements Serializable {
    private int productId;
    private String productName;
    private String productPrice;
    private String poductQty;
    private String value_tot;
    private String stocks;
    private String customerName;
    private int full_amount;

    public Orders(int proId, String productName, String productPrice, String poductQty, String value_tot, String stocks,String customerName,int full_amount) {
        this.productId=proId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.poductQty = poductQty;
        this.value_tot = value_tot;
        this.stocks=stocks;
        this.customerName=customerName;
        this.full_amount=full_amount;
    }

    public Orders() {
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

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getPoductQty() {
        return poductQty;
    }

    public void setPoductQty(String poductQty) {
        this.poductQty = poductQty;
    }

    public String getValue_tot() {
        return value_tot;
    }

    public void setValue_tot(String value_tot) {
        this.value_tot = value_tot;
    }

    public String getStocks() {
        return stocks;
    }

    public void setStocks(String stocks) {
        this.stocks = stocks;
    }

    public String getCustomerName() {
        return customerName;
    }


    public int getFull_amount() {
        return full_amount;
    }

    public void setFull_amount(int full_amount) {
        this.full_amount = full_amount;
    }

    public String setCustomerName(String customerName) {
        this.customerName = customerName;
        return customerName;
    }

    @Override
    public String toString() {
        return customerName;

    }
}
