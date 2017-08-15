package com.xiaoshujing.kid.data;

import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.api.GsonImpl;
import com.xiaoshujing.kid.api.RetrofitManager;
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.common.SharedPreferencesFactory;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.model.BabyInfoBean;
import com.xiaoshujing.kid.model.BindTokenBean;
import com.xiaoshujing.kid.model.BodyBindToken;
import com.xiaoshujing.kid.model.BodyGrade;
import com.xiaoshujing.kid.model.BodyMinusCredit;
import com.xiaoshujing.kid.model.GetInfoBean;
import com.xiaoshujing.kid.model.GetTokenBean;
import com.xiaoshujing.kid.model.GradeBean;
import com.xiaoshujing.kid.model.MinusCreditBean;
import com.xiaoshujing.kid.model.UploadAvatarBean;
import com.xiaoshujing.kid.model.UploadImgBean;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by LiuXiaocong on 12/5/2016.
 */

public class UserInfoManage {
    private volatile static UserInfoManage gInstance = null;
    private GetInfoBean.UserBean mUserBean = null;

    private UserInfoManage() {

    }

    public static UserInfoManage getInstance() {
        if (gInstance == null) {
            synchronized (UserInfoManage.class) {
                if (gInstance == null) {
                    gInstance = new UserInfoManage();
                }
            }
        }
        return gInstance;
    }

    public void setUserBean(GetInfoBean.UserBean userBean) {
        mUserBean = userBean;
    }

    public GetInfoBean.UserBean getUserBean() {
        if (mUserBean == null) {
            String userStr = SharedPreferencesFactory.getKeyUserBean(MyApplication.getInstance());
            if (!Util.isNullOrEmpty(userStr)) {
                try {
                    GetInfoBean getInfoBean = GsonImpl.get().toObject(userStr, GetInfoBean.class);
                    if (getInfoBean != null) {
                        UserInfoManage.getInstance().setUserBean(getInfoBean.getUser());
                    }
                } catch (Exception e) {

                }
            }
        }
        return mUserBean;
    }

    public void getUserInfoFromUUID(SimpleCallback<ResponseBody> simpleCallback, String uuid) {
        Call<ResponseBody> call = RetrofitManager.getInstance().getApiService().getUserInfoFromUUID(uuid);
        simpleCallback.enqueueCall(call);
    }

    public void uploadUserAvatar(SimpleCallback<UploadAvatarBean> simpleCallback, MultipartBody multipartBody) {
        Call<UploadAvatarBean> call = RetrofitManager.getInstance().getApiService().uploadAvatar(multipartBody);
        simpleCallback.enqueueCall(call);
    }

    public void uploadImages(SimpleCallback<UploadImgBean> simpleCallback, MultipartBody multipartBody) {
        Call<UploadImgBean> call = RetrofitManager.getInstance().getApiService().uploadImgs(multipartBody);
        simpleCallback.enqueueCall(call);
    }

    public void getGrade(SimpleCallback<GradeBean> simpleCallback, List<String> imageList) {
        Call<GradeBean> call = RetrofitManager.getInstance().getApiService().getGrade(
                BodyGrade.newBuilder().img_urls(imageList).build()
        );
        simpleCallback.enqueueCall(call);
    }

    public void getBabyInfo(SimpleCallback<BabyInfoBean> simpleCallback, String bid) {
        Call<BabyInfoBean> call = RetrofitManager.getInstance().getApiService().getBabyInfo(
                bid);
        simpleCallback.enqueueCall(call);
    }

    public void bindToken(SimpleCallback<BindTokenBean> simpleCallback, String token) {
        Call<BindTokenBean> call = RetrofitManager.getInstance().getApiService().bindToken(token, BodyBindToken.newBuilder().device_type(2).build());
        simpleCallback.enqueueCall(call);
    }

    public void getToken(SimpleCallback<GetTokenBean> simpleCallback, String token) {
        Call<GetTokenBean> call = RetrofitManager.getInstance().getApiService().validBindToken(token);
        simpleCallback.enqueueCall(call);
    }

    public void minusCredit(SimpleCallback<MinusCreditBean> simpleCallback, int credit) {
        Call<MinusCreditBean> call = RetrofitManager.getInstance().getApiService().minusCredit(
                new BodyMinusCredit.Builder().credit(credit).build()
        );
        simpleCallback.enqueueCall(call);
    }
}
