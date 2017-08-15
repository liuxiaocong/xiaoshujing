package com.xiaoshujing.kid.common;

/**
 * Created by LiuXiaocong on 12/19/2016.
 */

public enum LoadingType {
    EIdel(0),
    ERefresh(1),
    ELoadMore(2);
    private final int value;

    LoadingType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
