package com.xiaoshujing.kid.model.video;

import java.util.List;

/**
 * Created by LiuXiaocong on 12/7/2016.
 */

public class GetSeasonVideoListBean {

    /**
     * description : 儿童眼保健操
     * created_at : 1481510058
     * is_free : true
     * updated_at : 1481510058
     * cover_url : http://xiaoshujing.oss-cn-shanghai.aliyuncs.com/cover/2016/12/12/5084d3bf-53d8-4fd8-a1d8-b3315f981d45.jpg
     * name : 儿童眼保健操
     * episodes : [{"description":"第一节第一节第一节第一节第一节","created_at":1481510146,"updated_at":1481510146,"object_id":"67f5e0fe-4a81-48d0-8f95-1897bd1b559d","isFree":false,"cover_url":"","resource_uri":"http://59.110.23.25/api/v1/episode/67f5e0fe-4a81-48d0-8f95-1897bd1b559d/","name":"第一节","video_url":"urlstr"}]
     * object_id : 161eadbf-8d8d-4119-9e2e-e09d2dfd3893
     * _status : 0
     * _reason :
     * seasonType : 2
     * resource_uri : http://59.110.23.25/api/v1/season/161eadbf-8d8d-4119-9e2e-e09d2dfd3893/
     */

    private String description;
    private int created_at;
    private boolean is_free;
    private int updated_at;
    private String cover_url;
    private String name;
    private String object_id;
    private int _status;
    private String _reason;
    private int seasonType;
    private String resource_uri;
    /**
     * description : 第一节第一节第一节第一节第一节
     * created_at : 1481510146
     * updated_at : 1481510146
     * object_id : 67f5e0fe-4a81-48d0-8f95-1897bd1b559d
     * isFree : false
     * cover_url :
     * resource_uri : http://59.110.23.25/api/v1/episode/67f5e0fe-4a81-48d0-8f95-1897bd1b559d/
     * name : 第一节
     * video_url : urlstr
     */

    private List<EpisodesBean> episodes;

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

    public boolean isIs_free() {
        return is_free;
    }

    public void setIs_free(boolean is_free) {
        this.is_free = is_free;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObject_id() {
        return object_id;
    }

    public void setObject_id(String object_id) {
        this.object_id = object_id;
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

    public int getSeasonType() {
        return seasonType;
    }

    public void setSeasonType(int seasonType) {
        this.seasonType = seasonType;
    }

    public String getResource_uri() {
        return resource_uri;
    }

    public void setResource_uri(String resource_uri) {
        this.resource_uri = resource_uri;
    }

    public List<EpisodesBean> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<EpisodesBean> episodes) {
        this.episodes = episodes;
    }

    public static class EpisodesBean {
        private String description;
        private int created_at;
        private int updated_at;
        private String object_id;
        private boolean isFree;
        private String cover_url;
        private String resource_uri;
        private String name;
        private String video_url;

        private EpisodesBean(Builder builder) {
            setDescription(builder.description);
            setCreated_at(builder.created_at);
            setUpdated_at(builder.updated_at);
            setObject_id(builder.object_id);
            isFree = builder.isFree;
            setCover_url(builder.cover_url);
            setResource_uri(builder.resource_uri);
            setName(builder.name);
            setVideo_url(builder.video_url);
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

        public boolean isIsFree() {
            return isFree;
        }

        public void setIsFree(boolean isFree) {
            this.isFree = isFree;
        }

        public String getCover_url() {
            return cover_url;
        }

        public void setCover_url(String cover_url) {
            this.cover_url = cover_url;
        }

        public String getResource_uri() {
            return resource_uri;
        }

        public void setResource_uri(String resource_uri) {
            this.resource_uri = resource_uri;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }


        public static final class Builder {
            private String description;
            private int created_at;
            private int updated_at;
            private String object_id;
            private boolean isFree;
            private String cover_url;
            private String resource_uri;
            private String name;
            private String video_url;

            public Builder() {
            }

            public Builder description(String val) {
                description = val;
                return this;
            }

            public Builder created_at(int val) {
                created_at = val;
                return this;
            }

            public Builder updated_at(int val) {
                updated_at = val;
                return this;
            }

            public Builder object_id(String val) {
                object_id = val;
                return this;
            }

            public Builder isFree(boolean val) {
                isFree = val;
                return this;
            }

            public Builder cover_url(String val) {
                cover_url = val;
                return this;
            }

            public Builder resource_uri(String val) {
                resource_uri = val;
                return this;
            }

            public Builder name(String val) {
                name = val;
                return this;
            }

            public Builder video_url(String val) {
                video_url = val;
                return this;
            }

            public EpisodesBean build() {
                return new EpisodesBean(this);
            }
        }
    }
}
