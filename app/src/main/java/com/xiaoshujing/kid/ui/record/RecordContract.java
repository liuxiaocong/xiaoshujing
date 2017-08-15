package com.xiaoshujing.kid.ui.record;

import android.support.v7.widget.RecyclerView;

import com.xiaoshujing.kid.common.BasePresenter;
import com.xiaoshujing.kid.common.BaseView;

/**
 * Created by Liuxiaocong on 8/11/16.
 */
public interface RecordContract {
    interface View extends BaseView<Presenter> {
        void setAdapter(RecyclerView.Adapter adapter);
    }

    interface Presenter extends BasePresenter<View> {
        void start();
    }
}
