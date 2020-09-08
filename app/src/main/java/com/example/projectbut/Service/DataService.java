package com.example.projectbut.Service;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DataService implements Serializable {

    private String BASE_URL = "http://10.0.2.2:8080/";

    Retrofit retrofitClient =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(new OkHttpClient.Builder().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

    public SelectAPI select = retrofitClient.create(SelectAPI.class);

}

