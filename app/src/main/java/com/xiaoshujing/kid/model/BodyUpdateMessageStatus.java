package com.xiaoshujing.kid.model;

/**
 * Created by xiaoconglau on 08/01/2017.
 */

public class BodyUpdateMessageStatus {

    /**
     * isRead : 1
     */

    private int isRead;

    private BodyUpdateMessageStatus(Builder builder) {
        setIsRead(builder.isRead);
    }


    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public int getIsRead() {
        return isRead;
    }

    public static final class Builder {
        private int isRead;

        public Builder() {
        }

        public Builder isRead(int val) {
            isRead = val;
            return this;
        }

        public BodyUpdateMessageStatus build() {
            return new BodyUpdateMessageStatus(this);
        }
    }
}
