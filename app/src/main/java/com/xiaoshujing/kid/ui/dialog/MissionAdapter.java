package com.xiaoshujing.kid.ui.dialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.model.GetMissionBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LiuXiaocong on 12/31/2016.
 */

public class MissionAdapter extends RecyclerView.Adapter<MissionAdapter.ViewHolder> {

    List<GetMissionBean.MissionBean> mMissionBeanList = new ArrayList<>();

    interface IMissionAdapterListener {
        void onItemClick(GetMissionBean.MissionBean missionBean);
    }

    IMissionAdapterListener mIMissionAdapterListener;
    LayoutInflater mLayoutInflater;
    int mSelectPosition = 0;

    public MissionAdapter(Context context, IMissionAdapterListener iMissionAdapterListener) {
        mIMissionAdapterListener = iMissionAdapterListener;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_mission, parent, false));
    }

    public void addData(List<GetMissionBean.MissionBean> data, boolean needRefresh) {
        if (needRefresh) {
            mMissionBeanList.clear();
        }
        mMissionBeanList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mMissionBeanList.get(position) != null) {
            holder.bind(mMissionBeanList.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return mMissionBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.name)
        TextView mName;
        @BindView(R.id.des)
        TextView mDes;
        @BindView(R.id.root)
        View mRoot;
        @BindView(R.id.check)
        View mCheck;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(GetMissionBean.MissionBean missionBean, int position) {
            mName.setText(missionBean.getName());
            mDes.setText(missionBean.getDescription());
            mRoot.setTag(position);
            mRoot.setOnClickListener(this);
            if (position == mSelectPosition) {
                mCheck.setVisibility(View.VISIBLE);
            } else {
                mCheck.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.root: {
                    if (v.getTag() != null) {
                        GetMissionBean.MissionBean missionBean = mMissionBeanList.get((Integer) v.getTag());
                        mSelectPosition = (int) v.getTag();
                        notifyDataSetChanged();
                        if (mIMissionAdapterListener != null) {
                            mIMissionAdapterListener.onItemClick(missionBean);
                        }
                    }
                }
                break;
            }
        }
    }
}
