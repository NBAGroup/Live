package com.yp.paparazzilive.adapters;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.rock.teachlibrary.adapters.TeachBaseAdapter;
import com.yp.paparazzilive.R;
import com.yp.paparazzilive.model.BigModel;
import com.yp.paparazzilive.model.HomePagerModel.Live1;


import org.xutils.x;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by hedianbo on 2016/9/20.
 */
public class MyGridView extends TeachBaseAdapter<BigModel.RecZhuboBean.DataListBean> {

    public MyGridView(Context context, List<BigModel.RecZhuboBean.DataListBean> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bindData(ViewHolder holder, BigModel.RecZhuboBean.DataListBean item) {
//        TextView name = (TextView) holder.getView(R.id.live_item_name);
//        TextView count = (TextView) holder.getView(R.id.live_item_count);
        TextView content = (TextView) holder.getView(R.id.live_item_content);
        TextView title = (TextView) holder.getView(R.id.live_item_title);

        ImageView imageView = (ImageView) holder.getView(R.id.live_item_image);

//        name.setText(item.getName());
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
//        count.setText((decimalFormat.format(item.getViewers() / (double) 10000) + "万人"));
        content.setText(item.getTitle());
        title.setText(item.getSourcename()+"."+item.getCommentator());

       x.image().bind(imageView,"http://i9.pdim.gs/45/c13722d601987f0780e6de9e1e822e51/w338/h190.jpg");
    }
}
