package com.xiaoshujing.kid.model;

import java.io.Serializable;

/**
 * Created by LiuXiaocong on 12/9/2016.
 */

public class SeasonBean implements Serializable {

    /**
     * cover_url : http://xiaoshujing.oss-cn-shanghai.aliyuncs.com/cover/2016/12/12/5084d3bf-53d8-4fd8-a1d8-b3315f981d45.jpg
     * description : 儿童眼保健操
     * created_at : 1481510058
     * updated_at : 1481510058
     * object_id : 161eadbf-8d8d-4119-9e2e-e09d2dfd3893
     * is_free : false
     * resource_uri : http://59.110.23.25/api/v1/season/161eadbf-8d8d-4119-9e2e-e09d2dfd3893/
     * seasonType : 2
     * name : 儿童眼保健操
     */

    private String cover_url;
    private String description;
    private int created_at;
    private int updated_at;
    private String object_id;
    private boolean is_free;
    private String resource_uri;
    private int seasonType;
    private String name;

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public boolean isIs_free() {
        return is_free;
    }

    public void setIs_free(boolean is_free) {
        this.is_free = is_free;
    }

    public String getResource_uri() {
        return resource_uri;
    }

    public void setResource_uri(String resource_uri) {
        this.resource_uri = resource_uri;
    }

    public int getSeasonType() {
        return seasonType;
    }

    public void setSeasonType(int seasonType) {
        this.seasonType = seasonType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
