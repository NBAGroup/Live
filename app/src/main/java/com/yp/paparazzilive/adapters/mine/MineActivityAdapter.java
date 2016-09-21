package com.yp.paparazzilive.adapters.mine;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yp on 2016/9/21.
 */
public class MineActivityAdapter extends FragmentPagerAdapter {

    private List<Fragment> data;

    public MineActivityAdapter(FragmentManager fm,List<Fragment> data) {
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
