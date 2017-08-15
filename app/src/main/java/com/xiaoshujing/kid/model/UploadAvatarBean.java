package com.xiaoshujing.kid.model;

/**
 * Created by LiuXiaocong on 12/5/2016.
 */

public class UploadAvatarBean {

    /**
     * image_url : http://xiaoshujing.oss-cn-shanghai.aliyuncs.com//moments/2016/11/01/19eccf30-9fd1-11e6-bdf2-7ce9d3bf10e7.jpg
     * _status : 0
     * _reason :
     */

    private String image_url;
    private int _status;
    private String _reason;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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
}
