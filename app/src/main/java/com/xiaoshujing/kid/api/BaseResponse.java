package com.xiaoshujing.kid.api;

import com.google.gson.Gson;

/**
 * Created by zzz on 10/28/16.
 */

public class BaseResponse {

    /**
     * _reason :
     * _status : 1
     */


    protected String _reason;
    protected int _status;


    public String get_reason() {
        return _reason;
    }

    public void set_reason(String _reason) {
        this._reason = _reason;
    }

    public int get_status() {
        return _status;
    }

    public void set_status(int _status) {
        this._status = _status;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
