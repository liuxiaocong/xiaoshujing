package com.xiaoshujing.kid.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by LiuXiaocong on 7/29/2016.
 */
public class BrandTextView extends TextView {
    public BrandTextView(Context context) {
        super(context);
        setDefault();
    }

    public BrandTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDefault();
    }

    public BrandTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setDefault();
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        //super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/yuanti.ttf"));
    }
    private  void setDefault(){
        //setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/yuanti.ttf"));
    }
}
