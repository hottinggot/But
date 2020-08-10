package com.example.projectbut;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DataService implements Serializable {

    private String BASE_URL = "http://10.0.2.2:8080/";

    Retrofit retrofitClient =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(new OkHttpClient.Builder().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

    SelectAPI select = retrofitClient.create(SelectAPI.class);

}

interface SelectAPI{
    @GET("/")
    Call<List<Receipt>> selectAll();
}
