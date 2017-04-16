package com.cgapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cgapp.R;

/**
 * Created by asus on 2017/3/26.
 */

public class HomeFramgent extends Fragment {
    private WebView webView ;
    private static HomeFramgent homeFramgent;
    public static HomeFramgent getInstance()
    {
        if(homeFramgent == null)
        {
            homeFramgent = new HomeFramgent();
        }
        return homeFramgent;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        webView = (WebView) view.findViewById(R.id.home_webview);
        webView.loadUrl("http://geek.csdn.net/");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        return view;
    }
}
