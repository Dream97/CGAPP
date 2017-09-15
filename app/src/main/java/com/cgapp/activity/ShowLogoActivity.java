package com.cgapp.activity;

/**
 * Created by asus on 2017/3/24.
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.cgapp.R;
import com.cgapp.Util.Api;
import com.cgapp.Util.CommonVari;
import com.cgapp.Util.JsonUtil;
import com.cgapp.Util.OkHttpUtil;
import com.cgapp.Util.SharedPreferencesUtil;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by D&LL on 2016/5/25.
 * 初始页面加载界面
 */
public class ShowLogoActivity extends Activity {
    //token验证api
    private String url = Api.url+"auth/logout";
    private ImageView imageView;
    private AlphaAnimation alphaAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    /**
     * 初始化View组件
     */
    private void initView() {
        imageView = (ImageView) findViewById(R.id.splashimage);
        //设置AlphaAnimation动画
        setAnimation();
    }

    /**
     * 动画设置
     */
    private void setAnimation() {
        alphaAnimation = new AlphaAnimation(0.1f,1.0f);//透明度范围
        imageView.setAnimation(alphaAnimation);//为组件绑定动画
        alphaAnimation.setDuration(2000);//时间
        //设置监听，当结束时启动登录界面
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            /***
             * 在动画结束前验证token令牌
             * 1.token令牌正确的的话直接跳过登录
             * 2.不正确的话需要登录
             * @param animation
             */
            @Override
            public void onAnimationEnd(Animation animation) {

                CommonVari.token = SharedPreferencesUtil.getData(ShowLogoActivity.this,"token");
                Log.d(TAG, "onAnimationEnd: token是是是是是是是"+CommonVari.token);
                Map<String,String> map = new HashMap<String, String>();
                map.put("token",CommonVari.token);
                OkHttpUtil.get(Api.getInfo, new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d(TAG, "onResponse: 状态是失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseBody = response.body().string();
                        try {
                            int status = JsonUtil.getIntCode(responseBody);
                            Log.d(TAG, "onResponse: 状态是"+responseBody);
                            if(status == 1)
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(ShowLogoActivity.this,MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });

                            }
                            else{
                                Intent intent = new Intent(ShowLogoActivity.this,LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },map);
                //finish();

            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}


