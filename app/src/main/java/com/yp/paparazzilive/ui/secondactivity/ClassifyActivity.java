package com.yp.paparazzilive.ui.secondactivity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yp.paparazzilive.R;
import com.yp.paparazzilive.adapters.classify.ClassifyActivityAdapter;
import com.yp.paparazzilive.ui.fragments.clasify.LiveFragment;
import com.yp.paparazzilive.ui.fragments.clasify.VideoFragment;

import java.util.ArrayList;
import java.util.List;

public class ClassifyActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private RadioGroup mController;
    private ViewPager mViewPager;
    private ClassifyActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify);
        initView();
    }

    private void initView() {
        mController = (RadioGroup) findViewById(R.id.classify_activity_controller);
        mController.setOnCheckedChangeListener(this);
        mViewPager = (ViewPager) findViewById(R.id.classify_activity_viewpager);
        List<Fragment> data=new ArrayList<>();
        data.add(new LiveFragment());
        data.add(new VideoFragment());
        adapter = new ClassifyActivityAdapter(getSupportFragmentManager(),data);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.classify_activity_controller_left_live:
                mViewPager.setCurrentItem(0);
                break;

            case R.id.classify_activity_controller_right_video:
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
}
