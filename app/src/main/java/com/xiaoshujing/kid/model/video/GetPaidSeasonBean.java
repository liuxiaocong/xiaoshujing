package com.xiaoshujing.kid.model.video;

import java.util.List;

/**
 * Created by LiuXiaocong on 12/7/2016.
 */

public class GetPaidSeasonBean {

    /**
     * _status : 0
     * objects : [{"name":"权利游戏","object_id":"bb15a99c15f046dd9e51c139c34fb398"},{"name":"火影忍者","object_id":"e13090ffc77d47508797e9c9c4dd2285"}]
     * _reason :
     */

    private int _status;
    private String _reason;
    /**
     * name : 权利游戏
     * object_id : bb15a99c15f046dd9e51c139c34fb398
     */

    private List<PaidSeasonBean> objects;

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

    public List<PaidSeasonBean> getObjects() {
        return objects;
    }

    public void setObjects(List<PaidSeasonBean> objects) {
        this.objects = objects;
    }

    public static class PaidSeasonBean {
        private String name;
        private String object_id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getObject_id() {
            return object_id;
        }

        public void setObject_id(String object_id) {
            this.object_id = object_id;
        }
    }
}
