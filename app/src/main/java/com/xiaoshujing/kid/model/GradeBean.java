package com.xiaoshujing.kid.model;

/**
 * Created by LiuXiaocong on 12/15/2016.
 */

public class GradeBean {

    /**
     * _status : 0
     * _reason :
     * studyScore : 90
     */

    private int _status;
    private String _reason;
    private double studyScore;

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

    public double getStudyScore() {
        return studyScore;
    }

    public void setStudyScore(double studyScore) {
        this.studyScore = studyScore;
    }
}
