package com.xiaoshujing.kid.model.video;

import com.xiaoshujing.kid.model.SeasonBean;

/**
 * Created by LiuXiaocong on 12/9/2016.
 */

public class GetSeasonPaidVideoDetailBean {

    /**
     * description : 第二集
     * video_url : http://xiaoshujing.oss-cn-shanghai.aliyuncs.com/video/999.mp4
     * season : {"description":"火影忍者火影忍者火影忍者火影忍者","created_at":1479151816,"updated_at":1479151816,"object_id":"e13090ff-c77d-4750-8797-e9c9c4dd2285","name":"火影忍者","resource_uri":"http://59.110.23.25/api/v1/season/e13090ff-c77d-4750-8797-e9c9c4dd2285/"}
     * created_at : 1479151856
     * updated_at : 1479151856
     * object_id : 9a90a6cd-02f2-4a7f-a776-b905dcc55aa0
     * isFree : false
     * _status : 0
     * resource_uri : http://59.110.23.25/api/v1/paid_episode/9a90a6cd-02f2-4a7f-a776-b905dcc55aa0/
     * _reason :
     * name : 第二集
     */

    private String description;
    private String video_url;
    /**
     * description : 火影忍者火影忍者火影忍者火影忍者
     * created_at : 1479151816
     * updated_at : 1479151816
     * object_id : e13090ff-c77d-4750-8797-e9c9c4dd2285
     * name : 火影忍者
     * resource_uri : http://59.110.23.25/api/v1/season/e13090ff-c77d-4750-8797-e9c9c4dd2285/
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
