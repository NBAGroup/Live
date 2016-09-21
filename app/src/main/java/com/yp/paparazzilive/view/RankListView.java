package com.yp.paparazzilive.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by yp on 2016/9/21.
 */
public class RankListView extends ListView {
    public RankListView(Context context) {
        super(context);
    }

    public RankListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RankListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
