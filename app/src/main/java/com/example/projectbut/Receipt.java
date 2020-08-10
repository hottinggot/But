package com.example.projectbut;

import java.util.Date;

import lombok.Data;

@Data
public class Receipt {

    private int id;

    private String title;

    private String shopName;

    private String wifiId;

    private String wifiPw;

    private int userId;

    private int barcode;

    private String storePhoneNumber;

    private String card;

    private int orderNum;


    private Date createDate;
}
