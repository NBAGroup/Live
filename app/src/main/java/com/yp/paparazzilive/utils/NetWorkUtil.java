package com.yp.paparazzilive.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by yp on 2016/9/17.
 */
public class NetWorkUtil {

    public static boolean isConnected(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo!=null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {

            return true;
        }
        return false;
    }
}
