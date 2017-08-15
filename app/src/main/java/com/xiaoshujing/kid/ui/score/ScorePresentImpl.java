package com.xiaoshujing.kid.ui.score;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.camera.CameraManager;
import com.xiaoshujing.kid.camera.PhotoQuality;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.data.PractiseManage;
import com.xiaoshujing.kid.data.UserInfoManage;
import com.xiaoshujing.kid.event.EventAutoFocus;
import com.xiaoshujing.kid.event.EventGetPhotoData;
import com.xiaoshujing.kid.model.CommonRetBean;
import com.xiaoshujing.kid.model.UploadImgBean;
import com.xiaoshujing.kid.ui.photoSuccess.PhotoSuccessActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.lang.ref.WeakReference;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class ScorePresentImpl implements ScoreContract.Presenter {
    String TAG = "ScorePresentImpl";
    Activity mContext;
    ScoreContract.View mView;
    CameraManager mCameraManager;
    private int mCameIndex = Camera.CameraInfo.CAMERA_FACING_BACK;
    private int mPreviewWidth;
    private int mPreviewHeight;
    private int mDisplayWidth;
    private int mDisplayHeight;
    private boolean mIsInMirror = false;
    private int mDegree = 0;
    private PhotoQuality mPhotoQuality;
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    public ScorePresentImpl(Activity context) {
        mContext = context;
        mCameraManager = CameraManager.getInstance();
        EventBus.getDefault().register(this);
    }


    @Override
    public void bindView(ScoreContract.View view) {
        mView = view;
        mView.setSurfaceTextureListener(mSurfaceTextureListener);
    }


    @Override
    public void startPreview() {
        if (mView == null) return;
        if (mView.getTextureView() == null) return;
        mPhotoQuality = new PhotoQuality(
                mView.getTargetWidth(), mView.getTargetHeight(), 15);
        mCameraManager.openCameraAndPreview((Activity) mContext, mCameIndex, mPhotoQuality, mView.getTextureView(), mView.getTargetWidth(), mView.getTargetHeight(), mOpenCameraListener, mPreviewCallback);
    }

    @Override
    public void stopPreview() {
        mCameraManager.stopPreview();
    }

    @Override
    public void releaseCamera() {
        mCameraManager.releaseCamera();
    }

    @Override
    public void toggleFlash() {
        mCameraManager.toggleFlash();
    }

    @Override
    public void takePhoto() {
        mCameraManager.takePhoto(new WeakReference<>((Activity) mContext), mView.getTargetWidth(), mView.getTargetHeight());
    }

    @Override
    public void clear() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void openPhotoSuccessPage(View view) {
        Pair<View, String> pair1 = null;
        Intent intent = new Intent(mContext, PhotoSuccessActivity.class);
        intent.putExtra(PhotoSuccessActivity.OPEN_FROM, "take");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            pair1 = Pair.create(view, view.getTransitionName());
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) mContext, pair1);
            mContext.startActivity(intent, options.toBundle());
        } else {
            mContext.startActivity(intent);
        }
    }

    @Override
    public void check() {
        PractiseManage.getInstance().check(mCommonRetBeanForCheckSimpleCallback);
    }

    @Override
    public void focus() {
        CameraManager.getInstance().focus();
    }

    SimpleCallback<CommonRetBean> mCommonRetBeanForCheckSimpleCallback = new SimpleCallback<CommonRetBean>() {
        @Override
        public void onSuccess(CommonRetBean response) {
            if (response.get_status() == 0) {

            } else {
                Util.showMessageAlert(mContext, "", response.get_reason(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mContext.finish();
                    }
                }, false);
            }
        }

        @Override
        public void onFailure(int responseCode) {
            MyApplication.getInstance().showNote("error");
            mContext.finish();
        }
    };


    private CameraManager.OpenCameraListener mOpenCameraListener = new CameraManager.OpenCameraListener() {
        @Override
        public void onCallbackCameraInfo(final int realwidth, final int realheight, final int degree, int cindex) {
            if (mCameIndex == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                mIsInMirror = true;
            }
            mDegree = degree;
            int previewWidth = realwidth;
            int previewHeight = realheight;
            if (degree == 90 || degree == 270) {
                previewWidth = realheight;
                previewHeight = realwidth;
            }
            ((Activity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    resetCameraViewSize(mView.getTextureView(), realwidth, realheight, degree);
                }
            });
        }
    };

    public void resetCameraViewSize(View targetView, int realWidth, int realHeight, int degree) {
        int currentWidth = targetView.getMeasuredWidth();
        int currentHeight = targetView.getMeasuredHeight();
        mPreviewWidth = realWidth;
        mPreviewHeight = realHeight;
        mDisplayWidth = realWidth;
        mDisplayHeight = realHeight;
        if (degree == 90 || degree == 270) {
            mDisplayWidth = realHeight;
            mDisplayHeight = realWidth;
        }
        float scaleRatioOfWidth = (float) mDisplayWidth / (float) currentWidth;
        float scaleRatioOfHeight = (float) mDisplayHeight / (float) currentHeight;
        float expectRatio = scaleRatioOfWidth < scaleRatioOfHeight ? scaleRatioOfWidth : scaleRatioOfHeight;
        int expectWidth = (int) ((float) mDisplayWidth / expectRatio);
        int expectHeight = (int) ((float) mDisplayHeight / expectRatio);
        int parentWidth = ((View) targetView.getParent()).getMeasuredWidth();
        int parentHeight = ((View) targetView.getParent()).getMeasuredHeight();
        Log.d(TAG, "degree:" + degree);
        Log.d(TAG, "currentWidth:" + currentWidth);
        Log.d(TAG, "currentHeight:" + currentHeight);
        Log.d(TAG, "mDisplayWidth:" + mDisplayWidth);
        Log.d(TAG, "mDisplayHeight:" + mDisplayHeight);
        Log.d(TAG, "scaleRatioOfWidth:" + scaleRatioOfWidth);
        Log.d(TAG, "scaleRatioOfHeight:" + scaleRatioOfHeight);
        Log.d(TAG, "expectRatio:" + expectRatio);
        Log.d(TAG, "expectWidth:" + expectWidth);
        Log.d(TAG, "expectHeight:" + expectHeight);
        Log.d(TAG, "parentWidth:" + parentWidth);
        Log.d(TAG, "parentHeight:" + parentHeight);

        ViewGroup.LayoutParams lp = targetView.getLayoutParams();
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            lp.width = expectWidth;
            lp.height = expectHeight;
            int marginTop = (int) ((float) (parentHeight - expectHeight) / 2);
            int marginLeft = (int) ((float) (parentWidth - expectWidth) / 2);
            ((ViewGroup.MarginLayoutParams) lp).setMargins(marginLeft, marginTop, 0, 0);
            targetView.setLayoutParams(lp);
            Log.d(TAG, "marginTop:" + marginTop);
            Log.d(TAG, "marginLeft:" + marginLeft);
        }
    }

    private Camera.PreviewCallback mPreviewCallback = new Camera.PreviewCallback() {
        @Override
        public void onPreviewFrame(byte[] bytes, Camera camera) {
            CameraManager.getInstance().addBuffer(bytes);
        }
    };
    private TextureView.SurfaceTextureListener mSurfaceTextureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
            Log.d(TAG, "onSurfaceTextureAvailable");
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {
            Log.d(TAG, "onSurfaceTextureSizeChanged");
        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            Log.d(TAG, "onSurfaceTextureDestroyed");
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            //Log.d(TAG,"onSurfaceTextureUpdated");
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventGetPhotoData(EventAutoFocus eventGetPhotoData) {
        if (eventGetPhotoData != null) {
            if (mView != null) {
                mView.showFocusStatus(eventGetPhotoData.isAutoFocus);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventGetPhotoData(EventGetPhotoData eventGetPhotoData) {
        if (eventGetPhotoData != null) {
            if (mView != null) {
                mView.setDisplayPhoto(eventGetPhotoData.photo, eventGetPhotoData.filePath);
                mView.updateUi(ETakePhotoStatus.ETakePhotoSuccess);
                mView.showFocusStatus(false);
            }

//            File f = new File(eventGetPhotoData.filePath);
//            if (f.length() > 0) {
//                MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
//                prepareFile(requestBodyBuilder, f);
//                UserInfoManage.getInstance().uploadImages(mUploadImageBeanSimpleCallback, requestBodyBuilder.build());
//            }
        }
    }

    private static void prepareFile(MultipartBody.Builder builder, File file) {
        builder.addFormDataPart("fileList", file.getName(),
                RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file));
    }

    SimpleCallback<UploadImgBean> mUploadImageBeanSimpleCallback = new SimpleCallback<UploadImgBean>() {
        @Override
        public void onSuccess(UploadImgBean response) {
            UploadImgBean ret = response;
        }

        @Override
        public void onFailure(int responseCode) {
            int ret = responseCode;
        }
    };
}
