package com.yp.paparazzilive.adapters.classify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yp.paparazzilive.R;
import com.yp.paparazzilive.model.classify.GameInfo;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yp on 2016/9/20.
 */
public class LiveAdapter extends BaseAdapter {

    private List<GameInfo> data;

    private LayoutInflater inflater;

    public LiveAdapter(Context context,List<GameInfo> data){
        inflater=LayoutInflater.from(context);
        if (data!=null) {
            this.data=data;
        }else {
            this.data=new ArrayList<>();
        }

    }

    public void addRes(List<GameInfo> data){
        if (data!=null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public GameInfo getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder=null;
        if (convertView==null) {
            convertView=inflater.inflate(R.layout.live_fragment_gridview_item,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.title.setText(getItem(position).getTitle());
        holder.room.setText(getItem(position).getSourcename()+"·"+getItem(position).getRawcoverimage());
        x.image().bind(holder.image,getItem(position).getRawcoverimage());

        return convertView;
    }

    public class ViewHolder{

        ImageView image;

        TextView title;

        TextView room;

        public ViewHolder(View convertView){
            image= (ImageView) convertView.findViewById(R.id.live_fragment_grideview_item_image);
            title= (TextView) convertView.findViewById(R.id.live_fragment_grideview_item_title);
            room= (TextView) convertView.findViewById(R.id.live_fragment_grideview_item_room);
        }


    }

}
