package com.cgapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cgapp.R;
import com.cgapp.Util.Api;
import com.cgapp.Util.CommonVari;
import com.cgapp.Util.JsonUtil;
import com.cgapp.Util.OkHttpUtil;
import com.cgapp.Util.ToastUtil;

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
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.register_getvc_bt:
                getVC();
                break;
            case R.id.register_bt:
                register();

                break;
        }
    }

    /**
     * 处理注册按钮事件
     */
    private void register() {
        String url1 = Api.url+"auth/register";
        String name = registerName.getText().toString().trim();
        String phone = registerId.getText().toString().trim();
        String password = registerPassword.getText().toString().trim();
        String password2 = registerPassword2.getText().toString().trim();
        String code = registerVc.getText().toString().trim();
        if (password.length()<6)
        {
            new ToastUtil(RegisterActivity.this, CommonVari.LIT);
        }else
        if(!password.equals(password2))
        {
            new ToastUtil(RegisterActivity.this, CommonVari.DIF);
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
                        final int status = JsonUtil.getIntCode(responseData);
                        if(status==1)
                        {
                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                new ToastUtil(RegisterActivity.this,status);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },map);
        }
    }

    /**
     * 处理验证码事件
     */
    private void getVC() {
        String url = Api.url+"auth/getVerificationCode";
        String key = "phone";
        String value = registerId.getText().toString().trim();
        if (value.length()!=11)
        {
            new ToastUtil(RegisterActivity.this, CommonVari.IDNULL);
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
                        final int status = JsonUtil.getIntCode(responseData);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (status==1)
                                {
                                    new ToastUtil(RegisterActivity.this,CommonVari.VCSUCCESS);
                                }else {
                                    new ToastUtil(RegisterActivity.this,status);
                                }
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },map);
        }
    }
}
