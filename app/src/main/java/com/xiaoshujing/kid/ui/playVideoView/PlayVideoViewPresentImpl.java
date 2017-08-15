package com.xiaoshujing.kid.ui.playVideoView;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.data.SeasonVideoManage;
import com.xiaoshujing.kid.model.video.GetSeasonPaidVideoDetailBean;
import com.xiaoshujing.kid.model.video.GetSeasonVideoDetailBean;
import com.xiaoshujing.kid.ui.dialog.PlayVideoTipDialog;
import com.xiaoshujing.kid.ui.makeWish.MakeWishActivity;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class PlayVideoViewPresentImpl implements PlayVideoViewContract.Presenter {
    String TAG = "PlayVideoViewPresentImpl";
    PlayVideoViewContract.View mView;
    Activity mContext;
    boolean mIsFreeVideo;
    String mVideoId;
    GetSeasonVideoDetailBean mGetSeasonVideoDetailBean;
    GetSeasonPaidVideoDetailBean mGetSeasonPaidVideoDetailBean;
    String mVideoUrl;
    boolean mNeedPay = false;
    MyTimeHandler mMyTimeHandler;
    boolean mAutoPlay = true;

    public PlayVideoViewPresentImpl(Activity context, String videoId, boolean isFreeVideo) {
        mContext = context;
        mVideoId = videoId;
        mIsFreeVideo = isFreeVideo;
    }

    //free
    SimpleCallback<GetSeasonVideoDetailBean> mGetSeasonVideoDetailBeanSimpleCallback = new SimpleCallback<GetSeasonVideoDetailBean>() {
        @Override
        public void onSuccess(GetSeasonVideoDetailBean response) {
            mGetSeasonVideoDetailBean = response;
            Util.DLog(TAG, "video url: " + mGetSeasonVideoDetailBean.getVideo_url());
            if (!Util.isNullOrEmpty(mGetSeasonVideoDetailBean.getVideo_url())) {
                mVideoUrl = mGetSeasonVideoDetailBean.getVideo_url();
                mView.prepareVideo(mVideoUrl);
                mView.readyToPlay();
                if (mAutoPlay) {
                    play();
                }
                if (!mIsFreeVideo) {
                    checkIsPaid();
                }
            } else {
                MyApplication.getInstance().showNote("暂时无法播放～");
            }
        }

        @Override
        public void onFailure(int responseCode) {
            int ret = responseCode;
        }
    };

    private void showCloseAbleTips() {
        PlayVideoTipDialog playVideoTipDialog = PlayVideoTipDialog.createInstance(mContext, new PlayVideoTipDialog.IPlayVideoTipDialogListener() {
            @Override
            public void onWishClicked(Dialog dialog) {
                dialog.dismiss();
                goWishActivity();
            }

            @Override
            public void onCloseClicked(Dialog dialog) {
                dialog.dismiss();
            }
        }, true);
        playVideoTipDialog.show();
    }

    private void showUnCloseAbleTips() {
        PlayVideoTipDialog playVideoTipDialog = PlayVideoTipDialog.createInstance(mContext, new PlayVideoTipDialog.IPlayVideoTipDialogListener() {
            @Override
            public void onWishClicked(Dialog dialog) {
                dialog.dismiss();
                goWishActivity();
            }

            @Override
            public void onCloseClicked(Dialog dialog) {
                dialog.dismiss();
                mContext.finish();
            }
        }, false);
        playVideoTipDialog.show();
    }

    private void goWishActivity() {
        MakeWishActivity.openActivity(mContext, mGetSeasonVideoDetailBean);
    }

    private void checkIsPaid() {
        SeasonVideoManage.getInstance().getSeasonPaidVideoDetail(mGetSeasonPaidVideoDetailBeanSimpleCallback, mVideoId);
    }

    //paid
    SimpleCallback<GetSeasonPaidVideoDetailBean> mGetSeasonPaidVideoDetailBeanSimpleCallback = new SimpleCallback<GetSeasonPaidVideoDetailBean>() {
        @Override
        public void onSuccess(GetSeasonPaidVideoDetailBean response) {
            mGetSeasonPaidVideoDetailBean = response;
            if (response.get_status() == 0) {
                mNeedPay = false;
            } else {
                mNeedPay = true;
                showCloseAbleTips();
            }
        }

        @Override
        public void onFailure(int responseCode) {
            int ret = responseCode;
            mNeedPay = true;
            showCloseAbleTips();
        }
    };


    @Override
    public void start() {
        if (mIsFreeVideo) {
            SeasonVideoManage.getInstance().getSeasonVideoDetail(mGetSeasonVideoDetailBeanSimpleCallback, mVideoId);
        } else {
            SeasonVideoManage.getInstance().getSeasonVideoDetail(mGetSeasonVideoDetailBeanSimpleCallback, mVideoId);
            //SeasonVideoManage.getInstance().getSeasonPaidVideoDetail(mGetSeasonPaidVideoDetailBeanSimpleCallback, mVideoId);
        }
    }

    @Override
    public void clear() {
        if (mMyTimeHandler != null) {
            mMyTimeHandler.removeMessages(1);
            mMyTimeHandler = null;
        }
    }

    @Override
    public void play() {
        mView.playVideo(mVideoUrl, mNeedPay);
        mMyTimeHandler = new MyTimeHandler();
        Message message = new Message();
        message.what = 1;
        mMyTimeHandler.sendMessageDelayed(message, 900);
    }

    @Override
    public void onVideoStarted(View view) {
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mNeedPay) {
                    //showUnCloseAbleTips();
                }
            }
        }, 60 * 1000 * 3);
    }

    private class MyTimeHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Message message = new Message();
            message.what = 1;
            sendMessageDelayed(message, 900);
            if (mView != null) {
                mView.updateProgress();
            }
        }
    }

    @Override
    public void bindView(PlayVideoViewContract.View view) {
        mView = view;
    }
}
