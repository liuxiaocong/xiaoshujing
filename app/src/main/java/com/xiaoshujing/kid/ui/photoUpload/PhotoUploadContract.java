package com.xiaoshujing.kid.ui.photoUpload;

import android.support.v7.widget.RecyclerView;

import com.xiaoshujing.kid.common.BasePresenter;
import com.xiaoshujing.kid.common.BaseView;

/**
 * Created by Liuxiaocong on 8/11/16.
 */
public interface PhotoUploadContract {
    interface View extends BaseView<Presenter> {
        void setAdapter(RecyclerView.Adapter adapter);

        void showUploading();

        void hideUploading();

        void setTipsText(String tipsText);

        android.view.View getTips();
    }

    interface Presenter extends BasePresenter<View> {
        void start();
    }
}
