package com.xiaoshujing.kid.ui.seasonList;

import android.support.v7.widget.RecyclerView;

import com.xiaoshujing.kid.common.BasePresenter;
import com.xiaoshujing.kid.common.BaseView;

/**
 * Created by Liuxiaocong on 8/11/16.
 */
public interface SeasonListContract {
    interface View extends BaseView<Presenter> {
        void setAdapter(RecyclerView.Adapter adapter);
    }

    interface Presenter extends BasePresenter<View> {
        //init adapter and load data;
        void start();
    }
}
