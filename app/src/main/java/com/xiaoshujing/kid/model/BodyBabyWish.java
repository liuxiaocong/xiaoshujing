package com.xiaoshujing.kid.model;

/**
 * Created by LiuXiaocong on 12/6/2016.
 */

public class BodyBabyWish {

    /**
     * name : wish name
     * description : 我要SSR，我要SSR，我要SSR
     * wishType : 2
     * baby : 08ba1777-f15d-47a6-aa0d-36b2548c1877
     * product : 1e8c35cbb62745f9b915cb0e19e28e9e
     */

    private String name;
    private String description;
    private String wishType;
    private String baby;
    private String product;

    private BodyBabyWish(Builder builder) {
        setName(builder.name);
        setDescription(builder.description);
        setWishType(builder.wishType);
        setBaby(builder.baby);
        setProduct(builder.product);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWishType() {
        return wishType;
    }

    public void setWishType(String wishType) {
        this.wishType = wishType;
    }

    public String getBaby() {
        return baby;
    }

    public void setBaby(String baby) {
        this.baby = baby;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public static final class Builder {
        private String name;
        private String description;
        private String wishType;
        private String baby;
        private String product;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder wishType(String val) {
            wishType = val;
            return this;
        }

        public Builder baby(String val) {
            baby = val;
            return this;
        }

        public Builder product(String val) {
            product = val;
            return this;
        }

        public BodyBabyWish build() {
            return new BodyBabyWish(this);
        }
    }
}
