package com.yp.paparazzilive.ui.secondactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yp.paparazzilive.R;
import com.yp.paparazzilive.adapters.mine.MineActivityAdapter;
import com.yp.paparazzilive.ui.BaseActivity;
import com.yp.paparazzilive.ui.fragments.mine.AttentionFragment;
import com.yp.paparazzilive.ui.fragments.mine.RankListFragment;

import java.util.ArrayList;
import java.util.List;

public class MineActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, ViewPager.OnPageChangeListener {

    private RadioGroup mController;
    private ViewPager mViewPager;
    private ImageView mBack;
    private MineActivityAdapter adapter;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        initView();
    }

    private void initView() {

        Intent intent = getIntent();
        position = intent.getIntExtra("position",0);
        mController = (RadioGroup) findViewById(R.id.mine_activity_controller);
        mController.setOnCheckedChangeListener(this);
        mBack = (ImageView) findViewById(R.id.mine_activity_image_back);
        mBack.setOnClickListener(this);
        mViewPager = (ViewPager) findViewById(R.id.mine_activity_viewpager);
        List<Fragment> data=new ArrayList<>();
        data.add(new AttentionFragment());
        data.add(new RankListFragment());
        adapter = new MineActivityAdapter(getSupportFragmentManager(),data);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setCurrentItem(position);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.mine_activity_controller_left_live:
                mViewPager.setCurrentItem(0);
                break;

            case R.id.mine_activity_controller_right_video:
                mViewPager.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        RadioButton childAt = (RadioButton) mController.getChildAt(position);
        childAt.setChecked(true);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mine_activity_image_back:
                finish();
                break;
        }

    }

}
