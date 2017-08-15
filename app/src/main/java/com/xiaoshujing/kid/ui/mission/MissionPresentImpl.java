package com.xiaoshujing.kid.ui.mission;

import android.content.Context;

import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.data.MissionManage;
import com.xiaoshujing.kid.model.GetMissionBean;
import com.xiaoshujing.kid.ui.dialog.LoadingDialog;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class MissionPresentImpl implements MissionContract.Presenter {
    Context mContext;
    private MissionContract.View mView;
    LoadingDialog mLoadingDialog;
    MissionManage.MissionType mMissionType = MissionManage.MissionType.EAll;

    public MissionPresentImpl(Context context, int mMissionTypeInt) {
        if (mMissionTypeInt >= 0) {
            mMissionType = MissionManage.MissionType.valueOf(mMissionTypeInt);
        }
        mContext = context;
    }

    SimpleCallback<GetMissionBean> mDailyGetMissionBeanSimpleCallback = new SimpleCallback<GetMissionBean>() {
        @Override
        public void onSuccess(GetMissionBean response) {
            hideLoading();
            if (response.getObjects() != null && response.getObjects().size() > 0) {
                if (mView != null) {
                    mView.setDailyMissionList(response.getObjects());
                }
            }
        }

        @Override
        public void onFailure(int responseCode) {
            hideLoading();
            int ret = responseCode;
        }
    };
    SimpleCallback<GetMissionBean> mParentGetMissionBeanSimpleCallback = new SimpleCallback<GetMissionBean>() {
        @Override
        public void onSuccess(GetMissionBean response) {
            hideLoading();
            if (response.getObjects() != null && response.getObjects().size() > 0) {
                if (mView != null) {
                    mView.setParentMissionList(response.getObjects());
                }
            }
        }

        @Override
        public void onFailure(int responseCode) {
            hideLoading();
            int ret = responseCode;
        }
    };
    SimpleCallback<GetMissionBean> mActivityGetMissionBeanSimpleCallback = new SimpleCallback<GetMissionBean>() {
        @Override
        public void onSuccess(GetMissionBean response) {
            hideLoading();
            if (response.getObjects() != null && response.getObjects().size() > 0) {
                if (mView != null) {
                    mView.setActivityMissionList(response.getObjects());
                }
            }
        }

        @Override
        public void onFailure(int responseCode) {
            hideLoading();
            int ret = responseCode;
        }
    };

    @Override
    public void getMissionList() {
        mLoadingDialog = LoadingDialog.show(mContext);
        if (mMissionType.getValue() >= 0) {
            if (mMissionType.equals(MissionManage.MissionType.EActivity)) {
                MissionManage.getInstance().getMission(mActivityGetMissionBeanSimpleCallback, MissionManage.MissionType.EActivity.getValue(), MissionManage.MissionStatus.EWorkon.getValue());
            } else if (mMissionType.equals(MissionManage.MissionType.EParent)) {
                MissionManage.getInstance().getMission(mParentGetMissionBeanSimpleCallback, MissionManage.MissionType.EParent.getValue(), MissionManage.MissionStatus.EWorkon.getValue());
            } else if (mMissionType.equals(MissionManage.MissionType.EDaily)) {
                MissionManage.getInstance().getMission(mDailyGetMissionBeanSimpleCallback, MissionManage.MissionType.EDaily.getValue(), MissionManage.MissionStatus.EWorkon.getValue());
            }
        } else {
            MissionManage.getInstance().getMission(mActivityGetMissionBeanSimpleCallback, MissionManage.MissionType.EActivity.getValue(), MissionManage.MissionStatus.EWorkon.getValue());
            MissionManage.getInstance().getMission(mParentGetMissionBeanSimpleCallback, MissionManage.MissionType.EParent.getValue(), MissionManage.MissionStatus.EWorkon.getValue());
            MissionManage.getInstance().getMission(mDailyGetMissionBeanSimpleCallback, MissionManage.MissionType.EDaily.getValue(), MissionManage.MissionStatus.EWorkon.getValue());
        }
    }

    private void hideLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    @Override
    public void bindView(MissionContract.View view) {
        mView = view;
    }
}
