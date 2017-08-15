package com.xiaoshujing.kid.model;

import java.util.List;

/**
 * Created by LiuXiaocong on 12/15/2016.
 */

public class BodyMyMoment {

    /**
     * overallScore : 9.66
     * studyScore : 20.0
     * content : content test test test
     * img_urls : ["http://xiaoshujing.oss-cn-shanghai.aliyuncs.com/avatar/2016/11/03/29c947fe-a16f-11e6-8c11-00163e08076a.png","http://xiaoshujing.oss-cn-shanghai.aliyuncs.com/avatar/2016/11/03/29c947fe-a16f-11e6-8c11-00163e08076a.png"]
     * baby : 074ba30e-1d9e-4819-b5e8-c9b2d42d7db9
     * isShow : false
     * product : 0737da267b704fdf9b8058c32b28739d
     * pages : 32
     */

    private double overallScore;
    private double studyScore;
    private String content;
    private String baby;
    private boolean isShow;
    private String product;
    private int pages;
    private List<String> img_urls;

    public static BodyMyMoment.Builder newBuilder() {
        return new BodyMyMoment.Builder();
    }


    private BodyMyMoment(Builder builder) {
        setOverallScore(builder.overallScore);
        setStudyScore(builder.studyScore);
        setContent(builder.content);
        setBaby(builder.baby);
        isShow = builder.isShow;
        setProduct(builder.product);
        setPages(builder.pages);
        setImg_urls(builder.img_urls);
    }


    public double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(double overallScore) {
        this.overallScore = overallScore;
    }

    public double getStudyScore() {
        return studyScore;
    }

    public void setStudyScore(double studyScore) {
        this.studyScore = studyScore;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBaby() {
        return baby;
    }

    public void setBaby(String baby) {
        this.baby = baby;
    }

    public boolean isIsShow() {
        return isShow;
    }

    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<String> getImg_urls() {
        return img_urls;
    }

    public void setImg_urls(List<String> img_urls) {
        this.img_urls = img_urls;
    }

    public static final class Builder {
        private double overallScore;
        private double studyScore;
        private String content;
        private String baby;
        private boolean isShow;
        private String product;
        private int pages;
        private List<String> img_urls;

        public Builder() {
        }

        public Builder overallScore(double val) {
            overallScore = val;
            return this;
        }

        public Builder studyScore(double val) {
            studyScore = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public Builder baby(String val) {
            baby = val;
            return this;
        }

        public Builder isShow(boolean val) {
            isShow = val;
            return this;
        }

        public Builder product(String val) {
            product = val;
            return this;
        }

        public Builder pages(int val) {
            pages = val;
            return this;
        }

        public Builder img_urls(List<String> val) {
            img_urls = val;
            return this;
        }

        public BodyMyMoment build() {
            return new BodyMyMoment(this);
        }
    }
}
