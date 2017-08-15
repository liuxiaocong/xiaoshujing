package com.xiaoshujing.kid.model;

import java.util.List;

/**
 * Created by LiuXiaocong on 12/9/2016.
 */

public class BabyWishBean {

    /**
     * episode : http://59.110.23.25/api/v1/episode/0f4f908e-b636-4735-a0d3-2e620537e54c/
     * description : <p>
     火影忍者 · 第4集
     </p>
     <p>
     非常好看，但是需要购买
     </p>
     * object_id : 546693a5-fad0-4945-ac54-71cb6ac16f35
     * updated_at : 1483409907
     * season : http://59.110.23.25/api/v1/season/e13090ff-c77d-4750-8797-e9c9c4dd2285/
     * created_at : 1483166914
     * sales : 5
     * cover_url : http://xiaoshujing.oss-cn-shanghai.aliyuncs.com/cover/2016/12/31/24f60340-9d1a-4edb-92a9-caa3f6d6a1cb.jpg
     * summary :
     * description_img_urls : []
     * inventory : 99994
     * productType : 4
     * section : {"description":"","created_at":1478853371,"updated_at":1478853371,"object_id":"2ce88a8a-502d-4d56-acc3-58b258824293","name":"教育专区","resource_uri":"http://59.110.23.25/api/v1/section/2ce88a8a-502d-4d56-acc3-58b258824293/"}
     * resource_uri : http://59.110.23.25/api/v1/product/546693a5-fad0-4945-ac54-71cb6ac16f35/
     * thumbnail_url : http://xiaoshujing.oss-cn-shanghai.aliyuncs.com/cover/2016/12/31/24f60340-9d1a-4edb-92a9-caa3f6d6a1cb.jpg?x-oss-process=image/resize,m_fill,w_200
     * price : 20.0
     * name : 火影忍者 · 第4集
     */

    private ProductBean product;
    /**
     * product : {"episode":"http://59.110.23.25/api/v1/episode/0f4f908e-b636-4735-a0d3-2e620537e54c/","description":"<p>\r\n\t火影忍者 · 第4集\r\n<\/p>\r\n<p>\r\n\t非常好看，但是需要购买\r\n<\/p>","object_id":"546693a5-fad0-4945-ac54-71cb6ac16f35","updated_at":1483409907,"season":"http://59.110.23.25/api/v1/season/e13090ff-c77d-4750-8797-e9c9c4dd2285/","created_at":1483166914,"sales":5,"cover_url":"http://xiaoshujing.oss-cn-shanghai.aliyuncs.com/cover/2016/12/31/24f60340-9d1a-4edb-92a9-caa3f6d6a1cb.jpg","summary":"","description_img_urls":[],"inventory":99994,"productType":4,"section":{"description":"","created_at":1478853371,"updated_at":1478853371,"object_id":"2ce88a8a-502d-4d56-acc3-58b258824293","name":"教育专区","resource_uri":"http://59.110.23.25/api/v1/section/2ce88a8a-502d-4d56-acc3-58b258824293/"},"resource_uri":"http://59.110.23.25/api/v1/product/546693a5-fad0-4945-ac54-71cb6ac16f35/","thumbnail_url":"http://xiaoshujing.oss-cn-shanghai.aliyuncs.com/cover/2016/12/31/24f60340-9d1a-4edb-92a9-caa3f6d6a1cb.jpg?x-oss-process=image/resize,m_fill,w_200","price":20,"name":"火影忍者 · 第4集"}
     * description : 火影忍者 - 第四集
     好看，但是需要购买
     * created_at : 1483415258
     * updated_at : 1483415258
     * object_id : 4d6be033-bb5d-49e7-ac2f-d3807bfbcc18
     * name : 火影忍者 - 第四集
     * wishProgress : 0
     * user : http://59.110.23.25/api/v1/users/0bd08822-b442-4ad3-814b-688726a71f23/
     * baby : http://59.110.23.25/api/v1/baby/074ba30e-1d9e-4819-b5e8-c9b2d42d7db9/
     * _status : 0
     * _reason :
     * wishType : 2
     * resource_uri : http://59.110.23.25/api/v1/baby_wish/4d6be033-bb5d-49e7-ac2f-d3807bfbcc18/
     */

    private String description;
    private int created_at;
    private int updated_at;
    private String object_id;
    private String name;
    private int wishProgress;
    private String user;
    private String baby;
    private int _status;
    private String _reason;
    private int wishType;
    private String resource_uri;

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getWishType() {
        return wishType;
    }

    public void setWishType(int wishType) {
        this.wishType = wishType;
    }

    public String getResource_uri() {
        return resource_uri;
    }

    public void setResource_uri(String resource_uri) {
        this.resource_uri = resource_uri;
    }

    public static class ProductBean {
        private String episode;
        private String description;
        private String object_id;
        private int updated_at;
        private String season;
        private int created_at;
        private int sales;
        private String cover_url;
        private String summary;
        private int inventory;
        private int productType;
        /**
         * description :
         * created_at : 1478853371
         * updated_at : 1478853371
         * object_id : 2ce88a8a-502d-4d56-acc3-58b258824293
         * name : 教育专区
         * resource_uri : http://59.110.23.25/api/v1/section/2ce88a8a-502d-4d56-acc3-58b258824293/
         */

        private SectionBean section;
        private String resource_uri;
        private String thumbnail_url;
        private double price;
        private String name;
        private List<?> description_img_urls;

        public String getEpisode() {
            return episode;
        }

        public void setEpisode(String episode) {
            this.episode = episode;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getObject_id() {
            return object_id;
        }

        public void setObject_id(String object_id) {
            this.object_id = object_id;
        }

        public int getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(int updated_at) {
            this.updated_at = updated_at;
        }

        public String getSeason() {
            return season;
        }

        public void setSeason(String season) {
            this.season = season;
        }

        public int getCreated_at() {
            return created_at;
        }

        public void setCreated_at(int created_at) {
            this.created_at = created_at;
        }

        public int getSales() {
            return sales;
        }

        public void setSales(int sales) {
            this.sales = sales;
        }

        public String getCover_url() {
            return cover_url;
        }

        public void setCover_url(String cover_url) {
            this.cover_url = cover_url;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public int getInventory() {
            return inventory;
        }

        public void setInventory(int inventory) {
            this.inventory = inventory;
        }

        public int getProductType() {
            return productType;
        }

        public void setProductType(int productType) {
            this.productType = productType;
        }

        public SectionBean getSection() {
            return section;
        }

        public void setSection(SectionBean section) {
            this.section = section;
        }

        public String getResource_uri() {
            return resource_uri;
        }

        public void setResource_uri(String resource_uri) {
            this.resource_uri = resource_uri;
        }

        public String getThumbnail_url() {
            return thumbnail_url;
        }

        public void setThumbnail_url(String thumbnail_url) {
            this.thumbnail_url = thumbnail_url;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<?> getDescription_img_urls() {
            return description_img_urls;
        }

        public void setDescription_img_urls(List<?> description_img_urls) {
            this.description_img_urls = description_img_urls;
        }

        public static class SectionBean {
            private String description;
            private int created_at;
            private int updated_at;
            private String object_id;
            private String name;
            private String resource_uri;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getResource_uri() {
                return resource_uri;
            }

            public void setResource_uri(String resource_uri) {
                this.resource_uri = resource_uri;
            }
        }
    }
}
