package com.yp.paparazzilive.ui.fragments.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yp.paparazzilive.R;
import com.yp.paparazzilive.ui.fragments.BaseFragment;

/**
 * Created by yp on 2016/9/21.
 */
public class AttentionFragment extends BaseFragment {

    public static final String TAG=AttentionFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        layout=inflater.inflate(R.layout.attention_fragment,container,false);

        return layout;
    }
}
