package com.xiaoshujing.kid.ui.mission;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.common.BaseActivity;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.data.CommonDataManage;
import com.xiaoshujing.kid.data.MissionManage;
import com.xiaoshujing.kid.model.GetMissionBean;
import com.xiaoshujing.kid.model.GlobeSettingBean;
import com.xiaoshujing.kid.ui.missionDetail.MissionDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class MissionActivity extends BaseActivity implements MissionContract.View, View.OnClickListener {
    MissionContract.Presenter mPresenter;
    @BindView(R.id.daily_section_wrap)
    LinearLayout mLinearLayoutDaily;
    @BindView(R.id.parent_section_wrap)
    LinearLayout mLinearLayoutParent;
    @BindView(R.id.activiy_section_wrap)
    LinearLayout mLinearLayoutActivity;
    private MissionContract.Presenter mTaskPresent;
    LayoutInflater mLayoutInflater;
    private BroadcastReceiver mGoStartPageReceiver;
    public static String EXT_MISSION_TYPE = "EXT_MISSION_TYPE";
    int mMissionTypeInt = -1;

    //missionType null mean's all
    public static void openActivity(Activity context, MissionManage.MissionType missionType) {
        Intent intent = new Intent(context, MissionActivity.class);
        if (missionType == null) {
            intent.putExtra(EXT_MISSION_TYPE, -1);
        } else {
            intent.putExtra(EXT_MISSION_TYPE, missionType.getValue());
        }
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);
        mLayoutInflater = LayoutInflater.from(this);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent.hasExtra(EXT_MISSION_TYPE)) {
            mMissionTypeInt = intent.getIntExtra(EXT_MISSION_TYPE, -1);
        }
        mTaskPresent = new MissionPresentImpl(this, mMissionTypeInt);
        mTaskPresent.bindView(this);
        setPresenter(mTaskPresent);
        mTaskPresent.getMissionList();
        mGoStartPageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                MissionActivity.this.finish();
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.GO_START_PAGE");
        registerReceiver(mGoStartPageReceiver, filter);
    }


    @Override
    public void setPresenter(MissionContract.Presenter presenter) {
        mPresenter = presenter;
    }


    //wait api to implement set for different section

    @Override
    public void setDailyMissionList(List<GetMissionBean.MissionBean> missionList) {
        if (missionList != null && missionList.size() > 0) {
            mLinearLayoutDaily.setVisibility(View.VISIBLE);
            for (GetMissionBean.MissionBean missionBean : missionList) {
                View view = genMissionItem(missionBean);
                mLinearLayoutDaily.addView(view);
            }
            mLinearLayoutDaily.postInvalidate();
        }
    }

    @Override
    public void setParentMissionList(List<GetMissionBean.MissionBean> missionList) {
        if (missionList != null && missionList.size() > 0) {
            mLinearLayoutParent.setVisibility(View.VISIBLE);
            for (GetMissionBean.MissionBean missionBean : missionList) {
                View view = genMissionItem(missionBean);
                mLinearLayoutParent.addView(view);
            }
            mLinearLayoutParent.postInvalidate();
        }

    }

    @Override
    public void setActivityMissionList(List<GetMissionBean.MissionBean> missionList) {
        if (missionList != null && missionList.size() > 0) {
            mLinearLayoutDaily.setVisibility(View.VISIBLE);
            for (GetMissionBean.MissionBean missionBean : missionList) {
                View view = genMissionItem(missionBean);
                mLinearLayoutActivity.addView(view);
            }
            mLinearLayoutActivity.postInvalidate();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mGoStartPageReceiver != null) {
            unregisterReceiver(mGoStartPageReceiver);
        }
    }

    @OnClick({
            R.id.back
    })
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.back: {
                finish();
            }
            break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public View genMissionItem(GetMissionBean.MissionBean missionBean) {
        if (missionBean == null) return null;
        LinearLayout linearLayout = (LinearLayout) mLayoutInflater.inflate(R.layout.item_task, null);
        linearLayout.setTag(missionBean);
        linearLayout.setOnClickListener(this);
        LinearLayout content = (LinearLayout) linearLayout.findViewById(R.id.content);
        TextView name = (TextView) linearLayout.findViewById(R.id.name);
        name.setText(missionBean.getName());
        GlobeSettingBean globeSettingBean = CommonDataManage.getInstance().getGlobeSettingBean();
        int power = 0;
        if (globeSettingBean != null) {
            if (missionBean.getMissionType() == MissionManage.MissionType.EActivity.getValue()) {
                power = globeSettingBean.getSystemCredit();
            } else if (missionBean.getMissionType() == MissionManage.MissionType.EDaily.getValue()) {
                power = globeSettingBean.getDailCredit();
            } else if (missionBean.getMissionType() == MissionManage.MissionType.EParent.getValue()) {
                power = globeSettingBean.getParentsCredit();
            }
        }

        for (int i = 0; i < power; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.ic_energy_task);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Util.getPxFromDp(this, 8), Util.getPxFromDp(this, 11));
            if (i == power - 1) {
                layoutParams.setMargins(0, 0, 0, 0);
            } else {
                layoutParams.setMargins(0, 0, Util.getPxFromDp(this, 11), 0);
            }
            imageView.setLayoutParams(layoutParams);
            content.addView(imageView);
        }
        return linearLayout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.root: {
                if (v.getTag() != null && v.getTag() instanceof GetMissionBean.MissionBean) {
                    GetMissionBean.MissionBean missionBean = (GetMissionBean.MissionBean) v.getTag();
                    MissionDetailActivity.openActivity(this, missionBean);
                }
            }
            break;
        }
    }
}
