package com.yp.paparazzilive.utils;

import android.text.format.DateFormat;

/**
 * Created by yp on 2016/9/22.
 */
public class CommonUtil {

    public static CharSequence parseTime(int position){

        return DateFormat.format("mm:ss",position);
    }

}
