package com.xiaoshujing.kid.ui.photoUpload;

import android.animation.Animator;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.common.SharedPreferencesFactory;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.data.MissionManage;
import com.xiaoshujing.kid.data.MomentDataManage;
import com.xiaoshujing.kid.data.UserInfoManage;
import com.xiaoshujing.kid.model.BodyMyMoment;
import com.xiaoshujing.kid.model.CommonRetBean;
import com.xiaoshujing.kid.model.GradeBean;
import com.xiaoshujing.kid.model.PostMyMomentBean;
import com.xiaoshujing.kid.model.StudyMomentsBean;
import com.xiaoshujing.kid.model.UploadImgBean;
import com.xiaoshujing.kid.ui.record.RecordActivity;
import com.xiaoshujing.kid.ui.record.RecordItemAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */

/**
 * share adapter with record page
 */
public class PhotoUploadPresentImpl implements PhotoUploadContract.Presenter {
    String TAG = "PhotoUploadPresentImpl";
    Activity mContext;
    PhotoUploadContract.View mView;
    RecordItemAdapter mRecordItemAdapter;
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    private List<String> mUploadedUrl = new ArrayList<>();
    GradeBean mGradeBean;
    int pages = 0;
    int state = 0;

    public PhotoUploadPresentImpl(Activity context) {
        mContext = context;
    }


    @Override
    public void bindView(PhotoUploadContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        mRecordItemAdapter = new RecordItemAdapter(mContext);
        if (mView != null) {
            mView.setAdapter(mRecordItemAdapter);
        }
        loadData();
        startUpload();
        mView.showUploading();
    }

    private void startUpload() {
        if (MyApplication.getInstance().getPreUploadFilePath() != null &&
                MyApplication.getInstance().getPreUploadFilePath().size() > 0) {
            MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            for (String s : MyApplication.getInstance().getPreUploadFilePath()) {
                File f = new File(s);
                if (f.length() > 0) {
                    requestBodyBuilder.addFormDataPart("fileList", f.getName(),
                            RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), f));
                }
            }
            UserInfoManage.getInstance().uploadImages(mUploadImageBeanSimpleCallback, requestBodyBuilder.build());
            mView.setTipsText("图片上传中请稍候..");
            Util.showWithAlpha(500, mView.getTips(), null);
        }
    }

    SimpleCallback<UploadImgBean> mUploadImageBeanSimpleCallback = new SimpleCallback<UploadImgBean>() {
        @Override
        public void onSuccess(UploadImgBean response) {
            Util.DLog(TAG, "UploadImgBean onSuccess");
            UploadImgBean ret = response;
            mUploadedUrl = response.getImage_urls();

            if (ret.getImage_urls() != null) {
                pages = mUploadedUrl.size();
                UserInfoManage.getInstance().getGrade(mGradeBeanSimpleCallback, ret.getImage_urls());
            }
        }

        @Override
        public void onFailure(int responseCode) {
            Util.DLog(TAG, "UploadImgBean onFailure");
            int ret = responseCode;
            //MyApplication.getInstance().showNote("上传失败");
            mView.setTipsText("上传失败");
            hideTips();
        }
    };

    SimpleCallback<GradeBean> mGradeBeanSimpleCallback = new SimpleCallback<GradeBean>() {
        @Override
        public void onSuccess(GradeBean response) {
            Util.DLog(TAG, "GradeBean onSuccess");
            //MyApplication.getInstance().showNote("上传成功,你的得分是：" + response.getStudyScore());
            state = 1;
            //mView.setTipsText("上传成功,你的得分是：" + response.getStudyScore());
            mView.hideUploading();
            MyApplication.getInstance().clearPreUploadBitmapAndPath();
            mGradeBean = response;
            updateMission();
        }

        @Override
        public void onFailure(int responseCode) {
            Util.DLog(TAG, "GradeBean onFailure");
            int ret = responseCode;
            //MyApplication.getInstance().showNote("评分失败");
            mView.setTipsText("评分失败");
            hideTips();
        }
    };

    //makr as finish
    private void updateMission() {
        String pid = SharedPreferencesFactory.getKeyCurrentProductId(mContext);
        if (Util.isNullOrEmpty(pid)) {
            MissionManage.getInstance().updateMission(mUpdateMissionSimpleCallback, "", pages);
        } else {
            MissionManage.getInstance().updateMission(mUpdateMissionSimpleCallback, pid, pages);
        }
    }

    SimpleCallback<CommonRetBean> mUpdateMissionSimpleCallback = new SimpleCallback<CommonRetBean>() {
        @Override
        public void onSuccess(CommonRetBean response) {
            Util.DLog(TAG, "mUpdateMissionSimpleCallback onSuccess");
            //MyApplication.getInstance().showNote("任务已经完成");
            state = 2;
            //mView.setTipsText("任务已经完成");
            if (mGradeBean != null) {
                postToMoment(mGradeBean);
            }
        }

        @Override
        public void onFailure(int responseCode) {
            Util.DLog(TAG, "mUpdateMissionSimpleCallback onFailure");
            int ret = responseCode;
            //MyApplication.getInstance().showNote("任务完成失败");
            mView.setTipsText("任务完成失败");
            hideTips();
        }
    };

    private void postToMoment(GradeBean response) {
        if (UserInfoManage.getInstance().getUserBean() != null) {
            MomentDataManage.getInstance().postStudyMoments(mPostMyMomentBeanSimpleCallback,
                    BodyMyMoment.newBuilder()
                            .baby(UserInfoManage.getInstance().getUserBean().getBaby())
                            .content("")
                            .img_urls(mUploadedUrl)
                            .isShow(true)
                            .overallScore(response.getStudyScore())
                            .studyScore(response.getStudyScore())
                            .pages(1)
                            .product(UserInfoManage.getInstance().getUserBean().getDefaultCopybook())
                            .build());
        }
    }

    SimpleCallback<PostMyMomentBean> mPostMyMomentBeanSimpleCallback = new SimpleCallback<PostMyMomentBean>() {
        @Override
        public void onSuccess(PostMyMomentBean response) {
            //MyApplication.getInstance().showNote("已经成功发布到圈子");
            //mView.setTipsText("已经成功发布到圈子");
            state = 3;
            hideTips();
            loadData();
        }

        @Override
        public void onFailure(int responseCode) {
            //MyApplication.getInstance().showNote("发布圈子出错");
            mView.setTipsText("发布圈子出错");
            hideTips();
        }
    };

    private void hideTips() {
        Util.disappearWithAlpha(500, mView.getTips(), 2000, new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mView.getTips().setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        if (state >= 2) {
            Util.ShowAlert(mContext, "", "任务完成,你的本次得分是:" + mGradeBean.getStudyScore(), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(mContext, RecordActivity.class);
                    mContext.startActivity(intent);
                    mContext.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    mContext.finish();
                }
            }, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mContext.finish();
                }
            }, "查看记录", "返回首页");
        }
    }

    private void loadData() {
//        ArrayList<DailyRecordItemBean> dailyRecordItemBeanArrayList = new ArrayList<>();
//        String[] urls = new String[]{"http://att.bbs.duowan.com/forum/201311/04/154122t2ba6lbajlp6z62d.jpg", "http://people.chu.edu.tw/~b9204125/images/people.jpg",
//                "http://ast.ainimei.cn/articles/10008242/img/85a55b8ec933954db0f5e55ee24fdd8f.jpg", "https://public.bn1304.livefilestore.com/y3pB9PZi_ze7s_s5WufoMs-fRFtGOgbqPTEpLJUhNeen88RSgtwXrjEiNe4Leyb8Q_8OrrH2LGmFuAJHQccumNLKizQhfEgSlb5JfIucetVXrHUYSDWbATHsj6IygHAQcEgIwRGCNYmhM3m5iArIHO_ag/KSCL-1394.jpg?rdrts=153296708",
//                "http://comic.qq.com/z/gang/gallery/image/adh2004031.jpg"};
//        for (int i = 0; i < 5; i++) {
//            DailyRecordItemBean dailyRecordItemBean = new DailyRecordItemBean();
//            dailyRecordItemBean.setTime(i);
//            ArrayList<DailyRecordItemBean.PhotosBean> photosBeanArrayList = new ArrayList<>();
//            for (int j = -2; j < i && j < 2; j++) {
//                DailyRecordItemBean.PhotosBean photosBean = new DailyRecordItemBean.PhotosBean();
//                photosBean.setId(j);
//                photosBean.setUrl(urls[j + 2]);
//                photosBeanArrayList.add(photosBean);
//            }
//            dailyRecordItemBean.setPhotos(photosBeanArrayList);
//            dailyRecordItemBeanArrayList.add(dailyRecordItemBean);
//        }
//        mRecordItemAdapter.addData(dailyRecordItemBeanArrayList, true);
        MomentDataManage.getInstance().getStudyMoments(mStudyMomentsBeanSimpleCallback);
    }

    SimpleCallback<StudyMomentsBean> mStudyMomentsBeanSimpleCallback = new SimpleCallback<StudyMomentsBean>() {
        @Override
        public void onSuccess(StudyMomentsBean response) {
            if (response != null && response.getObjects() != null) {
                mRecordItemAdapter.addData(response.getObjects(), true);
            }
        }

        @Override
        public void onFailure(int responseCode) {

        }
    };
}
