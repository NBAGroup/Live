package com.yp.paparazzilive.ui.fragments.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yp.paparazzilive.R;
import com.yp.paparazzilive.adapters.mine.LiveRankAdapter;
import com.yp.paparazzilive.model.mine.LiveRankList;
import com.yp.paparazzilive.model.mine.RankModel;
import com.yp.paparazzilive.ui.fragments.BaseFragment;
import com.yp.paparazzilive.web.web;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;


/**
 * Created by yp on 2016/9/21.
 */
public class GameFragment extends BaseFragment {


    private static final String TAG = GameFragment.class.getSimpleName();
    private ImageView mImageOne;
    private ImageView mImageTwo;
    private ImageView mImageThree;
    private TextView mNameOne;
    private TextView mNameTwo;
    private TextView mNameThree;
    private TextView mMoodsOne;
    private TextView mMoodsTwo;
    private TextView mMoodsThree;
    private ListView mListView;
    private LiveRankAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        layout=inflater.inflate(R.layout.ranklist_game_fragment,container,false);

        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setupView();
    }

    private void initView() {

        mImageOne = ((ImageView) layout.findViewById(R.id.ranklist_game_fragment_image_one));
        mImageTwo = ((ImageView) layout.findViewById(R.id.ranklist_game_fragment_image_two));
        mImageThree = ((ImageView) layout.findViewById(R.id.ranklist_game_fragment_image_three));
        mNameOne = ((TextView) layout.findViewById(R.id.ranklist_game_fragment_name_one));
        mNameTwo = ((TextView) layout.findViewById(R.id.ranklist_game_fragment_name_two));
        mNameThree = ((TextView) layout.findViewById(R.id.ranklist_game_fragment_name_three));
        mMoodsOne = ((TextView) layout.findViewById(R.id.ranklist_game_fragment_moods_one));
        mMoodsTwo = ((TextView) layout.findViewById(R.id.ranklist_game_fragment_moods_two));
        mMoodsThree = ((TextView) layout.findViewById(R.id.ranklist_game_fragment_moods_three));

        mListView = ((ListView) layout.findViewById(R.id.ranklist_game_fragment_listview));
        adapter = new LiveRankAdapter(getActivity(),null);
        mListView.setAdapter(adapter);
        mListView.setDivider(null);

    }

    private void setupView() {

        RequestParams requestParams=new RequestParams(web.GAME_LIVE);

        x.http().get(requestParams, new Callback.CommonCallback<RankModel>() {
            public ImageOptions options;

            @Override
            public void onSuccess(RankModel result) {
                Log.e(TAG, "onSuccess: " );
                options=new ImageOptions.Builder()
                        .setCircular(true)
                        .build();
                LiveRankList commentators = result.getCommentators();
                mNameOne.setText(commentators.getGameinfo().get(0).getName());
                mNameTwo.setText(commentators.getGameinfo().get(1).getName());
                mNameThree.setText(commentators.getGameinfo().get(2).getName());
                mMoodsOne.setText("人气"+commentators.getGameinfo().get(0).getRenqi());
                mMoodsTwo.setText("人气"+commentators.getGameinfo().get(1).getRenqi());
                mMoodsThree.setText("人气"+commentators.getGameinfo().get(2).getRenqi());
                x.image().bind(mImageOne,commentators.getGameinfo().get(0).getRawcommentatorimage(),options);
                x.image().bind(mImageTwo,commentators.getGameinfo().get(1).getRawcommentatorimage(),options);
                x.image().bind(mImageThree,commentators.getGameinfo().get(2).getRawcommentatorimage(),options);

                adapter.addRes(commentators.getGameinfo());
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
