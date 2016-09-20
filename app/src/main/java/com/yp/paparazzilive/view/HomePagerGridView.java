package com.yp.paparazzilive.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by hedianbo on 2016/9/20.
 */
public class HomePagerGridView extends GridView {
    public HomePagerGridView(Context context) {
        super(context);
    }

    public HomePagerGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomePagerGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
