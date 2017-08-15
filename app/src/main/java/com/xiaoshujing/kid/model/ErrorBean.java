package com.xiaoshujing.kid.model;

/**
 * Created by Xingbo.Jie on 10/11/16.
 */

public class ErrorBean {

    public final static int CODE_LIVE_ENDED = 1001;

    private int code;
    private String error;

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
