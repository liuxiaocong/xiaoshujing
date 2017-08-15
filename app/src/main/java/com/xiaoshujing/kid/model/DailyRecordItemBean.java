package com.xiaoshujing.kid.model;

import java.util.List;

/**
 * Created by LiuXiaocong on 11/9/2016.
 */
public class DailyRecordItemBean {

    /**
     * time : 1346545646
     * photos : [{"id":1,"url":"http://g.png"},{"id":2,"url":"http://g.png"}]
     */

    private long time;
    /**
     * id : 1
     * url : http://g.png
     */

    private List<PhotosBean> photos;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<PhotosBean> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotosBean> photos) {
        this.photos = photos;
    }

    public static class PhotosBean {
        private int id;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
