package com.xiaoshujing.kid.data;

import com.xiaoshujing.kid.api.RetrofitManager;
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.model.BodyPractise;
import com.xiaoshujing.kid.model.BodyPractiseUpdate;
import com.xiaoshujing.kid.model.CommonRetBean;
import com.xiaoshujing.kid.model.PractiseEndBean;
import com.xiaoshujing.kid.model.PractiseStartBean;
import com.xiaoshujing.kid.model.PractiseUpdateBean;

import retrofit2.Call;

/**
 * Created by LiuXiaocong on 12/5/2016.
 */

public class PractiseManage {
    private volatile static PractiseManage gInstance = null;

    private PractiseManage() {

    }

    public static PractiseManage getInstance() {
        if (gInstance == null) {
            synchronized (PractiseManage.class) {
                if (gInstance == null) {
                    gInstance = new PractiseManage();
                }
            }
        }
        return gInstance;
    }

    public void start(SimpleCallback<PractiseStartBean> simpleCallback, String devicesId) {
        Call<PractiseStartBean> call = RetrofitManager.getInstance().getApiService().startPractise(
                BodyPractise.newBuilder().baby(devicesId).build()
        );
        simpleCallback.enqueueCall(call);
    }

    public void end(SimpleCallback<PractiseEndBean> simpleCallback) {
        Call<PractiseEndBean> call = RetrofitManager.getInstance().getApiService().endPractise();
        simpleCallback.enqueueCall(call);
    }

    public void update(SimpleCallback<PractiseUpdateBean> simpleCallback, int minute, double score) {
        Call<PractiseUpdateBean> call = RetrofitManager.getInstance().getApiService().updatePractise(
                (new BodyPractiseUpdate.Builder()).practiceMinutes(minute).sitScore(score).build()
        );
        simpleCallback.enqueueCall(call);
    }

    public void reset(SimpleCallback<CommonRetBean> simpleCallback) {
        Call<CommonRetBean> call = RetrofitManager.getInstance().getApiService().resetPractise();
        simpleCallback.enqueueCall(call);
    }

    public void check(SimpleCallback<CommonRetBean> simpleCallback) {
        Call<CommonRetBean> call = RetrofitManager.getInstance().getApiService().checkPractise();
        simpleCallback.enqueueCall(call);
    }
}
