package com.example.skantoro.myapplication.backend;

/**
 * Created by skantoro on 9/22/15.
 */
public class Address {
    private Integer AddressID;
    private String Street;
    private String City;
    private String State;
    private String Zip;
    private Double Lat;
    private Double Longitude;
    private Integer UserID;

    public Integer getAddressID() {
        return AddressID;
    }

    public void setAddressID(Integer addressID) {
        AddressID = addressID;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer ID) {
        UserID = ID;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getCity(){
        return City;
    }

    public void setCity(String city){
        City = city;
    }

    public String getState (){
        return State;
    }

    public void setState (String state){
        State = state;
    }

    public String getZip(){
        return Zip;
    }

    public void setZip(String zip){
        Zip = zip;
    }

    public Double getLat(){
        return Lat;
    }

    public void setLat(Double lat){
        Lat = lat;
    }

    public Double getLong(){
        return Longitude;
    }

    public void setLong(Double longitude){
        Longitude = longitude;
    }


}
