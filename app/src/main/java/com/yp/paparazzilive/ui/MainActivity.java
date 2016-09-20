package com.yp.paparazzilive.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.yp.paparazzilive.R;
import com.yp.paparazzilive.ui.fragments.ClassifyFragmetn;
import com.yp.paparazzilive.ui.fragments.HeaderListFragmetn;
import com.yp.paparazzilive.ui.fragments.HomePagerFragmetn;
import com.yp.paparazzilive.ui.fragments.MineFragmetn;

import java.lang.reflect.InvocationTargetException;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    private RadioGroup mController;
    private Fragment mShowFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mController = (RadioGroup) findViewById(R.id.paparazziLive_main_controller);
        mController.setOnCheckedChangeListener(this);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mShowFragment = new HomePagerFragmetn();

        transaction.add(R.id.paparazziLive_main_container,mShowFragment,HomePagerFragmetn.TAG);
        transaction.commit();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.controller_homepager:
                switchPages(HomePagerFragmetn.TAG,HomePagerFragmetn.class);
                break;
            case R.id.controller_classify:
                switchPages(ClassifyFragmetn.TAG,ClassifyFragmetn.class);
                break;
            case R.id.controller_headerlist:
                switchPages(HeaderListFragmetn.TAG,HeaderListFragmetn.class);
                break;
            case R.id.controller_mine:
                switchPages(MineFragmetn.TAG,MineFragmetn.class);
                break;
        }
    }

    private void switchPages(String tag, Class<? extends Fragment> cls){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.hide(mShowFragment);
        mShowFragment=fm.findFragmentByTag(tag);
        if (mShowFragment!=null){
            transaction.show(mShowFragment);
        }else {
            try {
                mShowFragment=cls.getConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            transaction.add(R.id.paparazziLive_main_container,mShowFragment,tag);
        }
        transaction.commit();
    }


}
