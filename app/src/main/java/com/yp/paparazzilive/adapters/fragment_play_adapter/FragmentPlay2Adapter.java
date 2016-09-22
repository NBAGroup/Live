package com.yp.paparazzilive.adapters.fragment_play_adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.rock.teachlibrary.adapters.TeachBaseAdapter;
import com.yp.paparazzilive.R;
import com.yp.paparazzilive.model.fragmentplay.PLay1Model;

import org.xutils.x;

import java.util.List;

/**
 * Created by hedianbo on 2016/9/22.
 */
public class FragmentPlay2Adapter extends TeachBaseAdapter<PLay1Model.DataBean.ListBean> {
    public FragmentPlay2Adapter(Context context, List<PLay1Model.DataBean.ListBean> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bindData(ViewHolder holder, PLay1Model.DataBean.ListBean item) {
        ImageView imageView1 = (ImageView) holder.getView(R.id.play2_image1);
        ImageView imageView2 = (ImageView) holder.getView(R.id.play2_image2);

        TextView textView1 = (TextView) holder.getView(R.id.play2_text1);
        TextView textView2 = (TextView) holder.getView(R.id.play2_text2);

        x.image().bind(imageView1,item.getCoin_url().getSrc());
        x.image().bind(imageView2,item.getCoin1_url().getSrc());

        textView1.setText(item.getUname());
        textView2.setText(item.getScore()+"");
    }
}
