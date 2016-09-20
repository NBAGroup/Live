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
import com.yp.paparazzilive.adapters.classify.LiveAdapter;
import com.yp.paparazzilive.event.GameName;
import com.yp.paparazzilive.model.classify.GameInfoList;
import com.yp.paparazzilive.model.classify.LiveContent;
import com.yp.paparazzilive.ui.fragments.BaseFragment;
import com.yp.paparazzilive.web.web;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by yp on 2016/9/20.
 *
 */
public class LiveFragment extends BaseFragment {

    public static final String TAG=LiveFragment.class.getSimpleName();
    private String name;
    private TextView mName;
    private GridView mGridView;
    private LiveAdapter adapter;

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
        adapter = new LiveAdapter(getActivity(),null);
        mGridView.setAdapter(adapter);

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
        RequestParams requestParams=new RequestParams(web.CLASSIFY_LIVE+name);

        x.http().get(requestParams, new Callback.CommonCallback<LiveContent>() {

            @Override
            public void onSuccess(LiveContent result) {
                Log.e(TAG, "onSuccess: ");
                GameInfoList commentators = result.getCommentators();
                Log.e(TAG, "onSuccess: "+commentators.getGameinfo().get(0).getTitle());
                adapter.addRes(commentators.getGameinfo());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: " );
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: ");
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: " );
            }
        });

    }
}
