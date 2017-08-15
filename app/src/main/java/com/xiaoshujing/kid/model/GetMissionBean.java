package com.xiaoshujing.kid.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LiuXiaocong on 12/8/2016.
 */

public class GetMissionBean {

    /**
     * _status : 0
     * meta : {"previous":null,"total_count":5,"offset":0,"limit":20,"next":null}
     * _reason :
     * objects : [{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","missionType":0,"product":null,"description":"SSR,SSR,SSREDIT","created_at":1481099802,"updated_at":1481099839,"object_id":"fd806324-44fe-41f9-aa50-cc65397a6148","finishedDays":0,"setDays":1,"exercisePages":0,"baby":"http://59.110.23.25/api/v1/baby/074ba30e-1d9e-4819-b5e8-c9b2d42d7db9/","babyWish":{"product":null,"description":"我要9999","created_at":1481099514,"updated_at":1481099514,"object_id":"3a247c69-2f76-4572-a8bd-a2cce1c4092f","wishProgress":0,"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","baby":"http://59.110.23.25/api/v1/baby/074ba30e-1d9e-4819-b5e8-c9b2d42d7db9/","resource_uri":"http://59.110.23.25/api/v1/baby_wish/3a247c69-2f76-4572-a8bd-a2cce1c4092f/","wishType":0,"name":"9998"},"resource_uri":"http://59.110.23.25/api/v1/mission/fd806324-44fe-41f9-aa50-cc65397a6148/","missionStatus":1,"name":"first test"},{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","missionType":0,"product":null,"description":"SSR,SSR,SSREDIT","created_at":1481099708,"updated_at":1481106129,"object_id":"b10f43d2-2778-4c0e-a488-b1d7ef849d03","finishedDays":0,"setDays":1,"exercisePages":0,"baby":"http://59.110.23.25/api/v1/baby/074ba30e-1d9e-4819-b5e8-c9b2d42d7db9/","babyWish":{"product":null,"description":"我要9999","created_at":1481099514,"updated_at":1481099514,"object_id":"3a247c69-2f76-4572-a8bd-a2cce1c4092f","wishProgress":0,"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","baby":"http://59.110.23.25/api/v1/baby/074ba30e-1d9e-4819-b5e8-c9b2d42d7db9/","resource_uri":"http://59.110.23.25/api/v1/baby_wish/3a247c69-2f76-4572-a8bd-a2cce1c4092f/","wishType":0,"name":"9998"},"resource_uri":"http://59.110.23.25/api/v1/mission/b10f43d2-2778-4c0e-a488-b1d7ef849d03/","missionStatus":1,"name":"first test"},{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","missionType":0,"product":null,"description":"SSR,SSR,SSREDIT","created_at":1480331757,"updated_at":1480331787,"object_id":"afbc3696-0a39-4a4c-82cf-53103b854915","finishedDays":0,"setDays":1,"exercisePages":5,"baby":"http://59.110.23.25/api/v1/baby/7c9df0f7-9f74-45bf-9703-c28c4dbfd61c/","babyWish":{"product":null,"description":"我要SSR，我要SSR，我要SSR","created_at":1478520456,"updated_at":1478520456,"object_id":"204918f8-bde1-476d-920c-fb11e37ed9f2","wishProgress":0,"user":"http://59.110.23.25/api/v1/users/45bcc93d-e260-4e5a-b722-29389f5132ed/","baby":"http://59.110.23.25/api/v1/baby/7c9df0f7-9f74-45bf-9703-c28c4dbfd61c/","resource_uri":"http://59.110.23.25/api/v1/baby_wish/204918f8-bde1-476d-920c-fb11e37ed9f2/","wishType":1,"name":"SSR"},"resource_uri":"http://59.110.23.25/api/v1/mission/afbc3696-0a39-4a4c-82cf-53103b854915/","missionStatus":0,"name":"first test"},{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","missionType":0,"product":null,"description":"SSR,SSR,SSR","created_at":1480331727,"updated_at":1481106130,"object_id":"92b57d19-bc32-436b-9740-3aad51d6b893","finishedDays":0,"setDays":1,"exercisePages":5,"baby":"http://59.110.23.25/api/v1/baby/7c9df0f7-9f74-45bf-9703-c28c4dbfd61c/","babyWish":{"product":null,"description":"我要SSR，我要SSR，我要SSR","created_at":1478520456,"updated_at":1478520456,"object_id":"204918f8-bde1-476d-920c-fb11e37ed9f2","wishProgress":0,"user":"http://59.110.23.25/api/v1/users/45bcc93d-e260-4e5a-b722-29389f5132ed/","baby":"http://59.110.23.25/api/v1/baby/7c9df0f7-9f74-45bf-9703-c28c4dbfd61c/","resource_uri":"http://59.110.23.25/api/v1/baby_wish/204918f8-bde1-476d-920c-fb11e37ed9f2/","wishType":1,"name":"SSR"},"resource_uri":"http://59.110.23.25/api/v1/mission/92b57d19-bc32-436b-9740-3aad51d6b893/","missionStatus":1,"name":"alltest"},{"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","missionType":0,"product":null,"description":"SSR,SSR,SSR","created_at":1480331714,"updated_at":1480331714,"object_id":"6d5b9473-2fe6-471e-b5ab-38c50b4f29ae","finishedDays":0,"setDays":1,"exercisePages":5,"baby":"http://59.110.23.25/api/v1/baby/7c9df0f7-9f74-45bf-9703-c28c4dbfd61c/","babyWish":{"product":null,"description":"我要SSR，我要SSR，我要SSR","created_at":1478520456,"updated_at":1478520456,"object_id":"204918f8-bde1-476d-920c-fb11e37ed9f2","wishProgress":0,"user":"http://59.110.23.25/api/v1/users/45bcc93d-e260-4e5a-b722-29389f5132ed/","baby":"http://59.110.23.25/api/v1/baby/7c9df0f7-9f74-45bf-9703-c28c4dbfd61c/","resource_uri":"http://59.110.23.25/api/v1/baby_wish/204918f8-bde1-476d-920c-fb11e37ed9f2/","wishType":1,"name":"SSR"},"resource_uri":"http://59.110.23.25/api/v1/mission/6d5b9473-2fe6-471e-b5ab-38c50b4f29ae/","missionStatus":0,"name":"alltest"}]
     */

    private int _status;
    /**
     * previous : null
     * total_count : 5
     * offset : 0
     * limit : 20
     * next : null
     */

    private MetaBean meta;
    private String _reason;
    /**
     * user : http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/
     * missionType : 0
     * product : null
     * description : SSR,SSR,SSREDIT
     * created_at : 1481099802
     * updated_at : 1481099839
     * object_id : fd806324-44fe-41f9-aa50-cc65397a6148
     * finishedDays : 0
     * setDays : 1
     * exercisePages : 0
     * baby : http://59.110.23.25/api/v1/baby/074ba30e-1d9e-4819-b5e8-c9b2d42d7db9/
     * babyWish : {"product":null,"description":"我要9999","created_at":1481099514,"updated_at":1481099514,"object_id":"3a247c69-2f76-4572-a8bd-a2cce1c4092f","wishProgress":0,"user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","baby":"http://59.110.23.25/api/v1/baby/074ba30e-1d9e-4819-b5e8-c9b2d42d7db9/","resource_uri":"http://59.110.23.25/api/v1/baby_wish/3a247c69-2f76-4572-a8bd-a2cce1c4092f/","wishType":0,"name":"9998"}
     * resource_uri : http://59.110.23.25/api/v1/mission/fd806324-44fe-41f9-aa50-cc65397a6148/
     * missionStatus : 1
     * name : first test
     */

    private List<MissionBean> objects;

    public int get_status() {
        return _status;
    }

    public void set_status(int _status) {
        this._status = _status;
    }

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public String get_reason() {
        return _reason;
    }

    public void set_reason(String _reason) {
        this._reason = _reason;
    }

    public List<MissionBean> getObjects() {
        return objects;
    }

    public void setObjects(List<MissionBean> objects) {
        this.objects = objects;
    }

    public static class MetaBean {
        private Object previous;
        private int total_count;
        private int offset;
        private int limit;
        private Object next;

        public Object getPrevious() {
            return previous;
        }

        public void setPrevious(Object previous) {
            this.previous = previous;
        }

        public int getTotal_count() {
            return total_count;
        }

        public void setTotal_count(int total_count) {
            this.total_count = total_count;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public Object getNext() {
            return next;
        }

        public void setNext(Object next) {
            this.next = next;
        }
    }

    public static class MissionBean implements Serializable {
        private String user;
        private int missionType;
        private String product;
        private String description;
        private int created_at;
        private int updated_at;
        private String object_id;
        private int finishedDays;
        private int setDays;
        private int exercisePages;
        private String baby;
        /**
         * product : null
         * description : 我要9999
         * created_at : 1481099514
         * updated_at : 1481099514
         * object_id : 3a247c69-2f76-4572-a8bd-a2cce1c4092f
         * wishProgress : 0
         * user : http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/
         * baby : http://59.110.23.25/api/v1/baby/074ba30e-1d9e-4819-b5e8-c9b2d42d7db9/
         * resource_uri : http://59.110.23.25/api/v1/baby_wish/3a247c69-2f76-4572-a8bd-a2cce1c4092f/
         * wishType : 0
         * name : 9998
         */

        private BabyWishBean babyWish;
        private String resource_uri;
        private int missionStatus;
        private String name;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public int getMissionType() {
            return missionType;
        }

        public void setMissionType(int missionType) {
            this.missionType = missionType;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public int getFinishedDays() {
            return finishedDays;
        }

        public void setFinishedDays(int finishedDays) {
            this.finishedDays = finishedDays;
        }

        public int getSetDays() {
            return setDays;
        }

        public void setSetDays(int setDays) {
            this.setDays = setDays;
        }

        public int getExercisePages() {
            return exercisePages;
        }

        public void setExercisePages(int exercisePages) {
            this.exercisePages = exercisePages;
        }

        public String getBaby() {
            return baby;
        }

        public void setBaby(String baby) {
            this.baby = baby;
        }

        public BabyWishBean getBabyWish() {
            return babyWish;
        }

        public void setBabyWish(BabyWishBean babyWish) {
            this.babyWish = babyWish;
        }

        public String getResource_uri() {
            return resource_uri;
        }

        public void setResource_uri(String resource_uri) {
            this.resource_uri = resource_uri;
        }

        public int getMissionStatus() {
            return missionStatus;
        }

        public void setMissionStatus(int missionStatus) {
            this.missionStatus = missionStatus;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static class BabyWishBean implements Serializable {
            private Object product;
            private String description;
            private int created_at;
            private int updated_at;
            private String object_id;
            private int wishProgress;
            private String user;
            private String baby;
            private String resource_uri;
            private int wishType;
            private String name;

            public Object getProduct() {
                return product;
            }

            public void setProduct(Object product) {
                this.product = product;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
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

            public int getWishProgress() {
                return wishProgress;
            }

            public void setWishProgress(int wishProgress) {
                this.wishProgress = wishProgress;
            }

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }

            public String getBaby() {
                return baby;
            }

            public void setBaby(String baby) {
                this.baby = baby;
            }

            public String getResource_uri() {
                return resource_uri;
            }

            public void setResource_uri(String resource_uri) {
                this.resource_uri = resource_uri;
            }

            public int getWishType() {
                return wishType;
            }

            public void setWishType(int wishType) {
                this.wishType = wishType;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
