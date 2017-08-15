package com.xiaoshujing.kid.ui.makeWish;

import android.app.Activity;
import android.content.Intent;

import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.data.SeasonVideoManage;
import com.xiaoshujing.kid.model.BabyWishBean;
import com.xiaoshujing.kid.model.video.GetSeasonVideoDetailBean;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class MakeWishPresentImpl implements MakeWishContract.Presenter {
    Activity mContext;
    MakeWishContract.View mView;

    public MakeWishPresentImpl(Activity context, GetSeasonVideoDetailBean getSeasonVideoDetailBean) {
        mContext = context;
    }


    @Override
    public void bindView(MakeWishContract.View view) {
        mView = view;
    }

    @Override
    public void makeWish(String name, String des, String baby, String product) {
        SeasonVideoManage.getInstance().makeWish(mBabyWishBeanSimpleCallback, name, des, baby, product);
    }

    SimpleCallback<BabyWishBean> mBabyWishBeanSimpleCallback = new SimpleCallback<BabyWishBean>() {
        @Override
        public void onSuccess(BabyWishBean response) {
            MyApplication.getInstance().showNote("许愿成功");
            Intent intent = new Intent("android.intent.action.MAKE_WISH");
            intent.putExtra("msg", "go main");
            mContext.sendBroadcast(intent);
            mContext.finish();
        }

        @Override
        public void onFailure(int responseCode) {
            MyApplication.getInstance().showNote("许愿失败，请稍后重试");
            Intent intent = new Intent("android.intent.action.MAKE_WISH");
            intent.putExtra("msg", "go main");
            mContext.sendBroadcast(intent);
            mContext.finish();
        }
    };
}
