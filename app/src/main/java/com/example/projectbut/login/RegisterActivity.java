package com.example.projectbut.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectbut.MainActivity;
import com.example.projectbut.R;
import com.kakao.usermgmt.LoginButton;

public class RegisterActivity extends AppCompatActivity {
    private Context mContext;

    private Button kakao_login;
    private LoginButton btn_kakao_login;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = getApplicationContext();

        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                RegisterActivity.this.startActivity(mainIntent);
            }
        });
        kakao_login = (Button) findViewById(R.id.kakao_login);
        kakao_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_kakao_login.performClick();
            }
        });

        btn_kakao_login = (LoginButton) findViewById(R.id.btn_kakao_login);
    }

}
