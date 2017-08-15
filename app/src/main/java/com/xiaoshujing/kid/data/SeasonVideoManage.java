package com.xiaoshujing.kid.data;

import com.xiaoshujing.kid.api.RetrofitManager;
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.model.BabyWishBean;
import com.xiaoshujing.kid.model.BodyBabyWish;
import com.xiaoshujing.kid.model.GetInfoBean;
import com.xiaoshujing.kid.model.video.GetSeasonBean;
import com.xiaoshujing.kid.model.video.GetSeasonPaidVideoDetailBean;
import com.xiaoshujing.kid.model.video.GetSeasonVideoDetailBean;
import com.xiaoshujing.kid.model.video.GetSeasonVideoListBean;

import retrofit2.Call;

/**
 * Created by LiuXiaocong on 12/5/2016.
 */

public class SeasonVideoManage {
    private volatile static SeasonVideoManage gInstance = null;
    private GetInfoBean.UserBean mUserBean = null;

    public enum SeasonType {
        EStudy(1),
        EFun(2);
        private final int value;

        SeasonType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private SeasonVideoManage() {

    }

    public static SeasonVideoManage getInstance() {
        if (gInstance == null) {
            synchronized (SeasonVideoManage.class) {
                if (gInstance == null) {
                    gInstance = new SeasonVideoManage();
                }
            }
        }
        return gInstance;
    }

    public void getSeason(SimpleCallback<GetSeasonBean> simpleCallback, int seasonType) {
        Call<GetSeasonBean> call = RetrofitManager.getInstance().getApiService().getSeason(seasonType);
        simpleCallback.enqueueCall(call);
    }

    public void getSeasonVideoList(SimpleCallback<GetSeasonVideoListBean> simpleCallback, String seasonId) {
        Call<GetSeasonVideoListBean> call = RetrofitManager.getInstance().getApiService().getSeasonVideoList(seasonId);
        simpleCallback.enqueueCall(call);
    }

    public void getSeasonVideoDetail(SimpleCallback<GetSeasonVideoDetailBean> simpleCallback, String videoId) {
        Call<GetSeasonVideoDetailBean> call = RetrofitManager.getInstance().getApiService().getSeasonVideoDetail(videoId);
        simpleCallback.enqueueCall(call);
    }

    public void getSeasonPaidVideoDetail(SimpleCallback<GetSeasonPaidVideoDetailBean> simpleCallback, String videoId) {
        Call<GetSeasonPaidVideoDetailBean> call = RetrofitManager.getInstance().getApiService().getSeasonPaidVideoDetail(videoId);
        simpleCallback.enqueueCall(call);
    }

    public void makeWish(SimpleCallback<BabyWishBean> simpleCallback, String name, String des, String baby, String product) {
        if (name == null) name = "";
        if (des == null) des = "";
        if (baby == null) baby = "";
        if (product == null) product = "";
        Call<BabyWishBean> call = RetrofitManager.getInstance().getApiService().babyWish(
                new BodyBabyWish.Builder().name(name).description(des).baby(baby).product(product).wishType("2").build()
        );
        simpleCallback.enqueueCall(call);
    }
}
