package com.yp.paparazzilive.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.yp.paparazzilive.R;
import com.yp.paparazzilive.ui.secondactivity.MineActivity;

/**
 * Created by yp on 2016/9/20.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {

    public static final String TAG=MineFragment.class.getSimpleName();
    private RelativeLayout mJump1;
    private RelativeLayout mJump2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        layout=inflater.inflate(R.layout.fragment_mine,container,false);

        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {

        mJump2 = ((RelativeLayout) layout.findViewById(R.id.mine_fragment_two));
        mJump2.setOnClickListener(this);

        mJump1 = ((RelativeLayout) layout.findViewById(R.id.mine_fragment_three));
        mJump1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getActivity(), MineActivity.class);

        switch (v.getId()) {

            case R.id.mine_fragment_two:
                intent.putExtra("position",0);
                startActivity(intent);
                break;

            case R.id.mine_fragment_three:
                intent = new Intent(getActivity(), MineActivity.class);
                intent.putExtra("position",1);
                startActivity(intent);
                break;

        }

    }
}
