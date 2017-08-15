package com.xiaoshujing.kid.model;

import java.util.List;

/**
 * Created by LiuXiaocong on 12/15/2016.
 */

public class PostMyMomentBean {

    /**
     * status : 2
     * product : http://59.110.23.25/api/v1/product/0737da26-7b70-4fdf-9b80-58c32b28739d/
     * practiceMinutes : 0
     * img_urls : ["http://www.baidu.com/","www.google.com/"]
     * updated_at : 1479965889
     * overallScore : 99
     * studyScore : 99.2
     * baby : http://59.110.23.25/api/v1/baby/074ba30e-1d9e-4819-b5e8-c9b2d42d7db9/
     * user : http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/
     * created_at : 1479958645
     * object_id : 3fe12203-1c3e-4a91-ab6c-470a0d1a42db
     * content : content test test test
     * _status : 0
     * likesCount : 0
     * _reason :
     * sitScore : 0
     * resource_uri : http://59.110.23.25/api/v1/my_moments/3fe12203-1c3e-4a91-ab6c-470a0d1a42db/
     */

    private int status;
    private String product;
    private int practiceMinutes;
    private int updated_at;
    private double overallScore;
    private double studyScore;
    private String baby;
    private String user;
    private int created_at;
    private String object_id;
    private String content;
    private int _status;
    private int likesCount;
    private String _reason;
    private double sitScore;
    private String resource_uri;
    private List<String> img_urls;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getPracticeMinutes() {
        return practiceMinutes;
    }

    public void setPracticeMinutes(int practiceMinutes) {
        this.practiceMinutes = practiceMinutes;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(double overallScore) {
        this.overallScore = overallScore;
    }

    public double getStudyScore() {
        return studyScore;
    }

    public void setStudyScore(double studyScore) {
        this.studyScore = studyScore;
    }

    public String getBaby() {
        return baby;
    }

    public void setBaby(String baby) {
        this.baby = baby;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public String getObject_id() {
        return object_id;
    }

    public void setObject_id(String object_id) {
        this.object_id = object_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int get_status() {
        return _status;
    }

    public void set_status(int _status) {
        this._status = _status;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public String get_reason() {
        return _reason;
    }

    public void set_reason(String _reason) {
        this._reason = _reason;
    }

    public double getSitScore() {
        return sitScore;
    }

    public void setSitScore(double sitScore) {
        this.sitScore = sitScore;
    }

    public String getResource_uri() {
        return resource_uri;
    }

    public void setResource_uri(String resource_uri) {
        this.resource_uri = resource_uri;
    }

    public List<String> getImg_urls() {
        return img_urls;
    }

    public void setImg_urls(List<String> img_urls) {
        this.img_urls = img_urls;
    }
}
