package com.xiaoshujing.kid.model;

import java.util.List;

/**
 * Created by LiuXiaocong on 1/4/2017.
 */

public class BabyInfoBean {

    /**
     * moments : ["http://59.110.23.25/api/v1/moments/950e4a2e-5cf0-4de9-b09e-b5a3e0ff6eea/","http://59.110.23.25/api/v1/moments/2a17e323-ada1-441c-a638-4bc9f067ba45/","http://59.110.23.25/api/v1/moments/c523edb3-484d-4070-b664-e401ff3cae5c/","http://59.110.23.25/api/v1/moments/1ab301b2-524c-44c3-a265-edc13b1e1868/","http://59.110.23.25/api/v1/moments/d5fa25c0-720c-4585-a279-573afdb5d7fd/","http://59.110.23.25/api/v1/moments/b40c733c-c400-4eaf-8f2b-48eeeb95f86d/","http://59.110.23.25/api/v1/moments/c12fd519-9641-4886-bcd4-ed50369aebe5/","http://59.110.23.25/api/v1/moments/c2cf1ee1-0305-4bda-8d1b-b302d97dc5be/","http://59.110.23.25/api/v1/moments/9a2ed727-93d8-4a74-989b-863c1c1b14ef/","http://59.110.23.25/api/v1/moments/7196208e-4c7e-4c5c-9012-62182a9129c5/","http://59.110.23.25/api/v1/moments/3d8b29fa-f894-445d-a299-43de04821860/","http://59.110.23.25/api/v1/moments/d2d759cc-7798-485a-bb5e-a082a02400b1/","http://59.110.23.25/api/v1/moments/d94b7ab9-fdb8-48ac-a367-22e9d58e08a9/","http://59.110.23.25/api/v1/moments/2e1fa1d6-c594-4c81-ad70-370853da3432/","http://59.110.23.25/api/v1/moments/fb42d56f-8d5b-42f2-b0d8-05b5a5880430/","http://59.110.23.25/api/v1/moments/131df8cc-5d61-4e76-88d4-b04d4b615708/","http://59.110.23.25/api/v1/moments/f14eae18-e081-49f4-b138-41d1967b1db7/","http://59.110.23.25/api/v1/moments/4aebb9b5-2667-4bd9-b52e-534800d1de36/","http://59.110.23.25/api/v1/moments/213e6fe1-b79d-4c17-805e-c6b18641231f/","http://59.110.23.25/api/v1/moments/62115923-55b2-4e5d-9bfb-761a05bd3dab/","http://59.110.23.25/api/v1/moments/fb650a63-a498-413b-bb30-b6e52fe10cee/","http://59.110.23.25/api/v1/moments/b4d8f500-d73f-43ec-aee8-07fb8e27e56b/","http://59.110.23.25/api/v1/moments/aa903abf-d1a1-41ea-87a4-3758f48a2652/","http://59.110.23.25/api/v1/moments/ac36ab8b-73a2-410d-8c09-f47d43735dd7/","http://59.110.23.25/api/v1/moments/7d30fbcb-76df-4311-8d05-b7e84624cd14/","http://59.110.23.25/api/v1/moments/b50387e0-bd1f-4605-ac83-ce5d63d2afd2/","http://59.110.23.25/api/v1/moments/7f435dea-aec4-4727-b2cb-a996c993e72a/","http://59.110.23.25/api/v1/moments/f89c1426-438e-4e1b-baf2-ee463888d810/","http://59.110.23.25/api/v1/moments/fcfe64e4-0501-46f4-b7b9-28f2a2f1f847/","http://59.110.23.25/api/v1/moments/4f61bdba-0705-4736-a245-53bae6645de1/","http://59.110.23.25/api/v1/moments/176980f0-0861-4d16-9349-83c34fbbc4e9/","http://59.110.23.25/api/v1/moments/74cf85ce-0abf-470e-b65f-66de381f1647/","http://59.110.23.25/api/v1/moments/a8f46706-ab2a-4c6b-bf0c-2dfc8504d9a7/"]
     * updated_at : 1483501391
     * birthday : 2009-09-09
     * user : http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/
     * babyWish : ["http://59.110.23.25/api/v1/baby_wish/95a6d1d2-2021-4ea5-9421-a64515936e53/","http://59.110.23.25/api/v1/baby_wish/3fc1eda4-c50a-4890-b214-9a62403eb220/","http://59.110.23.25/api/v1/baby_wish/bf1832d7-b288-47a5-8486-c40759b3bcf1/","http://59.110.23.25/api/v1/baby_wish/af81aeee-6a5e-4a4b-9cb2-e83bd666ad83/","http://59.110.23.25/api/v1/baby_wish/86f08282-d4a9-4c6b-bb55-b14ad6653b22/","http://59.110.23.25/api/v1/baby_wish/7c19436a-80b4-460d-b171-55d1e69cbe8c/","http://59.110.23.25/api/v1/baby_wish/8d29424b-4bdc-4bef-935d-aa4ee7e4aa58/","http://59.110.23.25/api/v1/baby_wish/3a247c69-2f76-4572-a8bd-a2cce1c4092f/","http://59.110.23.25/api/v1/baby_wish/d9fe56aa-429d-4d79-833d-410588128a07/","http://59.110.23.25/api/v1/baby_wish/17f0aa9d-328d-4a3a-af16-4d9726a0790f/","http://59.110.23.25/api/v1/baby_wish/506816e0-2b25-4ee6-adc4-7d04903c2442/","http://59.110.23.25/api/v1/baby_wish/4f7ccde4-503f-4ce0-b1ac-d65e5b5682a8/","http://59.110.23.25/api/v1/baby_wish/8458849e-1ae3-4896-8f25-e8662abc505b/","http://59.110.23.25/api/v1/baby_wish/64fef873-43ea-40d1-88cf-73fc6aa0ec9e/","http://59.110.23.25/api/v1/baby_wish/09e0f4d6-6b13-4a07-91ac-c1a51e437ea6/","http://59.110.23.25/api/v1/baby_wish/50d2be02-91b8-4f3f-9962-5f8d26c3e665/","http://59.110.23.25/api/v1/baby_wish/172b9550-59e8-4d16-bd5d-98337ac0959e/","http://59.110.23.25/api/v1/baby_wish/5ad4f501-17b9-4e08-bd2f-f7f9dee39a5b/","http://59.110.23.25/api/v1/baby_wish/dc536435-3422-4525-a371-a59383aed322/","http://59.110.23.25/api/v1/baby_wish/443e1c31-cb70-4f7e-89ab-5e5b2011ed73/","http://59.110.23.25/api/v1/baby_wish/4d6be033-bb5d-49e7-ac2f-d3807bfbcc18/","http://59.110.23.25/api/v1/baby_wish/d684f0f6-7420-4489-8041-5e809e84b083/"]
     * nickname : 单推测试
     * school : 222
     * credit : 435
     * gender : 1
     * created_at : 1478077148
     * object_id : 074ba30e-1d9e-4819-b5e8-c9b2d42d7db9
     * grade : grade
     * avatar_url : http://xiaoshujing.oss-cn-shanghai.aliyuncs.com/avatar/2016/12/14/0392d636-c1d1-11e6-8c11-00163e08076a.png
     * _status : 0
     * _reason :
     * resource_uri : http://59.110.23.25/api/v1/baby/074ba30e-1d9e-4819-b5e8-c9b2d42d7db9/
     */

    private int updated_at;
    private String birthday;
    private String user;
    private String nickname;
    private String school;
    private int credit;
    private String gender;
    private int created_at;
    private String object_id;
    private String grade;
    private String avatar_url;
    private int _status;
    private String _reason;
    private String resource_uri;
    private List<String> moments;
    private List<String> babyWish;

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
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

    public List<String> getMoments() {
        return moments;
    }

    public void setMoments(List<String> moments) {
        this.moments = moments;
    }

    public List<String> getBabyWish() {
        return babyWish;
    }

    public void setBabyWish(List<String> babyWish) {
        this.babyWish = babyWish;
    }
}
