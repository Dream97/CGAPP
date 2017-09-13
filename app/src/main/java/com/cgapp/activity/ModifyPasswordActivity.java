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
 * Created by asus on 2017/3/25.
 */

public class ModifyPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "ModifyPasswordActivity";
    private Toolbar toolbar;
    private EditText id;
    private EditText pass;
    private EditText pass2;
    private EditText verification;
    private Button verificationBt;
    private Button modifyBt;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        toolbar = (Toolbar) findViewById(R.id.modify_toolbar);
        id = (EditText) findViewById(R.id.modify_id);
        pass = (EditText) findViewById(R.id.modify_password);
        pass2 = (EditText) findViewById(R.id.modify_password2);
        verification = (EditText) findViewById(R.id.modify_verificationcode);
        verificationBt = (Button) findViewById(R.id.modify_getvc_bt);
        modifyBt = (Button) findViewById(R.id.modify_bt);
        initView();
    }

    private void initView() {
        //toolbar设置
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.backicon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {//为做商检设置
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setDisplayShowTitleEnabled(false);//隐藏title
        /**
         * 为按钮监听事件
         */
        verificationBt.setOnClickListener(this);
        modifyBt.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.modify_getvc_bt:
                getVC();
                break;
            case R.id.modify_bt:
                modify();
                break;
        }
    }

    /**
     * 处理修改密码按钮事件
     */
    private void modify() {
        String url1 = Api.forget;
        String phone = id.getText().toString();
        final String password = pass.getText().toString();
        String password2 = pass2.getText().toString();
        String code = verification.getText().toString();
        if (password.length()<6)
        {
            new ToastUtil(ModifyPasswordActivity.this, CommonVari.LIT);
        }else
        if(!password.equals(password2))
        {
            new ToastUtil(ModifyPasswordActivity.this, CommonVari.DIF);
        }else{
            Map<String,String> map =  new HashMap<>();
            map.put("email",phone);
            map.put("newPass",password);
            map.put("code",code);
            OkHttpUtil.post(url1, new okhttp3.Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "onFailure: ",e);
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String responseData = response.body().string();
                    try {
                        final int status = JsonUtil.getIntCode(responseData);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(status == 1)
                            {
                                Intent intent = new Intent(ModifyPasswordActivity.this,LoginActivity.class);
                                intent.putExtra(CommonVari.id,id.getText().toString());
                                intent.putExtra(CommonVari.password,pass.getText().toString());
                                startActivity(intent);
                                finish();
                            }else{
                                    Log.d(TAG, "run:"+"status是"+status);

                                    try {
                                        new ToastUtil(ModifyPasswordActivity.this,JsonUtil.getData(responseData));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
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

    /**
     * 处理获取验证码事件
     */
    private void getVC() {
        String url = Api.getCode;
        String key = "email";
        String value = id.getText().toString().trim();
        if (value.length()<=5)
        {
            new ToastUtil(ModifyPasswordActivity.this, CommonVari.IDNULL);
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
                                if (status == 1)
                                {
                                    new ToastUtil(ModifyPasswordActivity.this,CommonVari.VCSUCCESS);
                                }else {
                                    new ToastUtil(ModifyPasswordActivity.this,status);
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
