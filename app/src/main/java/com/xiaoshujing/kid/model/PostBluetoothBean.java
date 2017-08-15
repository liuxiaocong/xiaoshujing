package com.xiaoshujing.kid.model;

/**
 * Created by mac on 17/1/3.
 */

public class PostBluetoothBean {


    /**
     * typeCode : E5
     * created_at : 1483439154
     * updated_at : 1483439154
     * object_id : 78f48288-ac58-4134-883e-ebfc6fad62ec
     * isAnalyzed : false
     * user : http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/
     * _status : 0
     * resource_uri : http://59.110.23.25/api/v1/hwdata/78f48288-ac58-4134-883e-ebfc6fad62ec/
     * _reason :
     * dataConversion :
     * dataSource : EECCE5XXXXXX02FF
     */

    private String typeCode;
    private int created_at;
    private int updated_at;
    private String object_id;
    private boolean isAnalyzed;
    private String user;
    private int _status;
    private String resource_uri;
    private String _reason;
    private String dataConversion;
    private String dataSource;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
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

    public boolean isIsAnalyzed() {
        return isAnalyzed;
    }

    public void setIsAnalyzed(boolean isAnalyzed) {
        this.isAnalyzed = isAnalyzed;
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

    public String getDataConversion() {
        return dataConversion;
    }

    public void setDataConversion(String dataConversion) {
        this.dataConversion = dataConversion;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
}
