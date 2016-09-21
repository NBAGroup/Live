package com.yp.paparazzilive.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yp.paparazzilive.R;
import com.yp.paparazzilive.adapters.MovieBigAdapter;
import com.yp.paparazzilive.adapters.MyGridView;
import com.yp.paparazzilive.model.BigModel;
import com.yp.paparazzilive.model.HomePagerModel.BigLive;
import com.yp.paparazzilive.model.HomePagerModel.Live1;
import com.yp.paparazzilive.model.HomePagerModel.MovieBig;
import com.yp.paparazzilive.ui.secondactivity.PlayActivity;
import com.yp.paparazzilive.view.HomePagerGridView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yp on 2016/9/20.
 */
public class HomePagerFragmetn extends BaseFragment implements AdapterView.OnItemClickListener {

    public static final String TAG=HomePagerFragmetn.class.getSimpleName();
    private HomePagerGridView mGridView;
    private MyGridView myGridViewadapter;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;

    private TextView name1;
    private TextView name2;
    private TextView name3;
    private TextView name4;

    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private MovieBigAdapter movieBigAdapter;

    private List<MovieBig.DataBean> data;
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
    }

    private void initView() {

        image1 = ((ImageView) layout.findViewById(R.id.home_header_image1));
        image2 = ((ImageView) layout.findViewById(R.id.home_header_image2));
        image3 = ((ImageView) layout.findViewById(R.id.home_header_image3));
        image4 = ((ImageView) layout.findViewById(R.id.home_header_image4));

        name1 = ((TextView) layout.findViewById(R.id.home_header_name1));
        name2 = ((TextView) layout.findViewById(R.id.home_header_name2));
        name3 = ((TextView) layout.findViewById(R.id.home_header_name3));
        name4 = ((TextView) layout.findViewById(R.id.home_header_name4));

        text1 = ((TextView) layout.findViewById(R.id.home_header_text1));
        text2 = ((TextView) layout.findViewById(R.id.home_header_text2));
        text3 = ((TextView) layout.findViewById(R.id.home_header_text3));
        text4 = ((TextView) layout.findViewById(R.id.home_header_text4));

        mGridView = (HomePagerGridView) layout.findViewById(R.id.homepager_gridview);

        movieBigAdapter = new MovieBigAdapter(getContext(),null,R.layout.homepagerlive_item);
        mGridView.setAdapter(movieBigAdapter);
        mGridView.setOnItemClickListener(this);


        RequestParams params = new RequestParams("http://live.bilibili.com/mobile/rooms?_device=android&_hwid=02f07a17baea3af1&appkey=1d8b6e7d45233436&area_id=1&build=426003&mobi_app=android&page=2&platform=android&sort=hottest&sign=416260a389ba4a44f2da760adf401f56");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: "+result );
                Gson gson=new Gson();
                Log.e(TAG, "onSuccessttt: " );
                MovieBig bigLive = gson.fromJson(result, MovieBig.class);
                Log.e(TAG, "onSuccess: uuu" );
                data = bigLive.getData();
                Log.e(TAG, "onSuccess: "+data.get(0).getTitle());
                movieBigAdapter.updateRes(data);
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

    public List<BigModel.RecZhuboBean.DataListBean> getData() {

        List<BigModel.RecZhuboBean.DataListBean> data=new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            BigModel.RecZhuboBean.DataListBean live1 = new BigModel.RecZhuboBean.DataListBean();
            live1.setName("我的直播"+i);
            live1.setViewers(99999999);
            live1.setTitle("英雄联盟"+i);
            live1.setCommentator("搞起啊"+i);
            data.add(live1);
        }
        return data;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e(TAG, "onItemClick: "+position );
        Intent intent = new Intent(getContext(), PlayActivity.class);
        intent.putExtra("PLAYURL",data.get(position).getPlayurl());
        intent.putExtra("NAME",data.get(position).getTitle());
        startActivity(intent);
    }
}
