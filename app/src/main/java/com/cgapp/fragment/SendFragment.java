package com.cgapp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cgapp.R;
import com.cgapp.adapter.RecyclerAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/3/26.
 */

public class SendFragment extends Fragment {
    private Gson gson = new Gson();
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter recyclerAdapter;
    private SwipeRefreshLayout refreshLayout;
    List<String> listTitles;

    public static SendFragment getInstance()
    {
        return new SendFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_send,container,false);

        initView();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                //中间进行操作
                refreshLayout.setRefreshing(false);//停止
            }
        });
        //设置adapter
        recyclerView.setAdapter(recyclerAdapter);


        return view;
    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.send_recycler);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(this,listTitles(),listImages());
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.send_swi);
        //设置下拉颜色
        refreshLayout.setColorSchemeColors(R.color.colorPrimary);
    }

    private List<String> listTitles()
    {
        listTitles = new ArrayList<>();
        for (int i = 0;i<50;i++)
        {
            listTitles.add("伟大的毛主席说，“向雷锋同志学习！”。纵使时光荏苒，雷锋精神在时间的打磨下亦日益生辉，并一代代的传承下去。\n"+i);
        }


//        try{
//            final JSONArray[] jsonArray = new JSONArray[0];
//        String address = Api.GUOKR_ARTICLES;
//        GetHttpUtil.getHttpRequest(address, new HttpCallbackListener() {
//            @Override
//            public void onFinish(String response){
//                try {
//                    jsonArray[0] =new JSONArray(response);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(Exception e) {
//
//            }
//        });
//            //JSONArray jsonArray1 = jsonArray[0];
//            for(int i = 0;i<jsonArray[0].length();i++)
//            {
//                JSONObject jsonObject = jsonArray[0].getJSONObject(i);
//                listTitles.add(jsonObject.getString("title"));
//            }
//
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }

        return listTitles;
    }
    private  List<String> listImages()
    {
        List<String> listImages = new ArrayList<>();
        for (int i = 0;i<listTitles.size();i++) {
            //listImages.add();
        }
        return listImages;
    }
}
