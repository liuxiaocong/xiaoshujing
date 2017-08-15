package com.xiaoshujing.kid.camera;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.TextureView;

import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.common.BitmapUtil;
import com.xiaoshujing.kid.common.LocalImage;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.event.EventAutoFocus;
import com.xiaoshujing.kid.event.EventGetPhotoData;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by LiuXiaocong on 11/15/2016.
 */
public class CameraManager {
    String TAG = "CameraManager";
    private static CameraManager gCameraManager = null;
    private Camera mCameraDevice;
    private CameraThreadHandler mCameraThreadHandler = null;
    private UIThreadHandler mUIThreadHandler = null;
    private static final int MSG_ON_OPEN_AND_PREVIEW = 0x2000;
    private static final int MSG_ON_STOP_PREVIEW = MSG_ON_OPEN_AND_PREVIEW + 1;
    private static final int MSG_ON_RELEASE = MSG_ON_STOP_PREVIEW + 1;
    private static final int MSG_ON_TAKE_PHOTO = MSG_ON_RELEASE + 1;
    private static final int MSG_ON_TAKE_PHOTO_SUCCESS = MSG_ON_TAKE_PHOTO + 1;
    private static final int MSG_BG_ON_TOGGLE_FLASH = MSG_ON_TAKE_PHOTO_SUCCESS + 1;
    private int mCameraIndex = Camera.CameraInfo.CAMERA_FACING_BACK;
    private int mCurrentCameraDegree = 0;

    public enum TCurrentFlashStatus {
        EOn,
        EOff,
        EDisable
    }

    TCurrentFlashStatus mTCurrentFlashStatus = TCurrentFlashStatus.EOff;


    public interface OpenCameraListener {
        void onCallbackCameraInfo(int realwidth, int realheight, int degree, int cindex);
    }


    public static CameraManager getInstance() {
        if (gCameraManager == null) {
            gCameraManager = new CameraManager();
        }
        return gCameraManager;
    }

    //call on ui thread
    private CameraManager() {
        HandlerThread handlerThread = new HandlerThread("CameraManager");
        handlerThread.start();
        mCameraThreadHandler = new CameraThreadHandler(handlerThread.getLooper());
        mUIThreadHandler = new UIThreadHandler(Looper.getMainLooper());
    }

    public void takePhoto(final WeakReference<Activity> activityRef, final int expectWidth, final int expectHeight) {
        mCameraThreadHandler.removeMessages(MSG_ON_TAKE_PHOTO);
        Message message = new Message();
        message.what = MSG_ON_TAKE_PHOTO;
        message.obj = new Object[]{activityRef, expectWidth, expectHeight};
        mCameraThreadHandler.sendMessage(message);
    }

    void takePhotoInCameraThread(final WeakReference<Activity> activityRef, final int expectWidth, final int expectHeight) {
        if (mCameraDevice == null) {
            return;
        }
        List<String> supportedFocusModes = mCameraDevice.getParameters().getSupportedFocusModes();
        boolean hasAutoFocus = supportedFocusModes != null && supportedFocusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO);
        if (hasAutoFocus) {
            Camera.Parameters _params = mCameraDevice.getParameters();
            _params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            mCameraDevice.setParameters(_params);
            EventBus.getDefault().post(new EventAutoFocus(true));
            mCameraDevice.autoFocus(new Camera.AutoFocusCallback() {
                private boolean _takenPhoto = false;

                @Override
                public void onAutoFocus(boolean success, Camera camera) {
                    if (!_takenPhoto) {// && success) {
                        _takenPhoto = true;
                        Camera.Parameters _params = camera.getParameters();
                        List<String> focusModes = _params.getSupportedFocusModes();
                        if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)) {
                            _params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
                            camera.setParameters(_params);
                        }
                        doTakePhoto(activityRef, expectWidth, expectHeight);
                        camera.cancelAutoFocus();
                    }
                }
            });
        } else {
            doTakePhoto(activityRef, expectWidth, expectHeight);
        }
    }

    public void focus(){
        if(mCameraDevice == null || mCameraDevice.getParameters() == null) return;
        List<String> supportedFocusModes = mCameraDevice.getParameters().getSupportedFocusModes();
        boolean hasAutoFocus = supportedFocusModes != null && supportedFocusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO);
        if (hasAutoFocus) {
            Camera.Parameters _params = mCameraDevice.getParameters();
            _params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            mCameraDevice.setParameters(_params);
            mCameraDevice.autoFocus(new Camera.AutoFocusCallback() {
                @Override
                public void onAutoFocus(boolean success, Camera camera) {
                }
            });
        }
    }

    private void doTakePhoto(final WeakReference<Activity> activityRef, final int expectWidth, final int expectHeight) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            mCameraDevice.enableShutterSound(false);
//        }
        mCameraDevice.takePicture(
                new Camera.ShutterCallback() {
                    @Override
                    public void onShutter() {
                        Log.d(TAG, "onShutter");
                    }
                }, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        Log.d(TAG, "onPictureTaken1");
                    }
                },
                new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        Log.d(TAG, "onPictureTaken()2");
                    }
                }, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//                            mCameraDevice.enableShutterSound(true);
//                        }
                        Log.d(TAG, "onPictureTaken3");
                        mCameraThreadHandler.removeMessages(MSG_ON_TAKE_PHOTO_SUCCESS);
                        Message message = new Message();
                        message.what = MSG_ON_TAKE_PHOTO_SUCCESS;
                        message.obj = new Object[]{activityRef, data, expectWidth, expectHeight};
                        mCameraThreadHandler.sendMessage(message);
                    }
                });


    }

    public void toggleFlash() {
        mCameraThreadHandler.removeMessages(MSG_BG_ON_TOGGLE_FLASH);
        Message message = new Message();
        message.what = MSG_BG_ON_TOGGLE_FLASH;
        mCameraThreadHandler.sendMessage(message);
    }

    public void openCameraAndPreview(Activity activity, int cameraIndex, PhotoQuality photoQuality,
                                     TextureView textureView, int parentWidth, int parentHeight,
                                     OpenCameraListener openCameraListener,
                                     Camera.PreviewCallback previewCallback) {
        mCameraThreadHandler.removeMessages(MSG_ON_OPEN_AND_PREVIEW);

        Message message = new Message();
        message.what = MSG_ON_OPEN_AND_PREVIEW;
        message.obj = new Object[]{new WeakReference<>(activity), cameraIndex, photoQuality, new WeakReference<>(textureView), parentWidth, parentHeight,
                new WeakReference<>(openCameraListener), new WeakReference<>(previewCallback)};
        mCameraThreadHandler.sendMessage(message);
    }

    public void stopPreview() {
        mCameraThreadHandler.removeMessages(MSG_ON_STOP_PREVIEW);
        Message message = new Message();
        message.what = MSG_ON_STOP_PREVIEW;
        mCameraThreadHandler.sendMessage(message);
    }

    public void releaseCamera() {
        mCameraThreadHandler.removeMessages(MSG_ON_RELEASE);
        Message message = new Message();
        message.what = MSG_ON_RELEASE;
        mCameraThreadHandler.sendMessage(message);
    }

    //need call when you use setPreviewCallbackWithBuffer
    public void addBuffer(byte[] data) {
        if (mCameraDevice != null) {
            mCameraDevice.addCallbackBuffer(data);
        }
    }

    public void openCameraAndPreviewInCameraThread(Activity activity, int cameraIndex, PhotoQuality photoQuality,
                                                   TextureView textureView, int parentWidth, int parentHeight,
                                                   OpenCameraListener openCameraListener,
                                                   Camera.PreviewCallback previewCallback) {
        if (mCameraDevice != null) {
            return;
        } else {
            Camera.CameraInfo cameraInfo = null;
            for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
                cameraInfo = new Camera.CameraInfo();
                Camera.getCameraInfo(i, cameraInfo);
                if (cameraInfo.facing == cameraIndex) {
                    try {
                        mCameraDevice = Camera.open(i);
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
            Camera.Parameters camParams = mCameraDevice.getParameters();
            mCurrentCameraDegree = CameraUtil.getCameraDisplayOrientation(activity, cameraInfo);

            int expectWidth = photoQuality.getVideoWidth();
            int expectHeight = photoQuality.getVideoHeight();

            int[] outBestPreviewSize = new int[2];
            if (mCurrentCameraDegree == 0 || mCurrentCameraDegree == 180) {
                getBestSizeFitShorterSide(expectWidth, expectHeight, camParams.getSupportedPreviewSizes(), outBestPreviewSize, mCurrentCameraDegree);
            } else {
                getBestSizeFitShorterSide(expectHeight, expectWidth, camParams.getSupportedPreviewSizes(), outBestPreviewSize, mCurrentCameraDegree);
            }
            if (outBestPreviewSize[0] == 0 || outBestPreviewSize[1] == 0) {
                if (expectWidth <= expectHeight) {
                    outBestPreviewSize[0] = 240;
                    outBestPreviewSize[1] = 320;
                } else {
                    outBestPreviewSize[0] = 320;
                    outBestPreviewSize[1] = 240;
                }
            }
            Log.d(TAG, "camParams.setPreviewSize size.width = " + outBestPreviewSize[0] + "; size.height = " + outBestPreviewSize[1]);
            camParams.setPreviewSize(outBestPreviewSize[0], outBestPreviewSize[1]);
            if (openCameraListener != null) {
                openCameraListener.onCallbackCameraInfo(outBestPreviewSize[0], outBestPreviewSize[1], mCurrentCameraDegree, cameraIndex);
            }

            int[] outPicSizes = new int[2];
            if (Math.min(expectWidth, expectHeight) > 1080) {
                getBestSizeFitShorterSide(expectWidth, expectHeight, camParams.getSupportedPictureSizes(), outPicSizes, mCurrentCameraDegree);
            } else {
                getBestSizeFitShorterSide(720, 1280, camParams.getSupportedPictureSizes(), outPicSizes, mCurrentCameraDegree);
            }

            if (outPicSizes[0] == 0 || outPicSizes[1] == 0) {
                if (expectWidth <= expectHeight) {
                    outPicSizes[0] = 480;
                    outPicSizes[1] = 640;
                } else {
                    outPicSizes[0] = 640;
                    outPicSizes[1] = 480;
                }
            }
            camParams.setPictureSize(outPicSizes[0], outPicSizes[1]);
            if (Build.MODEL.toLowerCase().equals("sm-c115") && cameraIndex == Camera.CameraInfo.CAMERA_FACING_BACK) {
                List<Camera.Size> ls = camParams.getSupportedPictureSizes();
                if (ls != null)
                    for (Camera.Size l : ls) {
                        if (l.height == 1944 && l.width == 2592) {
                            camParams.setPictureSize(l.width, l.height);
                        }
                    }
            }
            mCameraDevice.setDisplayOrientation(mCurrentCameraDegree);
            camParams.setRotation(mCurrentCameraDegree);
            if (activity.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                camParams.set("orientation", "portrait");
            } else {
                camParams.set("orientation", "landscape");
            }
            List<String> focusModes = camParams.getSupportedFocusModes();
            if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)) {
                camParams.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
            }

            List<int[]> supportedPreviewFps = camParams.getSupportedPreviewFpsRange();
            int expect_min_fps = (photoQuality.getFrameRate() - 10) * 1000;
            int expect_max_fps = photoQuality.getFrameRate() * 1000;
            int min_fps = expect_min_fps;
            int max_fps = expect_max_fps;

            int dist_max_fps = Integer.MAX_VALUE;
            for (int i = 0; i < supportedPreviewFps.size(); i++) {
                int[] _sizes = supportedPreviewFps.get(i);
                Log.d(TAG, "supportedPreviewFps: " + _sizes[Camera.Parameters.PREVIEW_FPS_MIN_INDEX] + " - " + _sizes[Camera.Parameters.PREVIEW_FPS_MAX_INDEX]);
                int _dist_max = Math.abs(_sizes[Camera.Parameters.PREVIEW_FPS_MAX_INDEX] - expect_max_fps);
                if (_dist_max < dist_max_fps) {

                    dist_max_fps = _dist_max;
                    min_fps = _sizes[Camera.Parameters.PREVIEW_FPS_MIN_INDEX];
                    max_fps = _sizes[Camera.Parameters.PREVIEW_FPS_MAX_INDEX];
                }
            }
            Log.d(TAG, "chosen preview fps: " + min_fps + " - " + max_fps);
            camParams.setPreviewFpsRange(min_fps, max_fps);// expect_max_fps);//
            camParams.set("mode", "smart-auto");
            mCameraDevice.setParameters(camParams);
            mCameraDevice.addCallbackBuffer(new byte[outBestPreviewSize[0] * outBestPreviewSize[1] * 3 / 2]);
            mCameraDevice.addCallbackBuffer(new byte[outBestPreviewSize[0] * outBestPreviewSize[1] * 3 / 2]);
            if (Runtime.getRuntime().maxMemory() > 80 * 1000 * 1024) {// 80M
                mCameraDevice.addCallbackBuffer(new byte[outBestPreviewSize[0] * outBestPreviewSize[1] * 3 / 2]);
            }
            mCameraDevice.setPreviewCallbackWithBuffer(previewCallback);
            try {
                mCameraDevice.setPreviewTexture(textureView.getSurfaceTexture());
            } catch (IOException e) {
                e.printStackTrace();
            }
            startPreview();
        }
    }

    private void startPreview() {
        if (mCameraDevice != null) {
            mCameraDevice.startPreview();
        }
    }

    private class CameraThreadHandler extends Handler {

        public CameraThreadHandler(Looper looper) {
            super(looper);
        }

        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_ON_OPEN_AND_PREVIEW: {
                    Util.DLog(TAG, "camera start preview");
                    Object[] args = (Object[]) msg.obj;
                    WeakReference<Activity> activityWeakReference = (WeakReference<Activity>) args[0];
                    int cameraIndex = (int) args[1];
                    PhotoQuality photoQuality = (PhotoQuality) args[2];
                    WeakReference<TextureView> textureViewWeakReference = (WeakReference<TextureView>) args[3];
                    int parentWidth = (int) args[4];
                    int parentHeight = (int) args[5];
                    WeakReference<OpenCameraListener> openCameraListenerWeakReference = (WeakReference<OpenCameraListener>) args[6];
                    WeakReference<Camera.PreviewCallback> previewCallbackWeakReference = (WeakReference<Camera.PreviewCallback>) args[7];
                    if (activityWeakReference.get() == null || textureViewWeakReference.get() == null || openCameraListenerWeakReference.get() == null || previewCallbackWeakReference.get() == null) {
                        return;
                    } else {
                        openCameraAndPreviewInCameraThread(activityWeakReference.get(), cameraIndex, photoQuality, textureViewWeakReference.get(), parentWidth, parentHeight, openCameraListenerWeakReference.get(),
                                previewCallbackWeakReference.get());
                    }
                }
                break;
                case MSG_ON_STOP_PREVIEW: {
                    if (mCameraDevice != null) {
                        Util.DLog(TAG, "camera stop preview");
                        mCameraDevice.stopPreview();
                        try {
                            mCameraDevice.setPreviewDisplay(null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
                case MSG_ON_RELEASE: {
                    if (mCameraDevice != null) {
                        Util.DLog(TAG, "release camera");
                        mCameraDevice.setPreviewCallbackWithBuffer(null);
                        mCameraDevice.cancelAutoFocus();
                        mCameraDevice.release();
                        mCameraDevice = null;
                    }
                }
                break;
                case MSG_ON_TAKE_PHOTO: {
                    Object[] args = (Object[]) msg.obj;
                    WeakReference<Activity> activityRef = (WeakReference<Activity>) args[0];
                    int containerWidth = (int) args[1];
                    int containerHeight = (int) args[2];
                    takePhotoInCameraThread(activityRef, containerWidth, containerHeight);
                }
                break;
                case MSG_ON_TAKE_PHOTO_SUCCESS: {
                    Object[] args = (Object[]) msg.obj;
                    WeakReference<Activity> activityRef = (WeakReference<Activity>) args[0];
                    byte[] data = (byte[]) args[1];
                    int expectWidth = (int) args[2];
                    int expectHeight = (int) args[3];
                    File file = LocalImage.saveDataToFile(data, "my-score" + System.currentTimeMillis() + ".jpg");
                    String log = "mCurrentCameraDegree:" + mCurrentCameraDegree + "\n";
                    int degree = Util.getBitmapDegree(file.getAbsolutePath());
                    log = log + "Store photo degree:" + degree;
                    if(MyApplication.mIsDebug) {
                        Util.showMessageAlert(activityRef.get(), log);
                    }
                    Bitmap photo = BitmapUtil.cutOutCorrectPhoto(activityRef.get(), data, expectWidth, expectHeight, mCameraIndex == Camera.CameraInfo.CAMERA_FACING_FRONT, degree);
                    EventBus.getDefault().post(new EventGetPhotoData(photo, file.getAbsolutePath()));
                }
                break;
                case MSG_BG_ON_TOGGLE_FLASH: {
                    if (mTCurrentFlashStatus.equals(TCurrentFlashStatus.EOff)) {
                        if (mCameraDevice != null) {
                            Camera.Parameters parameter = mCameraDevice.getParameters();
                            parameter.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                            mCameraDevice.setParameters(parameter);
                            mTCurrentFlashStatus = TCurrentFlashStatus.EOn;
                        }
                    } else if (mTCurrentFlashStatus.equals(TCurrentFlashStatus.EOn)) {
                        if (mCameraDevice != null) {
                            Camera.Parameters parameter = mCameraDevice.getParameters();
                            parameter.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                            mCameraDevice.setParameters(parameter);
                            mTCurrentFlashStatus = TCurrentFlashStatus.EOff;
                        }
                    } else if (mTCurrentFlashStatus.equals(TCurrentFlashStatus.EDisable)) {

                    }
                }
                break;
            }
        }
    }

    private class UIThreadHandler extends Handler {
        public UIThreadHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

            }
        }
    }

    private void getBestSizeFitShorterSide(int expect_width, int expect_height, List<Camera.Size> sizes, int[] out, int degree) {
        List<Camera.Size> mFitSizeList = new ArrayList<>();
        for (Camera.Size size : sizes) {
            if (expect_height * size.width == expect_width * size.height) {
                mFitSizeList.add(size);
            }
        }
        if (mFitSizeList.size() == 0) {
            int shorter_side = Math.min(expect_width, expect_height);
            int area_preview = Integer.MAX_VALUE;
            for (int i = 0; i < sizes.size(); i++) {
                Camera.Size _size = sizes.get(i);
                Log.d(TAG, "supportedSize: w=" + _size.width + ", h=" + _size.height);
                if (Math.min(_size.width, _size.height) >= shorter_side
                        && _size.width != _size.height
                        && _size.width * _size.height < area_preview) {
                    out[0] = _size.width;
                    out[1] = _size.height;
                    area_preview = out[0] * out[1];
                }
            }
        } else {
            Collections.sort(mFitSizeList, new Comparator<Camera.Size>() {
                public int compare(Camera.Size size1, Camera.Size size2) {
                    // ## Ascending order
                    return size2.width - size1.width;
                }
            });
            for (int i = 0; i < mFitSizeList.size(); i++) {
                if (mFitSizeList.get(i).width == expect_width) {
                    out[0] = mFitSizeList.get(i).width;
                    out[1] = mFitSizeList.get(i).height;
                    break;
                } else if (mFitSizeList.get(i).width < expect_width) {
                    //last bigger one
                    int target = i - 1;
                    if (target < 0) {
                        target = 0;
                    }
                    out[0] = mFitSizeList.get(target).width;
                    out[1] = mFitSizeList.get(target).height;
                }
            }
            //if not find , set the only one;
            if (out[0] == 0) {
                out[0] = mFitSizeList.get(mFitSizeList.size() - 1).width;
                out[1] = mFitSizeList.get(mFitSizeList.size() - 1).height;
            }
        }
    }
}

