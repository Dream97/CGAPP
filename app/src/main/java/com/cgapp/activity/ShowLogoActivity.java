package com.cgapp.activity;

/**
 * Created by asus on 2017/3/24.
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.cgapp.R;

/**
 * Created by D&LL on 2016/5/25.
 * 初始页面加载界面
 */
public class ShowLogoActivity extends Activity {

    private ImageView imageView;
    private AlphaAnimation alphaAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //设置AlphaAnimation动画
        imageView = (ImageView) findViewById(R.id.splashimage);
        alphaAnimation = new AlphaAnimation(0.1f,1.0f);//透明度范围
        imageView.setAnimation(alphaAnimation);//为组件绑定动画
        alphaAnimation.setDuration(2000);//时间
        //设置监听，当结束时启动登录界面
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(ShowLogoActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


}


