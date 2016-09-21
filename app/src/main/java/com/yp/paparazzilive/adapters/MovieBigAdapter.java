package com.yp.paparazzilive.adapters;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rock.teachlibrary.adapters.TeachBaseAdapter;
import com.yp.paparazzilive.R;
import com.yp.paparazzilive.model.HomePagerModel.MovieBig;

import org.xutils.x;

import java.util.List;

/**
 * Created by hedianbo on 2016/9/21.
 */
public class MovieBigAdapter extends TeachBaseAdapter<MovieBig.DataBean> {

    private Context context;

    public MovieBigAdapter(Context context, List<MovieBig.DataBean> data, int layoutResId) {
        super(context, data, layoutResId);
        this.context=context;
    }

    @Override
    protected void bindData(ViewHolder holder, MovieBig.DataBean item) {
//        TextView name = (TextView) holder.getView(R.id.live_item_name);
//        TextView count = (TextView) holder.getView(R.id.live_item_count);
        TextView content = (TextView) holder.getView(R.id.live_item_content);
        TextView title = (TextView) holder.getView(R.id.live_item_title);

        ImageView imageView = (ImageView) holder.getView(R.id.live_item_image);

//        name.setText(item.getOwner().getName());
        content.setText(item.getOwner().getName());
        title.setText(item.getTitle());
//        count.setText(item.getOnline());
        x.image().bind(imageView, item.getCover().getSrc());
//        Glide.with(context).load(item.getCover()).placeholder(R.mipmap.loading_icon_default_2).into(imageView);
    }
}
