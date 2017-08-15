package com.xiaoshujing.kid.model;

import java.util.List;

/**
 * Created by LiuXiaocong on 12/6/2016.
 */

public class EditBabyInfoBean {

    /**
     * school : school222
     * grade : grade
     * gender : 1
     * created_at : 1478131291
     * updated_at : 1478131291
     * object_id : 68dc15ee-53a4-43c5-80e2-ab923f4bfbcb
     * nickname : nickname
     * birthday : 2009-09-09
     * user : http://59.110.23.25/api/v1/users/da6e9fe5-c7f3-45f9-94bf-b017e4f5b8c1/
     * babyWish : []
     * _status : 0
     * _reason :
     * resource_uri : http://59.110.23.25/api/v1/baby/68dc15ee-53a4-43c5-80e2-ab923f4bfbcb/
     */

    private String school;
    private String grade;
    private String gender;
    private String created_at;
    private String updated_at;
    private String object_id;
    private String nickname;
    private String birthday;
    private String user;
    private int _status;
    private String _reason;
    private String resource_uri;
    private List<?> babyWish;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getObject_id() {
        return object_id;
    }

    public void setObject_id(String object_id) {
        this.object_id = object_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public String getResource_uri() {
        return resource_uri;
    }

    public void setResource_uri(String resource_uri) {
        this.resource_uri = resource_uri;
    }

    public List<?> getBabyWish() {
        return babyWish;
    }

    public void setBabyWish(List<?> babyWish) {
        this.babyWish = babyWish;
    }
}
