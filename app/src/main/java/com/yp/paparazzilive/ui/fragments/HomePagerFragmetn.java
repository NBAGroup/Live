package com.yp.paparazzilive.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;

import com.yp.paparazzilive.R;
import com.yp.paparazzilive.adapters.MyGridView;
import com.yp.paparazzilive.model.HomePagerModel.BigLive;
import com.yp.paparazzilive.model.HomePagerModel.Live1;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by yp on 2016/9/20.
 */
public class HomePagerFragmetn extends BaseFragment {

    public static final String TAG=HomePagerFragmetn.class.getSimpleName();
    private GridView mGridView;
    private MyGridView myGridViewadapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        layout=inflater.inflate(R.layout.fragment_homepager,container,false);

        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setUpView();
    }

    private void initView() {

        mGridView = (GridView) layout.findViewById(R.id.homepager_gridview);
        myGridViewadapter = new MyGridView(getContext(),null, R.layout.homepagerlive_item);
        mGridView.setAdapter(myGridViewadapter);
    }

    private void setUpView() {
        RequestParams params = new RequestParams();

        x.http().get(params, new Callback.CommonCallback<BigLive>() {
            @Override
            public void onSuccess(BigLive result) {
                Log.e(TAG, "onSuccess: "+result );
                List<Live1> data_list = result.getRec_zhubo().getData_list();
                myGridViewadapter.addRes(data_list);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: " );
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: " );
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: " );
            }
        });
    }
}
