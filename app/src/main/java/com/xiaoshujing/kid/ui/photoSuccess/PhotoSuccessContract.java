package com.xiaoshujing.kid.ui.photoSuccess;

import com.xiaoshujing.kid.common.BasePresenter;
import com.xiaoshujing.kid.common.BaseView;

/**
 * Created by Liuxiaocong on 8/11/16.
 */
public interface PhotoSuccessContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {
        void openSelectPhotoPage();
        void openTakePhotoPage();
        void openUploadPhotoPage();
    }
}
