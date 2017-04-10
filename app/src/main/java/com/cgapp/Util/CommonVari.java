package com.cgapp.Util;

/**
 * 公共变量
 * Created by asus on 2017/4/9.
 */

public class CommonVari {
    //获取验证码中
    public static final int VCSUCCESS = 404;
    //手机号为空
    public static final int IDNULL = 405;
    //输入密码不同
    public static final int DIF = 406;
    //密码过短
    public static final int LIT = 407;
    //token令牌
    public static  String token  = null;
    //立fag判断是不是游客登录，1为游客，0为用户
    public static int FAG = 0;
    //立一个loginFag判断是否从注册或者修改你密中跳转过来的,0表示不是
    public static int LOGINFAG =0;
    //账号
    public static String id = "id";
    //密码
    public static String password = "password";
}
