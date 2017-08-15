package com.xiaoshujing.kid.ui.missionDetail;

import android.app.Activity;
import android.content.Intent;

import com.xiaoshujing.kid.model.GetMissionBean;
import com.xiaoshujing.kid.ui.start.StartActivity;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class MissionDetailPresentImpl implements MissionDetailContract.Presenter {
    Activity mContext;
    private MissionDetailContract.View mView;
    GetMissionBean.MissionBean mMissionBean;


    public MissionDetailPresentImpl(Activity context, GetMissionBean.MissionBean missionBean) {
        mMissionBean = missionBean;
        mContext = context;
    }


    @Override
    public void bindView(MissionDetailContract.View view) {
        mView = view;
    }

    @Override
    public void onClickStart() {
        StartActivity.openActivity(mContext, mMissionBean);
    }

    @Override
    public void onClickCancel() {

    }
}
