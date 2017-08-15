package com.xiaoshujing.kid.ui.message;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

import com.xiaoshujing.kid.common.BasePresenter;
import com.xiaoshujing.kid.common.BaseView;

/**
 * Created by Liuxiaocong on 8/11/16.
 */
public interface MessageContract {
    interface View extends BaseView<Presenter> {
        void setAdapter(RecyclerView.Adapter adapter);

        android.view.View findChildViewUnder(int x, int y);
    }

    interface Presenter extends BasePresenter<View> {
        //init adapter and load data;
        void start();

        boolean handlerTouchEvent(android.view.View view, MotionEvent motionEvent);
    }
}
