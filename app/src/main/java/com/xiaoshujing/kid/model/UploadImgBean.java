package com.xiaoshujing.kid.model;

import java.util.List;

/**
 * Created by LiuXiaocong on 12/14/2016.
 */

public class UploadImgBean {

    /**
     * _status : 0
     * _reason :
     * image_urls : ["http://xiaoshujing.oss-cn-shanghai.aliyuncs.com/moments/2016/11/01/19eccf30-9fd1-11e6-bdf2-7ce9d3bf10e7.jpg","http://xiaoshujing.oss-cn-shanghai.aliyuncs.com/moments/2016/11/01/1a00cc5e-9fd1-11e6-8cae-7ce9d3bf10e7.jpg","http://xiaoshujing.oss-cn-shanghai.aliyuncs.com/moments/2016/11/01/1a123180-9fd1-11e6-9f27-7ce9d3bf10e7.jpg"]
     */

    private int _status;
    private String _reason;
    private List<String> image_urls;

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

    public List<String> getImage_urls() {
        return image_urls;
    }

    public void setImage_urls(List<String> image_urls) {
        this.image_urls = image_urls;
    }
}
