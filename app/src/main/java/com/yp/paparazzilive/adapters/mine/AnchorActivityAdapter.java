package com.yp.paparazzilive.adapters.mine;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yp.paparazzilive.R;
import com.yp.paparazzilive.model.mine.AnchorContent;
import com.yp.paparazzilive.ui.fouractivity.PlayVideoActivity;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yp on 2016/9/22.
 */
public class AnchorActivityAdapter extends BaseAdapter implements View.OnClickListener {

    private List<AnchorContent> data;

    private LayoutInflater inflater;

    private Context context;

    public AnchorActivityAdapter(Context context,List<AnchorContent> data){

        this.context=context;

        inflater=LayoutInflater.from(context);
        if (data!=null) {
            this.data=data;
        }else {
            this.data=new ArrayList<>();
        }

    }

    public void addRes(List<AnchorContent> data){

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
    public AnchorContent getItem(int position) {
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
            convertView=inflater.inflate(R.layout.anchor_acitvity_lv_item,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.titleTop.setText(getItem(position).getTitle());
        holder.titleBottom.setText(getItem(position).getTitle());
        holder.watchTime.setText(getItem(position).getDuration());
        x.image().bind(holder.image,getItem(position).getRawcoverimage());
        String viewtimes = getItem(position).getViewtimes();

        Double num = Double.parseDouble(viewtimes);

        String updatetime = getItem(position).getUpdatetime();
        String[] split = updatetime.split(" ");
        holder.time.setText(split[0]);
        if (num>=10000) {
            Double i = num / 10000;
            String result=String.format("%.1f",i);
            holder.watchNum.setText(result+"万次");
        }else {
            holder.watchNum.setText(viewtimes);
        }

        holder.play.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(context, PlayVideoActivity.class);
        context.startActivity(intent);

    }


    public class ViewHolder{

        ImageView image;

        TextView titleTop;

        TextView titleBottom;

        TextView watchNum;

        TextView time;

        TextView watchTime;

        LinearLayout play;

        public ViewHolder(View convertView){

            image= (ImageView) convertView.findViewById(R.id.anchor_activity_lv_item_image);
            titleTop= (TextView) convertView.findViewById(R.id.anchor_activity_lv_item_title_top);
            titleBottom= (TextView) convertView.findViewById(R.id.anchor_activity_lv_item_title_bottom);
            watchNum= (TextView) convertView.findViewById(R.id.anchor_activity_lv_item_watch_num);
            time= (TextView) convertView.findViewById(R.id.anchor_activity_lv_item_time);
            watchTime= (TextView) convertView.findViewById(R.id.anchor_activity_lv_item_watch_time);
            play= (LinearLayout) convertView.findViewById(R.id.anchor_activity_lv_item_play);
        }

    }

}
