package com.yp.paparazzilive.model.HomePagerModel;

/**
 * Created by hedianbo on 2016/9/20.
 */

public class BigLive {

    private LiveList rec_zhubo;
    private NoLiveList no_live;
    private BoFangList live;

    public LiveList getRec_zhubo() {
        return rec_zhubo;
    }

    public void setRec_zhubo(LiveList rec_zhubo) {
        this.rec_zhubo = rec_zhubo;
    }

    public NoLiveList getNo_live() {
        return no_live;
    }

    public void setNo_live(NoLiveList no_live) {
        this.no_live = no_live;
    }

    public BoFangList getLive() {
        return live;
    }

    public void setLive(BoFangList live) {
        this.live = live;
    }
}
