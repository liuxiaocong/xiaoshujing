package com.xiaoshujing.kid.model;

import java.util.List;

/**
 * Created by LiuXiaocong on 12/8/2016.
 */

public class GlobeSettingBean {
    /**
     * instruction : http://59.110.23.25/ads/detail/?id=0f2c77f9-1699-463d-a9b3-8dba358a10a8
     * setDays : [{"value":"1天","key":"1"},{"value":"2天","key":"2"},{"value":"3天","key":"3"},{"value":"5天","key":"5"},{"value":"7天","key":"7"},{"value":"10天","key":"10"}]
     * systemCredit : 5
     * exercisePages : [{"value":"预计完成时间15分钟","key":"1"},{"value":"预计完成时间30分钟","key":"2"},{"value":"预计完成时间45分钟","key":"3"},{"value":"预计完成时间60分钟","key":"4"},{"value":"预计完成时间75分钟","key":"5"}]
     * aboutUs : http://59.110.23.25/ads/detail/?id=21385da0-455b-4bd3-a16b-4644c411ffb2
     * _status : 0
     * _reason :
     * parentsCredit : 5
     * dailCredit : 5
     */

    private String instruction;
    private int systemCredit;
    private String aboutUs;
    private int _status;
    private String _reason;
    private int parentsCredit;
    private int dailCredit;
    /**
     * value : 1天
     * key : 1
     */

    private List<SetDaysBean> setDays;
    /**
     * value : 预计完成时间15分钟
     * key : 1
     */

    private List<ExercisePagesBean> exercisePages;

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getSystemCredit() {
        return systemCredit;
    }

    public void setSystemCredit(int systemCredit) {
        this.systemCredit = systemCredit;
    }

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
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

    public int getParentsCredit() {
        return parentsCredit;
    }

    public void setParentsCredit(int parentsCredit) {
        this.parentsCredit = parentsCredit;
    }

    public int getDailCredit() {
        return dailCredit;
    }

    public void setDailCredit(int dailCredit) {
        this.dailCredit = dailCredit;
    }

    public List<SetDaysBean> getSetDays() {
        return setDays;
    }

    public void setSetDays(List<SetDaysBean> setDays) {
        this.setDays = setDays;
    }

    public List<ExercisePagesBean> getExercisePages() {
        return exercisePages;
    }

    public void setExercisePages(List<ExercisePagesBean> exercisePages) {
        this.exercisePages = exercisePages;
    }

    public static class SetDaysBean {
        private String value;
        private String key;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }

    public static class ExercisePagesBean {
        private String value;
        private String key;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
