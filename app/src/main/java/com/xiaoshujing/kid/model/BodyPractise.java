package com.xiaoshujing.kid.model;

/**
 * Created by LiuXiaocong on 12/7/2016.
 */

public class BodyPractise {

    /**
     * baby : 074ba30e1d9e4819b5e8c9b2d42d7db9
     */

    private String baby;

    private BodyPractise(Builder builder) {
        setBaby(builder.baby);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getBaby() {
        return baby;
    }

    public void setBaby(String baby) {
        this.baby = baby;
    }

    public static final class Builder {
        private String baby;

        public Builder() {
        }

        public Builder baby(String val) {
            baby = val;
            return this;
        }

        public BodyPractise build() {
            return new BodyPractise(this);
        }
    }
}
