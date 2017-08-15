package com.xiaoshujing.kid.ui.photoSuccess;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.common.BaseActivity;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.event.EventLoadPhotoSuccessPage;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class PhotoSuccessActivity extends BaseActivity implements PhotoSuccessContract.View {
    public static String OPEN_FROM = "OPEN_FROM";
    PhotoSuccessContract.Presenter mPresenter;
    @BindView(R.id.img_list)
    GridLayout mImageList;

    @BindView(R.id.first_image_wrap)
    LinearLayout mFirstImageWrap;
    @BindView(R.id.first_image)
    ImageView mFirstImage;
    LayoutInflater mLayoutInflater;
    int mTargetImageSize;
    String mFrom = "take";

    public static void openActivity(Activity activity, String photoUrl, String title, String des) {
        Intent intent = new Intent(activity, PhotoSuccessActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_success);
        mLayoutInflater = LayoutInflater.from(this);
        ButterKnife.bind(this);
        if (MyApplication.getInstance().getPreUploadBitmap() != null && MyApplication.getInstance().getPreUploadBitmap().size() > 0) {
            mFirstImage.setImageBitmap(MyApplication.getInstance().getPreUploadBitmap().get(MyApplication.getInstance().getPreUploadBitmap().size() - 1));
        }
        Intent intent = getIntent();
        if (intent.hasExtra(OPEN_FROM)) {
            mFrom = intent.getStringExtra(OPEN_FROM);
        }
        ButterKnife.bind(this);
        mPresenter = new PhotoSuccessPresentImpl(this);
        setPresenter(mPresenter);
        mPresenter.bindView(this);
        mTargetImageSize = (Util.getScreenWidth(this) - Util.getPxFromDp(this, 40)) / 3;
        ViewGroup.LayoutParams lp = mFirstImageWrap.getLayoutParams();
        lp.width = mTargetImageSize;
        lp.height = mTargetImageSize;
        mFirstImageWrap.setLayoutParams(lp);
        mFirstImage.invalidate();
        mFirstImage.postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(new EventLoadPhotoSuccessPage(true));
            }
        }, 100);
        if (MyApplication.getInstance().getPreUploadBitmap() != null) {
            for (int i = MyApplication.getInstance().getPreUploadBitmap().size() - 2; i >= 0; i--) {
                View view = genPhotoItem(MyApplication.getInstance().getPreUploadBitmap().get(i));
                mImageList.addView(view);
            }
        }
        if (MyApplication.getInstance().getPreUploadBitmap().size() < 4) {
            Bitmap addBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_add_green_photo);
            View addView = genPhotoItem(addBitmap);
            mImageList.addView(addView);
            addView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mPresenter != null) {
                        if (mFrom.equals("album")) {
                            mPresenter.openSelectPhotoPage();
                        } else {
                            mPresenter.openTakePhotoPage();
                        }
                        finish();
                    }
                }
            });
        }
        mImageList.invalidate();
    }


    @OnClick({R.id.upload, R.id.back})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.upload: {
                if (mPresenter != null) {
                    mPresenter.openUploadPhotoPage();
                    finish();
                }
            }
            break;
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

    @Override
    public void setPresenter(PhotoSuccessContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
