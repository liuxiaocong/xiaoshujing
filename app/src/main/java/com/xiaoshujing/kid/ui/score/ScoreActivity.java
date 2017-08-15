package com.xiaoshujing.kid.ui.score;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.TextureView;
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
public class ScoreActivity extends BaseActivity implements ScoreContract.View {
    static final String TAG = "ScoreActivity";
    @BindView(R.id.camera_texture)
    TextureView mTextureView;
    @BindView(R.id.camera_texture_parent)
    FrameLayout mTextureViewParent;
    @BindView(R.id.flash)
    ImageView mFlash;
    ScoreContract.Presenter mPresenter;
    @BindView(R.id.mask)
    ImageView mMask;
    @BindView(R.id.take_photo)
    ImageView mTakePhoto;
    @BindView(R.id.photo_preview)
    ImageView mPhotoPreview;
    @BindView(R.id.focus)
    ImageView mFocus;
    @BindView(R.id.retake)
    TextView mRetake;
    @BindView(R.id.finish)
    TextView mFinish;
    @BindView(R.id.cancel)
    TextView mCancel;

    int mTargetWidth;
    int mTargetHeight;
    int mMinHeightOfUserArea = 80;
    Bitmap mCurrentBitMap;
    String mCurrentFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Util.DLog(TAG, "onCreate");
        setKeepScreenStatus(true);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        mPresenter = new ScorePresentImpl(this);
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
        ViewGroup.LayoutParams layoutParams = mTextureViewParent.getLayoutParams();
        layoutParams.width = mTargetWidth;
        layoutParams.height = mTargetHeight;
        mTextureViewParent.setLayoutParams(layoutParams);
        mTextureView.invalidate();
        mFlash.invalidate();
        mMask.invalidate();
        mFocus.invalidate();
        mPhotoPreview.invalidate();
        if (!MyApplication.mIsDebug) {
            mPresenter.check();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Util.DLog(TAG, "onPause");
        if (mPresenter != null) {
            mPresenter.stopPreview();
            mPresenter.releaseCamera();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Util.DLog(TAG, "onStart");
        mTextureView.post(new Runnable() {
            @Override
            public void run() {
                if (mPresenter != null) {
                    mPresenter.startPreview();
                }
            }
        });
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
            R.id.flash,
            R.id.take_photo,
            R.id.retake,
            R.id.finish,
            R.id.cancel,
            R.id.back,
            R.id.camera_texture
    })
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.camera_texture: {
                //MyApplication.getInstance().showNote("auto focus");
                if (mPresenter != null) {
                    mPresenter.focus();
                }
            }
            break;
            case R.id.flash: {
                if (mPresenter != null) {
                    mPresenter.toggleFlash();
                }
            }
            break;
            case R.id.take_photo: {
                if (mPresenter != null) {
                    mPresenter.takePhoto();
                }
            }
            break;
            case R.id.retake: {
                updateUi(ETakePhotoStatus.ETakePhoto);
            }
            break;
            case R.id.cancel: {
                finish();
            }
            break;
            case R.id.finish: {
                if (mCurrentBitMap != null) {
                    MyApplication.getInstance().addPreUploadBitmap(mCurrentBitMap);
                    MyApplication.getInstance().addPreUploadBitmapPath(mCurrentFilePath);
                }
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
    public void setPresenter(ScoreContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void clear() {

    }

    @Override
    public void setSurfaceTextureListener(TextureView.SurfaceTextureListener listener) {
        mTextureView.setSurfaceTextureListener(listener);
    }

    @Override
    public TextureView getTextureView() {
        return mTextureView;
    }

    @Override
    public int getTargetWidth() {
        return mTargetWidth;
    }

    @Override
    public int getTargetHeight() {
        return mTargetHeight;
    }

    @Override
    public void setDisplayPhoto(Bitmap bitmap, String path) {
        mCurrentBitMap = bitmap;
        mCurrentFilePath = path;
        mPhotoPreview.setImageBitmap(bitmap);
        mPhotoPreview.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFocusStatus(boolean visible) {
        if (mFocus != null)
            if (visible) {
                mFocus.setVisibility(View.VISIBLE);
            } else {
                mFocus.setVisibility(View.GONE);
            }
    }

    @Override
    public void updateUi(ETakePhotoStatus eTakePhotoStatus) {
        if (eTakePhotoStatus.equals(ETakePhotoStatus.ETakePhotoSuccess)) {
            mPhotoPreview.setVisibility(View.VISIBLE);
            mRetake.setVisibility(View.VISIBLE);
            mTakePhoto.setVisibility(View.GONE);
            mFinish.setVisibility(View.VISIBLE);
        } else if (eTakePhotoStatus.equals(ETakePhotoStatus.ETakePhoto)) {
            mPhotoPreview.setVisibility(View.GONE);
            mRetake.setVisibility(View.GONE);
            mTakePhoto.setVisibility(View.VISIBLE);
            mFinish.setVisibility(View.GONE);

            mPresenter.releaseCamera();
            mPresenter.startPreview();
        }
    }
}
