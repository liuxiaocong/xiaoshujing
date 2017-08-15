package com.xiaoshujing.kid.model;

/**
 * Created by LiuXiaocong on 12/29/2016.
 */

public class BodyUpdateMission {

    /**
     * product : 0485da61124b4744bc254ed043175315
     */

    private String product;
    private int pages;

    private BodyUpdateMission(Builder builder) {
        setProduct(builder.product);
        setPages(builder.pages);
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public static BodyUpdateMission.Builder newBuilder() {
        return new BodyUpdateMission.Builder();
    }

    public static final class Builder {
        private String product;
        private int pages;

        public Builder() {
        }

        public Builder product(String val) {
            product = val;
            return this;
        }

        public Builder pages(int val) {
            pages = val;
            return this;
        }

        public BodyUpdateMission build() {
            return new BodyUpdateMission(this);
        }
    }
}
