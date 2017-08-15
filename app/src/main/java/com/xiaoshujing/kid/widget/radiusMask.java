package com.xiaoshujing.kid.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.xiaoshujing.kid.common.Util;

/**
 * Created by LiuXiaocong on 11/10/2016.
 */
public class radiusMask extends View {
    public radiusMask(Context context) {
        super(context);
    }

    public radiusMask(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public radiusMask(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#f8b921"));
        canvas.drawRect(new RectF(0, 0, getWidth(), getHeight()), paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        paint.setColor(Color.parseColor("#00ff00"));
        canvas.drawRoundRect(new RectF(0, 0, getWidth(), getHeight()), Util.getPxFromDp(getContext(), 10), Util.getPxFromDp(getContext(), 10), paint);
    }
}
