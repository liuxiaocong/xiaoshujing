package com.xiaoshujing.kid.model;

/**
 * Created by LiuXiaocong on 12/5/2016.
 */

public class GetInfoBean {

    /**
     * _status : 0
     * code : b4f1b47f758734b48176e440659c30bf
     * _reason :
     * user : {"username":"15578158426","defaultCopybook":"0485da61-124b-4744-bc25-4ed043175315","gender":"1","created_at":1478077122,"updated_at":1481763748,"object_id":"0bd08822-b442-4ad3-814b-688726a71f23","phone":"15578158426","avatar_url":"http://xiaoshujing.oss-cn-shanghai.aliyuncs.com/avatar/2016/12/05/3cfaeda8-bad5-11e6-8c11-00163e08076a.png","willAutoUpload":true,"vibrate":true,"date_joined":1478077122,"defaultAddress":"d7976b25-da21-481f-ae78-a95c68ab9aaf","baby":"074ba30e-1d9e-4819-b5e8-c9b2d42d7db9","sounds":true,"api_key":"fed564faf5a1b0e34450e9df094e7557edf945e6","nickname":"馒头","region":"","resource_uri":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/"}
     */

    private int _status;
    private String code;
    private String _reason;
    /**
     * username : 15578158426
     * defaultCopybook : 0485da61-124b-4744-bc25-4ed043175315
     * gender : 1
     * created_at : 1478077122
     * updated_at : 1481763748
     * object_id : 0bd08822-b442-4ad3-814b-688726a71f23
     * phone : 15578158426
     * avatar_url : http://xiaoshujing.oss-cn-shanghai.aliyuncs.com/avatar/2016/12/05/3cfaeda8-bad5-11e6-8c11-00163e08076a.png
     * willAutoUpload : true
     * vibrate : true
     * date_joined : 1478077122
     * defaultAddress : d7976b25-da21-481f-ae78-a95c68ab9aaf
     * baby : 074ba30e-1d9e-4819-b5e8-c9b2d42d7db9
     * sounds : true
     * api_key : fed564faf5a1b0e34450e9df094e7557edf945e6
     * nickname : 馒头
     * region :
     * resource_uri : http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/
     */

    private UserBean user;

    public int get_status() {
        return _status;
    }

    public void set_status(int _status) {
        this._status = _status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String get_reason() {
        return _reason;
    }

    public void set_reason(String _reason) {
        this._reason = _reason;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        private String username;
        private String defaultCopybook;
        private String gender;
        private int created_at;
        private int updated_at;
        private String object_id;
        private String phone;
        private String avatar_url;
        private boolean willAutoUpload;
        private boolean vibrate;
        private int date_joined;
        private String defaultAddress;
        private String baby;
        private boolean sounds;
        private String api_key;
        private String nickname;
        private String region;
        private String resource_uri;
        private String mac;

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getDefaultCopybook() {
            return defaultCopybook;
        }

        public void setDefaultCopybook(String defaultCopybook) {
            this.defaultCopybook = defaultCopybook;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public boolean isWillAutoUpload() {
            return willAutoUpload;
        }

        public void setWillAutoUpload(boolean willAutoUpload) {
            this.willAutoUpload = willAutoUpload;
        }

        public boolean isVibrate() {
            return vibrate;
        }

        public void setVibrate(boolean vibrate) {
            this.vibrate = vibrate;
        }

        public int getDate_joined() {
            return date_joined;
        }

        public void setDate_joined(int date_joined) {
            this.date_joined = date_joined;
        }

        public String getDefaultAddress() {
            return defaultAddress;
        }

        public void setDefaultAddress(String defaultAddress) {
            this.defaultAddress = defaultAddress;
        }

        public String getBaby() {
            return baby;
        }

        public void setBaby(String baby) {
            this.baby = baby;
        }

        public boolean isSounds() {
            return sounds;
        }

        public void setSounds(boolean sounds) {
            this.sounds = sounds;
        }

        public String getApi_key() {
            return api_key;
        }

        public void setApi_key(String api_key) {
            this.api_key = api_key;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getResource_uri() {
            return resource_uri;
        }

        public void setResource_uri(String resource_uri) {
            this.resource_uri = resource_uri;
        }
    }
}
