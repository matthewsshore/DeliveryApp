package com.example.skantoro.myapplication.backend;

import java.text.DecimalFormat;
import java.util.Currency;

/**
 * Created by skantoro on 9/22/15.
 */
public class Order {
    private Integer OrderID;
    private Integer AddressID;
    private String OrderName;
    private Integer Status;
    private Double EstPrice;
    private Double Bid;
    private Integer UserID;

    public Integer getOrderID() {
        return OrderID;
    }

    public void setOrderID(Integer orderID) {
        OrderID = orderID;
    }

    public Integer getAddressID() {
        return AddressID;
    }

    public void setAddressID(Integer addressID) {
        AddressID = addressID;
    }

    public String getOrderName() {
        return OrderName;
    }

    public void setOrderName(String orderName) {
        OrderName = orderName;
    }

    public Integer getStatus(){
        return Status;
    }

    public void setStatus(Integer status){
        Status = status;
    }

    public Double getEstPrice (){
        return EstPrice;
    }

    public void setEstPrice (Double estPrice){
        EstPrice = estPrice;
    }

    public Double getBid(){
        return Bid;
    }

    public void setBid(Double bid ){
        Bid = bid;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer ID) {
        UserID = ID;
    }


}
