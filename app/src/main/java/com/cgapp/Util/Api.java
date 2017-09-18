package com.cgapp.Util;

/**
 * Created by asus on 2017/3/30.
 */

public class Api {

    //果壳的Api,测试用
    public static final String GUOKR_ARTICLES = "http://apis.guokr.com/handpick/article.json?retrieve_type=by_since&category=all&limit=25&ad=1";

    //主接口
    public static final String url = "http://dawnki.cn:8002/api/";

    //注册
    public static final String register = url+"user/register";
    //登录
    public static final String login = url+"user/login";
    //忘记密码
    public static final String forget = url+"user/forget";
    //获取验证码
    public static final String getCode = url+"user/getCode";
    //获取用户信息
    public static final String getInfo = url+"user/getInfo";
    //登出
    public static final String logout = url+"user/logout";
    //修改
    public static final String modify = url +"user/modify";





//    1.注册
//
//    参数名	类型	含义	是否必选
//    email	string	电子邮箱(务必邮箱格式)	是
//    phone	string	手机号码	是
//    password	string	密码最少8位	是
//    name	string	用户昵称	否
//    请求方法:POST
//
//    请求地址: http://域名/user/register
//
//    {
//        "status": 1,      //状态码 1成功 0失败
//            "errmsg": "OK!",
//            "data": "注册成功!"
//    }
//2.登录
//
//    参数名	类型	含义	是否必选
//    email	string	电子邮箱(务必邮箱格式)	是
//    password	string	密码	是
//    请求方法:POST
//
//    请求地址: http://域名/user/login
//
//    {
//        "status": 1,
//            "errmsg": "OK!",
//            "data": {
//        "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwMDIvYXBpL3VzZXIvbG9naW4iLCJpYXQiOjE1MDI3MjA3MzYsImV4cCI6MTUwMjcyNDMzNiwibmJmIjoxNTAyNzIwNzM2LCJqdGkiOiJFaVg4VmcxQzV1V0k2d2dIIiwic3ViIjoyfQ.XGkOQi3ujJIXXjxeJWzbgwLO4JLFz2ahIaYx8S8a8tE", //登录token 内部接口需要用到
//                "info": {    //用户基本信息
//            "id": 2,  //用户主键 可忽略
//                    "phone": "15555555555",   //用户手机
//                    "email": "245357001@qq.com",  //用户邮箱
//                    "name": "Dawnki",   //用户名
//                    "identity": 0,     //用户身份 0普通用户 1计维工作人员 2管理员
//                    "last_login": "2017-08-14 14:25:36", //最后登录时间
//                    "created_at": "2017-08-14 14:23:36",  //账户创建时间
//                    "updated_at": "2017-08-14 14:25:36"   //账户最后更改时间
//        }
//    }
//    }
//3.登出
//
//    参数名	类型	含义	是否必选
//    Authorization	Header	验证token	是
//    备注:注意,Authorization是header参数!Authorization是header参数!Authorization是header参数!Authorization的值内容是Bearer+空格+token值
//
//    如下:
//
//            "Authorization":Bearer xxxxxxx.xxxxx.xxxx
//    请求方法:POST
//
//    请求地址: http://域名/user/logout
//
//    {
//        "status": 1,
//            "errmsg": "OK!",
//            "data": "登出成功!"
//    }
//4.获取验证码
//
//    参数名	类型	含义	是否必选
//    email	string	电子邮箱(务必邮箱格式)	是
//    请求方法:POST
//
//    请求地址: http://域名/user/getCode
//
//    {
//        "status": 1,
//            "errmsg": "OK!",
//            "data": "验证码发送成功!"
//    }
//5.忘记密码
//
//    参数名	类型	含义	是否必选
//    email	string	电子邮箱(务必邮箱格式)	是
//    code	string	验证码(来自于接口4)	是
//    newPass	string	新密码	是
//    请求方法:POST
//
//    请求地址: http://域名/user/forget
//
//    {
//        "status": 1,
//            "errmsg": "OK!",
//            "data": "修改密码成功"
//    }
//6.获取用户信息
//
//    参数名	类型	含义	是否必选
//    Authorization	Header	验证token	是
//    请求方法:GET
//
//    请求地址: http://域名/user/getInfo
//
//    {
//        "status": 1,
//            "errmsg": "OK!",
//            "data": {
//        "info": {
//            "id": 2,
//                    "phone": "15555555555",
//                    "email": "245357001@qq.com",
//                    "name": "Dawnki",
//                    "identity": 0,   //0普通用户  1计维人员 2管理员
//                    "last_login": "2017-08-14 14:44:47",
//                    "created_at": "2017-08-14 14:23:36",
//                    "updated_at": "2017-08-14 14:44:47"
//        }
//    }
//    }
//7.修改用户资料
//
//    参数名	类型	含义	是否必选
//    Authorization	Header	验证token	是
//    phone	string	手机号码	否
//    password	string	密码最少8位	否
//    name	string	用户昵称	否
//    请求方法:POST
//
//    请求地址: http://域名/user/modify
//
//    {
//        "status": 1,
//            "errmsg": "OK!",
//            "data": "修改成功!"
//    }
//    错误码
//
//    错误码	内容
//1000	参数列表错误
//1500	获取验证码失败
//1501	验证码错误
//1502	邮箱已被注册
//1503	账号不存在
//1504	登录异常,请重新登录
//1505	账号或密码错误
//1506	Token不正确或者已过期
//1507	账户权限不足
}
