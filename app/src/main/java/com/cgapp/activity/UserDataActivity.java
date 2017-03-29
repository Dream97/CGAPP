package com.cgapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cgapp.R;
import com.cgapp.Util.GetHttpUtil;
import com.cgapp.Util.HttpCallbackListener;

/**
 * Created by asus on 2017/3/28.
 */

public class UserDataActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdata);
        Button button = (Button) findViewById(R.id.user_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://apis.guokr.com/handpick/article.json?retrieve_type=by_since&category=all&limit=25&ad=1";
                GetHttpUtil.getHttpRequest(url, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        Log.d("TAG",response);
                    }

                    @Override
                    public void onError(Exception error) {
                        Log.e("TAG",error.getMessage()+"哈", error);
                    }
                });
            }
        });
    }
}
