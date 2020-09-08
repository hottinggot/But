package com.example.projectbut.login.retrofit;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectbut.Api.UserApi;
import com.example.projectbut.R;
import com.example.projectbut.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitPost extends AppCompatActivity {
    private String URL = "http://192.168.0.4:8080/user/";
    //private String URL = "http://jsonplaceholder.typicode.com/";
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        post();

    }

    private void post(){
        setContentView(R.layout.activity_test);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserApi userApi = retrofit.create(UserApi.class);
        Call<List<User>> call = userApi.getUser();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("code: " + response.code());
                    return;
                }

                List<User> users = response.body();

                for (User user : users) {
                    String content = "";
                    content += "User Id: " + user.getUserId() + "\n";
                    content += "User Number: " + user.getUserNum() + "\n";
                    content += "Password: " + user.getPassword() + "\n";
                    content += "Phone: " + user.getPhone() + "\n";
                    content += "Email" + user.getEmail() + "\n";
                    textViewResult.append(content);
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

}
