package com.yp.paparazzilive.adapters.classify;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yp.paparazzilive.R;
import com.yp.paparazzilive.model.classify.Column;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yp on 2016/9/20.
 */
public class ClassifyFragmentAdapter extends RecyclerView.Adapter<ClassifyFragmentAdapter.ViewHolder> implements View.OnClickListener {

    private static final String TAG = ClassifyFragmentAdapter.class.getSimpleName();
    private List<Column> data;

    private LayoutInflater inflater;

    private RecyclerView mRecyclerView;

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ClassifyFragmentAdapter(Context context, List<Column> data){
        inflater=LayoutInflater.from(context);
        if (data!=null) {
            this.data=data;
        }else {
            this.data=new ArrayList<>();
        }
    }

    public void addRes(List<Column> data){
        if (data!=null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void updataRes(List<Column> data){
        if (data!=null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }


    public Column getItem(int position){
        return data.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.fragment_classify_recycler_item, parent, false);

        itemView.setOnClickListener(this);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.title.setText(data.get(position).getName());
        holder.peopleColumn.setText(data.get(position).getLivenum()+"个直播");
        x.image().bind(holder.image,data.get(position).getImgurl());

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView=recyclerView;
    }

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }

    @Override
    public void onClick(View v) {
        int childAdapterPosition = mRecyclerView.getChildAdapterPosition(v);
        Log.e(TAG, "onClick: "+childAdapterPosition );
        if (listener!=null) {
            listener.onItemClick(childAdapterPosition);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;

        TextView title;

        TextView peopleColumn;


        public ViewHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.fragment_classify_recycler_item_imager);
            title= (TextView) itemView.findViewById(R.id.fragment_classify_recycler_item_title);
            peopleColumn= (TextView) itemView.findViewById(R.id.fragment_classify_recycler_column);
        }
    }

}
