package com.cgapp.fragment;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cgapp.R;
import com.cgapp.Util.CompressUtil;

/**
 * Created by asus on 2017/3/26.
 */

public class RepairFragment extends Fragment {
    private ImageView personImage;
    public static RepairFragment getInstance()
    {
        return new RepairFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_repair,container,false);
        personImage = (ImageView) view.findViewById(R.id.fragment_repair_person);
        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res,R.drawable.repair_person);//转bitmap
       // Bitmap bitmap1 = CompressUtil.getZoomImage(bitmap,80);
        Bitmap bitmap1 = CompressUtil.getZoomImage(bitmap,200,200);
        //personImage.setImageBitmap(CompressUtil.compressImage(bitmap));
        //personImage.setImageResource(R.drawable.repair_person);
        personImage.setImageBitmap(bitmap1);
        return view;
    }
}
