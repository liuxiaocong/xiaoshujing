package com.xiaoshujing.kid.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.data.MissionManage;
import com.xiaoshujing.kid.model.GetMissionBean;


/**
 * Created by liuxiaocong on 11/11/15.
 */
public class MissionSelectDialog extends Dialog implements View.OnClickListener {

    public interface IMissionSelectDialogListener {
        void onStartClicked(Dialog dialog, GetMissionBean.MissionBean missionBean);

        void onCloseClicked(Dialog dialog);
    }

    private IMissionSelectDialogListener mIMissionSelectDialogListener;
    private Context mContext;
    GetMissionBean.MissionBean mSelectedMissionBean;
    TextView mTitle;
    RecyclerView mRecyclerView;
    View mLoading;
    View mEmpty;
    View mStart;
    View mCancel;
    View mClose;
    View mRoot;
    MissionAdapter mMissionAdapter;

    public static MissionSelectDialog createInstance(Context context,
                                                     IMissionSelectDialogListener missionSelectDialogListener, boolean closeAble) {
        return new MissionSelectDialog(context, missionSelectDialogListener, closeAble);
    }

    public MissionSelectDialog(Context context, IMissionSelectDialogListener missionSelectDialogListener, boolean closeAble) {
        super(context, R.style.AlertDialog);
        mIMissionSelectDialogListener = missionSelectDialogListener;
        mContext = context;
        setContentView(R.layout.dialog_mission_select);
        mRoot = findViewById(R.id.root);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mRoot.getLayoutParams();
        layoutParams.width = Util.getScreenWidth(context) - Util.getPxFromDp(context, 40);
        mRoot.setLayoutParams(layoutParams);
        mTitle = (TextView) findViewById(R.id.title);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mLoading = findViewById(R.id.loading);
        mEmpty = findViewById(R.id.empty);
        mRecyclerView.setVisibility(View.GONE);
        mLoading.setVisibility(View.GONE);
        mEmpty.setVisibility(View.GONE);
        mStart = findViewById(R.id.ok);
        mStart.setEnabled(false);
        mCancel = findViewById(R.id.cancel);
        mClose = findViewById(R.id.close);
        mStart.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        mClose.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mMissionAdapter = new MissionAdapter(context, new MissionAdapter.IMissionAdapterListener() {
            @Override
            public void onItemClick(GetMissionBean.MissionBean missionBean) {
                mSelectedMissionBean = missionBean;
            }
        });
        mRecyclerView.setAdapter(mMissionAdapter);
        setCanceledOnTouchOutside(closeAble);
        setCancelable(closeAble);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void load() {
        mLoading.setVisibility(View.VISIBLE);
        MissionManage.getInstance().getMissionByStatus(mGetMissionBeanSimpleCallback, 0);
    }

    SimpleCallback<GetMissionBean> mGetMissionBeanSimpleCallback = new SimpleCallback<GetMissionBean>() {
        @Override
        public void onSuccess(GetMissionBean response) {
            if (mRecyclerView == null) return;
            if (response.getObjects() == null || response.getObjects().size() == 0) {
                mRecyclerView.setVisibility(View.GONE);
                mLoading.setVisibility(View.GONE);
                mEmpty.setVisibility(View.VISIBLE);
            } else {
                mSelectedMissionBean = response.getObjects().get(0);
                mStart.setEnabled(true);
                mMissionAdapter.addData(response.getObjects(), true);
                mRecyclerView.setVisibility(View.VISIBLE);
                mLoading.setVisibility(View.GONE);
                mEmpty.setVisibility(View.GONE);
            }
        }

        @Override
        public void onFailure(int responseCode) {
            if (mRecyclerView == null) return;
            mRecyclerView.setVisibility(View.GONE);
            mLoading.setVisibility(View.GONE);
            mEmpty.setVisibility(View.VISIBLE);
        }
    };

    @Override
    public void show() {
        super.show();
        load();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (mGetMissionBeanSimpleCallback != null) {
            mGetMissionBeanSimpleCallback.cancel();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok: {
                if (mIMissionSelectDialogListener != null) {
                    mIMissionSelectDialogListener.onStartClicked(this, mSelectedMissionBean);
                }
            }
            break;
            case R.id.cancel:
            case R.id.close: {
                if (mIMissionSelectDialogListener != null) {
                    mIMissionSelectDialogListener.onCloseClicked(this);
                }
            }
            break;
        }
    }

}
