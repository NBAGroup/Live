package com.yp.paparazzilive.ui.fragments.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.yp.paparazzilive.R;
import com.yp.paparazzilive.adapters.mine.AllGamePopAdapter;
import com.yp.paparazzilive.adapters.mine.GameAdapter;
import com.yp.paparazzilive.model.mine.GameTitle;
import com.yp.paparazzilive.model.mine.GameTitleList;
import com.yp.paparazzilive.ui.fragments.BaseFragment;
import com.yp.paparazzilive.web.web;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yp on 2016/9/21.
 */
public class RankListFragment extends BaseFragment implements View.OnClickListener {

    public static final String TAG=RankListFragment.class.getSimpleName();
    private TabLayout mTabLayout;
    private List<String> gameTitle;
    private ViewPager mViewPager;
    private ImageView mAllGame;

    private PopupWindow popupWindow;
    private ImageView mBack;
    private GridView mTopGridView;
    private GridView mBottomGridView;
    private AllGamePopAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        layout=inflater.inflate(R.layout.ranklist_fragment,container,false);

        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setupView();
    }

    private void initView() {

        mAllGame = ((ImageView) layout.findViewById(R.id.ranklist_fragment_allgame));
        mAllGame.setOnClickListener(this);

        mTabLayout = ((TabLayout) layout.findViewById(R.id.ranklist_fragment_tablayout));
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        gameTitle=new ArrayList<>();

        mTabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FE7053"));

        mViewPager = ((ViewPager) layout.findViewById(R.id.ranklist_fragment_viewpager));

        mViewPager.setOffscreenPageLimit(3);


    }

    private void setupView() {

        RequestParams requestParams=new RequestParams(web.GAMETITLE);

        x.http().get(requestParams, new Callback.CommonCallback<GameTitleList>() {
            private GameAdapter adapter;

            @Override
            public void onSuccess(GameTitleList result) {
                Log.e(TAG, "onSuccess: " );
                List<GameTitle> all_game = result.getAll_game();
                for (int i = 0; i < all_game.size(); i++) {
                    gameTitle.add(all_game.get(i).getName());
                }
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

                List<Fragment> data=new ArrayList<>();

                for (int i = 0; i < gameTitle.size(); i++) {
                    data.add(new GameFragment());
                }
                adapter = new GameAdapter(getChildFragmentManager(),data,gameTitle);
                mViewPager.setAdapter(adapter);
                mTabLayout.setupWithViewPager(mViewPager);
            }
        });


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ranklist_fragment_allgame:

                Log.e(TAG, "onClick: "+6666);

                View allGamePop = LayoutInflater.from(getActivity()).inflate(R.layout.allgame_pop, null);

                mBottomGridView = ((GridView) allGamePop.findViewById(R.id.allgame_pop_gridview_bottom));

                mBack = ((ImageView) allGamePop.findViewById(R.id.allgame_pop_gridview_back));
                mBack.setOnClickListener(this);

                adapter = new AllGamePopAdapter(getActivity(),null);
                mBottomGridView.setAdapter(adapter);

                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                float density = getResources().getDisplayMetrics().density;
                float v1 = 50 * density + 0.5f;
                Log.e(TAG, "onClick: "+v1 );
                int widthPixels = displayMetrics.widthPixels;
                int heightPixels = displayMetrics.heightPixels;
                Log.d(TAG, "onClick: "+widthPixels);
                Log.d(TAG, "onClick: "+heightPixels);
                if (popupWindow==null) {
                    this.popupWindow=new PopupWindow(allGamePop);
                    popupWindow.setWidth(widthPixels);
                    popupWindow.setHeight((int) (heightPixels-135));
//                    popupWindow.setAnimationStyle(R.style.pop_animation);
                }

                if (popupWindow.isShowing()){
                    popupWindow.dismiss();
                    Log.d(TAG, "onClick: ");
                }else {
                    popupWindow.showAtLocation(mTabLayout,Gravity.NO_GRAVITY,0,130);
                    Log.d(TAG, "onClick: show");
                }

                allGameView();

                break;
            case R.id.allgame_pop_gridview_back:
                popupWindow.dismiss();
                break;


        }
    }

    private void allGameView() {

        RequestParams requestParams=new RequestParams(web.GAMETITLE);

        x.http().get(requestParams, new Callback.CommonCallback<GameTitleList>() {
            @Override
            public void onSuccess(GameTitleList result) {
                Log.e(TAG, "onSuccess: " );
                List<GameTitle> all_game = result.getAll_game();
                adapter.addRes(all_game);

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
