package com.yp.paparazzilive.model.mine;

import com.yp.paparazzilive.http.JsonParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by yp on 2016/9/22.
 */

@HttpResponse(parser = JsonParser.class)
public class AnchorModel {

    private AnchorContentList  videos;

    public AnchorContentList getVideos() {
        return videos;
    }

    public void setVideos(AnchorContentList videos) {
        this.videos = videos;
    }
}
