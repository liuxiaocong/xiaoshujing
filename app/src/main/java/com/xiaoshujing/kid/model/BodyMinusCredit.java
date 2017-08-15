package com.xiaoshujing.kid.model;

/**
 * Created by LiuXiaocong on 1/6/2017.
 */

public class BodyMinusCredit {

    /**
     * credit : 1
     */

    private int credit;

    private BodyMinusCredit(Builder builder) {
        setCredit(builder.credit);
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }


    public static final class Builder {
        private int credit;

        public Builder() {
        }

        public Builder credit(int val) {
            credit = val;
            return this;
        }

        public BodyMinusCredit build() {
            return new BodyMinusCredit(this);
        }
    }
}
