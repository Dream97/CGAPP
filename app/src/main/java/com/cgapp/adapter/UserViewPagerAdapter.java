package com.cgapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cgapp.fragment.FinishedFragment;
import com.cgapp.fragment.UnFinishedFragment;

/**
 * Created by asus on 2017/4/16.
 */

public class UserViewPagerAdapter extends FragmentPagerAdapter{
    private String[] titles = {"未完成","已完成"};
    public UserViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return UnFinishedFragment.getInstance();
            case 1:
                return FinishedFragment.getInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return titles[position];
    }
}
