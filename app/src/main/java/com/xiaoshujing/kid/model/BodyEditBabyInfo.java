package com.xiaoshujing.kid.model;

/**
 * Created by LiuXiaocong on 12/6/2016.
 */

public class BodyEditBabyInfo {

    /**
     * nickname : nickname
     * gender : 1
     * school : school222
     * grade : grade
     * birthday : 2009-09-09
     * avatar_url : http://www.baidu.com/1.jpg
     */

    private String nickname;
    private int gender;
    private String school;
    private String grade;
    private String birthday;
    private String avatar_url;

    private BodyEditBabyInfo(Builder builder) {
        setNickname(builder.nickname);
        setGender(builder.gender);
        setSchool(builder.school);
        setGrade(builder.grade);
        setBirthday(builder.birthday);
        setAvatar_url(builder.avatar_url);
    }
    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(BodyEditBabyInfo copy) {
        Builder builder = new Builder();
        builder.nickname = copy.nickname;
        builder.gender = copy.gender;
        builder.school = copy.school;
        builder.grade = copy.grade;
        builder.birthday = copy.birthday;
        builder.avatar_url = copy.avatar_url;
        return builder;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public static final class Builder {
        private String nickname;
        private int gender;
        private String school;
        private String grade;
        private String birthday;
        private String avatar_url;

        public Builder() {
        }

        public Builder nickname(String val) {
            nickname = val;
            return this;
        }

        public Builder gender(int val) {
            gender = val;
            return this;
        }

        public Builder school(String val) {
            school = val;
            return this;
        }

        public Builder grade(String val) {
            grade = val;
            return this;
        }

        public Builder birthday(String val) {
            birthday = val;
            return this;
        }

        public Builder avatar_url(String val) {
            avatar_url = val;
            return this;
        }

        public BodyEditBabyInfo build() {
            return new BodyEditBabyInfo(this);
        }
    }
}
