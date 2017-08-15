package com.xiaoshujing.kid.model.message;

/**
 * Created by LiuXiaocong on 12/9/2016.
 */

public class SendMsgToParentBean {

    /**
     * messageType : 1
     * created_at : 1481075136
     * updated_at : 1481075136
     * object_id : 77e05a31-2297-4929-803c-0f410bb33754
     * content : 好好学习,天天向上
     * user : http://59.110.23.25/api/v1/users/7732f9b2-85cb-4851-bea9-056085cec6aa/
     * _status : 0
     * _reason :
     * resource_uri : http://59.110.23.25/api/v1/user_message/77e05a31-2297-4929-803c-0f410bb33754/
     */

    private int messageType;
    private int created_at;
    private int updated_at;
    private String object_id;
    private String content;
    private String user;
    private int _status;
    private String _reason;
    private String resource_uri;

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
