package com.xiaoshujing.kid.model;

/**
 * Created by LiuXiaocong on 12/19/2016.
 */

public class BindTokenBean {

    /**
     * pk : 7
     * model : notifications.androiddevice
     * fields : {"deactivated_at":"x","service":4,"os_version":"","udid":"34c762421b5147a3a3086e3a25cb9bff","last_notified_at":"2016-12-06T09:12:13.054Z","is_active":true,"added_at":"2016-12-06T09:12:13.054Z","platform":"","token":"abcdffsccfasdfsadasdf_3456789012449678901236","user":"0bd08822-b442-4ad3-814b-688726a71f23","device_type":2,"display":""}
     */

    private int pk;
    private String model;
    /**
     * deactivated_at : x
     * service : 4
     * os_version :
     * udid : 34c762421b5147a3a3086e3a25cb9bff
     * last_notified_at : 2016-12-06T09:12:13.054Z
     * is_active : true
     * added_at : 2016-12-06T09:12:13.054Z
     * platform :
     * token : abcdffsccfasdfsadasdf_3456789012449678901236
     * user : 0bd08822-b442-4ad3-814b-688726a71f23
     * device_type : 2
     * display :
     */

    private FieldsBean fields;

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public FieldsBean getFields() {
        return fields;
    }

    public void setFields(FieldsBean fields) {
        this.fields = fields;
    }

    public static class FieldsBean {
        private String deactivated_at;
        private int service;
        private String os_version;
        private String udid;
        private String last_notified_at;
        private boolean is_active;
        private String added_at;
        private String platform;
        private String token;
        private String user;
        private int device_type;
        private String display;

        public String getDeactivated_at() {
            return deactivated_at;
        }

        public void setDeactivated_at(String deactivated_at) {
            this.deactivated_at = deactivated_at;
        }

        public int getService() {
            return service;
        }

        public void setService(int service) {
            this.service = service;
        }

        public String getOs_version() {
            return os_version;
        }

        public void setOs_version(String os_version) {
            this.os_version = os_version;
        }

        public String getUdid() {
            return udid;
        }

        public void setUdid(String udid) {
            this.udid = udid;
        }

        public String getLast_notified_at() {
            return last_notified_at;
        }

        public void setLast_notified_at(String last_notified_at) {
            this.last_notified_at = last_notified_at;
        }

        public boolean isIs_active() {
            return is_active;
        }

        public void setIs_active(boolean is_active) {
            this.is_active = is_active;
        }

        public String getAdded_at() {
            return added_at;
        }

        public void setAdded_at(String added_at) {
            this.added_at = added_at;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public int getDevice_type() {
            return device_type;
        }

        public void setDevice_type(int device_type) {
            this.device_type = device_type;
        }

        public String getDisplay() {
            return display;
        }

        public void setDisplay(String display) {
            this.display = display;
        }
    }
}
