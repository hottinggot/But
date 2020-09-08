package com.example.projectbut.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import lombok.Data;

@Data
public class Receipt implements Parcelable{

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.shopName);
        dest.writeString(this.wifiId);
        dest.writeString(this.wifiPw);
        dest.writeInt(this.userId);
        dest.writeInt(this.barcode);
        dest.writeString(this.storePhoneNumber);
        dest.writeString(this.card);
        dest.writeInt(this.orderNum);
        dest.writeLong(this.createDate != null ? this.createDate.getTime() : -1);
    }

    protected Receipt(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.shopName = in.readString();
        this.wifiId = in.readString();
        this.wifiPw = in.readString();
        this.userId = in.readInt();
        this.barcode = in.readInt();
        this.storePhoneNumber = in.readString();
        this.card = in.readString();
        this.orderNum = in.readInt();
        long tmpCreateDate = in.readLong();
        this.createDate = tmpCreateDate == -1 ? null : new Date(tmpCreateDate);
    }

    public static final Creator<Receipt> CREATOR = new Creator<Receipt>() {
        @Override
        public Receipt createFromParcel(Parcel source) {
            return new Receipt(source);
        }

        @Override
        public Receipt[] newArray(int size) {
            return new Receipt[size];
        }
    };
}
