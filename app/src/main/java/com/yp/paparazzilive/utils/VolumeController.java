package com.yp.paparazzilive.utils;

import android.content.Context;
import android.media.AudioManager;

/**
 * Created by yp on 2016/9/22.
 */
public class VolumeController {

    public static void volumeUp(Context context,float yDelta,int screenWidth){

        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float add = 2 * maxVolume * yDelta / screenWidth;
        float changed = Math.min(maxVolume, currentVolume - add);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, (int) changed,AudioManager.FLAG_SHOW_UI);
    }

    public static void volumeDown(Context context,float yDelta,int screenWidth){

        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float sub = 2 * maxVolume * yDelta / screenWidth;
        float changed = Math.max(0, currentVolume - sub);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, (int) changed,AudioManager.FLAG_SHOW_UI);
    }

}
