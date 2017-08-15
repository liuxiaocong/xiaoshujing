package com.xiaoshujing.kid.ui.home;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;

import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.common.SharedPreferencesFactory;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.data.MessageDataManage;
import com.xiaoshujing.kid.data.SeasonVideoManage;
import com.xiaoshujing.kid.model.GetMissionBean;
import com.xiaoshujing.kid.model.message.GetMessageBean;
import com.xiaoshujing.kid.ui.dialog.MissionSelectDialog;
import com.xiaoshujing.kid.ui.fun.FunActivity;
import com.xiaoshujing.kid.ui.message.MessageActivity;
import com.xiaoshujing.kid.ui.mission.MissionActivity;
import com.xiaoshujing.kid.ui.record.RecordActivity;
import com.xiaoshujing.kid.ui.score.ScoreActivity;
import com.xiaoshujing.kid.ui.scoreFromAlbum.ScoreFromAlbumActivity;
import com.xiaoshujing.kid.ui.seasonList.SeasonListActivity;
import com.xiaoshujing.kid.ui.start.StartActivity;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class HomePresentImpl implements HomeContract.Presenter {
    final String TAG = "HomePresentImpl";
    Activity mContext;
    HomeContract.View mView;

    public HomePresentImpl(Activity context) {
        mContext = context;
    }

    @Override
    public void openMessagePage() {
        Intent intent = new Intent(mContext, MessageActivity.class);
        mContext.startActivity(intent);
        mContext.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public void openStudyPage() {
        SeasonListActivity.openActivity(mContext, SeasonVideoManage.SeasonType.EStudy.getValue());
    }

    @Override
    public void openFunPage() {
        Intent intent = new Intent(mContext, FunActivity.class);
        mContext.startActivity(intent);
        mContext.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public void openRecordPage() {
        Intent intent = new Intent(mContext, RecordActivity.class);
        mContext.startActivity(intent);
        mContext.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public void openScorePage() {
        Util.ShowAlert(mContext, "", "", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(mContext, ScoreActivity.class);
                mContext.startActivity(intent);
                mContext.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                dialog.dismiss();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(mContext, ScoreFromAlbumActivity.class);
                mContext.startActivity(intent);
                mContext.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                dialog.dismiss();
            }
        }, "拍照", "相册");
    }

    @Override
    public void openTaskPage() {
        Intent intent = new Intent(mContext, MissionActivity.class);
        mContext.startActivity(intent);
        mContext.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public void openStartPage() {
        MissionSelectDialog.createInstance(mContext, new MissionSelectDialog.IMissionSelectDialogListener() {
            @Override
            public void onStartClicked(Dialog dialog, GetMissionBean.MissionBean missionBean) {
                if (missionBean != null) {
                    StartActivity.openActivity(mContext, missionBean);
                } else {
                    MyApplication.getInstance().showNote("任务不能为空");
                }
                dialog.dismiss();
            }

            @Override
            public void onCloseClicked(Dialog dialog) {
                dialog.dismiss();
            }
        }, true).show();
    }

    @Override
    public void getMessageCount() {
        MessageDataManage.getInstance().getMyUnReadMessage(mGetMessageBeanSimpleCallback, 0);
    }

    SimpleCallback<GetMessageBean> mGetMessageBeanSimpleCallback = new SimpleCallback<GetMessageBean>() {
        @Override
        public void onSuccess(GetMessageBean response) {
            if (response == null) return;
            if (response.getMeta() != null && response.getMeta().getTotal_count() > 0) {
                Util.DLog(TAG,"total unread message:" + response.getMeta().getTotal_count());
                if (mView != null) {
                    mView.setMessageCount(1);
                }
            } else {
                if (mView != null) {
                    mView.setMessageCount(0);
                }
            }

        }

        @Override
        public void onFailure(int responseCode) {
            int ret = responseCode;
        }
    };

    @Override
    public void bindView(HomeContract.View view) {
        mView = view;
    }
}
