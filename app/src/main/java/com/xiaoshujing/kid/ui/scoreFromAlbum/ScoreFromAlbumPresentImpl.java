package com.xiaoshujing.kid.ui.scoreFromAlbum;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

import com.soundcloud.android.crop.Crop;
import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.common.LocalImage;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.data.PractiseManage;
import com.xiaoshujing.kid.model.CommonRetBean;
import com.xiaoshujing.kid.ui.dialog.LoadingDialog;
import com.xiaoshujing.kid.ui.photoSuccess.PhotoSuccessActivity;

import java.io.File;
import java.io.IOException;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class ScoreFromAlbumPresentImpl implements ScoreFromAlbumContract.Presenter {
    String TAG = "ScoreFromAlbumPresentImpl";
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    Activity mContext;
    ScoreFromAlbumContract.View mView;
    int mTargetWidth = 200;
    int mTargetHeight = 200;
    String mPath = "";
    LoadingDialog mLoadingDialog;

    public ScoreFromAlbumPresentImpl(Activity context) {
        mContext = context;
    }


    @Override
    public void bindView(ScoreFromAlbumContract.View view) {
        mView = view;
    }

    @Override
    public void check() {
        mLoadingDialog = LoadingDialog.show(mContext);
        PractiseManage.getInstance().check(mCommonRetBeanForCheckSimpleCallback);
    }

    private void hideLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    @Override
    public void reSelect() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
        mContext.startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    SimpleCallback<CommonRetBean> mCommonRetBeanForCheckSimpleCallback = new SimpleCallback<CommonRetBean>() {
        @Override
        public void onSuccess(CommonRetBean response) {
            hideLoading();
            if (response.get_status() == 0) {
                start();
            } else {
                Util.showMessageAlert(mContext, "", response.get_reason(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mContext.finish();
                    }
                }, false);
            }
        }

        @Override
        public void onFailure(int responseCode) {
            MyApplication.getInstance().showNote("error");
            hideLoading();
            mContext.finish();
        }
    };


    @Override
    public void start() {
        reSelect();
    }

    @Override
    public void clear() {

    }

    @Override
    public void openPhotoSuccessPage(View view) {
        Pair<View, String> pair1 = null;
        Intent intent = new Intent(mContext, PhotoSuccessActivity.class);
        intent.putExtra(PhotoSuccessActivity.OPEN_FROM, "album");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            pair1 = Pair.create(view, view.getTransitionName());
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) mContext, pair1);
            mContext.startActivity(intent, options.toBundle());
        } else {
            mContext.startActivity(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                cropLib(uri);
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            Uri path = data.getParcelableExtra(MediaStore.EXTRA_OUTPUT);
            //Bitmap bitmap = BitmapFactory.decodeFile(mPath);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (bitmap == null) {
                if (data != null) {
                    bitmap = data.getParcelableExtra("data");
                }
            }
            mView.setDisplayPhoto(bitmap, mPath);
        } else if (requestCode == Crop.REQUEST_CROP) {
            handleCrop(resultCode, data);
        }
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == -1) {
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), Crop.getOutput(result));
                int degree = Util.getBitmapDegree(Crop.getOutput(result).getPath());
                if (degree != 0) {
                    // 下面的方法主要作用是把图片转一个角度，也可以放大缩小等
                    Matrix m = new Matrix();
                    int width = bitmap.getWidth();
                    int height = bitmap.getHeight();
                    m.setRotate(degree); // 旋转angle度
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                            m, true);// 从新生成图片

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (bitmap == null) {
                if (result != null) {
                    bitmap = result.getParcelableExtra("data");
                }
            }
            mView.setDisplayPhoto(bitmap, mPath);
        }
    }

    private void cropLib(Uri uri) {
        Uri destination = Uri.fromFile(new File(mContext.getCacheDir(), "my-score" + System.currentTimeMillis() + ".jpg"));
        mPath = destination.getPath();
        Crop.of(uri, destination).withAspect(mTargetWidth, mTargetHeight).start(mContext);
    }

    private void crop(Uri uri) {
        if (mContext == null) return;
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", mTargetWidth);
        intent.putExtra("aspectY", mTargetHeight);
        // 裁剪后输出图片的尺寸大小
//        intent.putExtra("outputX", mTargetWidth);
//        intent.putExtra("outputY", mTargetHeight);
        String folder = LocalImage.getSaveFolder();
        new File(folder).mkdirs();
        String fileName = "my-score" + System.currentTimeMillis() + ".jpg";
        mPath = folder + fileName;
        Uri cropUri = Uri.fromFile(new File(folder, fileName));
        //Uri destination = Uri.fromFile(new File(mContext.getCacheDir(), "cropped"));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);
        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        mContext.startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }


    @Override
    public void setTargetSize(int width, int height) {
        mTargetWidth = width;
        mTargetHeight = height;
    }


}
