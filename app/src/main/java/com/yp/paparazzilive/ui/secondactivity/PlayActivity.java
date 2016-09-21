package com.yp.paparazzilive.ui.secondactivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.yp.paparazzilive.R;
import com.yp.paparazzilive.adapters.VideoViewApdapter;
import com.yp.paparazzilive.ui.fragments.SecondFragment.FengSiBangFragment;
import com.yp.paparazzilive.ui.fragments.SecondFragment.HongYejiFragment;
import com.yp.paparazzilive.ui.fragments.SecondFragment.HuDongFragment;
import com.yp.paparazzilive.ui.fragments.SecondFragment.SevenBangFragment;
import com.yp.paparazzilive.utils.LightController;
import com.yp.paparazzilive.utils.ToastUtil;
import com.yp.paparazzilive.utils.VolumController;

import java.util.ArrayList;
import java.util.List;

import io.vov.vitamio.widget.VideoView;

public class PlayActivity extends AppCompatActivity implements io.vov.vitamio.MediaPlayer.OnErrorListener, View.OnTouchListener, GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private VideoView mVideoview;
    private int oldHeight;
    private int oldWidth;
    private int threshold = 3;
    private int heightPixels;
    private int widthPixels;
    private List<Fragment> data;
    private VideoViewApdapter videoViewApdapter;
    private TabLayout mTabName;
    private ViewPager mViewpager;
    private String playurl;
    private String name;
    private ImageView imageLoad;
    private GestureDetector mGestureDector;
    private boolean isLandscape;
    private boolean isShowBig;
    private boolean isShowSmall;
    private CheckBox mFullscreen;
    private ImageView mPlaymore;
    private CheckBox mPlayOrPause;
    private ImageView mBack;
    private TextView mPlayTime;
    private TextView mPlayName;
    private LinearLayout playInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initView();
    }

    private void initView() {

        Intent intent = getIntent();
        playurl = intent.getStringExtra("PLAYURL");
        name = intent.getStringExtra("NAME");


        mVideoview = (VideoView) findViewById(R.id.paly_videoview);
        mTabName = (TabLayout) findViewById(R.id.play_tab_name);
        playInfo = (LinearLayout) findViewById(R.id.play_linear_playinfo);
        mViewpager = (ViewPager) findViewById(R.id.play_vp_viewer);
        imageLoad = (ImageView) findViewById(R.id.play_image_load);
        mFullscreen = (CheckBox) findViewById(R.id.play_image_fullscreen);
        mBack = (ImageView) findViewById(R.id.play_detail_back);
        mPlaymore = (ImageView) findViewById(R.id.play_image_more);
        mPlayOrPause = (CheckBox) findViewById(R.id.play_cb_pause);
        mPlayTime = (TextView) findViewById(R.id.paly_time);
        mPlayName = (TextView) findViewById(R.id.paly_name);

        mBack.setOnClickListener(this);
        mPlaymore.setOnClickListener(this);
        mFullscreen.setOnCheckedChangeListener(this);
        mPlayOrPause.setOnCheckedChangeListener(this);

        //获取屏幕宽高
        heightPixels = getResources().getDisplayMetrics().heightPixels;
        widthPixels = getResources().getDisplayMetrics().widthPixels;

        oldHeight = mVideoview.getHeight();
        oldWidth = mVideoview.getWidth();

        videoViewApdapter = new VideoViewApdapter(getSupportFragmentManager(), getData());
        mViewpager.setAdapter(videoViewApdapter);
        mTabName.setupWithViewPager(mViewpager);
        playByStatus();

        mVideoview.setOnTouchListener(this);
        mGestureDector = new GestureDetector(this, this);
        mGestureDector.setOnDoubleTapListener(this);

    }

    private void playByStatus() {
        if (!mVideoview.isPlaying()) {
            imageLoad.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(PlayActivity.this, R.anim.play_loading);
            imageLoad.startAnimation(animation);
            playfunction(playurl);
        } else {
            imageLoad.setVisibility(View.VISIBLE);
        }
    }


    public void playfunction(String path) {

        mVideoview.setVideoPath(path);
        //焦点放在mVideoview控件上
        mVideoview.requestFocus();
        oldHeight = mVideoview.getHeight();
        oldWidth = mVideoview.getWidth();
        mVideoview.setOnPreparedListener(new io.vov.vitamio.MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(io.vov.vitamio.MediaPlayer mediaPlayer) {

                mVideoview.start();
                mediaPlayer.setPlaybackSpeed(1.0f);
                imageLoad.clearAnimation();
                imageLoad.setVisibility(View.GONE);
            }
        });
    }


    public List<Fragment> getData() {

        List<Fragment> fragments = new ArrayList<>();
        HuDongFragment huDongFragment = new HuDongFragment();
        HongYejiFragment hongYejiFragment = new HongYejiFragment();
        SevenBangFragment sevenBangFragment = new SevenBangFragment();
        FengSiBangFragment fengSiBangFragment = new FengSiBangFragment();

        fragments.add(huDongFragment);
        fragments.add(hongYejiFragment);
        fragments.add(sevenBangFragment);
        fragments.add(fengSiBangFragment);
        return fragments;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.play_image_fullscreen:
                if (isChecked) {
                    //变为横屏
                    changeFullScreen();
                }else {
                    //变为竖屏
                    changeSmallScreen();
                }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // 竖屏
            isLandscape = false;
            mFullscreen.setChecked(false);
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // 横屏
            isLandscape = true;
            mFullscreen.setChecked(true);
        }
        super.onConfigurationChanged(newConfig);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mVideoview.stopPlayback();
    }

    @Override
    public boolean onError(io.vov.vitamio.MediaPlayer mp, int what, int extra) {
        mVideoview.stopPlayback();
        return false;
    }

    //对VideoView的触摸监听，实现手势控制
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mGestureDector.onTouchEvent(event);
        return true;
    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        if (isLandscape) {
            //横屏
            //显示或者隐藏大屏的控制
            showOrHideBigControl();
        } else {
            //竖屏
            //显示或者隐藏小屏的控制
            showOrHideSmallControl();
        }
        return false;
    }

    //显示或者隐藏小屏的控制
    private void showOrHideSmallControl() {
        if (isShowSmall) {
            mFullscreen.setVisibility(View.GONE);
            mBack.setVisibility(View.GONE);
            mPlayOrPause.setVisibility(View.GONE);
            mPlayName.setVisibility(View.GONE);
            mPlayTime.setVisibility(View.GONE);
            mPlaymore.setVisibility(View.GONE);
        } else {
            mFullscreen.setVisibility(View.VISIBLE);
            mBack.setVisibility(View.VISIBLE);
            mPlayOrPause.setVisibility(View.VISIBLE);
            mPlayName.setVisibility(View.VISIBLE);
            mPlayTime.setVisibility(View.VISIBLE);
            mPlaymore.setVisibility(View.VISIBLE);
        }
        isShowSmall = !isShowSmall;
    }

    //显示或者隐藏大屏的控制
    private void showOrHideBigControl() {
        if (!isShowBig) {
            //隐藏状态栏
            mVideoview.setSystemUiVisibility(View.INVISIBLE);
        } else {
            //显示状态栏
            mVideoview.setSystemUiVisibility(View.VISIBLE);
            mFullscreen.setVisibility(View.VISIBLE);
            mBack.setVisibility(View.VISIBLE);

            mPlayOrPause.setVisibility(View.VISIBLE);
            mPlayName.setVisibility(View.VISIBLE);
            mPlayTime.setVisibility(View.VISIBLE);
            mPlaymore.setVisibility(View.VISIBLE);
        }
        isShowBig = !isShowBig;

    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {

        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (isLandscape) {
            float x = e2.getX();
            float y = e2.getY();
            if (Math.abs(distanceY) > threshold) {
                if (x > (heightPixels / 2 + 0.2 * heightPixels)) {  //音量改变
                    if (distanceY > 0) {
                        //音量加
                        VolumController.volumUp(this, distanceY, widthPixels);

                    } else {
                        //音量减
                        VolumController.volumDown(this, distanceY, widthPixels);
                    }
                } else if (x < (heightPixels / 2 - 0.2 * heightPixels)) {
                    if (distanceY > 0) {
                        //亮度加
                        LightController.lightUp(this, distanceY, widthPixels);
                    } else {
                        //亮度减
                        LightController.lightDown(this, distanceY, widthPixels);
                    }
                }
            }
        }

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    /**
     * 全屏
     */
    private void changeFullScreen() {
        //切换全屏
        oldHeight = mVideoview.getHeight();
        //添加全屏标记
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 旋转屏幕 横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        ViewGroup.LayoutParams layoutParams = mVideoview.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        mVideoview.setLayoutParams(layoutParams);
    }

    /**
     * 全屏变为竖屏
     */
    private void changeSmallScreen() {
        //旋转回来
        //清除全屏标记
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置高度
        ViewGroup.LayoutParams params = mVideoview.getLayoutParams();
        params.height = oldHeight;
        mVideoview.setLayoutParams(params);
    }

    //显示声音  亮度  自定义
    public void showToast(String text, boolean flag) {
        ToastUtil.makeToast(text, flag, this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isLandscape) {
                finish();
            } else {
                //全屏变为竖屏
              changeSmallScreen();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
