package com.xiaoshujing.kid.data;

import com.xiaoshujing.kid.api.RetrofitManager;
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.model.BodyMyMoment;
import com.xiaoshujing.kid.model.PostMyMomentBean;
import com.xiaoshujing.kid.model.StudyMomentsBean;

import retrofit2.Call;

/**
 * Created by LiuXiaocong on 12/5/2016.
 */

public class MomentDataManage {
    private volatile static MomentDataManage gInstance = null;

    public static String BY_LIKE = "-likesCount";
    public static String BY_TIME = "-created_at";

    private MomentDataManage() {

    }

    public static MomentDataManage getInstance() {
        if (gInstance == null) {
            synchronized (MomentDataManage.class) {
                if (gInstance == null) {
                    gInstance = new MomentDataManage();
                }
            }
        }
        return gInstance;
    }


    public void getStudyMoments(SimpleCallback<StudyMomentsBean> simpleCallback) {
        Call<StudyMomentsBean> call = RetrofitManager.getInstance().getApiService().getStudyMoments();
        simpleCallback.enqueueCall(call);
    }

    public void postStudyMoments(SimpleCallback<PostMyMomentBean> simpleCallback, BodyMyMoment bodyMyMoment) {
        Call<PostMyMomentBean> call = RetrofitManager.getInstance().getApiService().postStudyMoments(bodyMyMoment);
        simpleCallback.enqueueCall(call);
    }
}
