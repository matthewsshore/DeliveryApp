package com.example.skantoro.myapplication.backend;

/**
 * Created by skantoro on 9/22/15.
 */
public class User {
    private Integer UserID;
    private String FirstName;
    private String LastName;
    private String MobileNumber;
    private String Role;
    private String Email;

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer ID) {
        UserID = ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName(){
        return LastName;
    }

    public void setLastName(String lastName){
        LastName = lastName;
    }

    public String getMobileNumber (){
        return MobileNumber;
    }

    public void setMobileNumber (String mobileNumber){
        MobileNumber = mobileNumber;
    }

    public String getRole(){
        return Role;
    }

    public void setRole(String role){
        Role = role;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

}
