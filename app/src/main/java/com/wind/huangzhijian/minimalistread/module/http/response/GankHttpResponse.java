package com.wind.huangzhijian.minimalistread.module.http.response;

/**
 * Created by huangzhijian on 2017/3/9.
 */
public class GankHttpResponse<T> {
    private boolean error;
    private T results;

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
