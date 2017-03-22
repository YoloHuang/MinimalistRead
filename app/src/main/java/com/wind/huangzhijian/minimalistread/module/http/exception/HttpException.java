package com.wind.huangzhijian.minimalistread.module.http.exception;


import retrofit2.Response;

/**
 * Created by huangzhijian on 2017/3/14.
 */
public class HttpException extends Exception{

    private final transient Response<?> response;
    private final int code;
    private final String message;

    public HttpException(Response<?> response) {
        super();
        this.response = response;
        this.code = response.code();
        this.message = response.message();
    }

    public int code(){return code;}

    public String message(){return message;}

    public Response<?> response(){return response;}
}
