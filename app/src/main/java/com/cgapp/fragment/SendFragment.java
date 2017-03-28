package com.cgapp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cgapp.R;
import com.cgapp.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/3/26.
 */

public class SendFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter recyclerAdapter;

    public static SendFragment getInstance()
    {
        return new SendFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_send,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.send_recycler);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(this,initData());
        //设置adapter
        recyclerView.setAdapter(recyclerAdapter);
        return view;
    }

    private List<String> initData()
    {
        List<String> list = new ArrayList<>();
        for (int i = 0;i<50;i++)
        {
            list.add("这是推文"+i);
        }
        return list;
    }
}
