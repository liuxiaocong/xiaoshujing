package com.xiaoshujing.kid.ui.scanQRcode;

import android.graphics.Bitmap;

import com.xiaoshujing.kid.common.BasePresenter;
import com.xiaoshujing.kid.common.BaseView;

/**
 * Created by Liuxiaocong on 8/11/16.
 */
public interface ScanQRCodeContract {
    interface View extends BaseView<Presenter> {
        void setQRCodeImage(Bitmap bitmap);
        void setQRCode(String qrCode);
    }

    interface Presenter extends BasePresenter<View> {
        void genQRCode();
        void loopUuidScanned();
        void start();
        void clear();
    }
}
