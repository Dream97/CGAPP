package com.cgapp.fragment;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.cgapp.R;
import com.cgapp.Util.CompressUtil;
import com.cgapp.adapter.ImgGridAdapter;
import com.cgapp.callback.FreshImgCallBack;
import com.yanzhenjie.album.Album;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by asus on 2017/3/26.
 */

public class RepairFragment extends Fragment implements FreshImgCallBack{

    private static final int REQUEST_CODE_GALLERY = 100;//打开相册
    private static final int REQUEST_CODE_PREVIEW = 101; //预览图片
    private GridView gvImage;
    private ImgGridAdapter adapter;
    private ArrayList<String> imgList = new ArrayList<>();
    private final static int maxImgSize = 3;

    private ImageView personImage;
    private ImageButton pushPicture;
    static RepairFragment repairFragment ;
    public static RepairFragment getInstance()
    {
        if(repairFragment == null)
        {
            repairFragment = new RepairFragment();
            return  repairFragment;
        }else {
            return repairFragment;
        }
    }
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_repair,container,false);

        //压缩图片
        personImage = (ImageView) view.findViewById(R.id.fragment_repair_person);
        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res,R.drawable.repair_person);//转bitmap
       // Bitmap bitmap1 = CompressUtil.getZoomImage(bitmap,80);
        Bitmap bitmap1 = CompressUtil.getZoomImage(bitmap,200,200);
        //personImage.setImageBitmap(CompressUtil.compressImage(bitmap));
        //personImage.setImageResource(R.drawable.repair_person);
        personImage.setImageBitmap(bitmap1);

//        //上传图片
//        pushPicture = (ImageButton) view.findViewById(R.id.push_picture);
//        pushPicture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(),"上传图片",Toast.LENGTH_LONG).show();
//            }
//        });

        gvImage = (GridView) view.findViewById(R.id.gvImage);
        adapter = new ImgGridAdapter(getActivity(),imgList,maxImgSize,this);
        gvImage.setAdapter(adapter);

        return view;
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent intent)
    {
        super.onActivityResult(requestCode,resultCode,intent);
        switch(requestCode){
            case REQUEST_CODE_GALLERY:
                if(resultCode == RESULT_OK){
                    imgList.clear();//
                    for(int i = 0; i<= Album.parseResult(intent).size()-1; i++){
                        imgList.add(Album.parseResult(intent).get(i));
                    }
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }


    @Override
    public void previewImg(int position) {
        Album.gallery(this)//预览图片
                .requestCode(REQUEST_CODE_PREVIEW)
                .toolBarColor(ContextCompat.getColor(getActivity(),R.color.colorPrimary))
                .statusBarColor(ContextCompat.getColor(getActivity(),R.color.colorPrimaryDark))
                .currentPosition(position)
                .checkFunction(false)
                .start();
    }

    /**
     * 更新图片，当前用于删除
     * @param position
     */
    @Override
    public void updateGvImgShow(int position) {
        imgList.remove(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void openGallery() {
        Album.album(this)//打开相册
                .requestCode(REQUEST_CODE_GALLERY)
                .toolBarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary))
                .statusBarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark))
                .selectCount(maxImgSize)
                .columnCount(3)
                .camera(true)
                .start();
    }


}
