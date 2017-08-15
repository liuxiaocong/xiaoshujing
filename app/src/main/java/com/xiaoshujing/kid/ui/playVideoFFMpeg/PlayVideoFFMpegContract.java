package com.xiaoshujing.kid.ui.playVideoFFMpeg;

import com.xiaoshujing.kid.common.BasePresenter;
import com.xiaoshujing.kid.common.BaseView;

import mozat.rings.libffmpeg.FFMPEGPlayer;

/**
 * Created by Liuxiaocong on 8/11/16.
 */
public interface PlayVideoFFMpegContract {
    interface View extends BaseView<Presenter> {
        void playVideo(String url, boolean needbuy);

        void readyToPlay();

        FFMPEGPlayer getFFMPEGPlayer();

        void setPlayerListener(FFMPEGPlayer.FFMPEGPlayerListener listener);

        void setMediaControllerVisible(boolean b);
    }

    interface Presenter extends BasePresenter<View> {
        void start();

        void clear();

        void play();

        void onVideoStarted(android.view.View view);
    }
}
