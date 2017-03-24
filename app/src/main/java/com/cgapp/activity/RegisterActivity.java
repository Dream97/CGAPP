package com.cgapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cgapp.R;

/**
 * Created by asus on 2017/3/23.
 */

public class RegisterActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar = (Toolbar) findViewById(R.id.re_toolBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.backicon);//设置右上角返回图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {//为返回图标的设置监听时间
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setDisplayShowTitleEnabled(false);//隐藏toolbar上的title
        //actionBar.setHomeAsUpIndicator(R.drawable.back);
       // actionBar.setTitle("注册");


    }
}
