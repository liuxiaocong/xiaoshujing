package com.xiaoshujing.kid.ui.start;

import com.xiaoshujing.kid.common.BasePresenter;
import com.xiaoshujing.kid.common.BaseView;

/**
 * Created by Liuxiaocong on 8/11/16.
 */
public interface StartContract {
    interface View extends BaseView<Presenter> {
        void updateTime(long timeSpend);

        void updateData(int head, int sit, int light);

        void setFinishEnable(boolean enable);
    }

    interface Presenter extends BasePresenter<View> {
        void start();
        void onClickEnd();
        void clear();
    }
}
