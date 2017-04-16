package com.cgapp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cgapp.R;

/**
 * Created by asus on 2017/4/16.
 */

public class FinishedFragment extends Fragment {
    private static FinishedFragment instance;
    public static FinishedFragment getInstance()
    {

        if (instance == null)
        {
            instance = new FinishedFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_finished,container,false);
        return view;
    }


}
