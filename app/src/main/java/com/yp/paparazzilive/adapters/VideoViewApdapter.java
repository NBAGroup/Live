package com.yp.paparazzilive.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hedianbo on 2016/9/21.
 */
public class VideoViewApdapter extends FragmentPagerAdapter {

    private List<Fragment> data;
    String [] title;
    public VideoViewApdapter(FragmentManager fm,List<Fragment> data) {
        super(fm);
        if(data!=null){
            this.data=data;
        }else {
            this.data=new ArrayList<>();
        }
        title=new String []{"互动","红叶祭","七日榜","粉丝榜"};
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
