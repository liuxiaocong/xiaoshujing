package com.xiaoshujing.kid.ui.photoUpload;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.common.BaseActivity;
import com.xiaoshujing.kid.common.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class PhotoUploadActivity extends BaseActivity implements PhotoUploadContract.View {
    PhotoUploadContract.Presenter mPresenter;
    @BindView(R.id.record_list)
    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    @BindView(R.id.img_list)
    GridLayout mImageList;
    @BindView(R.id.loading)
    View mLoading;
    @BindView(R.id.flow_tips)
    View mFlowTips;
    @BindView(R.id.flow_tips_text)
    TextView mFlowTipsText;

    LayoutInflater mLayoutInflater;
    int mTargetImageSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_upload);
        mLayoutInflater = LayoutInflater.from(this);
        ButterKnife.bind(this);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mPresenter = new PhotoUploadPresentImpl(this);
        setPresenter(mPresenter);
        mPresenter.bindView(this);
        mTargetImageSize = (Util.getScreenWidth(this) - Util.getPxFromDp(this, 40)) / 3;
        if (MyApplication.getInstance().getPreUploadBitmap() != null) {
            for (int i = 0; i < MyApplication.getInstance().getPreUploadBitmap().size(); i++) {
                View view = genPhotoItem(MyApplication.getInstance().getPreUploadBitmap().get(i));
                mImageList.addView(view);
            }
        }
        mImageList.invalidate();
        mPresenter.start();
    }

    public View genPhotoItem(Bitmap bitmap) {
        FrameLayout frameLayout = (FrameLayout) mLayoutInflater.inflate(R.layout.item_photo, null);
        ImageView photoView = (ImageView) frameLayout.findViewById(R.id.photo);
        photoView.setImageBitmap(bitmap);
        LinearLayout linearLayout = (LinearLayout) frameLayout.findViewById(R.id.photo_wrap);
        ViewGroup.LayoutParams lp = linearLayout.getLayoutParams();
        lp.height = mTargetImageSize;
        lp.width = mTargetImageSize;
        linearLayout.setLayoutParams(lp);
        return frameLayout;
    }

    @OnClick({R.id.back})
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

    @Override
    public void setPresenter(PhotoUploadContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showUploading() {
        mLoading.invalidate();
        mLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideUploading() {
        mLoading.setVisibility(View.GONE);
    }

    @Override
    public void setTipsText(String tipsText) {
        if (mFlowTipsText != null) {
            mFlowTipsText.setText(tipsText);
        }
    }

    @Override
    public View getTips() {
        return mFlowTips;
    }
}
