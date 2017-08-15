package com.xiaoshujing.kid.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.common.Util;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by liuxiaocong on 11/11/15.
 */
public class ImageViewDialog extends Dialog implements View.OnClickListener {
    String TAG = "ImageViewDialog";

    public interface IImageViewDialogListener {

    }

    private IImageViewDialogListener mIImageViewDialogListener;
    private Context mContext;
    View mLast;
    View mNext;
    SimpleDraweeView mImage;
    List<String> mImageList = new ArrayList<>();
    String mCurrentImage = "";
    int mCurrentIndex = 0;

    public static ImageViewDialog createInstance(Context context,
                                                 IImageViewDialogListener iImageViewDialogListener, List<String> imageList, String currentImage, boolean closeAble) {
        return new ImageViewDialog(context, iImageViewDialogListener, imageList, currentImage, closeAble);
    }

    public ImageViewDialog(Context context, IImageViewDialogListener iImageViewDialogListener, List<String> imageList, String currentImage, boolean closeAble) {
        super(context, R.style.SimpleDialog);
        mIImageViewDialogListener = iImageViewDialogListener;
        mContext = context;
        setContentView(R.layout.dialog_image_view);
        mImageList = imageList;
        mCurrentImage = currentImage;
        for (int i = 0; i < mImageList.size(); i++) {
            if (mCurrentImage.equals(mImageList.get(i))) {
                mCurrentIndex = i;
                break;
            }
        }
        mImage = (SimpleDraweeView) findViewById(R.id.image);
        findViewById(R.id.close).setOnClickListener(this);
        mLast = findViewById(R.id.last);
        mLast.setOnClickListener(this);
        mNext = findViewById(R.id.next);
        mNext.setOnClickListener(this);
        updateImage();
        setCanceledOnTouchOutside(closeAble);
        setCancelable(closeAble);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void updateImage() {
        String url = mImageList.get(mCurrentIndex);
        Uri uri = Uri.parse(url);
        Util.DLog(TAG, url);
        mImage.setImageURI(uri);
        mLast.setEnabled(true);
        mNext.setEnabled(true);
        if (mCurrentIndex == 0) {
            mLast.setEnabled(false);
        }
        if (mCurrentIndex == mImageList.size() - 1) {
            mNext.setEnabled(false);
        }
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.last: {
                mCurrentIndex--;
                updateImage();
            }
            break;
            case R.id.next: {
                mCurrentIndex++;
                updateImage();
            }
            break;
            case R.id.close: {
                dismiss();
            }
        }
    }

}
