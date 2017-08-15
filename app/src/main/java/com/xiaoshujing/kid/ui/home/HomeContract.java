package com.xiaoshujing.kid.ui.home;

import android.content.Context;

import com.xiaoshujing.kid.common.BasePresenter;
import com.xiaoshujing.kid.common.BaseView;

/**
 * Created by Liuxiaocong on 8/11/16.
 */
public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void setMessageCount(int count);
    }

    interface Presenter extends BasePresenter<View> {
        void openMessagePage();
        void openStudyPage();
        void openFunPage();
        void openRecordPage();
        void openScorePage();
        void openTaskPage();
        void openStartPage();
        void getMessageCount();
    }
}
