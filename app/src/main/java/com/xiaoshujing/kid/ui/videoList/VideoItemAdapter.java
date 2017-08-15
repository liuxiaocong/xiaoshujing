package com.xiaoshujing.kid.ui.videoList;

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
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.data.UserInfoManage;
import com.xiaoshujing.kid.event.EventCreditUpdate;
import com.xiaoshujing.kid.event.EventMessageCountUpdate;
import com.xiaoshujing.kid.model.MinusCreditBean;
import com.xiaoshujing.kid.model.video.GetSeasonVideoListBean;
import com.xiaoshujing.kid.ui.dialog.LoadingDialog;
import com.xiaoshujing.kid.ui.playVideoFFMpeg.PlayVideoFFMpegActivity;
import com.xiaoshujing.kid.ui.playVideoView.PlayVideoViewActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LiuXiaocong on 11/9/2016.
 */
public class VideoItemAdapter extends RecyclerView.Adapter<VideoItemAdapter.ViewHolder> {
    LayoutInflater mLayoutInflater;
    Context mContext;
    ArrayList<GetSeasonVideoListBean.EpisodesBean> mData = new ArrayList<>();
    IVideoItemAdapterListener mIVideoItemAdapterListener;
    LoadingDialog mLoadingDialog;
    int costCredit = 5;

    public interface IVideoItemAdapterListener {
        void onItemClick(GetSeasonVideoListBean.EpisodesBean episodesBean);
    }

    public VideoItemAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setIVideoItemAdapterListener(IVideoItemAdapterListener iVideoItemAdapterListener) {
        mIVideoItemAdapterListener = iVideoItemAdapterListener;
    }

    private void hideLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    public void addData(List<GetSeasonVideoListBean.EpisodesBean> episodesBeanList, boolean needRefresh) {
        if (needRefresh) {
            mData.clear();
        }
        mData.addAll(episodesBeanList);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_season_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GetSeasonVideoListBean.EpisodesBean episodesBean = mData.get(position);
        if (episodesBean != null) {
            holder.bindData(episodesBean);
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

        public void bindData(GetSeasonVideoListBean.EpisodesBean episodesBean) {
            itemView.setTag(episodesBean);
            itemView.setOnClickListener(this);
            Uri uri = Uri.parse(episodesBean.getCover_url());
            mSimpleDraweeView.setImageURI(uri);
            mTitle.setText(episodesBean.getName());
            mDes.setText(episodesBean.getDescription());
            if (episodesBean.isIsFree()) {
                mImageView.setVisibility(View.VISIBLE);
            } else {
                mImageView.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.root: {
                    final GetSeasonVideoListBean.EpisodesBean episodesBean = (GetSeasonVideoListBean.EpisodesBean) view.getTag();
                    if (episodesBean != null) {
                        UserInfoManage.getInstance().minusCredit(new SimpleCallback<MinusCreditBean>() {
                            @Override
                            public void onSuccess(MinusCreditBean response) {
                                hideLoading();
                                if (response.isIsMinus()) {
                                    EventBus.getDefault().post(new EventCreditUpdate(response.getCredit()));
                                    PlayVideoViewActivity.openActivity((Activity) mContext, episodesBean);
                                } else {
                                    Util.showMessageAlert(mContext, "体力不足～");
                                }
                            }

                            @Override
                            public void onFailure(int responseCode) {
                                hideLoading();
                                Util.showMessageAlert(mContext, "错误码：" + responseCode);
                            }
                        }, costCredit);
                        //PlayVideoActivity.openActivity((Activity) mContext,episodesBean);
                    }
                }
                break;
            }
        }
    }
}
