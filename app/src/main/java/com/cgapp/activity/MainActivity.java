package com.cgapp.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.cgapp.R;

/**
 * Created by asus on 2017/3/24.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar ;
    private ActionBarDrawerToggle toggle;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     *初始化组件
     */
    private void initView()
    {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        //注册toobar
        toolbar = (Toolbar) findViewById(R.id.main_toolBar);
        setSupportActionBar(toolbar);
        //使用toggle监听drawerlayout
        toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();        //注册navigation并设置监听
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
