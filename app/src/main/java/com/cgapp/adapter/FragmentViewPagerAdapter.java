package com.cgapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cgapp.R;
import com.cgapp.fragment.HomeFramgent;
import com.cgapp.fragment.RepairFragment;
import com.cgapp.fragment.SendFragment;

/**
 * Created by asus on 2017/3/26.
 */

public class FragmentViewPagerAdapter extends FragmentPagerAdapter{
    private final int PAGE_COUNT = 3;
    private int[] tabimages =new int[]{
            R.drawable.tab_home_black,
            R.drawable.tab_push_black,
            R.drawable.tab_repair_black
    };

    private String[] titles ={"主页","推送","报修"};
    private Context context;

    public FragmentViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return HomeFramgent.getInstance();
            case 1:
                return SendFragment.getInstance();
            case 2:
                return RepairFragment.getInstance();
            default:return HomeFramgent.getInstance();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    /**
     * 返回tab
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position)
    {
        /**
         * 被我注释的是另一种设置图标方式
         */
//        Drawable image = context.getResources().getDrawable(tabimages[position]);
//        image.setBounds(0,0,image.getIntrinsicWidth(),image.getIntrinsicHeight());
//        SpannableString sb =  new SpannableString("  "+titles[position]);
//        ImageSpan imageSpan = new ImageSpan(image,ImageSpan.ALIGN_BASELINE);
//        sb.setSpan(imageSpan,0,1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return sb;
        return titles[position];
    }

    /**
     * 返回tab图标和文字
     * @param position
     * @return
     */
    public View getTabView(int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.tab_layout_view,null);
        TextView tv = (TextView) v.findViewById(R.id.textView);
        tv.setText(titles[position]);

        ImageView image = (ImageView) v.findViewById(R.id.imageView);
        image.setImageResource(tabimages[position]);
        return v;
    }
}
