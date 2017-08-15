package com.xiaoshujing.kid.ui.videoList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.common.BaseActivity;
import com.xiaoshujing.kid.common.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class VideoListActivity extends BaseActivity implements VideoListContract.View {
    public static final String EXT_SEASON_ID = "EXT_SEASON_ID";
    public static final String EXT_SEASON_NAME = "EXT_SEASON_NAME";
    VideoListContract.Presenter mPresenter;
    @BindView(R.id.video_item_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.title)
    TextView mTitle;

    LinearLayoutManager mLinearLayoutManager;
    private String mSeasonId = "";
    private String mSeasonName = "视频列表";

    //String testUrl = "https://webapi-rings-aws.shabikplus.mozat.com/images/2016/10/28/aa/0/aa39debf46639a51247d72635$3d2a2c20161028.jpeg";

    public static void openActivity(Context context, String seasonId, String seasonName) {
        Intent intent = new Intent(context, VideoListActivity.class);
        intent.putExtra(EXT_SEASON_ID, seasonId);
        intent.putExtra(EXT_SEASON_NAME, seasonName);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent.hasExtra(EXT_SEASON_ID)) {
            mSeasonId = intent.getStringExtra(EXT_SEASON_ID);
        }
        if (intent.hasExtra(EXT_SEASON_NAME)) {
            mSeasonName = intent.getStringExtra(EXT_SEASON_NAME);
        }
        if (Util.isNullOrEmpty(mSeasonId)) {
            MyApplication.getInstance().showNote("Error happens , please try again later");
            finish();
        }

        setContentView(R.layout.activity_video_list);
        ButterKnife.bind(this);

        mTitle.setText(mSeasonName);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mPresenter = new VideoListPresentImpl(this, mSeasonId);
        setPresenter(mPresenter);
        mPresenter.bindView(this);
        mPresenter.start();
    }

    @OnClick({
            R.id.back
    })
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.back: {
                finish();
            }
            break;
        }
    }

    @Override
    public void setPresenter(VideoListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(adapter);
        }
    }
}
