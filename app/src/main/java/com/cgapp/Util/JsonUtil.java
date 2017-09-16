package com.cgapp.Util;

import android.util.Log;

import com.cgapp.bean.UserBean;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

/**
 * Created by asus on 2017/4/8.
 */

public class JsonUtil {
    public  static int getIntCode(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        int status = jsonObject.getInt("status");
        return status;
    }


    public static String getToken(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.has("errcode"))
        {
            return null;
        } else
        {
            JSONObject data = jsonObject.getJSONObject("data");
            String token = data.getString("token");
            return token;
        }
    }


    public static String getData(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        Log.d("获取错误信息", "getData: ");
        if (jsonObject.has("errmsg"))
        {
            JSONObject data = jsonObject.getJSONObject("errmsg");
            String content = data.getString("content");
            return content;
        }
        return "";
    }

    public static void getInfo(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        Log.d("获取用户信息", "getData: ");
        if (jsonObject.has("data"))
        {
            JSONObject data = jsonObject.getJSONObject("data");
            JSONObject info = data.getJSONObject("info");
            UserBean.id = info.getString("id");
            Log.d(TAG, "getInfo: id是"+UserBean.id);
            UserBean.phone = info.getString("phone");
            UserBean.email = info.getString("email");
            UserBean.name = info.getString("name");
            UserBean.identity = info.getString("identity");
            UserBean.last_login = info.getString("last_login");
            UserBean.created_at = info.getString("created_at");
            UserBean.updated_at = info.getString("updated_at");
        }
    }
}
