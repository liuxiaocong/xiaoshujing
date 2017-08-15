package com.xiaoshujing.kid.ui.playVideoView;

import com.xiaoshujing.kid.common.BasePresenter;
import com.xiaoshujing.kid.common.BaseView;

/**
 * Created by Liuxiaocong on 8/11/16.
 */
public interface PlayVideoViewContract {
    interface View extends BaseView<Presenter> {
        void playVideo(String url, boolean needbuy);

        void prepareVideo(String url);

        void readyToPlay();

        void updateProgress();
    }

    interface Presenter extends BasePresenter<View> {
        void start();

        void clear();

        void play();

        void onVideoStarted(android.view.View view);
    }
}
