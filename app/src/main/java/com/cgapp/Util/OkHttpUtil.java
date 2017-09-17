package com.cgapp.Util;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by asus on 2017/4/8.
 */

public class OkHttpUtil {

    public static void post(String address, okhttp3.Callback callback, Map<String,String> map)
    {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        if (map!=null)
        {
            for (Map.Entry<String,String> entry:map.entrySet())
            {
                builder.add(entry.getKey(),entry.getValue());
            }
        }
        FormBody body = builder.build();
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }


    public static void logout(String address, okhttp3.Callback callback, Map<String,String> map)
    {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        if (map!=null)
        {
            for (Map.Entry<String,String> entry:map.entrySet())
            {
                builder.add(entry.getKey(),entry.getValue());
            }
        }
        FormBody body = builder.build();
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .addHeader("Authorization","Bearer "+CommonVari.token)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void get(String address, okhttp3.Callback callback,Map<String,String> map)
    {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        FormBody body = builder.build();
 //        String token = map.get("token");
        Request request = new Request.Builder()
                .url(address)
                .addHeader("Authorization","Bearer "+map.get("token"))
                .build();
        client.newCall(request).enqueue(callback);
    }
}
