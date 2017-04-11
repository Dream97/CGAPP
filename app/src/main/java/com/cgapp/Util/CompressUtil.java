package com.cgapp.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * 主要两种压缩方式，质量压缩和大小压缩
 * Created by asus on 2017/4/11.
 */

public class CompressUtil {


    /**质量压缩
     * 耗时大，效率低
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);//100表示，压缩后的数据存放在byteArrayOutputStream
        int options = 100;
        while(byteArrayOutputStream.toByteArray().length/1024>80&&options>0)//循环判断压缩后的图片是否大于100kb，大于继续压缩
        {
             byteArrayOutputStream.reset();//清空byteArrayOutputStream
            image.compress(Bitmap.CompressFormat.PNG,options,byteArrayOutputStream);//压缩options%
            options-=10;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());//把压缩后的数据存放到Byte...中
        Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream,null,null);//把byteArrayInpoutStream数据生成图片
        return bitmap;
    }

    /**
     * 根据尺寸压缩
     * @param bitmap
     * @param needWidth
     * @param needHeight
     * @return
     */
    public static Bitmap getZoomImage(Bitmap bitmap, double needWidth, double needHeight) {
        if(bitmap == null)
        {
            return null;
        }
        if(bitmap.isRecycled())
        {
            return null;
        }
        if(needHeight<=0||needWidth<=0)
        {
            return null;
        }
        //获取图片的宽和高
        float width = bitmap.getWidth();
        float height = bitmap.getHeight();
        //创建操作图片的matrix对象
        Matrix matrix = new Matrix();
        //计算宽高缩放率
        float scaleWidth = ((float)needWidth)/width;
        float scaleHeight = ((float)needHeight)/height;
        //缩放图片动作
        matrix.postScale(scaleWidth,scaleHeight);

        return Bitmap.createBitmap(bitmap,0,0,(int)width,(int)height,matrix,true);
    }

    /**
     * 由尺寸压缩封装=成一个按大小压缩的方法
     * @param bitmap
     * @param maxSize
     * @return
     */
    public static Bitmap getZoomImage(Bitmap bitmap,double maxSize)
    {
        if (bitmap==null){
            return null;
        }
        if(bitmap.isRecycled())
        {
            return null;
        }
        //单位：从Byte换算成KB
        double currentSize = bitmapToByteArray(bitmap,false).length/1024;
        //判断是否大于允许空间
        while(currentSize>maxSize)
        {
            //计算bitmap的大小是maxSize的多少倍
            double multiple = currentSize/maxSize;
            /**
             * 开始压缩：将宽和高度压缩到对应的平方根倍
             * 1.保持新的高度和宽度
             * 2.压缩后达到最大大小对应的新bitmap，显示效果最好
             */
             bitmap = getZoomImage(bitmap,bitmap.getWidth()/Math.sqrt(multiple),bitmap.getHeight()/Math.sqrt(multiple));
            currentSize = bitmapToByteArray(bitmap,false).length/1024;
        }
        return bitmap;
    }



    /**
     * 将bity转换成byte数组
     * @param bitmap
     * @param needRecycle
     * @return
     */
    private static byte[] bitmapToByteArray(Bitmap bitmap, boolean needRecycle) {
        if(null==bitmap)
        {
            return null;
        }
        if(bitmap.isRecycled())
        {
            return null;
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,output);//百分之一百保存到ouput中
        if(needRecycle){
            bitmap.recycle();
        }
        byte[] result =output.toByteArray();
        try{
            output.close();
        }catch(Exception e)
        {

        }
        return result;
    }

}
