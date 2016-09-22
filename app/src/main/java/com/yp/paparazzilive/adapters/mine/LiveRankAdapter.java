package com.yp.paparazzilive.adapters.mine;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yp.paparazzilive.R;
import com.yp.paparazzilive.model.mine.LiveRank;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yp on 2016/9/21.
 *
 */
public class LiveRankAdapter extends BaseAdapter implements View.OnClickListener {


    private static final String TAG = LiveRankAdapter.class.getSimpleName();
    private ImageOptions options;

    private LayoutInflater inflater;

    private List<LiveRank> data;

    private onItemClickListener listener;

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public LiveRankAdapter(Context context, List<LiveRank> data){

        inflater=LayoutInflater.from(context);
        if (data!=null){
            this.data=data;
        }else {
            this.data=new ArrayList<>();
        }

        options=new ImageOptions.Builder()
                .setCircular(true)
                .build();

    }

    public void addRes(List<LiveRank> data){

        if (data!=null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }

    }


    @Override
    public int getCount() {
        return data!=null?data.size()-3:0;
    }

    @Override
    public LiveRank getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder=null;

        if (convertView==null) {
            convertView=inflater.inflate(R.layout.ranklist_game_fragment_lv_item,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.num.setText(String.valueOf(position+3));
        holder.name.setText(getItem(position+3).getCommentator());
        holder.gamename.setText(getItem(position+3).getName());
        holder.moods.setText("人气"+getItem(position+3).getRenqi());
        x.image().bind(holder.image,getItem(position+3).getRawcommentatorimage(),options);

        holder.relative.setOnClickListener(this);
        holder.relative.setTag(position+3);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        Integer position = (Integer) v.getTag();
        Log.e(TAG, "onClick: "+position );
        if (listener!=null) {
            listener.onItemClick(position);
        }

    }


    public interface onItemClickListener{

        void onItemClick(int position);

    }

    public class ViewHolder{

        TextView num;
        TextView name;
        TextView moods;
        TextView gamename;
        ImageView image;
        RelativeLayout relative;

        public ViewHolder(View convertView){

            num= (TextView) convertView.findViewById(R.id.ranklist_game_fragment_lv_item_num);
            name = (TextView) convertView.findViewById(R.id.ranklist_game_fragment_lv_item_name);
            gamename = (TextView) convertView.findViewById(R.id.ranklist_game_fragment_lv_item_game_name);
            moods = (TextView) convertView.findViewById(R.id.ranklist_game_fragment_lv_item_moods);
            image = (ImageView) convertView.findViewById(R.id.ranklist_game_fragment_lv_item_image);
            relative= (RelativeLayout) convertView.findViewById(R.id.ranklist_game_fragment_lv_item_relative);


        }


    }

}
