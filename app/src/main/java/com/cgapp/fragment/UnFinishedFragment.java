package com.cgapp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import com.cgapp.R;

/**
 * Created by asus on 2017/4/16.
 */

public class UnFinishedFragment extends Fragment {
    private static UnFinishedFragment instance;
    public static UnFinishedFragment getInstance()
    {
        if(instance == null)
        {
            instance = new UnFinishedFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_unfinished,container,false);
        return view;
    }


}
