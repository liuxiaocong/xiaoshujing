package com.xiaoshujing.kid.ui.scoreFromAlbum;

import android.content.Intent;
import android.graphics.Bitmap;

import com.xiaoshujing.kid.common.BasePresenter;
import com.xiaoshujing.kid.common.BaseView;

/**
 * Created by Liuxiaocong on 8/11/16.
 */
public interface ScoreFromAlbumContract {

    interface View extends BaseView<Presenter> {
        void clear();

        void setDisplayPhoto(Bitmap bitmap, String path);

    }

    interface Presenter extends BasePresenter<View> {

        void start();

        void clear();

        void check();

        void reSelect();

        void openPhotoSuccessPage(android.view.View view);

        void onActivityResult(int requestCode, int resultCode, Intent data);

        void setTargetSize(int width, int height);
    }
}
