package com.cgapp.Util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by asus on 2017/4/8.
 */

public class JsonUtil {
    public  static int getJsonDate(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        if(jsonObject.has("errcode"))
            return 0 ;
        else return 1;
    }
}
