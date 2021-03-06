package com.xiaoshujing.kid.ui.missionDetail;

import com.xiaoshujing.kid.common.BasePresenter;
import com.xiaoshujing.kid.common.BaseView;

/**
 * Created by Liuxiaocong on 8/11/16.
 */
public interface MissionDetailContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {
        void onClickStart();

        void onClickCancel();
    }
}
