package com.xiaoshujing.kid.data;

import com.xiaoshujing.kid.api.RetrofitManager;
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.model.BodyUpdateMission;
import com.xiaoshujing.kid.model.CommonRetBean;
import com.xiaoshujing.kid.model.GetInfoBean;
import com.xiaoshujing.kid.model.GetMissionBean;

import retrofit2.Call;

/**
 * Created by LiuXiaocong on 12/5/2016.
 */

public class MissionManage {
    private volatile static MissionManage gInstance = null;
    private GetInfoBean.UserBean mUserBean = null;

    public enum MissionType {
        EAll(-1),
        EParent(0),
        EDaily(1),
        EActivity(2);
        private final int value;

        MissionType(int value) {
            this.value = value;
        }

        public static MissionType valueOf(int value) {
            switch (value) {
                case -1:
                    return EAll;
                case 0:
                    return EParent;
                case 1:
                    return EDaily;
                case 2:
                    return EActivity;
                default:
                    return EAll;
            }
        }
        public int getValue() {
            return value;
        }
    }

    public enum MissionStatus {
        EWorkon(0),
        EFinish(1);
        private final int value;

        MissionStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    private MissionManage() {

    }

    public static MissionManage getInstance() {
        if (gInstance == null) {
            synchronized (MissionManage.class) {
                if (gInstance == null) {
                    gInstance = new MissionManage();
                }
            }
        }
        return gInstance;
    }

    public void getMission(SimpleCallback<GetMissionBean> simpleCallback, int misstype, int missionStatus) {
        Call<GetMissionBean> call = RetrofitManager.getInstance().getApiService().getMission(misstype, missionStatus);
        simpleCallback.enqueueCall(call);
    }

    public void getMissionDetail(SimpleCallback<GetMissionBean.MissionBean> simpleCallback, String mid) {
        Call<GetMissionBean.MissionBean> call = RetrofitManager.getInstance().getApiService().getMissionDetail(mid);
        simpleCallback.enqueueCall(call);
    }

    public void getMissionByStatus(SimpleCallback<GetMissionBean> simpleCallback, int missionStatus) {
        Call<GetMissionBean> call = RetrofitManager.getInstance().getApiService().getMissionByStatus(missionStatus);
        simpleCallback.enqueueCall(call);
    }

    public void getMission(SimpleCallback<GetMissionBean> simpleCallback, int misstype) {
        Call<GetMissionBean> call = RetrofitManager.getInstance().getApiService().getMission(misstype);
        simpleCallback.enqueueCall(call);
    }

    public void getMission(SimpleCallback<GetMissionBean> simpleCallback) {
        Call<GetMissionBean> call = RetrofitManager.getInstance().getApiService().getMission();
        simpleCallback.enqueueCall(call);
    }

    //productId 字体id
    public void updateMission(SimpleCallback<CommonRetBean> simpleCallback) {
        Call<CommonRetBean> call = RetrofitManager.getInstance().getApiService().updateMission();
        simpleCallback.enqueueCall(call);
    }

    public void updateMission(SimpleCallback<CommonRetBean> simpleCallback, String productId, int pages) {
        Call<CommonRetBean> call = RetrofitManager.getInstance().getApiService().updateMission(BodyUpdateMission.newBuilder().product(productId).pages(pages).build());
        simpleCallback.enqueueCall(call);
    }
}
