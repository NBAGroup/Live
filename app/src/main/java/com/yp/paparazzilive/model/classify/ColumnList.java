package com.yp.paparazzilive.model.classify;

import com.yp.paparazzilive.http.JsonParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.List;

/**
 * Created by yp on 2016/9/20.
 */
@HttpResponse(parser = JsonParser.class)
public class ColumnList {

    private List<Column> data_list;

    public List<Column> getData_list() {
        return data_list;
    }

    public void setData_list(List<Column> data_list) {
        this.data_list = data_list;
    }
}
