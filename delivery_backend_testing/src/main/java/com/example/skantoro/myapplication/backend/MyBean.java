package com.example.skantoro.myapplication.backend;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private String myData;
    private Integer myID;

    public Integer getMyID() {
        return myID;
    }

    public void setMyID(Integer ID) {
        myID = ID;
    }

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }
}