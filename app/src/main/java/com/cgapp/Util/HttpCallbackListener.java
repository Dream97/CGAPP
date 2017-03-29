package com.cgapp.Util;

/**
 * Created by asus on 2017/3/29.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
