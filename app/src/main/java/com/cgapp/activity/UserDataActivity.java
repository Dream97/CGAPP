package com.cgapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cgapp.R;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

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
                Intent intent = new Intent(UserDataActivity.this,LoginActivity.class);
                intent.setFlags(FLAG_ACTIVITY_CLEAR_TASK|FLAG_ACTIVITY_NEW_TASK);//只保留栈顶
                startActivity(intent);
                finish();
            }
        });
    }

}
