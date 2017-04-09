package com.cgapp.Util;

import android.content.Context;
import android.os.Looper;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/**
 * Created by D&LL on 2016/9/20.
 * 关于post的一些方法类
 */
public class PostUtil {
    private static PostUtil instance = null;
    private String data;
    public HttpClient httpClient = getThreadSafeClient();
    public String result;
    private int isOk = 0;

    //工具类的实例化
    public static PostUtil getInstance() {
        if (instance == null) {
            instance = new PostUtil();
        }
        return instance;
    }
    //=增加线程安全
    public static DefaultHttpClient getThreadSafeClient() {

        DefaultHttpClient client = new DefaultHttpClient();
        ClientConnectionManager mgr = client.getConnectionManager();
        HttpParams params = client.getParams();

        client = new DefaultHttpClient(
                new ThreadSafeClientConnManager(params,
                        mgr.getSchemeRegistry()), params);

        return client;
    }
    /**
     * 没有返回值的请求
     *
     * @param context
     * @param url
     * @param maps    参数
     */
    public int postNoResult(final Context context, final String url, final Map<String, String> maps) {
        class MyThread extends Thread {
            @Override
            public void run() {
                try {
                    HttpPost post = new HttpPost(url);
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    for (String key : maps.keySet()) {
                        params.add(new BasicNameValuePair(key, maps.get(key)));
                    }
                    post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    HttpResponse response = httpClient.execute(post);
                    if (response.getStatusLine().getStatusCode() == 200) {
                        Looper.prepare();
                        String r = EntityUtils.toString(response.getEntity());
                        //ToastUtil.print_log(r);
                        JSONObject result = new JSONObject(r);
                        int status = result.getInt("status");
                        if (status == 0) {
                            int errcode = result.getInt("errcode");
                            isOk = errcode;
                        } else {
                            isOk = 1;
                        }

                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
        MyThread thread = new MyThread();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isOk;
    }

    /**
     * 有返回值的请求
     *
     * @param context
     * @param url
     * @param maps
     * @return
     */
    public String postResult(final Context context, final String url, final Map<String, String> maps) {
        FutureTask<String> task = new FutureTask<String>(
                new Callable<String>() {
                    @Override
                    public String call() {
                        try {
                            HttpPost post = new HttpPost(url);
                            List<NameValuePair> params = new ArrayList<NameValuePair>();
                            for (String key : maps.keySet()) {
                                params.add(new BasicNameValuePair(key, maps.get(key)));
                            }
                            post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                            HttpResponse response = httpClient.execute(post);
                            if (response.getStatusLine().getStatusCode() == 200) {
                                Looper.prepare();
                                result = EntityUtils.toString(response.getEntity());
                                //ToastUtil.print_log(result);
                                return result;
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (ClientProtocolException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                });
        new Thread(task).start();//处理获得返回值
        try {
            task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

}
