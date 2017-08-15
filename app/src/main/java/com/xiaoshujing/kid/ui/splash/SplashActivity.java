package com.xiaoshujing.kid.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.api.GsonImpl;
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.common.BaseActivity;
import com.xiaoshujing.kid.common.DeviceUuidFactory;
import com.xiaoshujing.kid.common.SharedPreferencesFactory;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.data.CommonDataManage;
import com.xiaoshujing.kid.data.UserInfoManage;
import com.xiaoshujing.kid.model.GetInfoBean;
import com.xiaoshujing.kid.ui.home.HomeActivity;
import com.xiaoshujing.kid.ui.scanQRcode.ScanQRCodeActivity;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

/**
 * Created by LiuXiaocong on 12/6/2016.
 */

public class SplashActivity extends BaseActivity {
    private String TAG = "SplashActivity";

    private DeviceUuidFactory mUuidFactory;
    String mQrcode;

    private int mRetryTimeInterval = 3000;

    @BindView(R.id.loading)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        if (MyApplication.mIsDebug) {
            goHomePage();
        } else {
            detectUserInfo();
        }

//        MissionManage.getInstance().getMission(mGetMissionBeanSimpleCallback);
    }

//    SimpleCallback<GetMissionBean> mGetMissionBeanSimpleCallback = new SimpleCallback<GetMissionBean>() {
//        @Override
//        public void onSuccess(GetMissionBean response) {
//            GetMissionBean getMissionBean = response;
//        }
//
//        @Override
//        public void onFailure(int responseCode) {
//            int ret = responseCode;
//        }
//    };

    SimpleCallback<ResponseBody> mGetInfoBeanSimpleCallback = new SimpleCallback<ResponseBody>() {
        @Override
        public void onSuccess(ResponseBody response) {
            try {
                String ret = response.string();
                GetInfoBean getInfoBean = GsonImpl.get().toObject(ret, GetInfoBean.class);
                if (getInfoBean != null && getInfoBean.getUser() != null && !Util.isNullOrEmpty(getInfoBean.getUser().getApi_key())) {
                    UserInfoManage.getInstance().setUserBean(getInfoBean.getUser());
                    goHomePage();
                    finish();
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            goScanQRCodePage();
            finish();
        }

        @Override
        public void onFailure(int responseCode) {
            //keep retry when error happen
            int code = responseCode;
            showLoading();
            getWindow().getDecorView().postDelayed(new Runnable() {
                @Override
                public void run() {
                    UserInfoManage.getInstance().getUserInfoFromUUID(mGetInfoBeanSimpleCallback, mQrcode);
                }
            }, mRetryTimeInterval);

        }
    };

    private void showLoading() {
        if (mProgressBar != null) {
            mProgressBar.setAlpha(1f);
        }
    }

    private void detectUserInfo() {
        String userStr = SharedPreferencesFactory.getKeyUserBean(this);
        if (!Util.isNullOrEmpty(userStr)) {
            try {
                GetInfoBean getInfoBean = GsonImpl.get().toObject(userStr, GetInfoBean.class);
                if (getInfoBean != null) {
                    UserInfoManage.getInstance().setUserBean(getInfoBean.getUser());
                    goHomePage();
                    finish();
                    return;
                }
            } catch (Exception e) {
                Util.DLog(TAG, e.toString());
            }
        }

        //mean new user
        mUuidFactory = new DeviceUuidFactory(this);
        mQrcode = mUuidFactory.getUuid().toString();
        if (!Util.isNullOrEmpty(mQrcode)) {
            mQrcode = mQrcode.replace("-", "");
        }
        UserInfoManage.getInstance().getUserInfoFromUUID(mGetInfoBeanSimpleCallback, mQrcode);
    }

    private void goHomePage() {
        CommonDataManage.getInstance().refreshGlobeSetting();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    private void goScanQRCodePage() {
        Intent intent = new Intent(this, ScanQRCodeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}
