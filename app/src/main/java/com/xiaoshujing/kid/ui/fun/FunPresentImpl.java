package com.xiaoshujing.kid.ui.fun;

import android.app.Activity;

import com.xiaoshujing.kid.data.SeasonVideoManage;
import com.xiaoshujing.kid.ui.seasonList.SeasonListActivity;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class FunPresentImpl implements FunContract.Presenter {
    Activity mContext;

    public FunPresentImpl(Activity context) {
        mContext = context;
    }

    @Override
    public void bindView(FunContract.View view) {

    }

    @Override
    public void watchVideo() {
        SeasonListActivity.openActivity(mContext, SeasonVideoManage.SeasonType.EFun.getValue());
    }

    @Override
    public void playGame() {

    }
}
