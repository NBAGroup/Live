package com.yp.paparazzilive.ui.fragments.clasify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.yp.paparazzilive.R;
import com.yp.paparazzilive.event.GameName;
import com.yp.paparazzilive.ui.fragments.BaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by yp on 2016/9/20.
 *
 */
public class LiveFragment extends BaseFragment {

    public static final String TAG=LiveFragment.class.getSimpleName();
    private String name;
    private TextView mName;
    private GridView mGridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        layout=inflater.inflate(R.layout.live_fragment,container,false);

        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {

        mName = ((TextView) layout.findViewById(R.id.live_fragment_title));
        mGridView = ((GridView) layout.findViewById(R.id.live_fragment_grideview));


    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEvent(GameName event){
        name = event.getName();
        Log.e(TAG, "onEvent: "+name );
        mName.setText(name);
        setupView();
    }

    private void setupView() {

    }
}
