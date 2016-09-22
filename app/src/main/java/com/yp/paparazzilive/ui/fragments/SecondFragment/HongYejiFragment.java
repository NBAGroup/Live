package com.yp.paparazzilive.ui.fragments.SecondFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.yp.paparazzilive.R;
import com.yp.paparazzilive.adapters.fragment_play_adapter.FragmentPlay2Adapter;
import com.yp.paparazzilive.model.fragmentplay.PLay1Model;
import com.yp.paparazzilive.ui.fragments.BaseFragment;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by hedianbo on 2016/9/21.
 */
public class HongYejiFragment extends BaseFragment {

    private ListView mListview;
    private FragmentPlay2Adapter fragmentPlay2Adapter;
    private String TAG=HongYejiFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        layout = inflater.inflate(R.layout.fragment_play2, container, false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {

        mListview = (ListView) layout.findViewById(R.id.fragment_play2);
        mListview.setDivider(null);
        fragmentPlay2Adapter = new FragmentPlay2Adapter(getContext(),null,R.layout.item_play2);
        mListview.setAdapter(fragmentPlay2Adapter);

        RequestParams params = new RequestParams("http://live.bilibili.com/AppRoom/opTop?_device=android&_hwid=02f07a17baea3af1&appkey=1d8b6e7d45233436&build=426003&mobi_app=android&platform=android&room_id=101526&scale=xhdpi&type=redleaf&sign=9cce4a1615c98ee8ff2b8de6bd27c854");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: "+result );
                Gson gson = new Gson();
                PLay1Model pLay1Model = gson.fromJson(result, PLay1Model.class);

                List<PLay1Model.DataBean.ListBean> list = pLay1Model.getData().getList();
                Log.e(TAG, "onSuccess: "+list.get(0).getScorename());
                fragmentPlay2Adapter.addRes(list);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
