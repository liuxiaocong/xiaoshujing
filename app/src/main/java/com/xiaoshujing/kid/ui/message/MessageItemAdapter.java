package com.xiaoshujing.kid.ui.message;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.model.message.GetMessageBean;
import com.xiaoshujing.kid.ui.playVideoView.PlayVideoViewActivity;
import com.xiaoshujing.kid.widget.SlideView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LiuXiaocong on 11/9/2016.
 */
public class MessageItemAdapter extends RecyclerView.Adapter<MessageItemAdapter.ViewHolder> {
    LayoutInflater mLayoutInflater;
    Context mContext;
    ArrayList<GetMessageBean.MessageBean> mData = new ArrayList<>();
    SlideView.OnSlideViewListener<GetMessageBean.MessageBean> mOnSlideViewListener;

    public MessageItemAdapter(Context context, SlideView.OnSlideViewListener<GetMessageBean.MessageBean> onSlideViewListener) {
        mOnSlideViewListener = onSlideViewListener;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    public void addData(List<GetMessageBean.MessageBean> messageBeen, boolean needRefresh) {
        if (needRefresh) {
            mData.clear();
        }
        mData.addAll(messageBeen);
        Comparator<GetMessageBean.MessageBean> comparator = new isRead();
        Collections.sort(mData, comparator);
        notifyDataSetChanged();
    }

    public class isRead implements Comparator<GetMessageBean.MessageBean> {
        @Override
        public int compare(GetMessageBean.MessageBean o1, GetMessageBean.MessageBean o2) {
            int v1 = o1.isRead() ? 1 : 0;
            int v2 = o2.isRead() ? 1 : 0;
            return v1 - v2;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_message_list_full, parent, false);
        SlideView slideView = (SlideView) view.findViewById(R.id.slideView);
        View itemView = mLayoutInflater.inflate(R.layout.item_message, null);
        itemView.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        slideView.setContentView(itemView);
        slideView.setOnSlideListener(mOnSlideViewListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GetMessageBean.MessageBean messageBean = mData.get(position);
        if (messageBean != null) {
            holder.bindData(messageBean, position);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void delete(String object_id) {
        if (mData != null) {
            int target = -1;
            for (int i = 0; i < mData.size(); i++) {
                if (mData.get(i).getObject_id().equals(object_id)) {
                    target = i;
                    break;
                }
            }
            if (target >= 0) {
                mData.remove(target);
                notifyDataSetChanged();
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.message_title)
        TextView mTitle;
        @BindView(R.id.message_time)
        TextView mTime;
        @BindView(R.id.read_dot)
        View mReadDot;
        @BindView(R.id.slideView)
        SlideView mSlideView;
        @BindView(R.id.delete)
        TextView mDelete;
        @BindView(R.id.root)
        View mRoot;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(final GetMessageBean.MessageBean messageBean, final int position) {
            itemView.setTag(messageBean);
            mSlideView.setTag(messageBean);
            mSlideView.shrink();
            mDelete.setTag(messageBean);
            if (messageBean.isRead()) {
                mReadDot.setVisibility(View.VISIBLE);
            } else {
                mReadDot.setVisibility(View.GONE);
            }
            mTitle.setText(messageBean.getContent());
            mTime.setText(Util.getDate(messageBean.getCreated_at()));
            mRoot.setTag(messageBean);
            mReadDot.setVisibility(messageBean.isRead() ? View.INVISIBLE : View.VISIBLE);
            mDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mSlideView.shrink();
                    if (mOnSlideViewListener != null) {
                        mOnSlideViewListener.onDeleteItem(messageBean, position);
                    }
                }
            });

        }

        @Override
        public void onClick(View view) {

        }
    }
}
