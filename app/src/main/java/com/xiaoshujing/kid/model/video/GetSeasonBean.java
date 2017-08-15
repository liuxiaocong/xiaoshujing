package com.xiaoshujing.kid.model.video;

import com.xiaoshujing.kid.model.SeasonBean;

import java.util.List;

/**
 * Created by LiuXiaocong on 12/7/2016.
 */

public class GetSeasonBean {

    /**
     * _status : 0
     * meta : {"previous":null,"total_count":2,"offset":0,"limit":20,"next":null}
     * _reason :
     * objects : [{"description":"权利游戏","created_at":1479180723,"updated_at":1481178611,"object_id":"bb15a99c-15f0-46dd-9e51-c139c34fb398","name":"权利游戏","cover_url":"http://xiaoshujing.oss-cn-shanghai.aliyuncs.com/cover/2016/12/08/8a749e50-5593-4144-9ec6-c9b4b8b010a0.jpg","resource_uri":"http://59.110.23.25/api/v1/season/bb15a99c-15f0-46dd-9e51-c139c34fb398/"},{"description":"火影忍者火影忍者火影忍者火影忍者","created_at":1479180616,"updated_at":1481178603,"object_id":"e13090ff-c77d-4750-8797-e9c9c4dd2285","name":"火影忍者","cover_url":"http://xiaoshujing.oss-cn-shanghai.aliyuncs.com/cover/2016/12/08/c2710d7e-9a17-4d4b-b7a9-1428eb6b9862.jpg","resource_uri":"http://59.110.23.25/api/v1/season/e13090ff-c77d-4750-8797-e9c9c4dd2285/"}]
     */

    private int _status;
    /**
     * previous : null
     * total_count : 2
     * offset : 0
     * limit : 20
     * next : null
     */

    private MetaBean meta;
    private String _reason;
    /**
     * description : 权利游戏
     * created_at : 1479180723
     * updated_at : 1481178611
     * object_id : bb15a99c-15f0-46dd-9e51-c139c34fb398
     * name : 权利游戏
     * cover_url : http://xiaoshujing.oss-cn-shanghai.aliyuncs.com/cover/2016/12/08/8a749e50-5593-4144-9ec6-c9b4b8b010a0.jpg
     * resource_uri : http://59.110.23.25/api/v1/season/bb15a99c-15f0-46dd-9e51-c139c34fb398/
     */

    private List<SeasonBean> objects;

    public int get_status() {
        return _status;
    }

    public void set_status(int _status) {
        this._status = _status;
    }

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public String get_reason() {
        return _reason;
    }

    public void set_reason(String _reason) {
        this._reason = _reason;
    }

    public List<SeasonBean> getObjects() {
        return objects;
    }

    public void setObjects(List<SeasonBean> objects) {
        this.objects = objects;
    }

    public static class MetaBean {
        private Object previous;
        private int total_count;
        private int offset;
        private int limit;
        private Object next;

        public Object getPrevious() {
            return previous;
        }

        public void setPrevious(Object previous) {
            this.previous = previous;
        }

        public int getTotal_count() {
            return total_count;
        }

        public void setTotal_count(int total_count) {
            this.total_count = total_count;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public Object getNext() {
            return next;
        }

        public void setNext(Object next) {
            this.next = next;
        }
    }
}
