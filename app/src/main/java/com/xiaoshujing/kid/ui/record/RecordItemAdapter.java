package com.xiaoshujing.kid.ui.record;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.model.StudyMomentsBean;
import com.xiaoshujing.kid.ui.dialog.ImageViewDialog;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LiuXiaocong on 11/9/2016.
 */
public class RecordItemAdapter extends RecyclerView.Adapter<RecordItemAdapter.ViewHolder> {
    LayoutInflater mLayoutInflater;
    Context mContext;
    ArrayList<StudyMomentsBean.RecordBean> mData = new ArrayList<>();
    int mTargetWidth = 0;
    List<String> mImageUrlList = new ArrayList<>();

    public enum TITEMType {
        E_SPECIAL_VIEW(0), E_NORMAL(1);
        private int mIntValue;

        TITEMType(int intValue) {
            mIntValue = intValue;
        }

        public int getIntValue() {
            return mIntValue;
        }

        public static TITEMType parseIntValue(int intValue) {
            for (TITEMType value : TITEMType.values()) {
                if (value.mIntValue == intValue) {
                    return value;
                }
            }
            return E_NORMAL;
        }
    }

    public RecordItemAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        int twoTargetWidth = Util.getScreenWidth(mContext) - Util.getPxFromDp(mContext, 26) - Util.getPxFromDp(mContext, 61) - Util.getPxFromDp(mContext, 25 + 8) - Util.getPxFromDp(mContext, 25 + 5);
        mTargetWidth = twoTargetWidth / 2;
    }

    public void addData(List<StudyMomentsBean.RecordBean> recordBeanList, boolean needRefresh) {
        if (needRefresh) {
            mData.clear();
        }
        mData.addAll(recordBeanList);
        mImageUrlList.clear();
        for (StudyMomentsBean.RecordBean recordBean : mData) {
            if (recordBean.getImg_urls() != null) {
                mImageUrlList.addAll(recordBean.getImg_urls());
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_daily_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StudyMomentsBean.RecordBean dailyRecordItemBean = mData.get(position);
        if (dailyRecordItemBean != null) {
            holder.bindData(dailyRecordItemBean);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return TITEMType.E_NORMAL.getIntValue();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.photo_container)
        LinearLayout mPhotoWrap;
        @BindView(R.id.zonghe)
        TextView mZonghe;
        @BindView(R.id.xuexi)
        TextView mXuexi;
        @BindView(R.id.zuozi)
        TextView mZuozi;
        @BindView(R.id.day)
        TextView mDay;
        @BindView(R.id.month)
        TextView mMonth;
        @BindView(R.id.time)
        TextView mTime;
        @BindView(R.id.zonghe_score)
        TextView mZongheScore;
        @BindView(R.id.xuexi_score)
        TextView mXuexiScore;
        @BindView(R.id.zuozi_score)
        TextView mZuoziScore;
        @BindView(R.id.photo_wrap_1)
        View mPhotoWrap1;
        @BindView(R.id.photo_wrap_2)
        View mPhotoWrap2;
        @BindView(R.id.image1)
        SimpleDraweeView mSimpleDraweeView1;
        @BindView(R.id.image2)
        SimpleDraweeView mSimpleDraweeView2;
        @BindView(R.id.image3)
        SimpleDraweeView mSimpleDraweeView3;
        @BindView(R.id.image4)
        SimpleDraweeView mSimpleDraweeView4;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(StudyMomentsBean.RecordBean dailyRecordItemBean) {
            mPhotoWrap1.setVisibility(View.GONE);
            mPhotoWrap2.setVisibility(View.GONE);
            mSimpleDraweeView1.setAlpha(0f);
            mSimpleDraweeView2.setAlpha(0f);
            mSimpleDraweeView3.setAlpha(0f);
            mSimpleDraweeView4.setAlpha(0f);
            if (dailyRecordItemBean.getImg_urls().size() > 0) {
                mPhotoWrap1.setVisibility(View.VISIBLE);
                if (dailyRecordItemBean.getImg_urls().size() > 2) {
                    mPhotoWrap2.setVisibility(View.VISIBLE);
                }
                for (int i = 0; i < dailyRecordItemBean.getImg_urls().size(); i++) {
                    Uri uri = Uri.parse(dailyRecordItemBean.getImg_urls().get(i));
                    switch (i) {
                        case 0: {
                            mSimpleDraweeView1.setImageURI(uri);
                            mSimpleDraweeView1.setTag(dailyRecordItemBean.getImg_urls().get(i));
                            mSimpleDraweeView1.setOnClickListener(this);
                            mSimpleDraweeView1.setAlpha(1f);
                        }
                        break;
                        case 1: {
                            mSimpleDraweeView2.setImageURI(uri);
                            mSimpleDraweeView2.setTag(dailyRecordItemBean.getImg_urls().get(i));
                            mSimpleDraweeView2.setOnClickListener(this);
                            mSimpleDraweeView2.setAlpha(1f);
                        }
                        break;
                        case 2: {
                            mSimpleDraweeView3.setImageURI(uri);
                            mSimpleDraweeView3.setTag(dailyRecordItemBean.getImg_urls().get(i));
                            mSimpleDraweeView3.setOnClickListener(this);
                            mSimpleDraweeView3.setAlpha(1f);
                        }
                        break;
                        case 3: {
                            mSimpleDraweeView4.setImageURI(uri);
                            mSimpleDraweeView4.setTag(dailyRecordItemBean.getImg_urls().get(i));
                            mSimpleDraweeView4.setOnClickListener(this);
                            mSimpleDraweeView4.setAlpha(1f);
                        }
                        break;
                    }
                }
            }
            mPhotoWrap.postInvalidate();
            Timestamp timestamp = new Timestamp((long) dailyRecordItemBean.getUpdated_at() * 1000);
            Date date = new Date(timestamp.getTime());
            mMonth.setText(date.getMonth() + 1 + "月");
            mDay.setText(date.getDate() + "");

            SimpleDateFormat formatter;
            formatter = new SimpleDateFormat("KK:mm a");
            String ctime = formatter.format(date);

            mTime.setText(ctime + "");
            mZongheScore.setText(dailyRecordItemBean.getOverallScore() + "");
            mXuexiScore.setText(dailyRecordItemBean.getStudyScore() + "");
            mZuoziScore.setText(dailyRecordItemBean.getSitScore() + "");
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image1:
                case R.id.image2:
                case R.id.image3:
                case R.id.image4: {
                    ImageViewDialog.createInstance(mContext, null, mImageUrlList, (String) v.getTag(), true).show();
                }
                break;
            }
        }
    }
}
