package org.ecommercems.orderservice.controller;

import org.springframework.lang.NonNull;

import java.util.Date;

public class Order {
    @NonNull
    private double orderId;
    private double customerId;
    private double productId;
    @NonNull
    private int productQuantity;
    @NonNull
    private Date orderDate;

    public Order(int productQuantity) {
        this.orderId = Math.random() * 1000;
        this.productQuantity = productQuantity;
        this.orderDate = new Date();
    }
    public double getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getCustomerId() {
        return customerId;
    }
    public void setCustomerId(double customerId) {
        this.customerId = customerId;
    }

    public double getProductId() {
        return productId;
    }
    public void setProductId(double productId) {
        this.productId = productId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }


}
