package com.xiaoshujing.kid.model.message;

/**
 * Created by LiuXiaocong on 12/12/2016.
 */

public class DeleteMessageBean {

    /**
     * messageType : 2
     * created_at : 1481344834
     * updated_at : 1481344834
     * object_id : db57d8f4-46d2-469e-aa83-b1c45d440fc1
     * content : 你接受到一个新任务
     * user : http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/
     * _status : 0
     * _reason : delete successfully
     * resource_uri : http://59.110.23.25/api/v1/user_message/db57d8f4-46d2-469e-aa83-b1c45d440fc1/
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
