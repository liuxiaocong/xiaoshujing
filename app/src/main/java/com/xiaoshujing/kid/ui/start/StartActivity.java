package com.xiaoshujing.kid.ui.start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.bluetooth.BluetoothManager;
import com.xiaoshujing.kid.common.BaseActivity;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.data.UserInfoManage;
import com.xiaoshujing.kid.model.GetMissionBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class StartActivity extends BaseActivity implements StartContract.View {

    static String EXT_MISSIONBEAN = "EXT_MISSIONBEAN";
    StartContract.Presenter mPresenter;
    @BindView(R.id.head_down_wrap)
    View mHeadDownWrap;
    @BindView(R.id.head_down_inner)
    LinearLayout mHeadDownInner;
    @BindView(R.id.sit_pos_wrap)
    View mSitPosWrap;
    @BindView(R.id.sit_pos_inner)
    LinearLayout mSitPosInner;
    @BindView(R.id.time)
    TextView mTime;
    @BindView(R.id.head_down_count)
    TextView mHeadDownCount;
    @BindView(R.id.sit_pos_count)
    TextView mSitPosCount;
    private int mCurrentDegree = 0;
    @BindView(R.id.pointer)
    View mPointer;
    @BindView(R.id.finish)
    View mFinish;
    GetMissionBean.MissionBean mMissionBean;

    public static void openActivity(Activity context, GetMissionBean.MissionBean missionBean) {
        Intent intent = new Intent(context, StartActivity.class);
        intent.putExtra(EXT_MISSIONBEAN, missionBean);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

        //注册蓝牙监听
        BluetoothManager.getInstance().registerConnectionListener(UserInfoManage.getInstance().getUserBean().getMac());
        //连接蓝牙设备
        BluetoothManager.getInstance().connectDevice(UserInfoManage.getInstance().getUserBean().getMac());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Intent intent = getIntent();
        if (intent.hasExtra(EXT_MISSIONBEAN)) {
            mMissionBean = (GetMissionBean.MissionBean) intent.getSerializableExtra(EXT_MISSIONBEAN);
        }

        ButterKnife.bind(this);
        mPresenter = new StartPresentImpl(this,mMissionBean);
        setPresenter(mPresenter);
        mPresenter.bindView(this);
        mHeadDownInner.post(new Runnable() {
            @Override
            public void run() {
                int tarSize = Util.getScreenWidth(StartActivity.this) - Util.getPxFromDp(StartActivity.this, 24 + 5);
                tarSize = tarSize / 2 - Util.getPxFromDp(StartActivity.this, 24);
                ViewGroup.LayoutParams layoutParams = mHeadDownInner.getLayoutParams();
                layoutParams.height = tarSize;
                layoutParams.width = tarSize;
                mHeadDownInner.setLayoutParams(layoutParams);
                ViewGroup.LayoutParams layoutParams2 = mSitPosInner.getLayoutParams();
                layoutParams2.height = tarSize;
                layoutParams2.width = tarSize;
                mSitPosInner.setLayoutParams(layoutParams2);
            }
        });
        mPresenter.start();
    }

    @Override
    public void setPresenter(StartContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @OnClick({
            R.id.back,
            R.id.finish
    })
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.back: {
                finish();
            }
            break;
            case R.id.finish: {
                if (mPresenter != null) {
                    mPresenter.onClickEnd();
                }
            }
            break;
        }
    }


    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.clear();
        }
        super.onDestroy();

        //断开蓝牙设备
        BluetoothManager.getInstance().disconnectDevice(UserInfoManage.getInstance().getUserBean().getMac());
        //注销蓝牙监听
        BluetoothManager.getInstance().unregisterConnectionListener(UserInfoManage.getInstance().getUserBean().getMac());
    }

    @Override
    public void updateTime(long timeSpend) {
        String timeStr = Util.getIntervalFormatTime(timeSpend);
        if (mTime != null) {
            mTime.setText(timeStr);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    @Override
    public void updateData(int head, int sit, int light) {
        mHeadDownCount.setText(head + "");
        mSitPosCount.setText(sit + "");
        int degree = (int) (((float) light * 1.8f) - 90f);
        RotateAnimation rotateAnim = new RotateAnimation(mCurrentDegree, degree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setDuration(1000);
        rotateAnim.setFillAfter(true);
        mPointer.startAnimation(rotateAnim);
        mCurrentDegree = degree;
    }

    @Override
    public void setFinishEnable(boolean enable) {
        if (mFinish != null) {
            mFinish.setEnabled(enable);
        }
    }
}
