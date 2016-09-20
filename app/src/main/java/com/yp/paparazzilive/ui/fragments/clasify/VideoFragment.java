package com.yp.paparazzilive.ui.fragments.clasify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yp.paparazzilive.R;
import com.yp.paparazzilive.ui.fragments.BaseFragment;

/**
 * Created by yp on 2016/9/20.
 */
public class VideoFragment extends BaseFragment {

    public static final String TAG=VideoFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        layout=inflater.inflate(R.layout.video_fragment,container,false);

        return layout;
    }
}
