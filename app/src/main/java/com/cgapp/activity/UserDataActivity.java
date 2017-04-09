package com.cgapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cgapp.R;

/**
 * Created by asus on 2017/3/28.
 */

public class UserDataActivity extends Activity {
    private String TAG ="hei";
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdata);
        Button button = (Button) findViewById(R.id.user_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
