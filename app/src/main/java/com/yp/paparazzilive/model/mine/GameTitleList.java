package com.yp.paparazzilive.model.mine;

import com.yp.paparazzilive.http.JsonParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.List;

/**
 * Created by yp on 2016/9/21.
 */
@HttpResponse(parser = JsonParser.class)
public class GameTitleList {

    private List<GameTitle> all_game;

    private List<GameTitle> recent_game;

    public List<GameTitle> getRecent_game() {
        return recent_game;
    }

    public void setRecent_game(List<GameTitle> recent_game) {
        this.recent_game = recent_game;
    }

    public List<GameTitle> getAll_game() {
        return all_game;
    }

    public void setAll_game(List<GameTitle> all_game) {
        this.all_game = all_game;
    }
}
