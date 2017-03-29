package com.cgapp.Util;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by asus on 2017/3/29.
 */

public class GetHttpUtil {
/**
 * 使用HttpURLConnection
 */
//    public static void getHttpRequest(final String address,final HttpCallbackListener listener)
//    {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                HttpURLConnection connection = null;
//                try{
//                    URL url = new URL(address);
//                    connection = (HttpURLConnection) url.openConnection();
//                    connection.setRequestMethod("GET");
//                    connection.setConnectTimeout(8000);
//                    connection.setReadTimeout(8000);
//                    connection.setDoInput(true);
//                    connection.setDoOutput(true);
//                    InputStream in = connection.getInputStream();
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
//                    StringBuilder response = new StringBuilder();
//                    String line;
//                    while ((line= bufferedReader.readLine())!=null)
//                    {
//                        response.append(line);
//                    }
//                    if (listener!=null)
//                    {
//                        listener.onFinish(response.toString());
//                    }
//                }catch (Exception e)
//                {
//                    if(listener!=null)
//                    {
//                        listener.onError(e);
//                    }
//                }finally {
//                    if (connection!=null){
//                        connection.disconnect();
//                    }
//                }
//            }
//        }).start();
//    }

    /**
     * 使用HttpClient
     */
    public static void getHttpRequest(final String address, final HttpCallbackListener listener )
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet(address);
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    if(httpResponse.getStatusLine().getStatusCode()==200)
                    {
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity,"utf-8");
                        if(listener!=null)
                        {
                            listener.onFinish(response);
                        }
                    }
                }catch (Exception e)
                {
                    if(listener!=null)
                    listener.onError(e);
                }
            }
        }).start();
    }
}
