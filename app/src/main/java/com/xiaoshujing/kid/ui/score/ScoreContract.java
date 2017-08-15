package com.xiaoshujing.kid.ui.score;

import android.graphics.Bitmap;
import android.view.TextureView;

import com.xiaoshujing.kid.common.BasePresenter;
import com.xiaoshujing.kid.common.BaseView;

/**
 * Created by Liuxiaocong on 8/11/16.
 */
public interface ScoreContract {

    interface View extends BaseView<Presenter> {
        void clear();

        void setSurfaceTextureListener(TextureView.SurfaceTextureListener listener);

        TextureView getTextureView();

        int getTargetWidth();

        int getTargetHeight();

        void setDisplayPhoto(Bitmap bitmap,String path);

        void showFocusStatus(boolean visible);

        void updateUi(ETakePhotoStatus eTakePhotoStatus);
    }

    interface Presenter extends BasePresenter<View> {
        void startPreview();

        void stopPreview();

        void releaseCamera();

        void toggleFlash();

        void takePhoto();

        void clear();

        void openPhotoSuccessPage(android.view.View view);

        void check();

        void focus();
    }
}
