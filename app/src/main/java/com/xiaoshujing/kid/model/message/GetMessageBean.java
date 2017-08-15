package com.xiaoshujing.kid.model.message;

import java.util.List;

/**
 * Created by LiuXiaocong on 12/9/2016.
 */

public class GetMessageBean {

    /**
     * _status : 0
     * meta : {"previous":null,"total_count":100,"offset":0,"limit":20,"next":"http://59.110.23.25/api/v1/user_message/?limit=20&offset=20"}
     * _reason :
     * objects : [{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","extra":{"missionType":0,"type":"mission","object_id":"b53f5460-ef6c-4989-85c7-c0ecc2ee3090"},"created_at":1483799677,"updated_at":1483799677,"object_id":"d969b53d-2447-44a7-b4a8-44f8c50fc942","content":"单推测试完成了一个任务","messageType":1,"resource_uri":"http://59.110.23.25/api/v1/user_message/d969b53d-2447-44a7-b4a8-44f8c50fc942/"},{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","extra":{},"created_at":1483773617,"updated_at":1483773617,"object_id":"0ace5d34-0236-442c-a3b0-b7d1b2973f49","content":"201612241525-儿童端消息推送","messageType":2,"resource_uri":"http://59.110.23.25/api/v1/user_message/0ace5d34-0236-442c-a3b0-b7d1b2973f49/"},{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","extra":{},"created_at":1483772584,"updated_at":1483772584,"object_id":"692063ec-b8ba-46f8-aa88-98734c5e824d","content":"单推测试完成了一个任务","messageType":1,"resource_uri":"http://59.110.23.25/api/v1/user_message/692063ec-b8ba-46f8-aa88-98734c5e824d/"},{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","extra":{},"created_at":1483772507,"updated_at":1483772507,"object_id":"43a15492-756f-41be-b5e9-f5c59318ff69","content":"单推测试完成了一个任务","messageType":1,"resource_uri":"http://59.110.23.25/api/v1/user_message/43a15492-756f-41be-b5e9-f5c59318ff69/"},{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","extra":{},"created_at":1483772438,"updated_at":1483772438,"object_id":"bd39bfd7-3e7e-4773-a644-6b963aa841c7","content":"单推测试完成了一个任务","messageType":1,"resource_uri":"http://59.110.23.25/api/v1/user_message/bd39bfd7-3e7e-4773-a644-6b963aa841c7/"},{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","extra":{},"created_at":1483772401,"updated_at":1483772401,"object_id":"d5e0be25-e37a-4893-8fd0-a45a3e1d331e","content":"单推测试完成了一个任务","messageType":1,"resource_uri":"http://59.110.23.25/api/v1/user_message/d5e0be25-e37a-4893-8fd0-a45a3e1d331e/"},{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","extra":{},"created_at":1483772334,"updated_at":1483772334,"object_id":"9b84d8f5-3bce-429e-9d45-0be297d3d8db","content":"单推测试完成了一个任务","messageType":1,"resource_uri":"http://59.110.23.25/api/v1/user_message/9b84d8f5-3bce-429e-9d45-0be297d3d8db/"},{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","extra":{},"created_at":1483772249,"updated_at":1483772249,"object_id":"2b86921a-815d-4734-9097-8f07ec03164c","content":"单推测试完成了一个任务","messageType":1,"resource_uri":"http://59.110.23.25/api/v1/user_message/2b86921a-815d-4734-9097-8f07ec03164c/"},{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","extra":{},"created_at":1483772213,"updated_at":1483772213,"object_id":"878220ac-4fd3-4c5d-8990-1d124a38349e","content":"单推测试完成了一个任务","messageType":1,"resource_uri":"http://59.110.23.25/api/v1/user_message/878220ac-4fd3-4c5d-8990-1d124a38349e/"},{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","extra":{},"created_at":1483763920,"updated_at":1483763920,"object_id":"532a81b1-0772-4013-afe4-d3baf285f956","content":"201701071233-儿童端消息推送","messageType":1,"resource_uri":"http://59.110.23.25/api/v1/user_message/532a81b1-0772-4013-afe4-d3baf285f956/"},{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","extra":{},"created_at":1483762535,"updated_at":1483762535,"object_id":"7234f33d-3e41-4bdf-ba36-20a0904266ea","content":"201612241525-儿童端消息推送","messageType":1,"resource_uri":"http://59.110.23.25/api/v1/user_message/7234f33d-3e41-4bdf-ba36-20a0904266ea/"},{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","extra":{},"created_at":1483759842,"updated_at":1483759842,"object_id":"664c40bb-dd02-4078-a18b-1ad1f243420b","content":"nickname完成了一个任务","messageType":1,"resource_uri":"http://59.110.23.25/api/v1/user_message/664c40bb-dd02-4078-a18b-1ad1f243420b/"},{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","extra":{},"created_at":1483759621,"updated_at":1483759621,"object_id":"e396a458-5840-48bc-9362-18377b8e29b1","content":"nickname完成了一个任务","messageType":1,"resource_uri":"http://59.110.23.25/api/v1/user_message/e396a458-5840-48bc-9362-18377b8e29b1/"},{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","extra":{},"created_at":1483759584,"updated_at":1483759584,"object_id":"9fc560ae-db52-482d-a88d-b7ebf615cb9d","content":"nickname完成了一个任务","messageType":1,"resource_uri":"http://59.110.23.25/api/v1/user_message/9fc560ae-db52-482d-a88d-b7ebf615cb9d/"},{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","extra":{},"created_at":1483759519,"updated_at":1483759519,"object_id":"00d24b42-a1e5-4906-80ad-060556afa659","content":"nickname完成了一个任务","messageType":1,"resource_uri":"http://59.110.23.25/api/v1/user_message/00d24b42-a1e5-4906-80ad-060556afa659/"},{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","extra":{},"created_at":1483756996,"updated_at":1483756996,"object_id":"494df71e-c4ee-4dad-86b7-28cfcf927e72","content":"nickname完成了一个任务","messageType":1,"resource_uri":"http://59.110.23.25/api/v1/user_message/494df71e-c4ee-4dad-86b7-28cfcf927e72/"},{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","extra":{},"created_at":1483756950,"updated_at":1483756950,"object_id":"7412f190-ea3e-45b1-9e32-378136a9f5b8","content":"nickname完成了一个任务","messageType":1,"resource_uri":"http://59.110.23.25/api/v1/user_message/7412f190-ea3e-45b1-9e32-378136a9f5b8/"},{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","extra":{},"created_at":1483756868,"updated_at":1483756868,"object_id":"ea33bd50-6cc1-46ad-8767-6fb98710928c","content":"nickname完成了一个任务","messageType":1,"resource_uri":"http://59.110.23.25/api/v1/user_message/ea33bd50-6cc1-46ad-8767-6fb98710928c/"},{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","extra":{},"created_at":1483756620,"updated_at":1483756620,"object_id":"01b79a17-6748-47ab-87ec-ad46a9610cc1","content":"nickname完成了一个任务","messageType":1,"resource_uri":"http://59.110.23.25/api/v1/user_message/01b79a17-6748-47ab-87ec-ad46a9610cc1/"},{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","extra":{},"created_at":1483756465,"updated_at":1483756465,"object_id":"b7bd0094-68d5-4b34-adc0-2a982a780c33","content":"nickname完成了一个任务","messageType":1,"resource_uri":"http://59.110.23.25/api/v1/user_message/b7bd0094-68d5-4b34-adc0-2a982a780c33/"}]
     */

    private int _status;
    private MetaEntity meta;
    private String _reason;
    private List<MessageBean> objects;

    public void set_status(int _status) {
        this._status = _status;
    }

    public void setMeta(MetaEntity meta) {
        this.meta = meta;
    }

    public void set_reason(String _reason) {
        this._reason = _reason;
    }

    public void setObjects(List<MessageBean> objects) {
        this.objects = objects;
    }

    public int get_status() {
        return _status;
    }

    public MetaEntity getMeta() {
        return meta;
    }

    public String get_reason() {
        return _reason;
    }

    public List<MessageBean> getObjects() {
        return objects;
    }

    public static class MetaEntity {
        /**
         * previous : null
         * total_count : 100
         * offset : 0
         * limit : 20
         * next : http://59.110.23.25/api/v1/user_message/?limit=20&offset=20
         */

        private Object previous;
        private int total_count;
        private int offset;
        private int limit;
        private String next;

        public void setPrevious(Object previous) {
            this.previous = previous;
        }

        public void setTotal_count(int total_count) {
            this.total_count = total_count;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public Object getPrevious() {
            return previous;
        }

        public int getTotal_count() {
            return total_count;
        }

        public int getOffset() {
            return offset;
        }

        public int getLimit() {
            return limit;
        }

        public String getNext() {
            return next;
        }
    }

    public static class MessageBean {
        /**
         * user : http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/
         * extra : {"missionType":0,"type":"mission","object_id":"b53f5460-ef6c-4989-85c7-c0ecc2ee3090"}
         * created_at : 1483799677
         * updated_at : 1483799677
         * object_id : d969b53d-2447-44a7-b4a8-44f8c50fc942
         * content : 单推测试完成了一个任务
         * messageType : 1
         * resource_uri : http://59.110.23.25/api/v1/user_message/d969b53d-2447-44a7-b4a8-44f8c50fc942/
         */

        private String user;
        private ExtraEntity extra;
        private int created_at;
        private int updated_at;
        private String object_id;
        private String content;
        private int messageType;
        private String resource_uri;
        private boolean isRead;

        public boolean isRead() {
            return isRead;
        }

        public void setRead(boolean read) {
            isRead = read;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public void setExtra(ExtraEntity extra) {
            this.extra = extra;
        }

        public void setCreated_at(int created_at) {
            this.created_at = created_at;
        }

        public void setUpdated_at(int updated_at) {
            this.updated_at = updated_at;
        }

        public void setObject_id(String object_id) {
            this.object_id = object_id;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setMessageType(int messageType) {
            this.messageType = messageType;
        }

        public void setResource_uri(String resource_uri) {
            this.resource_uri = resource_uri;
        }

        public String getUser() {
            return user;
        }

        public ExtraEntity getExtra() {
            return extra;
        }

        public int getCreated_at() {
            return created_at;
        }

        public int getUpdated_at() {
            return updated_at;
        }

        public String getObject_id() {
            return object_id;
        }

        public String getContent() {
            return content;
        }

        public int getMessageType() {
            return messageType;
        }

        public String getResource_uri() {
            return resource_uri;
        }

        public static class ExtraEntity {
            /**
             * missionType : 0
             * type : mission
             * object_id : b53f5460-ef6c-4989-85c7-c0ecc2ee3090
             */

            private int missionType;
            private String type;
            private String object_id;
            private int productType;
            private String season_id;
            private String episode_id;

            public void setProductType(int productType) {
                this.productType = productType;
            }

            public void setSeason_id(String season_id) {
                this.season_id = season_id;
            }

            public void setEpisode_id(String episode_id) {
                this.episode_id = episode_id;
            }

            public int getProductType() {
                return productType;
            }

            public String getSeason_id() {
                return season_id;
            }

            public String getEpisode_id() {
                return episode_id;
            }

            public void setMissionType(int missionType) {
                this.missionType = missionType;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setObject_id(String object_id) {
                this.object_id = object_id;
            }

            public int getMissionType() {
                return missionType;
            }

            public String getType() {
                return type;
            }

            public String getObject_id() {
                return object_id;
            }
        }
    }
}
