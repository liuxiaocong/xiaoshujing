package com.xiaoshujing.kid.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by LiuXiaocong on 11/14/2016.
 */
public class SquareFramelayout extends FrameLayout {
    public SquareFramelayout(Context context) {
        super(context);
    }

    public SquareFramelayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareFramelayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = 0;

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        if (width > height) {
            size = width;
        } else {
            size = height;
        }
        setMeasuredDimension(size, size);
    }
}
