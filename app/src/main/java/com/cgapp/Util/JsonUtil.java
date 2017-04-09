package com.cgapp.Util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by asus on 2017/4/8.
 */

public class JsonUtil {
    public  static int getIntCode(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        int status = jsonObject.getInt("status");
        if(jsonObject.has("errcode"))
        {
            return jsonObject.getInt("errcode");
        }
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

}
