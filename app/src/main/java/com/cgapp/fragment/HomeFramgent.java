package com.cgapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cgapp.R;

/**
 * Created by asus on 2017/3/26.
 */

public class HomeFramgent extends Fragment {
    public static HomeFramgent getInstance()
    {
        return new HomeFramgent();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        return view;
    }
}
