package com.wind.huangzhijian.minimalistread.module.http.response;

/**
 * Created by huangzhijian on 2017/3/9.
 */
public class GoldHttpResponse<T> {
    private T results;

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
