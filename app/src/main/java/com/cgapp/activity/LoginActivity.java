package com.cgapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cgapp.R;
import com.cgapp.Util.Api;
import com.cgapp.Util.CommonVari;
import com.cgapp.Util.JsonUtil;
import com.cgapp.Util.OkHttpUtil;
import com.cgapp.Util.SharedPreferencesUtil;
import com.cgapp.Util.ToastUtil;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
//接风是傻逼
    String TAG = "LoginActivity";
    private Button visitorBt;
    private EditText id;
    private EditText password;
    private Button registerBt;
    private Button loginBt;
    private Button modifyBt;
    //private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView()
    {

        id = (EditText) findViewById(R.id.login_id);
        password = (EditText) findViewById(R.id.login_password);
        loginBt = (Button) findViewById(R.id.login_bt);
        registerBt = (Button) findViewById(R.id.register);
        modifyBt = (Button) findViewById(R.id.login_modify_bt);
        visitorBt = (Button) findViewById(R.id.visitor);
        if(CommonVari.LOGINFAG == 0)
        {
            //读取上一次登录的账号密码
            id.setText(SharedPreferencesUtil.getData(this,"id"));
            password.setText(SharedPreferencesUtil.getData(this,"password"));
        }else{
            //如果是从注册或者修改密码界面中过来的就读取Extra内容
            Intent intent = getIntent();
            id.setText(intent.getStringExtra(CommonVari.id));
            password.setText(intent.getStringExtra(CommonVari.password));
        }
        //注册登录按钮
        loginBt.setOnClickListener(this);
        //注册按钮监听
        registerBt.setOnClickListener(this);
        //注册修改密码按钮
        modifyBt.setOnClickListener(this);
        //游客登录按钮
        visitorBt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                 break;
            case R.id.login_bt:
                login();
                break;
            case R.id.login_modify_bt:
                Intent intent2 = new Intent(LoginActivity.this,ModifyPasswordActivity.class);
                startActivity(intent2);
                break;
            case R.id.visitor:
                Intent intent3 = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent3);
                finish();
                break;
        }
    }

    /**
     * 处理登录事件
     */
    private void login() {
        String url = Api.url+"auth/login";
        Map<String,String> map = new HashMap<>();
        if(id.getText().toString().length()!=11)
        {
            new ToastUtil(LoginActivity.this, CommonVari.IDNULL);
        }else if(password.getText().toString().length()<6){
            new ToastUtil(LoginActivity.this,CommonVari.LIT);
        }
        else{
            map.put("phone",id.getText().toString());
            map.put("password",password.getText().toString());

            OkHttpUtil.post(url, new okhttp3.Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "onFailure: ",e);
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseBody = response.body().string();
                    try {
                        final String token = JsonUtil.getToken(responseBody);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(token!=null)
                                {
                                    CommonVari.token = token;
                                    Intent intent1 = new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(intent1);
                                    Log.d(TAG, "run: toekn令牌"+token);
                                    SharedPreferencesUtil.pustData(LoginActivity.this,id.getText().toString(),password.getText().toString(),token);
                                    CommonVari.FAG = 1;
                                    finish();
                                }else{
                                    new ToastUtil(LoginActivity.this,0);
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