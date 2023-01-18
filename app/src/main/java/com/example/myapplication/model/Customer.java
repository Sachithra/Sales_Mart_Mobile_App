package com.example.myapplication.model;

import java.io.Serializable;

public class Customer implements Serializable {
    private int customerId;
    private String customerName;
    private String customerPhone;
    private String customerAge;
    private String check_type;
    private byte[] image;




    public Customer(int customerId, String customerName, String customerPhone, String customerAge) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAge = customerAge;
    }

    public Customer(int customerId, String customerName, String customerPhone, String customerAge,String check_type) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAge = customerAge;
        this.check_type=check_type;

    }
    public Customer() {

    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(String customerAge) {
        this.customerAge = customerAge;
    }


    public String getCheck_type() {
        return check_type;
    }

    public void setCheck_type(String check_type) {
        this.check_type = check_type;
    }


    @Override
    public String toString() {
        return customerName;
    }
}
