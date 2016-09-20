package com.yp.paparazzilive.model.HomePagerModel;

/**
 * Created by hedianbo on 2016/9/20.
 */
public class Live1 {

    private String rawcoverimage;
    private String title;
    private String jsdesc;
    private String name;
    private String sourcename;
    private String commentator;

    private int roomid;
    private int viewers;
    private String gameurl;
    private String url;


    public String getSourcename() {
        return sourcename;
    }

    public void setSourcename(String sourcename) {
        this.sourcename = sourcename;
    }

    public String getCommentator() {
        return commentator;
    }

    public void setCommentator(String commentator) {
        this.commentator = commentator;
    }

    public String getRawcoverimage() {
        return rawcoverimage;
    }

    public void setRawcoverimage(String rawcoverimage) {
        this.rawcoverimage = rawcoverimage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJsdesc() {
        return jsdesc;
    }

    public void setJsdesc(String jsdesc) {
        this.jsdesc = jsdesc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public int getViewers() {
        return viewers;
    }

    public void setViewers(int viewers) {
        this.viewers = viewers;
    }

    public String getGameurl() {
        return gameurl;
    }

    public void setGameurl(String gameurl) {
        this.gameurl = gameurl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
