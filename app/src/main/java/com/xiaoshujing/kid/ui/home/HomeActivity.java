package com.xiaoshujing.kid.ui.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.common.PermissionRequestUtil;
import com.xiaoshujing.kid.common.SharedPreferencesFactory;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.data.UserInfoManage;
import com.xiaoshujing.kid.event.EventCreditUpdate;
import com.xiaoshujing.kid.event.EventMessageCountUpdate;
import com.xiaoshujing.kid.model.BabyInfoBean;
import com.xiaoshujing.kid.model.GetTokenBean;
import com.xiaoshujing.kid.ui.scanQRcode.ScanQRCodeActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {
    HomeContract.Presenter mPresenter;
    @BindView(R.id.btn_study)
    View mStudyBtn;
    @BindView(R.id.email_count)
    TextView mMessageCount;
    @BindView(R.id.welcome)
    TextView mWelcome;
    @BindView(R.id.credit)
    TextView mCredit;
    static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        mPresenter = new HomePresentImpl(this);
        updateWelcomeText();
        updateCreditText();
        mPresenter.bindView(this);
        setPresenter(mPresenter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshBabyInfo();
        checkToken();
        if (mPresenter != null) {
            mPresenter.getMessageCount();
        }
    }

    private void checkToken() {
        if (!Util.isNullOrEmpty(SharedPreferencesFactory.getKeyYoumengToken(this))) {
            Util.DLog("Token:" + TAG, SharedPreferencesFactory.getKeyYoumengToken(this));
            UserInfoManage.getInstance().getToken(new SimpleCallback<GetTokenBean>() {
                @Override
                public void onSuccess(GetTokenBean response) {
                    if (response.get_status() != 0) {
                        SharedPreferencesFactory.setBabyCredit(HomeActivity.this, "");
                        SharedPreferencesFactory.setBabyName(HomeActivity.this, "");
                        SharedPreferencesFactory.setKeyUserBean(HomeActivity.this, "");
                        SharedPreferencesFactory.setKeyGlobeSettingBean(HomeActivity.this, "");
                        goScanQRCodePage();
                        finish();
                    }
                }

                @Override
                public void onFailure(int responseCode) {
                    int ret = responseCode;
                }
            }, SharedPreferencesFactory.getKeyYoumengToken(this));
        } else {
            Util.DLog("Token:" + TAG, "check token null");
        }
    }

    private void goScanQRCodePage() {
        Intent intent = new Intent(this, ScanQRCodeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    private void refreshBabyInfo() {
        if (UserInfoManage.getInstance().getUserBean() != null && !Util.isNullOrEmpty(UserInfoManage.getInstance().getUserBean().getBaby())) {
            UserInfoManage.getInstance().getBabyInfo(mBabyInfoBeanSimpleCallback, UserInfoManage.getInstance().getUserBean().getBaby());
        }
    }

    SimpleCallback<BabyInfoBean> mBabyInfoBeanSimpleCallback = new SimpleCallback<BabyInfoBean>() {
        @Override
        public void onSuccess(BabyInfoBean response) {
            SharedPreferencesFactory.setBabyCredit(HomeActivity.this, response.getCredit() + "");
            SharedPreferencesFactory.setBabyName(HomeActivity.this, response.getNickname());
            updateWelcomeText();
            updateCreditText();
        }

        @Override
        public void onFailure(int responseCode) {

        }
    };

    void updateWelcomeText() {
        if (!Util.isNullOrEmpty(SharedPreferencesFactory.getBabyName(this))) {
            mWelcome.setText("欢迎" + SharedPreferencesFactory.getBabyName(this) + "宝宝");
        }
    }

    void updateCreditText() {
        if (!Util.isNullOrEmpty(SharedPreferencesFactory.getBabyCredit(this))) {
            mCredit.setText(SharedPreferencesFactory.getBabyCredit(this));
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @OnClick({
            R.id.email,
            R.id.btn_study,
            R.id.btn_fun,
            R.id.btn_record,
            R.id.btn_score,
            R.id.btn_task,
            R.id.start,
    })
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.email:
                mPresenter.openMessagePage();
                break;
            case R.id.btn_study:
                mPresenter.openStudyPage();
                break;
            case R.id.btn_fun:
                mPresenter.openFunPage();
                break;
            case R.id.btn_record:
                mPresenter.openRecordPage();
                break;
            case R.id.btn_score:
                requestPermission();
                break;
            case R.id.btn_task:
                mPresenter.openTaskPage();
                break;
            case R.id.start:
                mPresenter.openStartPage();
                break;
        }
    }

    public void requestPermission() {
        ArrayList<String> permissionList = new ArrayList<>();
        if (!PermissionRequestUtil.isRecordAudioPermissionActive(this)) {
            permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!PermissionRequestUtil.isRecordAudioPermissionActive(this)) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!PermissionRequestUtil.isGetAccountPermissionActive(this)) {
            permissionList.add(Manifest.permission.CAMERA);
        }
        if (permissionList.size() > 0) {
            boolean isUserDeniedAndClickNeverAskAgain = SharedPreferencesFactory.getKeyDeniedPermissionAsk(this);
            if (isUserDeniedAndClickNeverAskAgain) {
                MyApplication.getInstance().showNote("You must go to Settings - Apps - xiaoshujing-kid to update permissions");
            } else {
                PermissionRequestUtil.requestMultiplePermission(this, permissionList.toArray(new String[permissionList.size()]));
            }
        } else {
            requestPermissionSuccess();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean isUserDeniedAndClickNeverAskAgain = false;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            for (int i = 0, len = permissions.length; i < len; i++) {
                String permission = permissions[i];
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = shouldShowRequestPermissionRationale(permission);
                    if (!showRationale) {
                        isUserDeniedAndClickNeverAskAgain = true;
                        SharedPreferencesFactory.setKeyDeniedPermissionAsk(this, true);
                        break;
                    }
                }
            }
        }
        Util.DLog(TAG, "isUserClickNeverAskAgain:" + isUserDeniedAndClickNeverAskAgain);
        handlerGrantResult(requestCode, grantResults);
    }

    public void requestPermissionSuccess() {
        mPresenter.openScorePage();
    }

    private void handlerGrantResult(int requestCode, int[] grantResults) {
        switch (requestCode) {
            case PermissionRequestUtil.REQUEST_MULTIPLE_PERMISSION: {
                boolean needRequestAgain = false;
                for (int grantResult : grantResults) {
                    if (grantResult != PackageManager.PERMISSION_GRANTED) {
                        needRequestAgain = true;
                        break;
                    }
                }
                if (needRequestAgain) {
                    requestPermission();
                } else {
                    requestPermissionSuccess();
                }
            }
        }
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setMessageCount(int count) {
        if (mMessageCount != null) {
            if (count == 0) {
                mMessageCount.setText("");
                mMessageCount.setVisibility(View.GONE);
            } else {
                mMessageCount.setText("");
                mMessageCount.setVisibility(View.VISIBLE);
            }
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventCreditUpdate(EventCreditUpdate eventCreditUpdate) {
        if (eventCreditUpdate != null) {
            SharedPreferencesFactory.setBabyCredit(HomeActivity.this, eventCreditUpdate.credit + "");
            updateCreditText();
        }
    }
}
