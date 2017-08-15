package com.xiaoshujing.kid.data;

import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.api.GsonImpl;
import com.xiaoshujing.kid.api.RetrofitManager;
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.common.SharedPreferencesFactory;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.model.GlobeSettingBean;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by LiuXiaocong on 12/5/2016.
 */

public class CommonDataManage {
    private volatile static CommonDataManage gInstance = null;
    private GlobeSettingBean mGlobeSettingBean;
    private String DefaultGlobeStr = "{\"instruction\": \"http://59.110.23.25/ads/detail/?id=0f2c77f9-1699-463d-a9b3-8dba358a10a8\", \"setDays\": [{\"value\": \"1\\u5929\", \"key\": \"1\"}, {\"value\": \"2\\u5929\", \"key\": \"2\"}, {\"value\": \"3\\u5929\", \"key\": \"3\"}, {\"value\": \"5\\u5929\", \"key\": \"5\"}, {\"value\": \"7\\u5929\", \"key\": \"7\"}, {\"value\": \"10\\u5929\", \"key\": \"10\"}], \"systemCredit\": 5, \"exercisePages\": [{\"value\": \"\\u9884\\u8ba1\\u5b8c\\u6210\\u65f6\\u95f415\\u5206\\u949f\", \"key\": \"1\"}, {\"value\": \"\\u9884\\u8ba1\\u5b8c\\u6210\\u65f6\\u95f430\\u5206\\u949f\", \"key\": \"2\"}, {\"value\": \"\\u9884\\u8ba1\\u5b8c\\u6210\\u65f6\\u95f445\\u5206\\u949f\", \"key\": \"3\"}, {\"value\": \"\\u9884\\u8ba1\\u5b8c\\u6210\\u65f6\\u95f460\\u5206\\u949f\", \"key\": \"4\"}, {\"value\": \"\\u9884\\u8ba1\\u5b8c\\u6210\\u65f6\\u95f475\\u5206\\u949f\", \"key\": \"5\"}], \"aboutUs\": \"http://59.110.23.25/ads/detail/?id=21385da0-455b-4bd3-a16b-4644c411ffb2\", \"_status\": 0, \"_reason\": \"\", \"parentsCredit\": 5, \"dailCredit\": 5}";

    private CommonDataManage() {

    }

    public static CommonDataManage getInstance() {
        if (gInstance == null) {
            synchronized (CommonDataManage.class) {
                if (gInstance == null) {
                    gInstance = new CommonDataManage();
                }
            }
        }
        return gInstance;
    }

    public GlobeSettingBean getGlobeSettingBean() {
        if (mGlobeSettingBean == null) {
            try {
                String globeStr = SharedPreferencesFactory.getKeyGlobeSettingBean(MyApplication.getInstance());
                if (!Util.isNullOrEmpty(globeStr)) {
                    DefaultGlobeStr = globeStr;
                }
                mGlobeSettingBean = GsonImpl.get().toObject(DefaultGlobeStr, GlobeSettingBean.class);
            } catch (Exception e) {

            }
            refreshGlobeSetting();
        }
        return mGlobeSettingBean;
    }

    SimpleCallback<ResponseBody> mRefreshGlobeSettingCallback = new SimpleCallback<ResponseBody>() {
        @Override
        public void onSuccess(ResponseBody response) {
            try {
                SharedPreferencesFactory.setKeyGlobeSettingBean(MyApplication.getInstance(), response.string().toString());
                mGlobeSettingBean = GsonImpl.get().toObject(response.string().toString(), GlobeSettingBean.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(int responseCode) {

        }
    };

    public void refreshGlobeSetting() {
        Call<ResponseBody> call = RetrofitManager.getInstance().getApiService().getGlobeSetting();
        mRefreshGlobeSettingCallback.enqueueCall(call);
    }

}
