package com.yp.paparazzilive.utils;

import android.app.Activity;
import android.provider.Settings;
import android.view.WindowManager;

import com.yp.paparazzilive.ui.secondactivity.PlayActivity;

public class LightController {

    /**
     * 亮度加  distanceY > 0
     */
    public static void lightUp(Activity activity,float distanceY,int mScreenWidth){
        try {
            //系统当前亮度
            int currentLight = Settings.System.getInt(activity.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, -1);
            //增加的亮度
            float addLight = 2 * distanceY * 255 / mScreenWidth;

            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();

            attributes.screenBrightness = Math.min(255,currentLight + addLight) / 255;

            activity.getWindow().setAttributes(attributes);

            Settings.System.putInt(activity.getContentResolver(),Settings.System.SCREEN_BRIGHTNESS, ((int) Math.min(255, currentLight + addLight)));
            int currentLight_new = Settings.System.getInt(activity.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, -1);
            ((PlayActivity) activity).showToast((currentLight_new * 100 / 255) + "%",false);
        }catch (Exception e){

        }
    }

    /**
     * 亮度加  distanceY < 0
     */
    public static void lightDown(Activity activity,float distanceY,int mScreenWidth){
        try {
            //系统当前亮度
            int currentLight = Settings.System.getInt(activity.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, -1);
            //减少的的亮度
            float reduceLight = 2 * distanceY * 255 / mScreenWidth;

            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();

            attributes.screenBrightness = Math.max(0,currentLight + reduceLight) / 255;

            activity.getWindow().setAttributes(attributes);

            Settings.System.putInt(activity.getContentResolver(),Settings.System.SCREEN_BRIGHTNESS, ((int) Math.max(0, currentLight + reduceLight)));
            int currentLight_new = Settings.System.getInt(activity.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, -1);
            ((PlayActivity) activity).showToast((currentLight_new * 100 / 255) + "%",false);
        }catch (Exception e){

        }
    }
}
