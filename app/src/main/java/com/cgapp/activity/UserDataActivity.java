package com.cgapp.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.cgapp.R;
import com.cgapp.adapter.UserViewPagerAdapter;

/**
 * Created by asus on 2017/3/28.
 */

public class UserDataActivity extends AppCompatActivity implements View.OnClickListener, TabLayout.OnTabSelectedListener {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdata);
        toolbar = (Toolbar) findViewById(R.id.userdata_tool);
        tabLayout = (TabLayout) findViewById(R.id.userdata_tab);
        viewPager = (ViewPager) findViewById(R.id.userdata_viewpage);
        initView();
    }

    private void initView() {
        /**
         * toolBar设置
         */
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//去掉标题
        //放在后面，取代响应侧滑的按钮
        toolbar.setNavigationIcon(R.drawable.backicon);

        /**
         * viewpage与tab设置
         */
        UserViewPagerAdapter userViewPagerAdapter = new UserViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(userViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//模式
        tabLayout.setOnTabSelectedListener(this);


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

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}