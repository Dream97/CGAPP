package com.cgapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cgapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button registerBt;
    private Button loginBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //ActionBar actionBar = getActionBar();
        //actionBar.hide();
        //getSupportActionBar().hide();//隐藏actionBar
        setContentView(R.layout.activity_login);

        initView();

    }

    private void initView()
    {
        //注册登录按钮
        loginBt = (Button) findViewById(R.id.login_bt);
        loginBt.setOnClickListener(this);
        //注册按钮监听
        registerBt = (Button) findViewById(R.id.register);
        registerBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                 break;
            case R.id.login_bt:
                Intent intent1 = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent1);
                break;
        }
    }
}