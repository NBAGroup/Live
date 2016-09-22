package com.yp.paparazzilive.ui.fouractivity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.SeekBar;
import android.widget.TextView;

import com.yp.paparazzilive.R;
import com.yp.paparazzilive.utils.CommonUtil;
import com.yp.paparazzilive.utils.LightController;
import com.yp.paparazzilive.utils.VolumeController;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

public class PlayVideoActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener,Handler.Callback, View.OnTouchListener,GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    public static final String VIDEO_URL="http://live-play.acgvideo.com/live/986/live_11277668_2072113.flv?wsSecret=f7dd6c56aab92a32c0486302a74c61a9&wsTime=57bbf478";
    private static final int UPDATE_PROGRESS = 100;
    private static final String TAG = PlayVideoActivity.class.getSimpleName();

    private VideoView mVideoView;
    private CheckBox mPlay;
    private CheckBox mFullScreen;
    private TextView mCurrent;
    private TextView mTotal;
    private SeekBar mProgress;
    private int mScreenWidth;
    private int mScreenHeight;
    private View mController;

    private Handler mHandler=new Handler(this);
    private int oldHeight;
    private boolean isLandscape;
    private float mStratX;
    private float mStratY;
    private float mLastX;
    private float mLastY;
    private int threshold=10;
    private GestureDetector gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_vider);
        initView();
    }

    private void initView() {

        mVideoView = (VideoView) findViewById(R.id.play_video);
       mVideoView.setOnTouchListener(this);
        Vitamio.isInitialized(getApplicationContext());
        mVideoView.setVideoPath(VIDEO_URL);
        mVideoView.requestFocus();
//        mVideoView.invalidate();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setPlaybackSpeed(1.0f);

            }
        });

        mController = findViewById(R.id.play_video_controller);

        mPlay = (CheckBox) findViewById(R.id.play_video_controller_play);
        mPlay.setOnCheckedChangeListener(this);

        mFullScreen = (CheckBox) findViewById(R.id.play_video_controller_full_screen);
        mFullScreen.setOnCheckedChangeListener(this);

        mCurrent = (TextView) findViewById(R.id.play_video_controller_current_time);
        mTotal = (TextView) findViewById(R.id.play_video_controller_total_time);

        mProgress = (SeekBar) findViewById(R.id.play_video_controller_progress);
        mProgress.setOnSeekBarChangeListener(this);

        mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        mScreenHeight = getResources().getDisplayMetrics().heightPixels;

        gestureDetector = new GestureDetector(this,this);
        gestureDetector.setOnDoubleTapListener(this);


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

//        gestureDetector.onTouchEvent(event);

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStratX=x;
                mStratY=y;
                mLastX=x;
                mLastY=y;
                break;
            case MotionEvent.ACTION_MOVE:
                if (isLandscape){
                    float xDelta = x - mLastX;
                    float yDelta = y - mLastY;
                    if (Math.abs(xDelta)>Math.abs(yDelta)) {
                        if (Math.abs(xDelta)>threshold) {
                            if (xDelta>0) {
                                goForward(xDelta);
                            }else {
                                goBack(xDelta);
                            }
                        }
                    }else {

                        if (Math.abs(yDelta)>threshold){
                            if (x>mScreenHeight/2) {
                                if (yDelta>0) {
                                    VolumeController.volumeDown(this,yDelta,mScreenWidth);
                                }else {
                                    VolumeController.volumeUp(this,yDelta,mScreenWidth);
                                }
                            }
                        }else {
                            if (yDelta>0){
                                LightController.lightDown(this,yDelta,mScreenWidth);
                            }else{
                                LightController.lightUp(this,yDelta,mScreenWidth);
                            }
                        }
                    }
                }

                mLastX=x;
                mLastY=y;
                break;
            case MotionEvent.ACTION_UP:

                if (Math.abs(x-mStratX)<threshold&&Math.abs(y-mStratY)<threshold) {
                    showOrHideController();
                }
                break;
        }

        return false;
//        return true;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.play_video_controller_play:
                if (isChecked) {
                    mVideoView.start();
                    mHandler.sendEmptyMessage(UPDATE_PROGRESS);
//                    int duration = mVideoView.getDuration();
//                    mProgress.setMax(duration);
//                    mTotal.setText(CommonUtil.parseTime(duration));
                }else {
                    mVideoView.pause();
                    mHandler.removeMessages(UPDATE_PROGRESS);
                }

                break;
            case R.id.play_video_controller_full_screen:
                if (isChecked) {
                    oldHeight = mVideoView.getHeight();
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    ViewGroup.LayoutParams layoutParams = mVideoView.getLayoutParams();
                    layoutParams.height=ViewGroup.LayoutParams.MATCH_PARENT;
                    mVideoView.setLayoutParams(layoutParams);
                }else {
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    ViewGroup.LayoutParams params = mVideoView.getLayoutParams();
                    params.height=oldHeight;
                    mVideoView.setLayoutParams(params);
                }

                break;
        }

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if (fromUser) {
            mVideoView.seekTo(progress);
            mCurrent.setText(CommonUtil.parseTime(progress));
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        int progress = seekBar.getProgress();
        mVideoView.seekTo(progress);

    }

    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what) {
            case UPDATE_PROGRESS:

//                int currentPosition = mVideoView.getCurrentPosition();
//                mCurrent.setText(CommonUtil.parseTime(currentPosition));
//                mProgress.setProgress(currentPosition);
                mHandler.sendEmptyMessageDelayed(UPDATE_PROGRESS,1000);

                break;
        }

        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        if (newConfig.orientation== Configuration.ORIENTATION_PORTRAIT) {
            Log.e(TAG, "onConfigurationChanged: 竖屏" );
            isLandscape=false;
        }else if (newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            Log.e(TAG, "onConfigurationChanged: 横屏" );
            isLandscape=true;
        }

        super.onConfigurationChanged(newConfig);
    }

    private void goForward(float xDelta){
//        int currentPosition = mVideoView.getCurrentPosition();
//        int duration = mVideoView.getDuration();
//        double add = 0.1 * xDelta * duration / mScreenHeight;
//        double changed = Math.min(add + currentPosition, duration);
//        mVideoView.seekTo((int) changed);
    }

    private void goBack(float xDelta){
//        int currentPosition = mVideoView.getCurrentPosition();
//        int duration = mVideoView.getDuration();
//        double sub = 0.1 * xDelta * duration / mScreenHeight;
//        double changed = Math.max(sub + currentPosition, 0);
//        mVideoView.seekTo((int) changed);
    }

    public void showOrHideController(){

        if (mController.getVisibility()== View.VISIBLE) {
            mController.setVisibility(View.GONE);
            Animation exitAnimation = AnimationUtils.loadAnimation(this,R.anim.play_controller_exit);
            mController.startAnimation(exitAnimation);
        }else {
            mController.setVisibility(View.VISIBLE);
            Animation enterAnimation = AnimationUtils.loadAnimation(this, R.anim.play_controller_enter);
            mController.startAnimation(enterAnimation);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode==event.KEYCODE_BACK) {
            if (isLandscape){
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                ViewGroup.LayoutParams params = mVideoView.getLayoutParams();
                params.height=oldHeight;
                mVideoView.setLayoutParams(params);
            }else {
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onDown(MotionEvent e) {

        return false;
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
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
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
}
