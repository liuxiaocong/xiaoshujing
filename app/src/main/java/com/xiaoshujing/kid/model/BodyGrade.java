package com.xiaoshujing.kid.model;

import java.util.List;

/**
 * Created by LiuXiaocong on 12/15/2016.
 */

public class BodyGrade {

    private List<String> img_urls;

    private BodyGrade(Builder builder) {
        setImg_urls(builder.img_urls);
    }

    public static BodyGrade.Builder newBuilder() {
        return new BodyGrade.Builder();
    }

    public List<String> getImg_urls() {
        return img_urls;
    }

    public void setImg_urls(List<String> img_urls) {
        this.img_urls = img_urls;
    }

    public static final class Builder {
        private List<String> img_urls;

        public Builder() {
        }

        public Builder img_urls(List<String> val) {
            img_urls = val;
            return this;
        }

        public BodyGrade build() {
            return new BodyGrade(this);
        }
    }
}
