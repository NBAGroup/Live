package com.yp.paparazzilive.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yp.paparazzilive.R;

/**
 * Created by yp on 2016/9/20.
 */
public class MineFragmetn extends BaseFragment {

    public static final String TAG=MineFragmetn.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        layout=inflater.inflate(R.layout.fragment_mine,container,false);

        return layout;
    }
}
