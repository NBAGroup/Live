package com.yp.paparazzilive.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yp.paparazzilive.R;

import cn.appsdream.nestrefresh.normalstyle.NestRefreshLayout;

/**
 * Created by hedianbo on 2016/9/22.
 */
public class CustomHeader extends RelativeLayout implements NestRefreshLayout.LoaderDecor {
    private ImageView mImage;
    private Context context;
    private AnimationDrawable mAnimation;
    private boolean isShow;

    public CustomHeader(Context context) {
        this(context, null);
    }

    public CustomHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        View view = inflate(context, R.layout.refresh_layout, this);
        mImage = ((ImageView) view.findViewById(R.id.image_loading_data));

        mImage.setBackgroundResource(R.drawable.refresh);
        mAnimation = ((AnimationDrawable) mImage.getBackground());

    }

    @Override
    public void scrollRate(int y) {
       if(!isShow){
           if(y>0){
               mAnimation.start();
               isShow = true;
           }
       }
        if(y<5){
            isShow = false;
        }
    }

    @Override
    public void setState(int state) {

    }
}
