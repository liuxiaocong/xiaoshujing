package com.xiaoshujing.kid.model;

/**
 * Created by LiuXiaocong on 12/9/2016.
 */

public class BodyBindToken {

    /**
     * device_type : 2
     */

    private int device_type;

    private BodyBindToken(Builder builder) {
        setDevice_type(builder.device_type);
    }

    public static BodyBindToken.Builder newBuilder() {
        return new BodyBindToken.Builder();
    }

    public int getDevice_type() {
        return device_type;
    }

    public void setDevice_type(int device_type) {
        this.device_type = device_type;
    }

    public static final class Builder {
        private int device_type;

        public Builder() {
        }

        public Builder device_type(int val) {
            device_type = val;
            return this;
        }

        public BodyBindToken build() {
            return new BodyBindToken(this);
        }
    }
}
