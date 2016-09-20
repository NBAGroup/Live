package com.yp.paparazzilive.adapters.classify;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yp on 2016/9/20.
 */
public class ClassifyActivityAdapter extends FragmentPagerAdapter {

    private List<Fragment> data;

    public ClassifyActivityAdapter(FragmentManager fm,List<Fragment> data) {
        super(fm);
        this.data=data;
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
