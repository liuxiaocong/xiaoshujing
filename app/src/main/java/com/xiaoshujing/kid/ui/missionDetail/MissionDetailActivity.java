package com.xiaoshujing.kid.ui.missionDetail;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.common.BaseActivity;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.model.GetMissionBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class MissionDetailActivity extends BaseActivity implements MissionDetailContract.View {
    MissionDetailContract.Presenter mPresenter;
    GetMissionBean.MissionBean mMissionBean;
    static String EXT_MISSIONBEAN = "EXT_MISSIONBEAN";
    @BindView(R.id.title)
    TextView mPageTitle;
    @BindView(R.id.miss_title)
    TextView mMissionTitle;
    @BindView(R.id.miss_detail)
    TextView mMissionDetail;
    @BindView(R.id.wish_title)
    TextView mWishTitle;
    @BindView(R.id.wish_detail)
    TextView mWishDetail;
    private BroadcastReceiver mGoStartPageReceiver;

    public static void openActivity(Activity context, GetMissionBean.MissionBean missionBean) {
        Intent intent = new Intent(context, MissionDetailActivity.class);
        intent.putExtra(EXT_MISSIONBEAN, missionBean);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_detail);
        Intent intent = getIntent();
        if (intent.hasExtra(EXT_MISSIONBEAN)) {
            mMissionBean = (GetMissionBean.MissionBean) intent.getSerializableExtra(EXT_MISSIONBEAN);
        }
        if (mMissionBean == null) {
            MyApplication.getInstance().showNote("任务为空，请稍侯重试");
            finish();
            return;
        }
        ButterKnife.bind(this);
        if (!Util.isNullOrEmpty(mMissionBean.getName())) {
            mMissionTitle.setText(mMissionBean.getName());
        }
        if (!Util.isNullOrEmpty(mMissionBean.getDescription())) {
            mMissionDetail.setText(mMissionBean.getDescription());
        }

        if (mMissionBean.getBabyWish() != null && !Util.isNullOrEmpty(mMissionBean.getBabyWish().getName())) {
            mWishTitle.setText(mMissionBean.getBabyWish().getName());
        }
        if (mMissionBean.getBabyWish() != null && !Util.isNullOrEmpty(mMissionBean.getBabyWish().getDescription())) {
            mWishDetail.setText(mMissionBean.getBabyWish().getDescription());
        }
        mPresenter = new MissionDetailPresentImpl(this, mMissionBean);
        setPresenter(mPresenter);

        mGoStartPageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                MissionDetailActivity.this.finish();
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.GO_START_PAGE");
        registerReceiver(mGoStartPageReceiver, filter);
    }

    @Override
    public void setPresenter(MissionDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mGoStartPageReceiver != null) {
            unregisterReceiver(mGoStartPageReceiver);
        }
    }

    @OnClick({
            R.id.back,
            R.id.fanhui,
            R.id.start
    })
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.back: {
                finish();
            }
            break;
            case R.id.fanhui: {
                finish();
            }
            break;
            case R.id.start: {
                if (mPresenter != null) {
                    mPresenter.onClickStart();
                }
            }
            break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

}
