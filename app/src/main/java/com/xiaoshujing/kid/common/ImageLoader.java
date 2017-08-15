package com.xiaoshujing.kid.common;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by liuxiaocong on 11/8/16.
 */

public class ImageLoader {
    public static void load(Context context, ImageView imageView, String url) {
        Glide.with(context).load(url).into(imageView);
    }

    public static void load(Fragment fragment, ImageView imageView, String url) {
        Glide.with(fragment).load(url).into(imageView);
    }
}
