package com.xiaoshujing.kid.ui.playVideoFFMpeg;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.common.BaseActivity;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.model.video.GetSeasonVideoListBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mozat.rings.libffmpeg.FFMPEGMediaController;
import mozat.rings.libffmpeg.FFMPEGPlayer;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class PlayVideoFFMpegActivity extends BaseActivity implements PlayVideoFFMpegContract.View {
    PlayVideoFFMpegContract.Presenter mPresenter;
    final String TAG = "PlayVideoFFMpegActivity";
    private static final String EXT_PHOTO_URL = "EXT_PHOTO_URL";
    private static final String EXT_TITLE = "EXT_TITLE";
    private static final String EXT_DES = "EXT_DES";
    private static final String EXT_ID = "EXT_ID";
    private static final String EXT_VIDEO_ISFREE = "EXT_VIDEO_ISFREE";
    private String mPhotoUrl;
    private String mTitle;
    private String mDescription;
    private String mVideoId;
    private boolean mIsFree;
    @BindView(R.id.cover)
    SimpleDraweeView mCover;
    @BindView(R.id.title)
    TextView mTitleTextView;
    @BindView(R.id.des)
    TextView mDescriptionTextView;

    //set width to this view
    @BindView(R.id.video_area)
    View mVideoArea;

    //set height to this two view
    @BindView(R.id.video_content)
    View mVideoContent;

    @BindView(R.id.video_mask)
    View mVideoMask;

    @BindView(R.id.top_wrap)
    View mTopWrap;
    @BindView(R.id.info_wrap)
    View mInfoWrap;
    @BindView(R.id.max)
    View mMax;
    @BindView(R.id.min)
    View mMin;


    private FFMPEGPlayer.EVIDEO_LAYOUT mTargetLayout = FFMPEGPlayer.EVIDEO_LAYOUT.EFIT_HEIGHT_CENTER_WIDTH;
    @BindView(R.id.player)
    public FFMPEGPlayer mFFMPEGPlayer;
    @BindView(R.id.media_control)
    public FFMPEGMediaController mFFMPEGMediaController;


    @BindView(R.id.play_icon)
    View mPlayIcon;

    int mTargetWidth = 300;
    int mMaxHeight = 300;

    boolean mStartedPlay = false;
    private BroadcastReceiver mMakeWishReceiver;

    public static void openActivity(Activity activity, GetSeasonVideoListBean.EpisodesBean episodesBean) {
        Intent intent = new Intent(activity, PlayVideoFFMpegActivity.class);
        intent.putExtra(EXT_PHOTO_URL, episodesBean.getCover_url());
        intent.putExtra(EXT_TITLE, episodesBean.getName());
        intent.putExtra(EXT_DES, episodesBean.getDescription());
        intent.putExtra(EXT_ID, episodesBean.getObject_id());
        intent.putExtra(EXT_VIDEO_ISFREE, episodesBean.isIsFree());
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video_ffmpeg);
        Intent intent = getIntent();
        mPhotoUrl = intent.getStringExtra(EXT_PHOTO_URL);
        mTitle = intent.getStringExtra(EXT_TITLE);
        mDescription = intent.getStringExtra(EXT_DES);
        mVideoId = intent.getStringExtra(EXT_ID);
        mIsFree = intent.getBooleanExtra(EXT_VIDEO_ISFREE, false);
        mTargetWidth = Util.getScreenWidth(this) - Util.getPxFromDp(this, 40);
        mMaxHeight = Util.getScreenHeight(this) - Util.getPxFromDp(this, 40);
        mMaxHeight = (int) ((float) mMaxHeight / 1136f * 650f);
        Util.DLog(TAG, "mTargetWidth:" + mTargetWidth);
        Util.DLog(TAG, "mMaxHeight:" + mMaxHeight);
        ButterKnife.bind(this);

        mPlayIcon.setEnabled(false);
        mPlayIcon.setAlpha(0.6f);

        mFFMPEGPlayer.setRenderMode(FFMPEGPlayer.ERENDER_MODE.ENORMAL_TEXTURE_VIEW);
        mFFMPEGPlayer.setVideoLayout(mTargetLayout, false);
        mFFMPEGPlayer.setmMediaControllerRightMargin(Util.getPxFromDp(this, 30));
        mTitleTextView.setText(mTitle);
        mDescriptionTextView.setText(mDescription);
        if (!Util.isNullOrEmpty(mPhotoUrl)) {
            Uri uri = Uri.parse(mPhotoUrl);
            mCover.setImageURI(uri);
        }
        mPresenter = new PlayVideoFFMpegPresentImpl(this, mVideoId, mIsFree);
        setPresenter(mPresenter);
        mPresenter.bindView(this);

        mPresenter.start();
        mMakeWishReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                PlayVideoFFMpegActivity.this.finish();
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.MAKE_WISH");
        registerReceiver(mMakeWishReceiver, filter);
    }


//    public void resetVideoViewLayout(IMediaPlayer iMediaPlayer) {
//        if (iMediaPlayer == null) return;
//        int width = iMediaPlayer.getVideoWidth();
//        int height = iMediaPlayer.getVideoHeight();
//        Util.DLog(TAG, "videoWidth:" + width);
//        Util.DLog(TAG, "videoHeight:" + height);
//        int targetHeight = mTargetWidth * height / width;
//        int targetWidth = mTargetWidth;
//
//        if (targetHeight > mMaxHeight) {
//            targetHeight = mMaxHeight;
//            targetWidth = mMaxHeight * width / height;
//        }
//        LinearLayout.LayoutParams videoAreaLayoutParams = (LinearLayout.LayoutParams) mVideoArea.getLayoutParams();
//        videoAreaLayoutParams.width = targetWidth;
//        mVideoArea.setLayoutParams(videoAreaLayoutParams);
//
//        FrameLayout.LayoutParams videoContentLayoutParams = (FrameLayout.LayoutParams) mVideoContent.getLayoutParams();
//        videoContentLayoutParams.height = targetHeight;
//        mVideoContent.setLayoutParams(videoContentLayoutParams);
//
//        FrameLayout.LayoutParams videoMaskLayoutParams = (FrameLayout.LayoutParams) mVideoMask.getLayoutParams();
//        videoMaskLayoutParams.height = targetHeight;
//        mVideoMask.setLayoutParams(videoMaskLayoutParams);
//
//        Util.DLog(TAG, "realWidth:" + targetWidth);
//        Util.DLog(TAG, "realHeight:" + targetHeight);
//    }

    @OnClick({R.id.play_icon, R.id.back, R.id.max, R.id.min})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.play_icon: {
                mPresenter.play();
            }
            break;
            case R.id.max: {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                setFullScreenLayout();
            }
            break;
            case R.id.min: {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                setMinScreenLayout();
            }
            break;
            case R.id.back: {
                finish();
            }
            break;
        }
    }

    public void setFullScreenLayout() {
        mMin.setVisibility(View.VISIBLE);
        mMax.setVisibility(View.GONE);
        mTopWrap.setVisibility(View.GONE);
        mInfoWrap.setVisibility(View.GONE);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mVideoArea.getLayoutParams();
        layoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT;
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
        mVideoArea.setLayoutParams(layoutParams);
        FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) mVideoContent.getLayoutParams();
        layoutParams1.height = FrameLayout.LayoutParams.MATCH_PARENT;
        layoutParams1.width = FrameLayout.LayoutParams.MATCH_PARENT;
        mVideoContent.setLayoutParams(layoutParams1);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) mVideoMask.getLayoutParams();
        layoutParams2.height = FrameLayout.LayoutParams.MATCH_PARENT;
        layoutParams2.width = FrameLayout.LayoutParams.MATCH_PARENT;
        mVideoMask.setLayoutParams(layoutParams2);
    }

    public void setMinScreenLayout() {
        mMax.setVisibility(View.VISIBLE);
        mMin.setVisibility(View.GONE);
        mTopWrap.setVisibility(View.VISIBLE);
        mInfoWrap.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mVideoArea.getLayoutParams();
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.width = Util.getPxFromDp(300);
        mVideoArea.setLayoutParams(layoutParams);
        FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) mVideoContent.getLayoutParams();
        layoutParams1.height = Util.getPxFromDp(216);
        layoutParams1.width = FrameLayout.LayoutParams.MATCH_PARENT;
        mVideoContent.setLayoutParams(layoutParams1);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) mVideoMask.getLayoutParams();
        layoutParams2.height = Util.getPxFromDp(216);
        layoutParams2.width = FrameLayout.LayoutParams.MATCH_PARENT;
        mVideoMask.setLayoutParams(layoutParams2);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {

        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

        }
    }

    public void setKeepScreenStatus(boolean isOn) {
        if (isOn) {
            getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    @Override
    public void setPresenter(PlayVideoFFMpegContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onResume() {
//        if (mIjkMediaPlayer != null && mIjkMediaPlayer.isPlayable()) {
//            mIjkMediaPlayer.start();
//        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void playVideo(String url, boolean needPay) {
        Util.DLog(TAG, "playVideo url:" + url);
        if (mFFMPEGPlayer != null && mVideoContent != null) {
            String[] playUrls = new String[]{url};
            mFFMPEGPlayer.requestFocus();
            mFFMPEGPlayer.playA(
                    playUrls,
                    FFMPEGPlayer.ERENDER_MODE.ENORMAL_TEXTURE_VIEW,
                    0,
                    0,
                    AudioManager.STREAM_MUSIC,
                    0,
                    0,
                    0);// no intermission report
            mPresenter.onVideoStarted(mVideoArea);
            setKeepScreenStatus(true);
            mCover.setVisibility(View.GONE);
            //mVideoMask.setVisibility(View.GONE);
            mPlayIcon.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.clear();
        }
        if (mFFMPEGPlayer != null) {
            mFFMPEGPlayer.destroy();
        }
        setKeepScreenStatus(false);
        super.onDestroy();
    }

    @Override
    public void setPlayerListener(FFMPEGPlayer.FFMPEGPlayerListener listener) {
        if (mFFMPEGPlayer != null) {
            mFFMPEGPlayer.setPlayerListener(listener);
        }
    }

    @Override
    public void setMediaControllerVisible(boolean b) {
        if (mFFMPEGPlayer != null) {
            mFFMPEGPlayer.setMediaControllerVisible(b);
        }
    }


    @Override
    public void readyToPlay() {
        Util.DLog(TAG, "readyToPlay");
        mPlayIcon.setEnabled(true);
        mPlayIcon.setAlpha(1f);
    }


    @Override
    public FFMPEGPlayer getFFMPEGPlayer() {
        return mFFMPEGPlayer;
    }
}
