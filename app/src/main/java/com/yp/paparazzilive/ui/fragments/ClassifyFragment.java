package com.yp.paparazzilive.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yp.paparazzilive.R;
import com.yp.paparazzilive.adapters.classify.ClassifyFragmentAdapter;
import com.yp.paparazzilive.model.classify.Column;
import com.yp.paparazzilive.model.classify.ColumnList;
import com.yp.paparazzilive.web.web;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by yp on 2016/9/20.
 */
public class ClassifyFragment extends BaseFragment implements View.OnClickListener,ClassifyFragmentAdapter.OnItemClickListener {

    public static final String TAG=ClassifyFragment.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private ClassifyFragmentAdapter adapter;
    private ImageView mSearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        layout=inflater.inflate(R.layout.fragment_classify,container,false);

        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setupView();
    }

    private void initView() {
        mRecyclerView = ((RecyclerView) layout.findViewById(R.id.fragment_classify_recycler));
        GridLayoutManager layout = new GridLayoutManager(getActivity(),3);
        mRecyclerView.setLayoutManager(layout);
        adapter = new ClassifyFragmentAdapter(getActivity(),null);
        adapter.setListener(this);
        mRecyclerView.setAdapter(adapter);

        mSearch = ((ImageView) layout.findViewByPosition(R.id.fragment_classify_image_search));
        mSearch.setOnClickListener(this);
    }

    private void setupView() {

        RequestParams requestParams=new RequestParams(web.CLASSIFY_URL);
        x.http().get(requestParams, new Callback.CommonCallback<ColumnList>() {

            @Override
            public void onSuccess(ColumnList result) {
                List<Column> data_list = result.getData_list();
                Log.e(TAG, "onSuccess: "+data_list.get(0).getName());
                adapter.addRes(data_list);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: ");
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: ");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_classify_image_search:

                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        Log.e(TAG, "onItemClick: "+position );
    }
}
