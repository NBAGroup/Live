package com.yp.paparazzilive.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yp.paparazzilive.R;
import com.yp.paparazzilive.adapters.MyGridView;
import com.yp.paparazzilive.model.HomePagerModel.BigLive;
import com.yp.paparazzilive.model.HomePagerModel.Live1;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yp on 2016/9/20.
 */
public class HomePagerFragmetn extends BaseFragment {

    public static final String TAG=HomePagerFragmetn.class.getSimpleName();
    private GridView mGridView;
    private MyGridView myGridViewadapter;
    private List<Live1> data;
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

        mGridView = (GridView) layout.findViewById(R.id.homepager_gridview);
        myGridViewadapter = new MyGridView(getContext(), getData(), R.layout.homepagerlive_item);
        mGridView.setAdapter(myGridViewadapter);

    }

    private void setUpView() {
        RequestParams params = new RequestParams("http://zhibo.sogou.com/getMyConcernAndRecData?appid=4bd846d2589853514566976c6767950c371cd016&type=json");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: "+result );
                Gson gson=new Gson();
                Log.e(TAG, "onSuccessttt: " );
                BigLive bigLive = gson.fromJson(result, BigLive.class);
                Log.e(TAG, "onSuccess: uuu" );
                List<Live1> data_list = bigLive.getRec_zhubo().getData_list();
                Log.e(TAG, "onSuccess: "+data_list.get(0).getName());
                myGridViewadapter.updateRes(data_list);
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

    public List<Live1> getData() {

        List<Live1> data=new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Live1 live1 = new Live1();
            live1.setName("hh"+i);
            live1.setViewers(99);
            live1.setTitle("hhdd"+i);
            live1.setCommentator("daa"+i);
            data.add(live1);
        }
        return data;
    }
}
