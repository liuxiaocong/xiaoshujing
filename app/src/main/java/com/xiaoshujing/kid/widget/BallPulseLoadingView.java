package com.xiaoshujing.kid.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.common.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqingyun on 1/26/16.
 */
public class BallPulseLoadingView extends View {

    private static final int NUMBER_OF_BALLS = 3;

    private static final int ANIMATION_DURATION = 750;

    private static final int[] ANIMATION_DELAYS = new int[]{120, 240, 360};

    private List<Animator> mAnimationList;
    private float[] mScaleArray = new float[]{1.0f, 1.0f, 1.0f};

    private Paint mPaint;

    private int mBallColor;

    private boolean mStarted = true;

    public BallPulseLoadingView(Context context) {
        super(context);

        init(context, null);
    }

    public BallPulseLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);
    }

    public BallPulseLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mAnimationList = new ArrayList<>(NUMBER_OF_BALLS);

        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BallPulseLoadingView);
            mBallColor = array.getColor(0, Color.WHITE);
            array.recycle();
        } else {
            mBallColor = Color.WHITE;
        }
        mPaint.setColor(mBallColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int siz = Util.getPxFromDp(30 + 6 * 2);
        setMeasuredDimension(siz, siz);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float ballGap = Util.getPxFromDp(MyApplication.getInstance(), 6);
        float rV = getHeight() / 2;
        float rH = (getWidth() - 2 * ballGap) / 6;
        float radius = Util.getPxFromDp(MyApplication.getInstance(), 5); // rV < rH ? rV : rH;

        float x = getWidth() / 2 - ballGap - 2 * radius;
        float y = getHeight() / 2;

        for (int i = 0; i < NUMBER_OF_BALLS; i++) {
            canvas.save();

            float translateX = x + (2 * radius + ballGap) * i;
            canvas.translate(translateX, y);
            canvas.scale(mScaleArray[i], mScaleArray[i]);
            canvas.drawCircle(0, 0, radius, mPaint);

            canvas.restore();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        createAnimations();
        if (mStarted) {
            startAnimation();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        destroyAnimations();
    }

    public void startAnimation() {
        for (Animator anim : mAnimationList) {
            if (!anim.isRunning()) {
                anim.start();
            }
        }
        mStarted = true;
    }

    public void stopAnimation() {
        for (Animator anim : mAnimationList) {
            if (anim.isRunning()) {
                anim.end();
            }
        }
        mStarted = false;
    }

    private void createAnimations() {
        destroyAnimations();

        for (int i = 0; i < NUMBER_OF_BALLS; i++) {
            ValueAnimator anim = ValueAnimator.ofFloat(1.0f, 0.3f, 1.0f);
            anim.setDuration(ANIMATION_DURATION);
            anim.setStartDelay(ANIMATION_DELAYS[i]);
            anim.setRepeatCount(ValueAnimator.INFINITE);

            final int ii = i;
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float scale = (float) animation.getAnimatedValue();
                    mScaleArray[ii] = scale;
                    postInvalidate();
                }
            });

            mAnimationList.add(anim);
        }
    }

    private void destroyAnimations() {
        for (Animator anim : mAnimationList) {
            anim.cancel();
            anim.removeAllListeners();
        }
        mAnimationList.clear();
    }

    public void onDestroy() {
        destroyAnimations();
    }

}