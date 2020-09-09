package com.example.projectbut.entity;

import com.google.gson.annotations.SerializedName;

public class User {

    int user_num;
    String user_id;
    String password;
    String email;
    String phone;

    public int getUserNum(){
        return user_num;
    }
    public String getUserId(){
        return user_id;
    }
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
