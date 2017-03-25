package com.cgapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cgapp.R;

/**
 * Created by asus on 2017/3/25.
 */

public class ModifyPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private EditText id;
    private EditText pass;
    private EditText pass2;
    private EditText verification;
    private Button verificationBt;
    private Button modifyBt;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);

        initView();
    }

    private void initView() {
        //toolbar设置
        toolbar = (Toolbar) findViewById(R.id.modify_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.backicon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {//为做商检设置
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setDisplayShowTitleEnabled(false);//隐藏title

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

        }
    }
}
