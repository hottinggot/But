package com.example.projectbut.entity;

import com.google.gson.annotations.SerializedName;

public class User {

    int userNum;
    String userId;
    String password;
    String email;
    String phone;

    public int getUserNum(){
        return userNum;
    }
    public String getUserId(){ return userId; }
    public String getPassword(){
        return password;
    }
    public String getEmail(){
        return email;
    }
    public String getPhone(){
        return phone;
    }
}
