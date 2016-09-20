package com.yp.paparazzilive.adapters;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.rock.teachlibrary.adapters.TeachBaseAdapter;
import com.yp.paparazzilive.R;
import com.yp.paparazzilive.model.HomePagerModel.Live1;

import org.xutils.x;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by hedianbo on 2016/9/20.
 */
public class MyGridView extends TeachBaseAdapter<Live1> {

    public MyGridView(Context context, List<Live1> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bindData(ViewHolder holder, Live1 item) {
        TextView name = (TextView) holder.getView(R.id.live_item_name);
        TextView count = (TextView) holder.getView(R.id.live_item_count);
        TextView content = (TextView) holder.getView(R.id.live_item_content);
        TextView title = (TextView) holder.getView(R.id.live_item_title);

        ImageView imageView = (ImageView) holder.getView(R.id.live_item_image);

        name.setText(item.getName());
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        count.setText((decimalFormat.format(item.getViewers() / (double) 10000) + "W"));
        content.setText(item.getTitle());
        title.setText(item.getSourcename()+"."+item.getCommentator());

        x.image().bind(imageView,item.getRawcoverimage());


    }
}
