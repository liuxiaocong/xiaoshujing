package com.xiaoshujing.kid.ui.playVideoView;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.common.BaseActivity;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.model.video.GetSeasonVideoListBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class PlayVideoViewActivity extends BaseActivity implements PlayVideoViewContract.View, MediaPlayer.OnPreparedListener {
    PlayVideoViewContract.Presenter mPresenter;
    final String TAG = "PlayVideoViewActivity";
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
    MediaPlayer mMediaPlayer;
    //set width to this view
    @BindView(R.id.video_area)
    View mVideoArea;

    private String mUrl;

    //set height to this two view
    @BindView(R.id.video_content)
    View mVideoContent;
    @BindView(R.id.video_mask)
    View mVideoMask;

    @BindView(R.id.seek_bar)
    SeekBar mSeekBar;

    @BindView(R.id.video_view)
    VideoView mVideoView;
    SurfaceHolder mSurfaceHolder;

    @BindView(R.id.play_icon)
    View mPlayIcon;

    int mTargetWidth = 300;
    int mMaxHeight = 300;

    @BindView(R.id.top_wrap)
    View mTopWrap;
    @BindView(R.id.info_wrap)
    View mInfoWrap;
    @BindView(R.id.max)
    View mMax;
    @BindView(R.id.min)
    View mMin;

    boolean mStartedPlay = false;
    private BroadcastReceiver mMakeWishReceiver;

    public static void openActivity(Activity activity, GetSeasonVideoListBean.EpisodesBean episodesBean) {
        Intent intent = new Intent(activity, PlayVideoViewActivity.class);
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
        setContentView(R.layout.activity_play_video_view);
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
        mTitleTextView.setText(mTitle);
        mDescriptionTextView.setText(mDescription);
        if (!Util.isNullOrEmpty(mPhotoUrl)) {
            Uri uri = Uri.parse(mPhotoUrl);
            mCover.setImageURI(uri);
        }
        mPresenter = new PlayVideoViewPresentImpl(this, mVideoId, mIsFree);
        setPresenter(mPresenter);
        mPresenter.bindView(this);
        mVideoView.setOnPreparedListener(this);
        mPresenter.start();
        mMakeWishReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                PlayVideoViewActivity.this.finish();
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.MAKE_WISH");
        registerReceiver(mMakeWishReceiver, filter);
    }


    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            Util.DLog(TAG, "onProgressChanged:" + i);
//            if(b)
//            {
//                //from user
//                if(mIjkMediaPlayer!=null && mIjkMediaPlayer.isPlayable())
//                {
//                    mIjkMediaPlayer.seekTo(i);
//                }
//            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Util.DLog(TAG, "onStopTrackingTouch:" + seekBar.getProgress());
            if (mMediaPlayer != null) {
                if (mUrl != null && !(mUrl.indexOf("m3u8") > 0)) {
                    mMediaPlayer.seekTo(seekBar.getProgress());
                }
//                mVideoView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mMediaPlayer.start();
//                    }
//                },500);
            }
        }
    };

    public void resetVideoViewLayout(MediaPlayer iMediaPlayer) {
        if (iMediaPlayer == null) return;
        int width = iMediaPlayer.getVideoWidth();
        int height = iMediaPlayer.getVideoHeight();
        Util.DLog(TAG, "videoWidth:" + width);
        Util.DLog(TAG, "videoHeight:" + height);
        int targetHeight = mTargetWidth * height / width;
        int targetWidth = mTargetWidth;

        if (targetHeight > mMaxHeight) {
            targetHeight = mMaxHeight;
            targetWidth = mMaxHeight * width / height;
        }
        LinearLayout.LayoutParams videoAreaLayoutParams = (LinearLayout.LayoutParams) mVideoArea.getLayoutParams();
        videoAreaLayoutParams.width = targetWidth;
        mVideoArea.setLayoutParams(videoAreaLayoutParams);

        FrameLayout.LayoutParams videoContentLayoutParams = (FrameLayout.LayoutParams) mVideoContent.getLayoutParams();
        videoContentLayoutParams.height = targetHeight;
        mVideoContent.setLayoutParams(videoContentLayoutParams);

        FrameLayout.LayoutParams videoMaskLayoutParams = (FrameLayout.LayoutParams) mVideoMask.getLayoutParams();
        videoMaskLayoutParams.height = targetHeight;
        mVideoMask.setLayoutParams(videoMaskLayoutParams);

        Util.DLog(TAG, "realWidth:" + targetWidth);
        Util.DLog(TAG, "realHeight:" + targetHeight);
    }

    @OnClick({R.id.play_icon, R.id.back, R.id.max, R.id.min})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.play_icon: {
                mPresenter.play();
            }
            break;
            case R.id.back: {
                finish();
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
        mVideoView.invalidate();
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
        mVideoView.invalidate();
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
    public void setPresenter(PlayVideoViewContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (mVideoView != null && mVideoView.isPlaying()) {
            mVideoView.pause();
        }
        super.onPause();
    }

    @Override
    public void playVideo(String url, boolean needPay) {
        Util.DLog(TAG, "playVideo url:" + url);
        if (mVideoView != null) {
            play();
        } else {
            mStartedPlay = true;
        }
    }

    @Override
    protected void onDestroy() {
        if (mMakeWishReceiver != null) {
            unregisterReceiver(mMakeWishReceiver);
        }
        if (mPresenter != null) {
            mPresenter.clear();
        }
        setKeepScreenStatus(false);
        mVideoView = null;
        super.onDestroy();
    }

    private void play() {
        if (mVideoView != null && mVideoContent != null) {
            mVideoView.start();
            mPresenter.onVideoStarted(mVideoArea);
            setKeepScreenStatus(true);
            mCover.setVisibility(View.GONE);
            //mVideoMask.setVisibility(View.GONE);
            mPlayIcon.setVisibility(View.GONE);
        }
    }

    @Override
    public void prepareVideo(String url) {
        Util.DLog(TAG, "prepareVideo url:" + url);
        mUrl = url;
        if (!Util.isNullOrEmpty(url) && mVideoView != null) {
            mVideoView.setVideoURI(Uri.parse(url));
        }
    }

    @Override
    public void readyToPlay() {
        Util.DLog(TAG, "readyToPlay");
        mPlayIcon.setEnabled(true);
        mPlayIcon.setAlpha(1f);
    }

    @Override
    public void updateProgress() {
        if (mVideoView != null) {
            mSeekBar.setProgress(mVideoView.getCurrentPosition());
        }
    }


    @Override
    public void onPrepared(MediaPlayer mp) {
        mMediaPlayer = mp;
//        MediaController mc = new MediaController(this);
//        mVideoView.setMediaController(mc);
//                /*
//                 * and set its position on screen
//                 */
//        mc.setAnchorView(mVideoView);
        Util.DLog(TAG, "getDuration:" + mp.getDuration());
        mSeekBar.setMax(mp.getDuration());
        mSeekBar.setOnSeekBarChangeListener(mOnSeekBarChangeListener);
        mMediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mp) {
                Util.DLog(TAG, "onSeekComplete:");
                mp.start();
            }
        });
        resetVideoViewLayout(mp);
    }
}
