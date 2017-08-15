package com.xiaoshujing.kid.event;

import android.graphics.Bitmap;

import java.io.File;

/**
 * Created by LiuXiaocong on 11/15/2016.
 */
public class EventGetPhotoData {
    public Bitmap photo;
    public String filePath;

    public EventGetPhotoData(Bitmap photo, String filePath) {
        this.photo = photo;
        this.filePath = filePath;
    }
}
