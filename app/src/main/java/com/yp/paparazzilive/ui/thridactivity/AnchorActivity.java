package com.yp.paparazzilive.ui.thridactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.yp.paparazzilive.R;
import com.yp.paparazzilive.adapters.mine.AnchorActivityAdapter;
import com.yp.paparazzilive.model.mine.AnchorContentList;
import com.yp.paparazzilive.model.mine.AnchorModel;
import com.yp.paparazzilive.web.web;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class AnchorActivity extends AppCompatActivity {

    private static final String TAG = AnchorActivity.class.getSimpleName();
    private ListView mListView;
    private AnchorActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anchor);
        initView();
        setupView();
    }

    private void initView() {

        mListView = (ListView) findViewById(R.id.anchor_activity_lv);
        adapter = new AnchorActivityAdapter(this,null);
        mListView.setAdapter(adapter);

    }


    private void setupView() {

        RequestParams requestParams=new RequestParams(web.ANCHOR_URL);
        x.http().get(requestParams, new Callback.CommonCallback<AnchorModel>() {
            @Override
            public void onSuccess(AnchorModel result) {
                Log.e(TAG, "onSuccess: ");
                AnchorContentList videos = result.getVideos();
                adapter.addRes(videos.getGameinfo());
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
