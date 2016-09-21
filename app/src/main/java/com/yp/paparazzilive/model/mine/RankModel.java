package com.yp.paparazzilive.model.mine;

import com.yp.paparazzilive.http.JsonParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by yp on 2016/9/21.
 */
@HttpResponse(parser = JsonParser.class)
public class RankModel {

    private LiveRankList commentators;

    public LiveRankList getCommentators() {
        return commentators;
    }

    public void setCommentators(LiveRankList commentators) {
        this.commentators = commentators;
    }
}
