package com.xiaoshujing.kid.model;

/**
 * Created by LiuXiaocong on 1/6/2017.
 */

public class GetTokenBean {

    /**
     * _status : 1017
     * _reason : 该token未绑定设备
     */

    private int _status;
    private String _reason;

    public int get_status() {
        return _status;
    }

    public void set_status(int _status) {
        this._status = _status;
    }

    public String get_reason() {
        return _reason;
    }

    public void set_reason(String _reason) {
        this._reason = _reason;
    }
}
