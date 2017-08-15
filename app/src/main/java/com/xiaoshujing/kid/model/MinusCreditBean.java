package com.xiaoshujing.kid.model;

/**
 * Created by LiuXiaocong on 1/6/2017.
 */

public class MinusCreditBean {

    /**
     * credit : 9
     * _status : 0
     * _reason :
     * isMinus : false
     */

    private int credit;
    private int _status;
    private String _reason;
    private boolean isMinus;

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

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

    public boolean isIsMinus() {
        return isMinus;
    }

    public void setIsMinus(boolean isMinus) {
        this.isMinus = isMinus;
    }
}
