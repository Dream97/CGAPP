package com.cgapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cgapp.R;
import com.cgapp.Util.Api;
import com.cgapp.Util.JsonUtil;
import com.cgapp.Util.OkHttpUtil;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 注册界面
 * Created by asus on 2017/3/23.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "RegisterActivity";
    private Toolbar toolbar;
    private EditText registerName;
    private EditText registerId;
    private EditText registerPassword;
    private EditText registerPassword2;
    private EditText registerVc;
    private Button registerVcBt;
    private Button registerBt;
    private Handler handler;
    private  int IDNULL = 404;
    private  int DIF = 405;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar = (Toolbar) findViewById(R.id.re_toolBar);
        registerId = (EditText) findViewById(R.id.register_id);
        registerVcBt = (Button) findViewById(R.id.register_getvc_bt);
        registerName = (EditText) findViewById(R.id.register_name);
        registerPassword = (EditText) findViewById(R.id.register_pass);
        registerPassword2 = (EditText) findViewById(R.id.register_pass2);
        registerVc = (EditText) findViewById(R.id.register_verificationcode);
        registerBt = (Button) findViewById(R.id.register_bt);
        initView();
    }

    private void initView() {
        /**
         * toolbar设置
         */
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.backicon);//设置右上角返回图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {//为返回图标的设置监听时间
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setDisplayShowTitleEnabled(false);//隐藏toolbar上的title
        /**
         * 按钮监听
         */
        registerVcBt.setOnClickListener(this);
        registerBt.setOnClickListener(this);
        handler = new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what)
                {
                    case 0:
                        Toast.makeText(RegisterActivity.this,"获取验证码失败",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(RegisterActivity.this,"获取验证码中",Toast.LENGTH_SHORT).show();
                        break;
                    case 404:
                        Toast.makeText(RegisterActivity.this,"ID/手机不能为空",Toast.LENGTH_SHORT).show();
                        break;
                    case 405:
                        Toast.makeText(RegisterActivity.this,"两次密码输入不相同",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.register_getvc_bt:
                String url = Api.url+"auth/getVerificationCode";
                String key = "phone";
                String value = registerId.getText().toString().trim();
                if (TextUtils.isEmpty(value))
                {
                    Message msg = new Message();
                    msg.what = IDNULL;
                    handler.sendMessage(msg);
                }else{
                    Map<String,String> map =  new HashMap<>();
                    map.put(key,value);
                    OkHttpUtil.post(url, new okhttp3.Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e(TAG, "onFailure: ",e);
                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData = response.body().string();
                            try {
                                int status = JsonUtil.getJsonDate(responseData);
                                Message msg = new Message();
                                msg.what = status;
                                handler.sendMessage(msg);
                            //Toast.makeText(RegisterActivity.this,hei,Toast.LENGTH_SHORT).show();
                            //Log.d(TAG, "onSuccess: "+hei);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },map);
                }
                break;
            case R.id.register_bt:
                String url1 = Api.url+"auth/register";
                String name = registerName.getText().toString().trim();
                String phone = registerId.getText().toString().trim();
                String password = registerPassword.getText().toString().trim();
                String password2 = registerPassword2.getText().toString().trim();
                String code = registerVc.getText().toString().trim();
                if (!password.equals(password2))
                {
                    Message msg = new Message();
                    msg.what = DIF;
                    handler.sendMessage(msg);
                }else{
                    Map<String,String> map =  new HashMap<>();
                    map.put("phone",phone);
                    map.put("name",name);
                    map.put("password",password);
                    map.put("code",code);
                    OkHttpUtil.post(url1, new okhttp3.Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e(TAG, "onFailure: ",e);
                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData = response.body().string();
                            try {
                                int status = JsonUtil.getJsonDate(responseData);
                                if(status==1)
                                {
                                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                                    }
                                });

//                                Message msg = new Message();
//                                msg.what = status;
//                                handler.sendMessage(msg);
                                //Toast.makeText(RegisterActivity.this,hei,Toast.LENGTH_SHORT).show();
                                //Log.d(TAG, "onSuccess: "+hei);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },map);
                }
                break;
        }
    }
}
