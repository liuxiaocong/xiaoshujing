package com.xiaoshujing.kid.ui.videoList;

import android.content.Context;

import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.data.SeasonVideoManage;
import com.xiaoshujing.kid.model.video.GetSeasonVideoListBean;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class VideoListPresentImpl implements VideoListContract.Presenter {
    Context mContext;
    VideoListContract.View mView;
    VideoItemAdapter mVideoItemAdapter;
    String mSeasonId = "";

    public VideoListPresentImpl(Context context, String seasonId) {
        mContext = context;
        mSeasonId = seasonId;
    }


    @Override
    public void bindView(VideoListContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        mVideoItemAdapter = new VideoItemAdapter(mContext);
        if (mView != null) {
            mView.setAdapter(mVideoItemAdapter);
        }
        loadData();
    }

    SimpleCallback<GetSeasonVideoListBean> mGetSeasonBeanSimpleCallback = new SimpleCallback<GetSeasonVideoListBean>() {
        @Override
        public void onSuccess(GetSeasonVideoListBean response) {
            if (response.getEpisodes() != null && response.getEpisodes().size() > 0) {
                if (mVideoItemAdapter != null) {
                    mVideoItemAdapter.addData(response.getEpisodes(), true);
                }
            }
        }

        @Override
        public void onFailure(int responseCode) {

        }
    };

    private void loadData() {
        SeasonVideoManage.getInstance().getSeasonVideoList(mGetSeasonBeanSimpleCallback, mSeasonId);
    }
}
