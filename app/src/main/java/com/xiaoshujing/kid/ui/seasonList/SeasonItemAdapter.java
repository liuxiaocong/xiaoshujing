package com.xiaoshujing.kid.ui.seasonList;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.model.SeasonBean;
import com.xiaoshujing.kid.ui.videoList.VideoListActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LiuXiaocong on 11/9/2016.
 */
public class SeasonItemAdapter extends RecyclerView.Adapter<SeasonItemAdapter.ViewHolder> {
    LayoutInflater mLayoutInflater;
    Context mContext;
    ArrayList<SeasonBean> mData = new ArrayList<>();
    ISeasonItemAdapterListener mISeasonItemAdapterListener;

    public interface ISeasonItemAdapterListener {
        void onItemClick(SeasonBean seasonBean);
    }

    public SeasonItemAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setISeasonItemAdapterListener(ISeasonItemAdapterListener iSeasonItemAdapterListener) {
        mISeasonItemAdapterListener = iSeasonItemAdapterListener;
    }

    public void addData(List<SeasonBean> seasonBeanList, boolean needRefresh) {
        if (needRefresh) {
            mData.clear();
        }
        mData.addAll(seasonBeanList);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_season, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SeasonBean seasonBean = mData.get(position);
        if (seasonBean != null) {
            holder.bindData(seasonBean);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.des)
        TextView mDes;
        @BindView(R.id.index)
        TextView mIndex;
        @BindView(R.id.cover)
        SimpleDraweeView mSimpleDraweeView;
        @BindView(R.id.free_icon)
        ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(SeasonBean seasonBean) {
            itemView.setTag(seasonBean);
            itemView.setOnClickListener(this);
            Uri uri = Uri.parse(seasonBean.getCover_url());
            mSimpleDraweeView.setImageURI(uri);
            mTitle.setText(seasonBean.getName());
            mDes.setText(seasonBean.getDescription());
            if (seasonBean.isIs_free()) {
                mImageView.setVisibility(View.VISIBLE);
            } else {
                mImageView.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.root: {
                    SeasonBean seasonBean = (SeasonBean) view.getTag();
                    if (seasonBean != null) {
                        VideoListActivity.openActivity((Activity) mContext, seasonBean.getObject_id(), seasonBean.getName());
                    }
                }
                break;
            }
        }
    }
}
