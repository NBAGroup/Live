package com.yp.paparazzilive.adapters.fragment_play_adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.TextView;

import com.rock.teachlibrary.adapters.TeachBaseAdapter;
import com.yp.paparazzilive.R;
import com.yp.paparazzilive.model.fragmentplay.Message;

import java.util.List;

/**
 * Created by hedianbo on 2016/9/22.
 */
public class fragment_play1 extends TeachBaseAdapter<Message> {
    public fragment_play1(Context context, List<Message> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bindData(ViewHolder holder, Message item) {

        TextView view = (TextView) holder.getView(R.id.tv_msg_name);
        TextView view1 = (TextView) holder.getView(R.id.tv_msg_content);
        ImageView view2 = (ImageView) holder.getView(R.id.tv_image);

        view.setText(item.getName());
        view1.setText(item.getContent());
        view2.setImageResource(item.getImage());

    }

}
