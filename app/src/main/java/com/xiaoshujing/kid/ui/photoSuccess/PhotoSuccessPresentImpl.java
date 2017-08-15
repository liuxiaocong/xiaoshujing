package com.xiaoshujing.kid.ui.photoSuccess;

import android.app.Activity;
import android.content.Intent;

import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.ui.photoUpload.PhotoUploadActivity;
import com.xiaoshujing.kid.ui.score.ScoreActivity;
import com.xiaoshujing.kid.ui.scoreFromAlbum.ScoreFromAlbumActivity;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class PhotoSuccessPresentImpl implements PhotoSuccessContract.Presenter {
    PhotoSuccessContract.View mView;
    Activity mContext;

    public PhotoSuccessPresentImpl(Activity context) {
        mContext = context;
    }


    @Override
    public void bindView(PhotoSuccessContract.View view) {
        mView = view;
    }

    @Override
    public void openSelectPhotoPage() {
        Intent intent = new Intent(mContext, ScoreFromAlbumActivity.class);
        mContext.startActivity(intent);
        mContext.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public void openTakePhotoPage() {
        Intent intent = new Intent(mContext, ScoreActivity.class);
        mContext.startActivity(intent);
        mContext.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public void openUploadPhotoPage() {
        Intent intent = new Intent(mContext, PhotoUploadActivity.class);
        mContext.startActivity(intent);
        mContext.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}
