package com.xiaoshujing.kid.ui.scoreFromAlbum;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.common.BaseActivity;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.event.EventLoadPhotoSuccessPage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class ScoreFromAlbumActivity extends BaseActivity implements ScoreFromAlbumContract.View {
    static final String TAG = "ScoreFromAlbumActivity";
    @BindView(R.id.photo_preview_parent)
    FrameLayout mPhotoPreviewParent;
    @BindView(R.id.retake)
    TextView mRetake;
    @BindView(R.id.finish)
    View mFinish;
    ScoreFromAlbumContract.Presenter mPresenter;

    @BindView(R.id.photo_preview)
    ImageView mPhotoPreview;
    Bitmap mCurrentBitMap;
    String mCurrentFilePath;

    int mTargetWidth;
    int mTargetHeight;
    int mMinHeightOfUserArea = 80;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_from_photo);
        Util.DLog(TAG, "onCreate");
        setKeepScreenStatus(true);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        mFinish.setEnabled(false);
        mPresenter = new ScoreFromAlbumPresentImpl(this);
        mPresenter.bindView(this);
        setPresenter(mPresenter);
        mTargetWidth = Util.getScreenWidth(this) - Util.getPxFromDp(this, 32);
        while (mTargetWidth % 3 != 0) {
            mTargetWidth--;
        }
        mTargetHeight = mTargetWidth * 4 / 3;
        //40 is bar height , 20 is padding top and bottom
        if (Util.getScreenHeight(this) - Util.getPxFromDp(this, 40 + 20 + mMinHeightOfUserArea) < mTargetHeight) {
            mTargetHeight = Util.getScreenHeight(this) - Util.getPxFromDp(this, 40 + 80);
            while (mTargetHeight % 4 != 0) {
                mTargetHeight--;
            }
            mTargetWidth = mTargetHeight * 3 / 4;
        }
        ViewGroup.LayoutParams layoutParams = mPhotoPreviewParent.getLayoutParams();
        layoutParams.width = mTargetWidth;
        layoutParams.height = mTargetHeight;
        mPhotoPreviewParent.setLayoutParams(layoutParams);
        mPhotoPreviewParent.invalidate();
        mPhotoPreview.invalidate();
        mPresenter.setTargetSize(mTargetWidth, mTargetHeight);
        if (!MyApplication.mIsDebug) {
            mPresenter.check();
        } else {
            mPresenter.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Util.DLog(TAG, "onPause");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Util.DLog(TAG, "onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Util.DLog(TAG, "onDestroy");
        EventBus.getDefault().unregister(this);
        if (mPresenter != null) {
            mPresenter.clear();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventLoadPhotoSuccessPage(EventLoadPhotoSuccessPage eventLoadPhotoSuccessPage) {
        finish();
    }

    @OnClick({
            R.id.retake,
            R.id.finish,
            R.id.back
    })
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.retake: {
                if (mPresenter != null) {
                    mPresenter.reSelect();
                }
            }
            break;
            case R.id.finish: {
                if (mPresenter != null) {
                    mPresenter.openPhotoSuccessPage(mPhotoPreview);
                }
            }
            break;
            case R.id.back: {
                finish();
            }
            break;
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
    public void setPresenter(ScoreFromAlbumContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void clear() {

    }

    @Override
    public void setDisplayPhoto(Bitmap bitmap, String path) {
        if (mPhotoPreview != null) {
            mPhotoPreview.setImageBitmap(bitmap);
            mCurrentBitMap = bitmap;
            mCurrentFilePath = path;
            if (mCurrentBitMap != null) {
                MyApplication.getInstance().addPreUploadBitmap(mCurrentBitMap);
                MyApplication.getInstance().addPreUploadBitmapPath(mCurrentFilePath);
            }
            mFinish.setEnabled(true);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mPresenter != null) {
            mPresenter.onActivityResult(requestCode, resultCode, data);
        }
    }
}
