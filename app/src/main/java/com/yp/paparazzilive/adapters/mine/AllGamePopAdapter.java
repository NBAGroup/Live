package com.yp.paparazzilive.adapters.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yp.paparazzilive.R;
import com.yp.paparazzilive.model.mine.GameTitle;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yp on 2016/9/21.
 */
public class AllGamePopAdapter extends BaseAdapter {

    private List<GameTitle> data;

    private LayoutInflater inflater;
    private ImageOptions options;

    public AllGamePopAdapter(Context context,List<GameTitle> data){

        inflater=LayoutInflater.from(context);
        if (data!=null) {
            this.data=data;
        }else {
            this.data=new ArrayList<>();
        }

        options=new ImageOptions.Builder()
                .setCircular(true)
                .build();

    }

    public void addRes(List<GameTitle> data) {

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
    public GameTitle getItem(int position) {
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
            convertView=inflater.inflate(R.layout.allgame_pop_item,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.name.setText(getItem(position).getName());
        x.image().bind(holder.image,getItem(position).getThumbnail(),options);

        return convertView;
    }


    public class ViewHolder{

        ImageView image;

        TextView name;

        public ViewHolder(View convertView){

            image= (ImageView) convertView.findViewById(R.id.allgame_pop_gridview_item_image);

            name= (TextView) convertView.findViewById(R.id.allgame_pop_gridview_item_name);

        }

    }

}
