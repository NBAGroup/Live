package com.yp.paparazzilive.model.classify;

import com.yp.paparazzilive.http.JsonParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by yp on 2016/9/20.
 */
@HttpResponse(parser = JsonParser.class)
public class LiveContent {

    private GameInfoList commentators;

    public GameInfoList getCommentators() {
        return commentators;
    }

    public void setCommentators(GameInfoList commentators) {
        this.commentators = commentators;
    }
}
