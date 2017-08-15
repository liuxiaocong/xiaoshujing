package com.xiaoshujing.kid.ui.scanQRcode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.api.GsonImpl;
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.common.DeviceUuidFactory;
import com.xiaoshujing.kid.common.EncodingHandler;
import com.xiaoshujing.kid.common.SharedPreferencesFactory;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.data.CommonDataManage;
import com.xiaoshujing.kid.data.UserInfoManage;
import com.xiaoshujing.kid.model.BindTokenBean;
import com.xiaoshujing.kid.model.GetInfoBean;
import com.xiaoshujing.kid.ui.home.HomeActivity;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class ScanQRCodePresentImpl implements ScanQRCodeContract.Presenter {
    final String TAG = "ScanQRCodePresentImpl";
    Activity mContext;
    ScanQRCodeContract.View mView;
    private DeviceUuidFactory mUuidFactory;
    String mQrcode;
    int mLooperInterval = 2000;
    Handler mLooperHandler;

    public ScanQRCodePresentImpl(Activity context) {
        mContext = context;
        mUuidFactory = new DeviceUuidFactory(context);
        mLooperHandler = new Handler() {
            public void handleMessage(Message msg) {
                Util.DLog(TAG, "loop user data");
                UserInfoManage.getInstance().getUserInfoFromUUID(mGetInfoBeanSimpleCallback, mQrcode);
                super.handleMessage(msg);
            }
        };
    }

    public void start() {
        genQRCode();
    }

    @Override
    public void clear() {
        if (mLooperHandler != null) {
            mLooperHandler.removeMessages(1);
            mLooperHandler = null;
        }
        if (mGetInfoBeanSimpleCallback != null) {
            mGetInfoBeanSimpleCallback.cancel();
            mGetInfoBeanSimpleCallback = null;
        }
    }

    SimpleCallback<BindTokenBean> mBindTokenBeanSimpleCallback = new SimpleCallback<BindTokenBean>() {
        @Override
        public void onSuccess(BindTokenBean response) {
            Util.DLog("Token:" + TAG, "BindToken onSuccess");
        }

        @Override
        public void onFailure(int responseCode) {
            Util.DLog("Token:" + TAG, "BindToken onFailure");
        }
    };

    SimpleCallback<ResponseBody> mGetInfoBeanSimpleCallback = new SimpleCallback<ResponseBody>() {
        @Override
        public void onSuccess(ResponseBody response) {
            try {
                String ret = response.string();
                Util.DLog("c:" + TAG, "GetInfoBean:" + ret);
                GetInfoBean getInfoBean = GsonImpl.get().toObject(ret, GetInfoBean.class);
                if (getInfoBean != null && getInfoBean.getUser() != null && !Util.isNullOrEmpty(getInfoBean.getUser().getApi_key())) {
                    SharedPreferencesFactory.setKeyUserBean(mContext, ret);
                    UserInfoManage.getInstance().setUserBean(getInfoBean.getUser());
                    if (!Util.isNullOrEmpty(MyApplication.getInstance().getToken())) {
                        Util.DLog("Token:" + TAG, "BindToken:" + MyApplication.getInstance().getToken());
                        UserInfoManage.getInstance().bindToken(mBindTokenBeanSimpleCallback, MyApplication.getInstance().getToken());
                    } else {
                        MyApplication.getInstance().setNeedCallRegistetAfterPushAgentReady(true);
                    }
                    gotoHomePage();
                    MyApplication.getInstance().showNote("授权成功!");
                    mContext.finish();
                } else {
                    delayLoop();
                }
            } catch (IOException e) {
                delayLoop();
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(int responseCode) {
            int code = responseCode;
            delayLoop();
        }
    };

    @Override
    public void genQRCode() {
        mQrcode = mUuidFactory.getUuid().toString();
        if (!Util.isNullOrEmpty(mQrcode)) {
            mQrcode = mQrcode.replace("-", "");
        }
        //String ret = "{\"type\":1,\"code\":\"" + mQrcode + "\"}";
        String ret = Util.SERVER + "/qrcode/?type=1&data=" + mQrcode;
        if (mView != null) {
            mView.setQRCode(mQrcode);
            try {
                Bitmap bitmap = EncodingHandler.createQRCode(ret, Util.getPxFromDp(mContext, 260));
                mView.setQRCodeImage(bitmap);
                startLooping();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void startLooping() {
        Message message = new Message();
        message.what = 1;
        mLooperHandler.sendMessage(message);
    }

    private void delayLoop() {
        if (mLooperHandler != null) {
            Message message = new Message();
            message.what = 1;
            mLooperHandler.sendMessageDelayed(message, mLooperInterval);
        }
    }

    private void gotoHomePage() {
        CommonDataManage.getInstance().refreshGlobeSetting();
        Intent intent = new Intent(mContext, HomeActivity.class);
        mContext.startActivity(intent);
        mContext.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public void loopUuidScanned() {

    }

    @Override
    public void bindView(ScanQRCodeContract.View view) {
        mView = view;
    }
}
