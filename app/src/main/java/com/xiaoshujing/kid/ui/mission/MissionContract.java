package com.xiaoshujing.kid.ui.mission;

import com.xiaoshujing.kid.common.BasePresenter;
import com.xiaoshujing.kid.common.BaseView;
import com.xiaoshujing.kid.model.GetMissionBean;

import java.util.List;

/**
 * Created by Liuxiaocong on 8/11/16.
 */
public interface MissionContract {
    interface View extends BaseView<Presenter> {
        void setDailyMissionList(List<GetMissionBean.MissionBean> taskList);
        void setParentMissionList(List<GetMissionBean.MissionBean> taskList);
        void setActivityMissionList(List<GetMissionBean.MissionBean> taskList);
    }

    interface Presenter extends BasePresenter<View> {
        void getMissionList();
    }
}
