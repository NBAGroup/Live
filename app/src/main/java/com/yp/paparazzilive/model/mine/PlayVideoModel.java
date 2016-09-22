package com.yp.paparazzilive.model.mine;

import com.yp.paparazzilive.http.JsonParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.List;

/**
 * Created by yp on 2016/9/22.
 */
@HttpResponse(parser = JsonParser.class)
public class PlayVideoModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private String playurl;

        public String getPlayurl() {
            return playurl;
        }

        public void setPlayurl(String playurl) {
            this.playurl = playurl;
        }
    }
}
