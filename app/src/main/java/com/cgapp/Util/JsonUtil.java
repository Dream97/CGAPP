package com.cgapp.Util;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

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
}
