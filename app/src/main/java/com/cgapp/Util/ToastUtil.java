package com.cgapp.Util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by asus on 2017/4/9.
 */

public class ToastUtil {
    private Context context;
    private int code;
    private String msg = "错误";
    public ToastUtil(Context context, int code)
    {
        this.context = context;
        this.code = code;
        initMsg();
        showToast();
    }
    public ToastUtil(Context context, String msg)
    {
        this.context = context;
        this.msg = msg;
        showToast();
    }

    private void initMsg() {
        switch (code)
        {
            case CommonVari.VCSUCCESS:
                msg = "获取验证码中";
                break;
            case CommonVari.IDNULL:
                msg = "ID为11位手机号";
                break;
            case CommonVari.LIT:
                msg = "密码不能少于6个字符";
                break;
            case CommonVari.DIF:
                msg = "两次密码输入不同";
                break;
            case 300:
                msg = "获取验证码失败";
                break;
            case 301:
                msg = "手机号已被注册";
                break;
            case 302:
                msg = "用户名已被注册";
                break;
            case 303:
                msg = "验证码错误或过期";
                break;
            case 304:
                msg = "手机号不存在";
                break;
            case 305:
                msg = "密码错误";
                break;
            case 306:
                msg = "新旧密码相同";
                break;
            default:
                break;
        }
    }

    private void showToast() {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }


}
