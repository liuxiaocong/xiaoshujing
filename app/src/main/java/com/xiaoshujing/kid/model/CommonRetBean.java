package com.xiaoshujing.kid.model;

/**
 * Created by LiuXiaocong on 12/7/2016.
 */

public class CommonRetBean {

    /**
     * _status : 6006
     * _reason : 还有未评分的练习，是否继续重置
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
