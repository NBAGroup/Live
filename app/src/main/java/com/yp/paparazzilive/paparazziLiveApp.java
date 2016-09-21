package com.yp.paparazzilive;

import android.app.Application;

import org.xutils.x;

import io.vov.vitamio.Vitamio;

/**
 * Created by yp on 2016/9/20.
 */
public class paparazziLiveApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
        Vitamio.isInitialized(this);
    }
}
