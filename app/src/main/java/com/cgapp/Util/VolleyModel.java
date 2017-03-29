package com.cgapp.Util;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by asus on 2017/3/29.
 */

/**
 * 以后研究现在不用
 */
public class VolleyModel {


    private Context context;
    private RequestQueue requestQueue;
    public VolleyModel(Context context)
    {
        this.context = context;
        this.requestQueue = requestQueue;
        requestQueue = Volley.newRequestQueue(context);
    }

    public void getResponse(String url)
    {
        final String[] result = {new String()};
        StringRequest stringRequest = new StringRequest(
                url,
                new Response.Listener<String>(){
            @Override
            public void onResponse(String response)
            {
                result[0] = response;
                Log.d("TAG", response);
            }
        },
        new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        requestQueue.add(stringRequest);
    }
}
