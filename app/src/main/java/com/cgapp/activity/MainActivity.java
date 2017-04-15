package com.cgapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cgapp.R;
import com.cgapp.Util.CommonVari;
import com.cgapp.adapter.FragmentViewPaferAdapter;

/**
 * Created by asus on 2017/3/24.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,TabLayout.OnTabSelectedListener, View.OnClickListener {


    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar ;
    private ActionBarDrawerToggle toggle;

    //设置tabLayout
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentViewPaferAdapter fragmentViewPaferAdapter;
    //绿色icon
    private int[] tabimages_green =new int[]{
            R.drawable.tab_home_green,
            R.drawable.tab_push_green,
            R.drawable.tab_repair_green
    };
    private int[] tabimages =new int[]{
            R.drawable.tab_home_black,
            R.drawable.tab_push_black,
            R.drawable.tab_repair_black
    };
    private String[] titles ={"主页","推送","报修"};

    //侧滑栏组件
    private View view ;
    private Button navLoginBt;//侧滑栏登录按钮
    private TextView navUsername;
    private TextView navId;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        initView();
    }

    /**
     *初始化组件
     */
    private void initView() {
        //注册toobar
        toolbar = (Toolbar) findViewById(R.id.main_toolBar);

        //toolbar.setLogo(R.drawable.jiwei_text_logo);
        //toolbar.setLogo(R.drawable.tab_home_black);
        //toolbar.setTitle("");
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

        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//去掉标题
        //放在后面，取代响应侧滑的按钮
        //toolbar.setNavigationIcon(R.drawable.tab_home_black);
        /**
         * tablayout
         */

        fragmentViewPaferAdapter = new FragmentViewPaferAdapter(getSupportFragmentManager(), this);

        viewPager.setAdapter(fragmentViewPaferAdapter);
        tabLayout.setupWithViewPager(viewPager);//按理解，只要绑定了就会知道多少个tab
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addOnTabSelectedListener(this);//监听，实时更换颜色
        //为tab设置图标
        setCustemTablayout(tabLayout);
        TabLayout.Tab tab = tabLayout.getTabAt(0);
        View v = tab.getCustomView();
        ImageView image = (ImageView) v.findViewById(R.id.imageView);
        image.setImageResource(tabimages_green[0]);

        /**
         * 侧滑栏组件设置点击事件
         * 实现该功能需要在xml中取消headerLayout="@layout/nav_header
         */
        view = navigationView.inflateHeaderView(R.layout.nav_header);
        navLoginBt = (Button) view.findViewById(R.id.nav_login_bt);
        navUsername = (TextView) view.findViewById(R.id.nav_username);
        navId = (TextView) view.findViewById(R.id.header_id);
        //判断是否是游客登录
        if(CommonVari.FAG==0)
        {
            navUsername.setVisibility(View.GONE);
            navId.setVisibility(View.GONE);
            navLoginBt.setOnClickListener(this);
        }else {
            navLoginBt.setVisibility(View.GONE);
        }

    }

    /**
     * 设置tab图标
     * @param tabLayout
     */

    private void setCustemTablayout(TabLayout tabLayout) {
        for (int i = 0;i<tabLayout.getTabCount();i++)
        {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
                    if(tab!=null)
                    {
                        tab.setCustomView(fragmentViewPaferAdapter.getTabView(i));
                    }
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.menu_data:
                Intent intent = new Intent(MainActivity.this,UserDataActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_history:
                Intent intent1 = new Intent(MainActivity.this,RepairHistoryActivity.class);
                startActivity(intent1);
                break;
            case R.id.menu_favorite:
                Intent intent2 = new Intent(MainActivity.this,FavoriteActivity.class);
                startActivity(intent2);
                break;
            case R.id.menu_comunite:
                Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_about:
                Intent intent3 = new Intent(MainActivity.this,RepairHistoryActivity.class);
                startActivity(intent3);
                break;
            case R.id.menu_dark:
                Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_refresh:
                Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
                break;
        }
        //drawerLayout.closeDrawer(GravityCompat.START);//关闭侧滑栏
        return false;
    }

    /**
     * 监听改变图标颜色
     * @param tab
     */
    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        for(int i=0;i<tabLayout.getTabCount();i++)
        {

            if(i==tab.getPosition())
            {
                //v = null;
                //View v = LayoutInflater.from(this).inflate(R.layout.tab_layout_view,null);
                //TextView tv = (TextView) v.findViewById(R.id.textView);
                //tv.setText(titles[i]);
                View v = tab.getCustomView();
                ImageView image = (ImageView) v.findViewById(R.id.imageView);
                image.setImageResource(tabimages_green[i]);
                //tab.setCustomView(v);
            }
            else
            {
               TabLayout.Tab tab2= tabLayout.getTabAt(i);
                //v = fragmentViewPaferAdapter.getTabView(i);
                //tab.setCustomView(v);
                View v = tab2.getCustomView();
                ImageView image = (ImageView) v.findViewById(R.id.imageView);
                image.setImageResource(tabimages[i]);
            }
        }

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.nav_login_bt:
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
