package com.example.projectbut.Service;

import com.example.projectbut.Pojo.Receipt;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SelectAPI {
    @GET("/")
    Call<List<Receipt>> selectAll();
}
