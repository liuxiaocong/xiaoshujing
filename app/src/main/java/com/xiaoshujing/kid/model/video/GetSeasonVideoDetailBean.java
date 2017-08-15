package com.xiaoshujing.kid.model.video;

import com.xiaoshujing.kid.model.SeasonBean;

import java.io.Serializable;

/**
 * Created by LiuXiaocong on 12/9/2016.
 */

public class GetSeasonVideoDetailBean implements Serializable {

    /**
     * description : 无耻之徒第一集
     * video_url : http://xiaoshujing.oss-cn-shanghai.aliyuncs.com/video/999.mp4
     * season : {"description":"无耻之徒无耻之徒无耻之徒无耻之徒无耻之徒","created_at":1479145429,"updated_at":1479145429,"object_id":"54e38025-dee5-4c3c-ba7a-ee8c2643bbb7","name":"无耻之徒","resource_uri":"http://59.110.23.25/api/v1/season/54e38025-dee5-4c3c-ba7a-ee8c2643bbb7/"}
     * created_at : 1479147932
     * updated_at : 1479149914
     * object_id : 41f2f6dd-5452-454a-a913-f6ea4fa73ae7
     * isFree : true
     * _status : 0
     * cover_url:""
     * resource_uri : http://59.110.23.25/api/v1/episode/41f2f6dd-5452-454a-a913-f6ea4fa73ae7/
     * _reason :
     * name : 第一集
     * product_id:"122";
     */

    private String description;
    private String cover_url;
    private String video_url;
    /**
     * description : 无耻之徒无耻之徒无耻之徒无耻之徒无耻之徒
     * created_at : 1479145429
     * updated_at : 1479145429
     * object_id : 54e38025-dee5-4c3c-ba7a-ee8c2643bbb7
     * name : 无耻之徒
     * resource_uri : http://59.110.23.25/api/v1/season/54e38025-dee5-4c3c-ba7a-ee8c2643bbb7/
     */

    private SeasonBean season;
    private int created_at;
    private int updated_at;
    private String object_id;
    private boolean isFree;
    private int _status;
    private String resource_uri;
    private String _reason;
    private String name;
    private String product_id;

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setFree(boolean free) {

        isFree = free;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public boolean isFree() {
        return isFree;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public SeasonBean getSeason() {
        return season;
    }

    public void setSeason(SeasonBean season) {
        this.season = season;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public String getObject_id() {
        return object_id;
    }

    public void setObject_id(String object_id) {
        this.object_id = object_id;
    }

    public boolean isIsFree() {
        return isFree;
    }

    public void setIsFree(boolean isFree) {
        this.isFree = isFree;
    }

    public int get_status() {
        return _status;
    }

    public void set_status(int _status) {
        this._status = _status;
    }

    public String getResource_uri() {
        return resource_uri;
    }

    public void setResource_uri(String resource_uri) {
        this.resource_uri = resource_uri;
    }

    public String get_reason() {
        return _reason;
    }

    public void set_reason(String _reason) {
        this._reason = _reason;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
