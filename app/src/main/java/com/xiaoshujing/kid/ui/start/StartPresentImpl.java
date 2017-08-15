package com.xiaoshujing.kid.ui.start;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.common.DeviceUuidFactory;
import com.xiaoshujing.kid.common.SharedPreferencesFactory;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.data.PractiseManage;
import com.xiaoshujing.kid.data.UserInfoManage;
import com.xiaoshujing.kid.model.CommonRetBean;
import com.xiaoshujing.kid.model.GetMissionBean;
import com.xiaoshujing.kid.model.PractiseEndBean;
import com.xiaoshujing.kid.model.PractiseStartBean;
import com.xiaoshujing.kid.model.PractiseUpdateBean;
import com.xiaoshujing.kid.ui.dialog.LoadingDialog;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class StartPresentImpl implements StartContract.Presenter {
    String TAG = "StartPresentImpl";
    Activity mContext;
    private long mStartTime;
    StartContract.View mView;
    UiUpdateHandler mUiUpdateHandler;
    UpdateHandler mUpdateHandler;
    boolean mIsStarted = false;

    int i = 0;
    int[] randomHead = new int[]{2, 4, 5, 10, 0};
    int[] randomSit = new int[]{12, 0, 5, 8, 3};
    int[] randomLight = new int[]{30, 50, 60, 10, 20};

    int j = 0;
    double[] randomScore = new double[]{60.21, 70.21, 80.21, 75, 88};

    private DeviceUuidFactory mUuidFactory;
    boolean mAlreadyEnd = false;
    int mUpdateInterval = 60 * 1000;
    LoadingDialog mLoadingDialog;
    GetMissionBean.MissionBean mMissionBean;

    public StartPresentImpl(Activity context, GetMissionBean.MissionBean missionBean) {
        mMissionBean = missionBean;
        mContext = context;
        mAlreadyEnd = false;
        mUuidFactory = new DeviceUuidFactory(context);
    }

    @Override
    public void bindView(StartContract.View view) {
        mView = view;
    }

    SimpleCallback<CommonRetBean> mRetBeanSimpleCallback = new SimpleCallback<CommonRetBean>() {
        @Override
        public void onSuccess(CommonRetBean response) {
            hideLoading();
            start();
        }

        @Override
        public void onFailure(int responseCode) {
            hideLoading();
            MyApplication.getInstance().showNote("重置失败");
            mContext.finish();
        }
    };

    SimpleCallback<PractiseStartBean> mPractiseStartBeanSimpleCallback = new SimpleCallback<PractiseStartBean>() {
        @Override
        public void onSuccess(PractiseStartBean response) {
            hideLoading();
            Util.DLog(TAG, "Start onSuccess, _status:" + response.get_status());
            if (response.get_status() == 0) {
                mIsStarted = true;
                MyApplication.getInstance().showNote("练习开始");
                onStartSuccess();
                if (mView != null) {
                    mView.setFinishEnable(true);
                }
            } else {
                Util.ShowAlert(mContext, "", response.get_reason(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        mContext.finish();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mLoadingDialog = LoadingDialog.show(mContext);
                        PractiseManage.getInstance().reset(mRetBeanSimpleCallback);
                        MyApplication.getInstance().clearPreUploadBitmapAndPath();
                        dialog.dismiss();
                    }
                }, "确定", "重置", false);
            }
        }

        @Override
        public void onFailure(int responseCode) {
            hideLoading();
            Util.DLog(TAG, "Start onFailure");
            int ret = responseCode;
        }
    };

    @Override
    public void start() {
        if (UserInfoManage.getInstance().getUserBean() != null
                && !Util.isNullOrEmpty(UserInfoManage.getInstance().getUserBean().getBaby())) {
            mLoadingDialog = LoadingDialog.show(mContext);
            PractiseManage.getInstance().start(mPractiseStartBeanSimpleCallback, UserInfoManage.getInstance().getUserBean().getBaby());
        } else {
            MyApplication.getInstance().showNote("没有练习任务喽");
            mContext.finish();
        }
    }

    private void hideLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    private void onStartSuccess() {
        Intent intent = new Intent("android.intent.action.GO_START_PAGE");
        intent.putExtra("msg", "go main");
        mContext.sendBroadcast(intent);
        if (mMissionBean == null) {
            SharedPreferencesFactory.setKeyCurrentProductId(mContext, "");
        } else {
            SharedPreferencesFactory.setKeyCurrentProductId(mContext, mMissionBean.getProduct());
        }
        mStartTime = System.currentTimeMillis();
        mUiUpdateHandler = new UiUpdateHandler();
        Message message = new Message();
        message.what = 2;
        mUiUpdateHandler.sendMessageDelayed(message, 900);

        mUpdateHandler = new UpdateHandler();
        Message messageUpdate = new Message();
        messageUpdate.what = 1;
        mUpdateHandler.sendMessageDelayed(messageUpdate, mUpdateInterval);
    }

    @Override
    public void onClickEnd() {
        if (!mIsStarted) return;
        if (!mAlreadyEnd) {
            mAlreadyEnd = true;
            end();
        }
    }

    SimpleCallback<PractiseEndBean> mPractiseEndBeanSimpleCallback = new SimpleCallback<PractiseEndBean>() {
        @Override
        public void onSuccess(PractiseEndBean response) {
            Util.DLog(TAG, "End onSuccess, _status:" + response.get_status());
            if (response.get_status() == 0) {
                if (mContext != null) {
                    MyApplication.getInstance().showNote("结束练习，请进行评分，否则无法开始新的练习");
                    mContext.finish();
                }
            } else {
                MyApplication.getInstance().showNote(response.get_reason());
            }
        }

        @Override
        public void onFailure(int responseCode) {
            Util.DLog(TAG, "End onFailure");
            int ret = responseCode;
        }
    };

    private void end() {
        if (mIsStarted) {
            mIsStarted = false;
            PractiseManage.getInstance().end(mPractiseEndBeanSimpleCallback);
        }
    }

    @Override
    public void clear() {
        if (mUpdateHandler != null) {
            mUpdateHandler.removeMessages(1);
            mUpdateHandler = null;
        }
        if (mUiUpdateHandler != null) {
            mUiUpdateHandler.removeMessages(2);
            mUiUpdateHandler = null;
        }
        if (mPractiseStartBeanSimpleCallback != null) {
            mPractiseStartBeanSimpleCallback.cancel();
        }
        if (mPractiseUpdateBeanSimpleCallback != null) {
            mPractiseUpdateBeanSimpleCallback.cancel();
        }
        if (!mAlreadyEnd) {
            mAlreadyEnd = true;
            end();
        }
    }


    boolean skip = false;

    private class UiUpdateHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            long timeSpent = System.currentTimeMillis() - mStartTime;
            Log.d(TAG, "timeSpent:" + timeSpent);
            Log.d(TAG, "mStartTime:" + mStartTime);
            Message message = new Message();
            message.what = 2;
            sendMessageDelayed(message, 900);
            if (mView != null) {
                mView.updateTime(timeSpent);
            }
            if (i > 4) {
                i = 0;
            }
            if (!skip) {
                skip = true;
                if (mView != null) {
                    mView.updateData(randomHead[i], randomSit[i], randomLight[i]);
                    i++;
                }
            } else {
                skip = false;
            }
        }
    }

    SimpleCallback<PractiseUpdateBean> mPractiseUpdateBeanSimpleCallback = new SimpleCallback<PractiseUpdateBean>() {
        @Override
        public void onSuccess(PractiseUpdateBean response) {
            Util.DLog(TAG, "Update onSuccess, _status:" + response.get_status());
//            if (response.get_status() == 0) {
//                onStartSuccess();
//            } else {
//                MyApplication.getInstance().showNote(response.get_reason());
//            }
            Message message = new Message();
            message.what = 1;
            mUpdateHandler.sendMessageDelayed(message, mUpdateInterval);
        }

        @Override
        public void onFailure(int responseCode) {
            Util.DLog(TAG, "Update onFailure");
            int ret = responseCode;
            Message message = new Message();
            message.what = 1;
            mUpdateHandler.sendMessageDelayed(message, mUpdateInterval);
        }
    };

    private class UpdateHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            long timeSpent = System.currentTimeMillis() - mStartTime;
            int minute = (int) (timeSpent / (60 * 1000));
            if (j > 4) {
                j = 0;
            }
            double score = randomScore[j];
            i++;
            Util.DLog(TAG, "Update , minute:" + minute + ",score:" + score);
            PractiseManage.getInstance().update(mPractiseUpdateBeanSimpleCallback, minute, score);
        }
    }

}
