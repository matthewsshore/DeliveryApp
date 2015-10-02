package com.example.skantoro.myapplication.backend;

/**
 * Created by skantoro on 9/22/15.
 */
public class Item {
    private Integer ItemID;
    private Integer OrderID;
    private String ItemName;
    private String PictureLocation;

    public Integer getItemID() {
        return ItemID;
    }

    public void setItemID(Integer itemID) {
        ItemID = itemID;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getPictureLocation() {
        return PictureLocation;
    }

    public void setPictureLocation(String pictureLocation) {
        PictureLocation = pictureLocation;
    }

    public Integer getOrderID(){
        return OrderID;
    }

    public void setOrderID(Integer orderID){
        OrderID = orderID;
    }


}
