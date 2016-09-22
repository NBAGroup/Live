package com.yp.paparazzilive.ui.fragments.SecondFragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yp.paparazzilive.R;
import com.yp.paparazzilive.adapters.fragment_play_adapter.fragment_play1;
import com.yp.paparazzilive.model.fragmentplay.Message;
import com.yp.paparazzilive.ui.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by hedianbo on 2016/9/21.
 */
public class HuDongFragment extends BaseFragment implements Handler.Callback{

    private ListView mRecycler;
    private fragment_play1 fragment_play1;
    private List<Message> data;
    private Handler mhandler=new Handler(this);
    private LinearLayoutManager manger;

     String[] name = {"德玛西亚","女神","丑女","大保健"};
    String[] content = {"我擦。。叼炸天a","撸起来","赶紧的"};
    int[] image={R.mipmap.ic_22,R.mipmap.ic_22_hide,R.mipmap.ic_33,R.mipmap.ic_33_hide};



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_play1, container, false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {

        mRecycler = (ListView) layout.findViewById(R.id.recycler_list_talk);
        manger = new LinearLayoutManager(getContext());
        fragment_play1 = new fragment_play1(getContext(), getData(), R.layout.item_play1);
        mRecycler.setDivider(null);
        mRecycler.setAdapter(fragment_play1);
    }



    public List<Message> getData() {
        List<Message> data=new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Message message=new Message();
            message.setName("德玛西亚"+i);
            message.setContent("来，撸一把"+i);
            data.add(message);
        }
      mhandler.sendEmptyMessage(100);
        return data;
    }

    @Override
    public boolean handleMessage(android.os.Message msg) {
        switch (msg.what) {
            case 100:
                data=new ArrayList<>();
                Random random = new Random();
                int num = random.nextInt(4);
                int num1= random.nextInt(3);
                int imag = random.nextInt(4);
                Message message=new Message();
                message.setName(name[num]);
                message.setContent(content[num1]);
                message.setImage(image[imag]);
                data.add(message);
                fragment_play1.addRes(data);
                mRecycler.setSelection(fragment_play1.getCount()-1);
                mhandler.sendEmptyMessageDelayed(100,1000);

                break;
        }
        return false;
    }
}
