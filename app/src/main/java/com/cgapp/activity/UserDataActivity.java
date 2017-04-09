package com.cgapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cgapp.R;

/**
 * Created by asus on 2017/3/28.
 */

public class UserDataActivity extends Activity {
    private String TAG ="hei";
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdata);
        Button button = (Button) findViewById(R.id.user_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String url = Api.url+"auth/getVerificationCode";
//                String key = "phone";
//                String value = "13416144624";
//                Map<String,String> map =  new HashMap<String, String>();
//                map.put(key,value);
//                OkHttpUtil.post(url, new okhttp3.Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        Log.e(TAG, "onFailure: ",e);
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        String responseData = response.body().string();
//                        try {
//                            String hei = JsonUtil.getJsonDate(responseData);
//                            //Toast.makeText(UserDataActivity.this,hei,Toast.LENGTH_SHORT).show();
//                            Log.d(TAG, "onSuccess: "+hei);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },map);
            }
        });
    }

}
