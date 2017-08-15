package com.xiaoshujing.kid;

import android.graphics.Bitmap;
import android.support.multidex.MultiDexApplication;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.common.SharedPreferencesFactory;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.data.UserInfoManage;
import com.xiaoshujing.kid.model.BindTokenBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiuXiaocong on 8/15/2016.
 */

public class MyApplication extends MultiDexApplication {
    private final String TAG = "MyApplication";
    private static MyApplication instance;
    private View mToastView;
    private Toast mToast;
    private ArrayList<Bitmap> mPreUploadBitmap = new ArrayList<>();
    private ArrayList<String> mPreUploadFilePath = new ArrayList<>();
    public static boolean mIsDebug = false;

    public static MyApplication getInstance() {
        return instance;
    }

    String mToken = "";
    boolean mNeedCallRegistetAfterPushAgentReady = false;

    @Override
    public void onCreate() {
        super.onCreate();
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this, config);
        instance = this;
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {

                Util.DLog(this.toString(), "umeng token:" + deviceToken);

                //注册成功会返回device token
                mToken = deviceToken;
                SharedPreferencesFactory.setKeyYoumengToken(MyApplication.this, mToken);
                if (mNeedCallRegistetAfterPushAgentReady) {
                    Util.DLog("Token:" + TAG, "BindToken:" + mToken);
                    UserInfoManage.getInstance().bindToken(mBindTokenBeanSimpleCallback, mToken);
                }
            }

            @Override
            public void onFailure(String s, String s1) {

                Util.DLog(this.toString(), "umeng token failure:" + s + ", & " + s1);

                String error1 = s;
                String error2 = s1;
            }
        });
//        EventBus.getDefault().register(this);
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

    public String getToken() {
        return mToken;
    }

    public void setNeedCallRegistetAfterPushAgentReady(boolean needCallRegistetAfterPushAgentReady) {
        mNeedCallRegistetAfterPushAgentReady = needCallRegistetAfterPushAgentReady;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
//        EventBus.getDefault().unregister(this);
    }

    public void addPreUploadBitmap(Bitmap bitmap) {
        mPreUploadBitmap.add(bitmap);
    }

    public void addPreUploadBitmapPath(String path) {
        mPreUploadFilePath.add(path);
    }

    public List<Bitmap> getPreUploadBitmap() {
        return mPreUploadBitmap;
    }

    public List<String> getPreUploadFilePath() {
        return mPreUploadFilePath;
    }

    public void clearPreUploadBitmapAndPath() {
        for (Bitmap bitmap : mPreUploadBitmap) {
            bitmap.recycle();
        }
        mPreUploadBitmap.clear();
        mPreUploadFilePath.clear();
    }

    public void showNote(String str) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = initToast();
        if (mToast != null && mToastView != null) {
            ((TextView) mToastView.findViewById(R.id.toast_text)).setText(str);
            mToast.show();
        }
    }

    private Toast initToast() {
        Toast toast = null;
        try {
            toast = new Toast(instance);
            LayoutInflater layoutInflater = LayoutInflater.from(instance);
            mToastView = layoutInflater.inflate(R.layout.globe_toast_layout, null);

            toast.setView(mToastView);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);

        } catch (Exception e) {

        }
        return toast;
    }

    public String GetAppName() {
        return "xiaoshujing-kid";
    }
}
