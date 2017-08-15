package com.xiaoshujing.kid.ui.score;

/**
 * Created by LiuXiaocong on 11/17/2016.
 */
public enum ETakePhotoStatus {
    ETakePhoto(0),
    ETakePhotoSuccess(1);
    private final int value;

    private ETakePhotoStatus(int value) {
        this.value = value;
    }

    public static ETakePhotoStatus valueOf(int value) {
        switch (value) {
            case 0:
                return ETakePhoto;
            case 1:
                return ETakePhotoSuccess;
            default:
                return ETakePhoto;
        }
    }

    public int getValue() {
        return value;
    }
}