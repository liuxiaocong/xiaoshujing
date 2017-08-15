package com.xiaoshujing.kid.model;

import java.util.List;

/**
 * Created by LiuXiaocong on 12/7/2016.
 */

public class StudyMomentsBean {

    /**
     * _status : 0
     * meta : {"previous":null,"total_count":3,"offset":0,"limit":20,"next":null}
     * _reason :
     * objects : [{"status":2,"product":"http://59.110.23.25/api/v1/product/0485da61-124b-4744-bc25-4ed043175315/","isLiked":true,"practiceMinutes":0,"created_at":1480331882,"sitScore":0,"updated_at":1480331953,"object_id":"2e1fa1d6-c594-4c81-ad70-370853da3432","overallScore":100,"content":"bababababababababababbbbaasdfsad","pages":0,"baby":"http://59.110.23.25/api/v1/baby/074ba30e-1d9e-4819-b5e8-c9b2d42d7db9/","resource_uri":"http://59.110.23.25/api/v1/my_moments/2e1fa1d6-c594-4c81-ad70-370853da3432/","user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","img_urls":["jpg","png"],"studyScore":99,"likesCount":3},{"status":2,"product":null,"isLiked":false,"practiceMinutes":0,"created_at":1480230810,"sitScore":0,"updated_at":1480230878,"object_id":"d94b7ab9-fdb8-48ac-a367-22e9d58e08a9","overallScore":100,"content":"bababababababababababbbbaasdfsad","pages":0,"baby":"http://59.110.23.25/api/v1/baby/074ba30e-1d9e-4819-b5e8-c9b2d42d7db9/","resource_uri":"http://59.110.23.25/api/v1/my_moments/d94b7ab9-fdb8-48ac-a367-22e9d58e08a9/","user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","img_urls":["jpg","png"],"studyScore":99,"likesCount":2},{"status":2,"product":null,"isLiked":false,"practiceMinutes":0,"created_at":1480220205,"sitScore":0,"updated_at":1480220598,"object_id":"d2d759cc-7798-485a-bb5e-a082a02400b1","overallScore":9.66,"content":"content test test test","pages":0,"baby":"http://59.110.23.25/api/v1/baby/074ba30e-1d9e-4819-b5e8-c9b2d42d7db9/","resource_uri":"http://59.110.23.25/api/v1/my_moments/d2d759cc-7798-485a-bb5e-a082a02400b1/","user":"http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/","img_urls":["jpg","png"],"studyScore":20,"likesCount":1}]
     */

    private int _status;
    /**
     * previous : null
     * total_count : 3
     * offset : 0
     * limit : 20
     * next : null
     */

    private MetaBean meta;
    private String _reason;
    /**
     * status : 2
     * product : http://59.110.23.25/api/v1/product/0485da61-124b-4744-bc25-4ed043175315/
     * isLiked : true
     * practiceMinutes : 0
     * created_at : 1480331882
     * sitScore : 0
     * updated_at : 1480331953
     * object_id : 2e1fa1d6-c594-4c81-ad70-370853da3432
     * overallScore : 100
     * content : bababababababababababbbbaasdfsad
     * pages : 0
     * baby : http://59.110.23.25/api/v1/baby/074ba30e-1d9e-4819-b5e8-c9b2d42d7db9/
     * resource_uri : http://59.110.23.25/api/v1/my_moments/2e1fa1d6-c594-4c81-ad70-370853da3432/
     * user : http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/
     * img_urls : ["jpg","png"]
     * studyScore : 99
     * likesCount : 3
     */

    private List<RecordBean> objects;

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

    public List<RecordBean> getObjects() {
        return objects;
    }

    public void setObjects(List<RecordBean> objects) {
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

    public static class RecordBean {
        private int status;
        private String product;
        private boolean isLiked;
        private int practiceMinutes;
        private int created_at;
        private float sitScore;
        private int updated_at;
        private String object_id;
        private float overallScore;
        private String content;
        private int pages;
        private String baby;
        private String resource_uri;
        private String user;
        private float studyScore;
        private int likesCount;
        private List<String> img_urls;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public boolean isIsLiked() {
            return isLiked;
        }

        public void setIsLiked(boolean isLiked) {
            this.isLiked = isLiked;
        }

        public int getPracticeMinutes() {
            return practiceMinutes;
        }

        public void setPracticeMinutes(int practiceMinutes) {
            this.practiceMinutes = practiceMinutes;
        }

        public int getCreated_at() {
            return created_at;
        }

        public void setCreated_at(int created_at) {
            this.created_at = created_at;
        }

        public float getSitScore() {
            return sitScore;
        }

        public void setSitScore(int sitScore) {
            this.sitScore = sitScore;
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

        public float getOverallScore() {
            return overallScore;
        }

        public void setOverallScore(int overallScore) {
            this.overallScore = overallScore;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
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

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public float getStudyScore() {
            return studyScore;
        }

        public void setStudyScore(int studyScore) {
            this.studyScore = studyScore;
        }

        public int getLikesCount() {
            return likesCount;
        }

        public void setLikesCount(int likesCount) {
            this.likesCount = likesCount;
        }

        public List<String> getImg_urls() {
            return img_urls;
        }

        public void setImg_urls(List<String> img_urls) {
            this.img_urls = img_urls;
        }
    }
}
