package com.xiaoshujing.kid.ui.playVideoFFMpeg;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;

import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.data.SeasonVideoManage;
import com.xiaoshujing.kid.model.video.GetSeasonPaidVideoDetailBean;
import com.xiaoshujing.kid.model.video.GetSeasonVideoDetailBean;
import com.xiaoshujing.kid.ui.dialog.PlayVideoTipDialog;
import com.xiaoshujing.kid.ui.makeWish.MakeWishActivity;

import mozat.rings.libffmpeg.FFMPEGPlayer;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class PlayVideoFFMpegPresentImpl implements PlayVideoFFMpegContract.Presenter {
    String TAG = "PlayVideoFFMpegPresentImpl";
    private String mPlayUrl = "http://d2bi7sjjwydkz8.cloudfront.net:80/video/mp4:/2016/12/31/00/10016207/1483116484698/playlist.m3u8";

    PlayVideoFFMpegContract.View mView;
    Activity mContext;
    boolean mIsFreeVideo;
    String mVideoId;
    GetSeasonVideoDetailBean mGetSeasonVideoDetailBean;
    GetSeasonPaidVideoDetailBean mGetSeasonPaidVideoDetailBean;
    String mVideoUrl;
    boolean mNeedPay = false;
    boolean mAutoPlay = true;
    private FFMPEGPlayer.EVIDEO_LAYOUT mTargetLayout = FFMPEGPlayer.EVIDEO_LAYOUT.EFIT_HEIGHT_CENTER_WIDTH;

    public PlayVideoFFMpegPresentImpl(Activity context, String videoId, boolean isFreeVideo) {
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
                //mVideoUrl = "http://d2bi7sjjwydkz8.cloudfront.net:80/video/mp4:/2016/12/31/00/10016207/1483116484698/playlist.m3u8";
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
        SeasonVideoManage.getInstance().getSeasonVideoDetail(mGetSeasonVideoDetailBeanSimpleCallback, mVideoId);
    }

    @Override
    public void clear() {

    }

    @Override
    public void play() {
        mView.playVideo(mVideoUrl, mNeedPay);
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


    @Override
    public void bindView(PlayVideoFFMpegContract.View view) {
        mView = view;
        mView.setPlayerListener(mFFMPEGPlayerListener);
    }

    private FFMPEGPlayer.FFMPEGPlayerListener mFFMPEGPlayerListener = new FFMPEGPlayer.FFMPEGPlayerListener() {
        @Override
        public void onCompletion() {

        }

        @Override
        public void onBufferingUpdate(float percent) {

        }

        @Override
        public void onStateChanged(int st) {
            switch (st) {
                case FFMPEGPlayer.STATE_ERROR: {
                    //un normal stop

                }
                break;
                case FFMPEGPlayer.STATE_IDLE: {
                    //end

                }
                break;
                case FFMPEGPlayer.STATE_PREPARING: {

                }
                break;
                case FFMPEGPlayer.STATE_PREPARED: {

                }
                break;
                case FFMPEGPlayer.STATE_PLAYING: {
                    mView.setMediaControllerVisible(true);
                }
                break;
                case FFMPEGPlayer.STATE_PAUSED: {

                }
                break;
                case FFMPEGPlayer.STATE_STOPPING: {

                }
                break;
            }
        }

        @Override
        public void onVideoSizeChanged(int width, int height) {
            mView.getFFMPEGPlayer().setVideoLayout(mTargetLayout, false);
        }

        @Override
        public void onClicked() {

        }

        @Override
        public void onReportIntermission(long startAPts, long startVpts, int durationMS, int iccMS) {
            // no need
        }

        @Override
        public void onReportStalled() {
            // no need
        }

        @Override
        public void onDidLatencyOptimization(int cl, long apts) {
            // no need
        }
    };

}
