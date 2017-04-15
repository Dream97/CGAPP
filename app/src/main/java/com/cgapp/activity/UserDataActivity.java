package com.cgapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.cgapp.R;

/**
 * Created by asus on 2017/3/28.
 */

public class UserDataActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private Button backBt ;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdata);

        //backBt = (Button) toolbar.findViewById(R.id.userdata_back);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.userdata_tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//去掉标题
        //放在后面，取代响应侧滑的按钮
        toolbar.setNavigationIcon(R.drawable.backicon);
        //backBt.setOnClickListener(this);
    }

    /**
     * 左上角返回按钮监听
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // TODO Auto-generated method stub
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

        }
    }
}