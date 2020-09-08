package com.example.projectbut.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectbut.Api.UserApi;
import com.example.projectbut.MainActivity;
import com.example.projectbut.R;
import com.example.projectbut.entity.User;
import com.example.projectbut.login.retrofit.RetrofitPost;

import java.net.URL;
import java.security.MessageDigest;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    //private String URL = "http://192.168.0.4:8080/user";
    private String URL = "http://jsonplaceholder.typicode.com/";
    private Context mContext;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getAppKeyHash();
        TextView registerButton = (TextView) findViewById(R.id.registerButton);
        TextView findButton = (TextView) findViewById(R.id.findButton);
        Button loginButton = (Button) findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                /*Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(mainIntent);*/
                Intent mainIntent = new Intent(LoginActivity.this, RetrofitPost.class);
                LoginActivity.this.startActivity(mainIntent);

            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }

        });
        findButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent findIntent = new Intent(LoginActivity.this, FindActivity.class);
                LoginActivity.this.startActivity(findIntent);
            }

        });
    }

    private void getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("Hash key", something);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString());
        }
    }

    private void RetrofitPost(){
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
                    /*content += "User Number: " + user.getUserNum() + "\n";
                    content += "Password: " + user.getPassword() + "\n";
                    content += "Phone: " + user.getPhone() + "\n";
                    content += "Email" + user.getEmail() + "\n";*/
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